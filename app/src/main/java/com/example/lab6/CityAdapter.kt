package com.example.lab6

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class CityAdapter(private val onClick: (City) -> Unit) :
    ListAdapter<City, CityAdapter.CityViewHolder>(CityDiffCallback()) {

    class CityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cityNameTextView: TextView = itemView.findViewById(R.id.cityNameTextView)
    }

    class CityDiffCallback : DiffUtil.ItemCallback<City>() {
        override fun areItemsTheSame(oldItem: City, newItem: City): Boolean {
            // Tu lógica para comparar si los items son los mismos (e.g. comparar IDs únicos)
            return oldItem.id == newItem.id // Cambia "id" por el atributo correcto para comparar
        }

        override fun areContentsTheSame(oldItem: City, newItem: City): Boolean {
            // Tu lógica para comparar si los contenidos de los items son los mismos
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.city_item, parent, false)
        return CityViewHolder(view)
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        val city = getItem(position)
        holder.cityNameTextView.text = city.name // Cambia "name" al atributo correcto que deseas mostrar
        holder.itemView.setOnClickListener { onClick(city) }
    }
}
