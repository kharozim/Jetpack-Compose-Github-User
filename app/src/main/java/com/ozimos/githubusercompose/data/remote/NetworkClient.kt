package com.ozimos.githubusercompose.data.remote

import com.google.gson.GsonBuilder
import com.ozimos.githubusercompose.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object NetworkClient {

    private const val BASE_URL = "https://api.github.com"
    private const val TOKEN = "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"

    private fun getClient(): OkHttpClient {
        val okHttpClient = OkHttpClient.Builder()
        okHttpClient.addInterceptor { chain ->
            val origin = chain.request()
            val request = origin.newBuilder()
                .addHeader("Accept", "application/json")
                .addHeader("Authorization", "token $TOKEN")
                .method(origin.method, origin.body)
                .build()
            chain.proceed(request)
        }

        okHttpClient.callTimeout(5, TimeUnit.SECONDS)
        okHttpClient.connectTimeout(5, TimeUnit.SECONDS)

        if (BuildConfig.DEBUG) {
            val interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
            okHttpClient.addInterceptor(interceptor)
        }
        return okHttpClient.build()
    }

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
        .client(getClient())
        .build()

    val userService: UserService = retrofit.create(UserService::class.java)

}