package com.example.bokemonhiltapp.repository

import com.example.bokemonhiltapp.model.pokemonResponse
import com.example.bokemonhiltapp.network.PokemonsApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class Repository @Inject constructor(private var pokemonsApiService: PokemonsApiService) {

    suspend fun getPokemonResult(): pokemonResponse {
        val playList: pokemonResponse

        withContext(Dispatchers.IO) {
            playList = pokemonsApiService.getpokemons()
        }
        return playList
    }
}