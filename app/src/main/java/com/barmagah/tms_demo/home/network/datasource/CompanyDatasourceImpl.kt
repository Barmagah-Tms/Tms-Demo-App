package com.barmagah.tms_demo.home.network.datasource

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.barmagah.tms_demo.home.data.user.add_user.AddUserRequest
import com.barmagah.tms_demo.home.data.customer.MainCustomerRequest
import com.barmagah.tms_demo.home.data.customer.MainCustomersResponse
import com.barmagah.tms_demo.home.data.deleteResponse.DeleteUserRequest
import com.barmagah.tms_demo.home.data.user.list_user.ListUserRequest
import com.barmagah.tms_demo.home.data.user.list_user.MainUsersResponse
import com.barmagah.tms_demo.home.network.response.ApiNetworkService
import com.barmagah.tms_demo.system.internal.NoConnectivityException

class CompanyDatasourceImpl(
    private val apiNetworkService: ApiNetworkService
) : CompanyDatasource {


    //User
    private val _downloadListUsersResponse = MutableLiveData<MainUsersResponse>()
    private val _getDeleteUserResponse = MutableLiveData<MainUsersResponse>()
    private val _addUserResponse = MutableLiveData<MainUsersResponse>()

    //Customer
    private val _downloadMainCustomerResponse = MutableLiveData<MainCustomersResponse>()


    /*
    *  Users override fun
    *  Read
    *
    * */
    override val downloadMainUsersResponse: LiveData<MainUsersResponse>
        get() = _downloadListUsersResponse

    override suspend fun fetchDownloadListUsers(listUserRequest: ListUserRequest) {
        try {
            val fetchedResponse = apiNetworkService
                .getListUsersAsync(listUserRequest)
                ?.await()


            _downloadListUsersResponse.postValue(fetchedResponse)


        } catch (e: NoConnectivityException) {
            Log.e("Connection", "No internet connection.", e)
        }
    }

    // Delete User
    override val deleteUserResponse: LiveData<MainUsersResponse>
        get() = _getDeleteUserResponse

    override suspend fun fetchDeleteUser(deleteUserRequest: DeleteUserRequest) {
        try {
            val fetchedResponse = apiNetworkService
                .deleteUserAsync(deleteUserRequest)
                ?.await()


            _getDeleteUserResponse.postValue(fetchedResponse)


        } catch (e: NoConnectivityException) {
            Log.e("Connection", "No internet connection.", e)
        }
    }

    // Add
    override val addUserResponse: LiveData<MainUsersResponse>
        get() = _addUserResponse

    override suspend fun fetchAddUser(addUserRequest: AddUserRequest) {
        try {
            val fetchedResponse = apiNetworkService
                .addUserAsync(addUserRequest)
                ?.await()


            _addUserResponse.postValue(fetchedResponse)


        } catch (e: NoConnectivityException) {
            Log.e("Connection", "No internet connection.", e)
        }
    }


    /*
    *
    * Customer fun
    * Read
    * */
    override val downloadMainCustomerResponse: LiveData<MainCustomersResponse>
        get() = _downloadMainCustomerResponse

    override suspend fun fetchDownloadedCustomers(mainCustomerRequest: MainCustomerRequest) {
        try {
            val fetchedResponse = apiNetworkService
                .getListCustomerAsync(mainCustomerRequest)
                ?.await()


            _downloadMainCustomerResponse.postValue(fetchedResponse)


        } catch (e: NoConnectivityException) {
            Log.e("Connection", "No internet connection.", e)
        }
    }
}