package com.example.pokemonapp

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private val retrofit = Retrofit.Builder()
    .baseUrl("https://pokeapi.co/api/v2/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

val apiService : ApiService = retrofit.create(ApiService::class.java)

interface ApiService {
    @GET("pokemon")
    suspend fun getPokemons(
        @Query("limit") limit : Int,
        @Query("offset") offset : Int = limit,
    ) : PokemonsResponse
}