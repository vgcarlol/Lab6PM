package com.example.lab6

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

// Define tu interfaz para Retrofit
interface TeleportApi {
    @GET("path_to_endpoint_for_cities")
    suspend fun getCities(): CitiesResponse

    @GET("path_to_endpoint_for_city_info/{city_id}")
    suspend fun getCityInfo(@Path("city_id") cityId: String): CityInfoResponse

    @GET("path_to_endpoint_for_alternate_names/{city_id}/alternate_names/")
    suspend fun getAlternateNames(@Path("city_id") cityId: String): AlternateNamesResponse
}


// Define los objetos que necesitarás para deserializar las respuestas de la API
data class UrbanAreasResponse(val _links: Links)
data class Links(val ua_items: List<UaItem>)
data class UaItem(val name: String, val href: String)

data class UrbanAreaImageResponse(val photos: Photos)
data class Photos(val image: List<Image>)
data class Image(val image_url: String)

// ViewModel
class UrbanAreaViewModel : ViewModel() {

    private val _cityInfo = MutableLiveData<CityInfoResponse>()
    val cityInfo: LiveData<CityInfoResponse> = _cityInfo

    private val _cities = MutableLiveData<CitiesResponse>()
    val cities: LiveData<CitiesResponse> = _cities

    private val _alternateNames = MutableLiveData<AlternateNamesResponse>()
    val alternateNames: LiveData<AlternateNamesResponse> = _alternateNames

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.teleport.org/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val api = retrofit.create(TeleportApi::class.java)

    fun getCityInfo(cityId: String) {
        viewModelScope.launch {
            try {
                val response = api.getCityInfo(cityId)
                _cityInfo.value = response
            } catch (e: Exception) {
                // Handle error
                // Example: Log.e("UrbanAreaViewModel", "Failed to fetch city info", e)
            }
        }
    }

    fun getCities() {
        viewModelScope.launch {
            try {
                val response = api.getCities()
                _cities.value = response
            } catch (e: Exception) {
                // Handle error
                // Example: Log.e("UrbanAreaViewModel", "Failed to fetch cities", e)
            }
        }
    }

    fun getAlternateNames(cityId: String) {
        viewModelScope.launch {
            try {
                val response = api.getAlternateNames(cityId)
                _alternateNames.value = response
            } catch (e: Exception) {
                // Handle error
                // Example: Log.e("UrbanAreaViewModel", "Failed to fetch alternate names", e)
            }
        }
    }
}
