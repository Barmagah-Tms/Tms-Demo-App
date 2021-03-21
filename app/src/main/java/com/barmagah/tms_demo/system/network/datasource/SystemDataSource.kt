package com.barmagah.tms_demo.system.network.datasource

import androidx.lifecycle.LiveData
import com.barmagah.tms_demo.system.data.login.LoginRequest
import com.barmagah.tms_demo.system.data.login.LoginResponseEntry

interface SystemDataSource {

    val downloadedLoginResponse: LiveData<LoginResponseEntry>

    suspend fun fetchLoginResponse(
        loginRequest: LoginRequest
    )
}