package com.barmagah.tms_demo.home.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.barmagah.tms_demo.home.data.customer.CustomerRecord
import com.barmagah.tms_demo.home.data.customer.MainCustomerRequest
import com.barmagah.tms_demo.home.data.customer.MainCustomersResponse
import com.barmagah.tms_demo.home.data.user.list_user.CommonUserRecords
import com.barmagah.tms_demo.home.data.user.list_user.ListUserRequest
import com.barmagah.tms_demo.home.data.user.list_user.MainUsersResponse
import com.barmagah.tms_demo.home.network.datasource.CompanyDatasource
import com.barmagah.tms_demo.utils.Constant.Companion.TAG_LIST_CUSTOMERS_
import com.barmagah.tms_demo.utils.Constant.Companion.TAG_LIST_USERS_

class CompanyRepositoryImpl(
    private val companyDatasource: CompanyDatasource
) : CompanyRepository {


    init {
        companyDatasource.apply {
            downloadMainUsersResponse.observeForever { listUsersResponse ->
                Log.d(TAG_LIST_USERS_, "persistFetchedData: $listUsersResponse")

            }
            downloadMainCustomerResponse.observeForever { response ->
                Log.d(TAG_LIST_CUSTOMERS_, "persistFetchedData: $response")
            }
        }
    }


    /*
    *
    * override get Users List
    *
    *
    * */
    private suspend fun fetchListUser(company_id: Int) {
        companyDatasource.fetchDownloadListUsers(
            ListUserRequest(CommonUserRecords(company_id))
        )
    }

    override suspend fun getListUser(company_id: Int): LiveData<out MainUsersResponse> {
        fetchListUser(company_id)
        return companyDatasource.downloadMainUsersResponse
    }

    //  get List Customer
    private suspend fun fetchListCustomer() {
        companyDatasource.fetchDownloadedCustomers(
            MainCustomerRequest(CustomerRecord(0))
        )
    }

    override suspend fun getListCustomer(): LiveData<out MainCustomersResponse> {
        fetchListCustomer()
        return companyDatasource.downloadMainCustomerResponse
    }


}