package com.example.lab6

import com.example.lab6.CityInfoResponse

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private val viewModel: UrbanAreaViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val adapter = CityAdapter(listOf()) { city ->
            val intent = Intent(this, DetailsActivity::class.java)
            intent.putExtra("city_id", city.geoname_id.toString())  // Suponiendo que 'geoname_id' es un atributo de tu clase de ciudad
            startActivity(intent)
        }
        recyclerView.adapter = adapter

        viewModel.cities.observe(this) { cities ->
            Log.d("MainActivity", "Cities: $cities")
            adapter.cities = cities // Asumiendo que tu adaptador maneja una lista de ciudades
            adapter.notifyDataSetChanged()
        }

        // Aquí puedes realizar las llamadas iniciales para obtener los datos.
        // Considera el flujo de usuario y cuándo es lógico cargar ciertos tipos de datos.
        viewModel.getCities()
    }
}
