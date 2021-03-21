package com.barmagah.tms_demo.system.repository

import androidx.lifecycle.LiveData
import com.barmagah.tms_demo.system.data.login.LoginResponseEntry

interface SystemRepository {
    suspend fun getLoginResponse(email: String, password: String): LiveData<out LoginResponseEntry>

}