package com.example.pokemonapp

data class Pokemon(
    val name : String,
    val url : String,
)

data class PokemonsResponse (
    val results : List<Pokemon>,
    val count : Int
)