package com.example.pokemonapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter

@Composable
fun PokemonsScreen(
    modifier: Modifier = Modifier,
) {
    val pokemonViewModel : PokemonViewModel = viewModel()
    val viewState by pokemonViewModel.pokemonsState

    Box(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        when {
            viewState.loading -> {
                CircularProgressIndicator(
                    modifier = Modifier
                        .align(Alignment.Center),
                )
            }
            viewState.error != null -> {
                Text("Error occurred when fetching data!")
            }
            else -> {
                CategoryScreen(pokemons = viewState.results)
            }
        }
    }
}

@Composable
fun CategoryScreen(
    modifier: Modifier = Modifier,
    pokemons: List<Pokemon>
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        modifier = Modifier
            .fillMaxSize(),
    ) {
        items(pokemons) { pokemon ->
            PokemonItem(pokemon)
        }
    }
}

@Composable
fun PokemonItem(
    pokemon: Pokemon,
) {
    val imageUrl : String = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/"
    val id = pokemon.url.split("/").last { it.isNotEmpty() }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Image(
            painter = rememberAsyncImagePainter("$imageUrl$id.png"),
            contentDescription = "",
            modifier = Modifier
                .fillMaxSize()
                .aspectRatio(1f)
        )



        Text(
            text = id + ". " + pokemon.name.first().uppercase() + pokemon.name.substring(1),
            color = Color.Black,
            style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 12.sp),
        )
    }
}




