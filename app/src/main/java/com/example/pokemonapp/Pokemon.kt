package com.example.pokemonapp

import android.media.Image

data class Pokemon(
    val name : String,
    val url : String,
    val order : Int,
    val artwork: String,
//    val types: List<Type>
)

//data class Type(
//    val name : String,
//    val url : String,
//    val imageUrl : String
//)
//
//data class Types (
//    val types : Map<Int, Type>
//)

data class Pokemons (
    val pokemons : List<Pokemon>
)