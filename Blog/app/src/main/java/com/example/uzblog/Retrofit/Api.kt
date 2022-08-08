package com.example.uzblog.Retrofit

import com.example.uzblog.model.PostModel
import com.example.uzblog.model.UserModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Path

interface Api {
    @Headers("app-id: 62ecd61539539b19730c0bb6")
    @GET("user")
    fun getUser(): Call<BaseResponse<List<UserModel>>>

    @Headers("app-id: 62ecd61539539b19730c0bb6")
    @GET("post")
    fun getPost(): Call<BaseResponse<List<PostModel>>>

    @Headers("app-id: 62ecd61539539b19730c0bb6")
    @GET("user/{userId}/post")
    fun getUserPost(@Path("userId") id:String): Call<BaseResponse<List<PostModel>>>

    @Headers("app-id: 62ecd61539539b19730c0bb6")
    @GET("post/{id}")
    fun getPostId(@Path("id") id: String): Call<PostModel>
}