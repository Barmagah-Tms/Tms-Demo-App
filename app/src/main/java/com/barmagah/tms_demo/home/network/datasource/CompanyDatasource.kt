package com.barmagah.tms_demo.home.network.datasource

import androidx.lifecycle.LiveData
import com.barmagah.tms_demo.home.data.user.add_user.AddUserRequest
import com.barmagah.tms_demo.home.data.customer.MainCustomerRequest
import com.barmagah.tms_demo.home.data.customer.MainCustomersResponse
import com.barmagah.tms_demo.home.data.deleteResponse.DeleteUserRequest
import com.barmagah.tms_demo.home.data.user.list_user.ListUserRequest
import com.barmagah.tms_demo.home.data.user.list_user.MainUsersResponse

interface CompanyDatasource {

    /*
    *
    * USERS
    * */
    val downloadMainUsersResponse: LiveData<MainUsersResponse>

    val deleteUserResponse: LiveData<MainUsersResponse>

    val addUserResponse: LiveData<MainUsersResponse>

    suspend fun fetchDownloadListUsers(
        listUserRequest: ListUserRequest
    )

    suspend fun fetchDeleteUser(
        deleteUserRequest: DeleteUserRequest
    )

    suspend fun fetchAddUser(
        addUserRequest: AddUserRequest
    )

    /*
   *
   * Customer
   * */
    val downloadMainCustomerResponse: LiveData<MainCustomersResponse>
    suspend fun fetchDownloadedCustomers(
        mainCustomerRequest: MainCustomerRequest
    )

}