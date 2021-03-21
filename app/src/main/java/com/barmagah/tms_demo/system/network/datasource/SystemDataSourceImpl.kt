package com.barmagah.tms_demo.system.network.datasource

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.barmagah.tms_demo.system.data.login.LoginRequest
import com.barmagah.tms_demo.system.data.login.LoginResponseEntry
import com.barmagah.tms_demo.system.internal.NoConnectivityException
import com.barmagah.tms_demo.system.network.response.ApiSystemService

class SystemDataSourceImpl(
    private val apiSystemService: ApiSystemService
) : SystemDataSource {

    private val _downloadedResponse = MutableLiveData<LoginResponseEntry>()

    /*
    *
    * override
    * */
    override val downloadedLoginResponse: LiveData<LoginResponseEntry>
        get() = _downloadedResponse

    override suspend fun fetchLoginResponse(loginRequest: LoginRequest) {
        try {
            val fetchedResponse = apiSystemService
                .getPostValueAsync(loginRequest)
                ?.await()
            _downloadedResponse.postValue(fetchedResponse)


        } catch (e: NoConnectivityException) {
            Log.e("Connection", "No internet connection.", e)
        }
    }
}