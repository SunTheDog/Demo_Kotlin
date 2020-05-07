package com.ugsg.sip.demo_kotlin.logic

import Weather
import androidx.lifecycle.liveData
import com.ugsg.sip.demo_kotlin.logic.dao.PlaceDao
import com.ugsg.sip.demo_kotlin.logic.model.Place
import com.ugsg.sip.demo_kotlin.logic.network.MyNetwork
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

object Repository {

    fun savePlace(place: Place) = PlaceDao.savePlace(place)

    fun getSavePlace() = PlaceDao.getPlace()

    fun isPlaceSaved() = PlaceDao.isPlaceSaved()

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

    fun refreshWeather(lng: String, lat: String) = liveData(Dispatchers.IO) {
        val result = try {
            coroutineScope {
                val deferredRealtime = async { MyNetwork.getRealtimeWeather(lng, lat) }
                val deferredDaily = async { MyNetwork.getDailyWeather(lng, lat) }
                val realtimeResponse = deferredRealtime.await()
                val dailyResponse = deferredDaily.await()
                if (dailyResponse.status == "ok" && realtimeResponse.status == "ok") {
                    val weather =
                        Weather(realtimeResponse.result.realtime, dailyResponse.result.daily)
                    Result.success(weather)

                } else {
                    Result.failure(
                        RuntimeException(
                            "realtime status is ${realtimeResponse.status}" +
                                    "daily status is ${dailyResponse.status}"

                        )
                    )
                }



            }
        } catch (e: Exception) {
            Result.failure<Weather>(e)
        }

        emit(result)
    }
}