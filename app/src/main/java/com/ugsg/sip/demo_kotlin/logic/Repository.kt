package com.ugsg.sip.demo_kotlin.logic

import androidx.lifecycle.liveData
import com.ugsg.sip.demo_kotlin.logic.model.Place
import com.ugsg.sip.demo_kotlin.logic.network.MyNetwork
import kotlinx.coroutines.Dispatchers

object Repository {
    fun searchPlaces(query: String) = liveData(Dispatchers.IO) {
        val result = try {
            val placeResponse = MyNetwork.searchPlaces(query)
            if (placeResponse.status == "ok") {
                val places = placeResponse.places
                Result.success(places)
            } else {
                Result.failure(RuntimeException("response status is ${placeResponse.status}"))

            }
        } catch (e: Exception) {
            Result.failure<List<Place>>(e)
        }
        emit(result)
    }
}