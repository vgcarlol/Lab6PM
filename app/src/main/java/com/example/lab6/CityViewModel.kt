package com.example.lab6

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CityViewModel : ViewModel() {

    private val apiService = Retrofit.Builder()
        .baseUrl("https://api.teleport.org/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(TeleportApiService::class.java)

    fun findCities(cityName: String) {
        viewModelScope.launch {
            try {
                val response = apiService.findCitiesByName(cityName)
                if (response.isSuccessful) {
                    // Handle successful response
                } else {
                    Log.e("CityViewModel", "API Error: ${response.errorBody()}")
                }
            } catch (e: Exception) {
                Log.e("CityViewModel", "Network Error: ${e.localizedMessage}")
            }
        }
    }

    // Similar functions for other endpoints...
}
