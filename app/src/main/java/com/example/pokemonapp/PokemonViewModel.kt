package com.example.pokemonapp

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class PokemonViewModel : ViewModel() {
    private val _pokemonsState = mutableStateOf(PokemonsState())
    val pokemonsState : State<PokemonsState> = _pokemonsState

    init {
        fetchPokemons()
    }

    private fun fetchPokemons() {
        viewModelScope.launch {
            try {
                val response = apiService.getPokemons(1000000, 0)
                _pokemonsState.value = _pokemonsState.value.copy(
                    loading = false,
                    error = null,
                    results = response.results,
                )
                Log.d("SUCCESS", "Got pokemons! ${_pokemonsState.value.results[0].name} is ready!")
                Log.d("SUCCESS", "Got pokemons! ${_pokemonsState.value.results} is ready!")
            }
            catch (e : Exception) {
                _pokemonsState.value = _pokemonsState.value.copy(
                    loading = false,
                    error = "ERROR FETCHING POKEMONS: ${e.message}",
                )
            }
        }
    }

    data class PokemonsState(
        val loading : Boolean = true,
        val error : String? = null,
        val results : List<Pokemon> = emptyList()
    )
}