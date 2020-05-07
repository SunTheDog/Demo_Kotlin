package com.ugsg.sip.demo_kotlin.ui.weather

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.ugsg.sip.demo_kotlin.logic.Repository
import com.ugsg.sip.demo_kotlin.logic.model.Location

class WeatherViewModel :ViewModel(){

    var locationLng = ""

    var locationLat = ""

    var placeName = ""

    private val locationLiveData = MutableLiveData<Location>()

    val weatherViewModel = Transformations.switchMap(locationLiveData){
        Repository.refreshWeather(it.lng, it.lat)

    }

    fun refreshWeather(lng: String, lat: String) {
        val location = Location(lng, lat)
        locationLiveData.value = location
    }
}