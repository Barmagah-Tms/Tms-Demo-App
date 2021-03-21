package com.barmagah.tms_demo.home.network.response

import com.barmagah.tms_demo.home.data.list_user.ListUserRequest
import com.barmagah.tms_demo.home.data.list_user.ListUsersResponse
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

    @POST("User/ListUser")
    fun getListUsersAsync(
        @Body listUserRequest: ListUserRequest
    ): Deferred<ListUsersResponse?>?


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

