package com.barmagah.tms_demo.system.provider

import com.barmagah.tms_demo.system.data.CurrentUser


interface UserPreferenceProvider {
    fun addUser(currentUser: CurrentUser)
    fun deleteUser()
    fun isLoggedIn(): Boolean
    fun getUserStringData(shared_: String): String
    fun getUserIntData(shared_: String): Int

}