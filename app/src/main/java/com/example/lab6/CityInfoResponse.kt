package com.example.lab6

data class CityInfoResponse(
    val full_name: String,
    val geoname_id: Int,
    val location: Location,
    val name: String,
    val population: Int
)

data class Location(
    val geohash: String,
    val latlon: LatLon
)

data class LatLon(
    val latitude: Double,
    val longitude: Double
)
