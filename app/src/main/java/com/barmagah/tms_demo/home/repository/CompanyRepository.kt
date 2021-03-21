package com.barmagah.tms_demo.home.repository

import androidx.lifecycle.LiveData
import com.barmagah.tms_demo.home.data.list_user.ListUsersResponse

interface CompanyRepository {
    suspend fun getListUser(company_id: Int): LiveData<out ListUsersResponse>

}