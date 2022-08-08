package com.example.uzblog.Retrofit

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object Networking {
    private val interceptor=HttpLoggingInterceptor().apply {
        setLevel(HttpLoggingInterceptor.Level.BODY)
    }
    private val client=OkHttpClient.Builder().addInterceptor(interceptor).build()
    private val retrofit=Retrofit.Builder()
        .baseUrl("https://dummyapi.io/data/v1/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()
    val api= retrofit.create(Api::class.java)
}