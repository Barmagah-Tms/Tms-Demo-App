package com.barmagah.tms_demo.system.network.response

import com.barmagah.tms_demo.system.data.login.LoginRequest
import com.barmagah.tms_demo.system.data.login.LoginResponseEntry
import com.barmagah.tms_demo.system.network.connectivity.ConnectivityInterceptor
import com.barmagah.tms_demo.utils.Constant.Companion.BASE_URL
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST


interface ApiSystemService {

    @POST("User/Login")
    fun getPostValueAsync(
        @Body loginRequest: LoginRequest
    ): Deferred<LoginResponseEntry?>?


    companion object {
        operator fun invoke(
            connectivityInterceptor: ConnectivityInterceptor
        ): ApiSystemService {
            val requestInterceptor = Interceptor { chain ->
                val url = chain.request()
                    .url
                    .newBuilder()//.addQueryParameter("apiKey", "111")
                    .build()

                val request = chain.request()
                    .newBuilder()
                    .url(url)//http://95.216.142.45/tms/api/User/Login
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
                .addCallAdapterFactory(CoroutineCallAdapterFactory())// Deferred!
                .addConverterFactory(GsonConverterFactory.create()) // Gson!
                .build()
                .create(ApiSystemService::class.java)
        }
    }

}


