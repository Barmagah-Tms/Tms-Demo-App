package com.barmagah.tms_demo.home.network.datasource

import androidx.lifecycle.LiveData
import com.barmagah.tms_demo.home.data.list_user.ListUserRequest
import com.barmagah.tms_demo.home.data.list_user.ListUsersResponse

interface CompanyDatasource {

    val downloadListUsersResponse: LiveData<ListUsersResponse>

    suspend fun fetchDownloadListUsers(
        listUserRequest: ListUserRequest
    )
}