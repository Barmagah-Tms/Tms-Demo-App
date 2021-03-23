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
        const val TAG_LIST_CUSTOMERS_ = "TAG_LIST_CUSTOMER"

        /*
        *
        * SHARED PRE _
        * */
        const val SH_USER_EMAIL = "user_email"
        const val SH_USER_PASS = "user_pass"
        const val SH_USER_TOKEN = "user_token"
        const val SH_USER_COMPANY_ID = "company_id"
        const val SH_IS_LOGGED_IN = "is_logged_in"

        const val SH_SWIPE_ICON = "is_swipe_icon_displayed"

    }
}
// val dao = TmsDatabase.invoke(context!!).currentUserDao()
/*
    #1 Simple Interface Response Call
    val apiNetworkService = ApiNetworkService(ConnectivityInterceptorImpl(requireContext()))
    val loginRequest = LoginRequest("mohamed.nagy.dev@gmail.com", "1234")
    GlobalScope.launch(Dispatchers.Main) {
        val response = apiNetworkService.getPostValueAsync(loginRequest)!!.await()
        text1.text = response.toString()
    }
*/
/*
    #2 Observer DataSourceImp Response Call
    val apiNetworkService = ApiNetworkService(ConnectivityInterceptorImpl(this.context!!))
    val loginRequest = LoginRequest("mohamed.nagy.dev@gmail.com", "1234")

    val loginDataSource = LoginDataSourceImpl(apiNetworkService)
    loginDataSource.downLoadedLoginLiveDataResponse.observe(viewLifecycleOwner, Observer {
        text1.text = it.toString()
    })

    GlobalScope.launch(Dispatchers.Main) {
        loginDataSource.fetchLoginLiveDataResponse(loginRequest)
    }
*/

/*
    #3 binding ViewModel &factory with Repository -> Manually Instance
    val apiNetworkService = ApiNetworkService(ConnectivityInterceptorImpl(this.context!!))
    val loginDataSource = LoginDataSourceImpl(apiNetworkService)

    val dao = LoginDatabase.invoke(context!!).loginDao()
    val loginRepository = LoginRepositoryImpl(dao, loginDataSource)
    val factory = LoginViewModelFactory(loginRepository)
     viewModel = ViewModelProvider(this, factory)
       .get(LoginViewModel::class.java)



    private fun bindUI() = launch {
        val _deferred = viewModel.deferredLoginRepository.await()//sync
        _deferred.observe(viewLifecycleOwner, Observer {
            if (it == null) {
                Log.d(TAG, "response nulls: ")
                return@Observer
            } else
                text1.text = it.toString()
        })
    }
*/


/*
    Auto Sign In Response Binding
    private fun bindUI() = launch {
        val _deferred = viewModel.deferredLoginRepository.await()//sync
        _deferred.observe(viewLifecycleOwner, Observer {
            if (it == null) {
                Log.d(TAG, "response nulls: ")
                return@Observer
            } else
                _getResponse(it)
        })
    }*/
