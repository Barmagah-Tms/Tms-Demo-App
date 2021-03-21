package com.barmagah.tms_demo.system.provider

import android.content.Context
import com.barmagah.tms_demo.system.data.CurrentUser
import com.barmagah.tms_demo.system.provider.preference.PreferenceProvider
import com.barmagah.tms_demo.utils.Constant.Companion.SH_IS_LOGGED_IN
import com.barmagah.tms_demo.utils.Constant.Companion.SH_USER_COMPANY_ID
import com.barmagah.tms_demo.utils.Constant.Companion.SH_USER_EMAIL
import com.barmagah.tms_demo.utils.Constant.Companion.SH_USER_PASS
import com.barmagah.tms_demo.utils.Constant.Companion.SH_USER_TOKEN

class UserPreferenceProviderImpl(
    context: Context
) : PreferenceProvider(context), UserPreferenceProvider {


    override fun getUserStringData(shared_: String): String {
        return preferences.getString(shared_, "default..")!!
    }

    override fun getUserIntData(shared_: String): Int {
        return preferences.getInt(shared_, 0)
    }


    override fun addUser(currentUser: CurrentUser) {
        preferencesEditor.putString(SH_USER_EMAIL, currentUser.email)
        preferencesEditor.putString(SH_USER_PASS, currentUser.pass)
        preferencesEditor.putString(SH_USER_TOKEN, currentUser.token)
        preferencesEditor.putString(SH_USER_COMPANY_ID, currentUser.token)

        preferencesEditor.putBoolean(SH_IS_LOGGED_IN, true)
        preferencesEditor.apply()
    }

    override fun deleteUser() {
        preferencesEditor.clear().commit()//settings.edit().remove("value").commit();
    }

    /*
    *
    *
    * override*/
    override fun isLoggedIn(): Boolean {
        return preferences.getBoolean(SH_IS_LOGGED_IN, false)
    }
}