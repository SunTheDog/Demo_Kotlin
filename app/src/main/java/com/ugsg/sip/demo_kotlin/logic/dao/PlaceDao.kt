package com.ugsg.sip.demo_kotlin.logic.dao

import android.content.Context
import androidx.core.content.edit
import com.google.gson.Gson
import com.ugsg.sip.demo_kotlin.MyApplication
import com.ugsg.sip.demo_kotlin.logic.model.Place

object PlaceDao {

    fun savePlace(place:Place) {
        sharedPreferences().edit {
            putString("place", Gson().toJson(place))
        }
    }

    fun getPlace():Place{
        val placeJson = sharedPreferences().getString("place", "")
        return Gson().fromJson(placeJson, Place::class.java)
    }

    fun isPlaceSaved() = sharedPreferences().contains("place")

    private fun sharedPreferences() =
        MyApplication.context.getSharedPreferences("weather", Context.MODE_PRIVATE)
}