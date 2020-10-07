package com.example.feedbook.data.api.api

import com.example.myfeed.data.Feed
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("v2/59b3f0b0100000e30b236b7e")
    suspend fun getSinglePost1(): Response<Feed>

    @GET("v2/59ac28a9100000ce0bf9c236")
    suspend fun getSinglePost2(): Response<Feed>

    @GET("v2/59ac293b100000d60bf9c239")
   suspend fun getSinglePost3(): Response<Feed>
}