package com.example.bokemonhiltapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bokemonhiltapp.databinding.ActivityMainBinding
import com.example.bokemonhiltapp.ui.adapter.PokemonListadapter
import com.example.bokemonhiltapp.viewModels.PokemonViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint//will take care of injecting member into the android component
class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding
    private lateinit var viewModel: PokemonViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

         binding = ActivityMainBinding.inflate(layoutInflater)
          setContentView(binding.root)


        val adapter = PokemonListadapter {}
        //binding.pokemonRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.pokemonRecyclerView.layoutManager = GridLayoutManager(this, 2)
        binding.pokemonRecyclerView.adapter = adapter

        //ViewModel
        viewModel=ViewModelProvider(this).get(PokemonViewModel::class.java)
        // Attach an observer on the Items list to update the UI automatically when the data changes.
        viewModel.pokemonList.observe(this) { items ->
           // items.let {
                adapter.submitList(items)
          //  }
        }
    }
}