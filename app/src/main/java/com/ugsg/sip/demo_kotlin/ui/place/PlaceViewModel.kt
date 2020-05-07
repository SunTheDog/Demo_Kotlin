package com.ugsg.sip.demo_kotlin.ui.place

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.ugsg.sip.demo_kotlin.logic.Repository
import com.ugsg.sip.demo_kotlin.logic.model.Place

class PlaceViewModel : ViewModel() {
    private val searchLiveData = MutableLiveData<String>()

    val placesList = ArrayList<Place>()

    val placeLiveData = Transformations.switchMap(searchLiveData) { query ->
        Repository.searchPlaces(query)

    }

    fun searchPlaces(query: String) {
        searchLiveData.value = query
    }
}