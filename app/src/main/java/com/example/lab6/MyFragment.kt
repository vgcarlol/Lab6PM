package com.example.lab6

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels




class MyFragment : Fragment() {

    private val viewModel: UrbanAreaViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Setup RecyclerView, Adapter, etc...

        viewModel.cityInfo.observe(viewLifecycleOwner) { cityInfo ->
            // Update UI with cityInfo
        }

        viewModel.cities.observe(viewLifecycleOwner) { cities ->
            // Update UI with cities
        }

        viewModel.alternateNames.observe(viewLifecycleOwner) { alternateNames ->
            // Update UI with alternateNames
        }

        // Trigger API calls
        viewModel.getCityInfo("someCityId")
        viewModel.getCities()
        viewModel.getAlternateNames("someCityId")
    }
}
