package com.ugsg.sip.demo_kotlin.ui.place

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.ugsg.sip.demo_kotlin.logic.Repository
import com.ugsg.sip.demo_kotlin.logic.model.Place

class PlaceViewModel : ViewModel() {
    val place = ArrayList<Place>()

    private val searchLiveData = MutableLiveData<String>()

    val placeLiveData = Transformations.switchMap(searchLiveData) {
        Repository.searchPlaces(it)
    }

    fun searchPlaces(query: String) {
        searchLiveData.value = query
    }

    fun savePlace(place: Place) = Repository.savePlace(place)

    fun getSavePlace() = Repository.getSavePlace()

    fun isPlaceSave() = Repository.isPlaceSaved()

}