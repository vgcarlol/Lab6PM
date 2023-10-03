package com.example.lab6

import android.os.Bundle
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class DetailsActivity : AppCompatActivity() {
    private val viewModel: UrbanAreaViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val cityId = intent.getStringExtra("city_id") ?: return

        viewModel.cityInfo.observe(this) { cityInfo ->
            // Actualiza la UI con la información de la ciudad
        }

        viewModel.alternateNames.observe(this) { alternateNames ->
            // Actualiza la UI con los nombres alternativos
        }

        // Cargar información detallada de la ciudad
        viewModel.getCityInfo(cityId)
        viewModel.getAlternateNames(cityId)
    }
}

