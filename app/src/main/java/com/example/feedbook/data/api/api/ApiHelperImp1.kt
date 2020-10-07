package com.example.feedbook.data.api.api

import com.example.myfeed.data.Feed
import retrofit2.Call
import retrofit2.Response

class ApiHelperImp1(private val apiService: ApiService) : ApiHelper {
    override suspend fun getFeed1(): Response<Feed> = apiService.getSinglePost1()
    override suspend fun getFeed2(): Response<Feed> = apiService.getSinglePost2()
    override suspend fun getFeed3(): Response<Feed> = apiService.getSinglePost3()


}