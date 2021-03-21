package com.barmagah.tms_demo.utils

class Constant {
    companion object {
        const val BASE_URL = "http://95.216.142.45/tms/api/"


        /*
        *
        * TAG!!
        *
        * */

        const val TAG_LOGIN_ = "TAG_LOGIN_DE"
        const val TAG_LIST_USERS_ = "TAG_LIST_USERS_"

        /*
        *
        * SHARED PRE _
        * */
        const val SH_USER_EMAIL = "user_email"
        const val SH_USER_PASS = "user_pass"
        const val SH_USER_TOKEN = "user_token"
        const val SH_USER_COMPANY_ID = "company_id"
        const val SH_IS_LOGGED_IN = "is_logged_in"


    }
}
// val dao = TmsDatabase.invoke(context!!).currentUserDao()
