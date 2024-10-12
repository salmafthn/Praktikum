package com.example.modul2.data.retrofit

import com.example.modul2.data.entity.Github
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("users/{username}")
    suspend fun getDetailUser(@Path("username") username: String): Github

}