package com.example.bokemonhiltapp.network

import com.example.bokemonhiltapp.model.pokemonResponse
import retrofit2.http.GET

//Api URL
const val BASE_URL =
    "https://pokeapi.co/api/v2/"

/**
 * A public interface that exposes the [getPhotos] method
 */
interface PokemonsApiService {
    /**
     * Returns a [List] of [pokemons] and this method can be called from a Coroutine.
     * The @GET annotation indicates that the "pokemons" endpoint will be requested with the GET
     * HTTP method
     */
    @GET("pokemon")
    suspend fun getpokemons(): pokemonResponse
}

