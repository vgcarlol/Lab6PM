package com.example.lab6

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TeleportApiService {
    @GET("cities/")
    suspend fun findCitiesByName(@Query("search") cityName: String): Response<CitiesResponse>

    @GET("cities/{city_id}/")
    suspend fun getCityInfo(@Path("city_id") cityId: String): Response<CityInfoResponse>

    @GET("cities/{city_id}/alternate_names/")
    suspend fun getAlternateNames(@Path("city_id") cityId: String): Response<AlternateNamesResponse>
}
