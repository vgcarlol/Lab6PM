import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lab6.City
import com.example.lab6.TeleportApiService
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CityViewModel : ViewModel() {

    private val apiService = Retrofit.Builder()
        .baseUrl("https://api.teleport.org/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(TeleportApiService::class.java)

    // Variable LiveData para almacenar la lista de ciudades
    private val _cities = MutableLiveData<List<City>>()
    val cities: LiveData<List<City>> = _cities

    // Método para actualizar la lista de ciudades
    private fun updateCities(newCities: List<City>) {
        _cities.value = newCities
    }

    // Función para buscar ciudades por nombre
    fun findCities(cityName: String) {
        viewModelScope.launch {
            try {
                val response = apiService.findCitiesByName(cityName)
                if (response.isSuccessful) {
                    // Manejar la respuesta exitosa y obtener la lista de ciudades
                    val citiesList = response.body()?.cities ?: emptyList()
                    updateCities(citiesList) // Actualizar la lista de ciudades en LiveData
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
