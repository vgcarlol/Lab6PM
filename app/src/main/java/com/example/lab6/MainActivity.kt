package com.example.lab6

import CityViewModel
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lab6.City
import com.example.lab6.CityAdapter
import com.example.lab6.DetailsActivity
import com.example.lab6.R

class MainActivity : AppCompatActivity() {
    private val viewModel: CityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val adapter = CityAdapter { city ->
            val intent = Intent(this, DetailsActivity::class.java)
            intent.putExtra("city_id", city.id) // Usamos "id" en lugar de "geoname_id"
            startActivity(intent)
        }
        recyclerView.adapter = adapter

        viewModel.cities.observe(this) { cities ->
            Log.d("MainActivity", "Cities: $cities")
            adapter.submitList(cities) // Utiliza submitList para actualizar la lista de ciudades
        }

        viewModel.findCities("city_name") // Debes proporcionar el nombre de la ciudad que deseas buscar
    }
}
