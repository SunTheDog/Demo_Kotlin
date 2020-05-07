package com.ugsg.sip.demo_kotlin.logic.network

import com.ugsg.sip.demo_kotlin.MyApplication
import com.ugsg.sip.demo_kotlin.logic.model.PlaceResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PlaceService {

    @GET("v2/place?token=${MyApplication.TOKEN}&lang=zh_CN")
    fun searchPlaces(@Query("query") query: String): Call<PlaceResponse>

}