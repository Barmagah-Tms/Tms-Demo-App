package com.barmagah.tms_demo.home.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.barmagah.tms_demo.home.data.list_user.CommonUserRecords
import com.barmagah.tms_demo.home.data.list_user.ListUserRequest
import com.barmagah.tms_demo.home.data.list_user.ListUsersResponse
import com.barmagah.tms_demo.home.network.datasource.CompanyDatasource
import com.barmagah.tms_demo.utils.Constant.Companion.TAG_LIST_USERS_

class CompanyRepositoryImpl(
    private val companyDatasource: CompanyDatasource
) : CompanyRepository {


    init {
        companyDatasource.apply {
            downloadListUsersResponse.observeForever { listUsersResponse ->
                persistFetchedFutureWeather(listUsersResponse)
            }
        }
    }

    private fun persistFetchedFutureWeather(listUsersResponse: ListUsersResponse?) {
        Log.d(TAG_LIST_USERS_, "persistFetchedFutureWeather: $listUsersResponse")

    }


    private suspend fun fetchListUser(company_id: Int) {
        companyDatasource.fetchDownloadListUsers(
            ListUserRequest(CommonUserRecords(company_id))
        )
    }

    /*
    *
    * override
    * */
    override suspend fun getListUser(company_id: Int): LiveData<out ListUsersResponse> {
        fetchListUser(company_id)
        return companyDatasource.downloadListUsersResponse
    }
}