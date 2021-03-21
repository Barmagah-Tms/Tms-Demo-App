package com.barmagah.tms_demo.system.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.barmagah.tms_demo.system.data.CurrentUser
import com.barmagah.tms_demo.system.data.login.LoginRequest
import com.barmagah.tms_demo.system.data.login.LoginResponseEntry
import com.barmagah.tms_demo.system.data.login.db.CurrentUserDao
import com.barmagah.tms_demo.system.network.datasource.SystemDataSource
import com.barmagah.tms_demo.system.provider.UserPreferenceProvider
import com.barmagah.tms_demo.utils.Constant
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

private const val TAG = Constant.TAG_LOGIN_

class SystemRepositoryImpl(
    private val currentUserDao: CurrentUserDao,
    private val systemDataSource: SystemDataSource,
    private val userPreferenceProvider: UserPreferenceProvider
) : SystemRepository {

    private var _currentPass: String = "-"


    init {
        systemDataSource.apply {
            downloadedLoginResponse.observeForever { newLoginResponse ->
                persistFetchedResponse(newLoginResponse)
            }
        }
    }

    /*
    * action
    * */
    private fun persistFetchedResponse(newLoginResponse: LoginResponseEntry?) {
        fun deleteOldData() {
            currentUserDao.deleteUser()
        }


        if (newLoginResponse?.token != null) {
            Log.d(TAG, "persistFetchedResponse: response $newLoginResponse")
            successfullyLogin(newLoginResponse)
            GlobalScope.launch(Dispatchers.IO) {
                newLoginResponse.let { currentUserDao.upsertUser(it) }
            }


        } else {
            Log.d(TAG, "persistFetchedResponse: null response $newLoginResponse")
            userPreferenceProvider.deleteUser()
            GlobalScope.launch(Dispatchers.IO) {
                deleteOldData()
            }
        }

    }


    private fun successfullyLogin(newLoginResponse: LoginResponseEntry) {
        Log.d(TAG, "successfullyLogin: add user")
        userPreferenceProvider.addUser(
            CurrentUser(
                newLoginResponse.userEmail,
                _currentPass,
                newLoginResponse.token,
                newLoginResponse.companyId,
                true
            )
        )
    }


    private suspend fun fetchUserData(email: String, password: String) {

        Log.d(TAG, "fetchUserData: $email & $password -> datasource")
        systemDataSource.fetchLoginResponse(LoginRequest(email, password))
    }

    /*
    *
    *
    * override*/
    override suspend fun getLoginResponse(
        email: String,
        password: String
    ): LiveData<out LoginResponseEntry> {
        /*
        *
        * */
        fetchUserData(email, password)
        _currentPass = password
        return withContext(Dispatchers.IO) {
            return@withContext currentUserDao.getCurrentUser()
        }
    }
}


