package fr.chann.pokedex.presentation.view

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import fr.chann.pokedex.presentation.event.PokemonDetailEvent
import fr.chann.pokedex.presentation.event.PokemonListEvent
import fr.chann.pokedex.presentation.viewmodel.PokemonDetailViewModel
import fr.chann.pokedex.presentation.viewmodel.PokemonListViewModel
import fr.chann.pokedex.presentation.viewstate.PokemonCardViewState
import fr.chann.pokedex.presentation.viewstate.PokemonDetailViewState
import fr.chann.pokedex.presentation.viewstate.PokemonListViewState

@OptIn(ExperimentalGlideComposeApi::class, ExperimentalMaterial3Api::class)
@Composable
fun PokemonDetailVIew(navController: NavController, pokemonId: String, viewModel: PokemonDetailViewModel = hiltViewModel()) {
    LaunchedEffect(Unit) {
        viewModel.onEvent(PokemonDetailEvent.GetPokemon(pokemonId))
    }
    val viewState = viewModel.viewState.collectAsState()
    Column {
        Text(text = "DETAIL VIEW $pokemonId")
        Button(onClick = { navController.navigate("pokemon_list") }) {
            Text("Go to Pokemon List")
        }
        when (val state = viewState.value) {
            is PokemonDetailViewState.Loading -> Text(text = "Loading")
            is PokemonDetailViewState.Content -> {
                val pokemon = state.pokemon
                Card (onClick = {
                    navController.navigate("pokemon_detail/${pokemon.id}")
                }) {
                    Text(text = pokemon.id)
                    Text(text = pokemon.title)
                    Text(text = pokemon.description)
                    GlideImage(model = pokemon.image, contentDescription = null)
                }
            }
            is PokemonDetailViewState.Error -> Text(text = "Error")
        }
    }
}