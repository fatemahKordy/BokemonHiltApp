package com.example.bokemonhiltapp.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.bokemonhiltapp.R
import com.example.bokemonhiltapp.databinding.PokemonItemBinding
import com.example.bokemonhiltapp.model.Pokemon


class PokemonListadapter(private val onItemClicked: (Pokemon) -> Unit) :
    ListAdapter<Pokemon, PokemonListadapter.PokemonViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val binding = PokemonItemBinding.inflate(LayoutInflater.from(parent.context))
        return PokemonViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        val current = getItem(position)
        //holder.itemView.setOnClickListener {
        //    onItemClicked(current)
       // }
        holder.bind(current)
    }

    class PokemonViewHolder(private var binding: PokemonItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Pokemon) {
            binding.apply {

                pokemonNameTextView.text = item.name
               Log.v("zeeImage",item.url)
                val imgUri = item.url.toUri().buildUpon().scheme("https").build()

                pokemonImageView.load(imgUri) {
                    placeholder(R.drawable.loading_animation)//This code sets the placeholder loading image to use while loading
                    error(R.drawable.ic_broken_image)//This code also sets an image to use if image loading fails
                }
            }

        }}

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<Pokemon>() {
            override fun areItemsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
                return oldItem.name == newItem.name
            }
        }
    }
}