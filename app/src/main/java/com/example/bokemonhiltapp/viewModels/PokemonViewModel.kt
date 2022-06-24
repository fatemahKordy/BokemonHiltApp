package com.example.bokemonhiltapp.viewModels

import android.util.Log
import androidx.lifecycle.*
import com.example.bokemonhiltapp.model.Pokemon
import com.example.bokemonhiltapp.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class PokemonViewModel @Inject constructor(var repository: Repository) : ViewModel() {

    private var _pokemonList = MutableLiveData<List<Pokemon>>()
    val pokemonList: LiveData<List<Pokemon>>
        get() = _pokemonList


    init {
        getPokemons()
    }

    /**
     * Refresh data from the repository. Use a coroutine launch to run in a
     * background thread.
     */
    private fun getPokemons() {
        var pokemonList=mutableListOf<Pokemon>()

        viewModelScope.launch {
            try {

                //get the all pokemons from api
                pokemonList=repository.getPokemonResult().results as MutableList

                //change the url for each pokemon
                pokemonList.forEach() {
                    val urlIndex = it.url.split("/")
                    it.url =
                       "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/" + urlIndex[urlIndex.lastIndex - 1] + ".png"
                }
                 //add the modified data to liveData variable
                _pokemonList.value =  pokemonList

            } catch (networkError: IOException) {
                 if(_pokemonList.value.isNullOrEmpty())
                 Log.v("ViewModel", " No data fetched")
            }
        }
    }

}