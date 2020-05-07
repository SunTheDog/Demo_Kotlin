package com.ugsg.sip.demo_kotlin.logic.model

data class PlaceResponse(
    val status: String,
    val query: String,
    val places: List<Place>

)

data class Place(
    val name: String,
    val location: Location,
    val formatted_address: String
)

data class Location(val lng: String, val lat: String)
