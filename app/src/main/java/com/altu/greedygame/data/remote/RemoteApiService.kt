package com.altu.greedygame.data.remote

import com.altu.greedygame.data.model.NewsResponse
import com.altu.greedygame.utils.CommonUtils
import retrofit2.http.GET
import retrofit2.http.Query

interface RemoteApiService {
    @GET("v2/top-headlines?country=in")
    suspend fun fetchNewsData(@Query("apiKey") key :String = CommonUtils.APIKEY):NewsResponse
    companion object {
        const val API_URL = "https://newsapi.org/"
    }

}