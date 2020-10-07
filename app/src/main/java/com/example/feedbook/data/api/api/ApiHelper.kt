package com.example.feedbook.data.api.api

import com.example.myfeed.data.Feed
import retrofit2.Call
import retrofit2.Response

interface ApiHelper{
    suspend fun getFeed1() : Response<Feed>
    suspend fun getFeed2() : Response<Feed>
    suspend fun  getFeed3(): Response<Feed>
}