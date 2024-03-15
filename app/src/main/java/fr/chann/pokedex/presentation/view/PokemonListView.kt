package fr.chann.pokedex.presentation.view

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import fr.chann.pokedex.presentation.event.PokemonListEvent
import fr.chann.pokedex.presentation.viewmodel.PokemonListViewModel
import fr.chann.pokedex.presentation.viewstate.PokemonListViewState

@OptIn(ExperimentalGlideComposeApi::class, ExperimentalMaterial3Api::class)
@Composable
fun PokemonListView(navController: NavController, viewModel: PokemonListViewModel = hiltViewModel()) {
    LaunchedEffect(Unit) {
        viewModel.onEvent(PokemonListEvent.GetAllPokemon)
    }
    val viewState = viewModel.viewState.collectAsState()
    Column {
        Text(text = "LIST VIEW")
        when (val state = viewState.value) {
            is PokemonListViewState.Loading -> Text(text = "Loading")
            is PokemonListViewState.Content -> {
                state.pokemonList.forEach { pokemon ->
                    Card (onClick = {
                        navController.navigate("pokemon_detail/${pokemon.id}")
                    }) {
                        Text(text = pokemon.id)
                        Text(text = pokemon.title)
                        Text(text = pokemon.description)
                        GlideImage(model = pokemon.image, contentDescription = null)
                    }
                }
            }
            is PokemonListViewState.Error -> Text(text = "Error")
        }
    }
}