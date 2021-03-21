package com.barmagah.tms_demo.home.network.datasource

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.barmagah.tms_demo.home.data.list_user.ListUserRequest
import com.barmagah.tms_demo.home.data.list_user.ListUsersResponse
import com.barmagah.tms_demo.home.network.response.ApiNetworkService
import com.barmagah.tms_demo.system.internal.NoConnectivityException

class CompanyDatasourceImpl(
    private val apiNetworkService: ApiNetworkService
) : CompanyDatasource {


    private val _downloadListUsersResponse = MutableLiveData<ListUsersResponse>()

    /*
    * override
    * */
    override val downloadListUsersResponse: LiveData<ListUsersResponse>
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
}