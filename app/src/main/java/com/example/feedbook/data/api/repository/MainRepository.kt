package com.example.feedbook.data.api.repository

import com.example.feedbook.data.api.api.ApiHelper

class MainRepository (private val apiHelper: ApiHelper){
    suspend fun getFeed1()=apiHelper.getFeed1()
    suspend fun getFeed2() =apiHelper.getFeed2()
    suspend fun getFeed3() = apiHelper.getFeed3()
}