package com.barmagah.tms_demo.home.network.response

import com.barmagah.tms_demo.home.data.user.add_user.AddUserRequest
import com.barmagah.tms_demo.home.data.customer.MainCustomerRequest
import com.barmagah.tms_demo.home.data.customer.MainCustomersResponse
import com.barmagah.tms_demo.home.data.deleteResponse.DeleteUserRequest
import com.barmagah.tms_demo.home.data.user.list_user.ListUserRequest
import com.barmagah.tms_demo.home.data.user.list_user.MainUsersResponse
import com.barmagah.tms_demo.system.network.connectivity.ConnectivityInterceptor
import com.barmagah.tms_demo.system.provider.UserPreferenceProvider
import com.barmagah.tms_demo.utils.Constant.Companion.BASE_URL
import com.barmagah.tms_demo.utils.Constant.Companion.SH_USER_TOKEN
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST


interface ApiNetworkService {

    /*
    * USERS
    * */
    @POST("User/ListUser")
    fun getListUsersAsync(
        @Body listUserRequest: ListUserRequest
    ): Deferred<MainUsersResponse?>?

    @POST("User/DeleteUser")
    fun deleteUserAsync(
        @Body deleteUserRequest: DeleteUserRequest
    ): Deferred<MainUsersResponse?>?

    @POST("User/AddUser")
    fun addUserAsync(
        @Body addUserRequest: AddUserRequest
    ): Deferred<MainUsersResponse?>?

    /*
    * Customers
    * */

    @POST("Customer/ListCustomer")
    fun getListCustomerAsync(
        @Body mainCustomerRequest: MainCustomerRequest
    ): Deferred<MainCustomersResponse?>?


    companion object {
        operator fun invoke(
            connectivityInterceptor: ConnectivityInterceptor,
            userPreferenceProvider: UserPreferenceProvider
        ): ApiNetworkService {
            val requestInterceptor = Interceptor { chain ->
                val url = chain.request()
                    .url
                    .newBuilder()
                    .build()

                val request = chain.request()
                    .newBuilder()
                    .url(url)
                    .addHeader(
                        "Authorization",
                        "bearer ${userPreferenceProvider.getUserStringData(SH_USER_TOKEN)}"
                    )
                    .build()
                return@Interceptor chain.proceed(request)
            }

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(requestInterceptor) //intercept every call
                .addInterceptor(connectivityInterceptor) //custom interceptor check Internet
                .build()


            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())// Deferred!
                .addConverterFactory(GsonConverterFactory.create()) // Gson!
                .build()
                .create(ApiNetworkService::class.java)
        }
    }

}

