package com.barmagah.tms_demo.home.repository

import androidx.lifecycle.LiveData
import com.barmagah.tms_demo.home.data.customer.MainCustomersResponse
import com.barmagah.tms_demo.home.data.user.list_user.MainUsersResponse

interface CompanyRepository {
    // get List Users
    suspend fun getListUser(company_id: Int): LiveData<out MainUsersResponse>

    // get Customer
    suspend fun getListCustomer(): LiveData<out MainCustomersResponse>


}