package com.example.modul2.data.entity

import com.example.modul2.data.retrofit.ApiConfig
import retrofit2.HttpException


class ProfileRepository {
    suspend fun getProfile(username: String): Github {
        return try{
            val response = ApiConfig.getApiService().getDetailUser(username)
            response
        } catch(e: HttpException){
            throw Exception(e.response()?.errorBody()?.string())
        } catch (e: Exception){
            throw Exception(e.message)
        }
    }
}