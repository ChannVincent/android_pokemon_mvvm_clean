package fr.chann.pokedex.presentation.view

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardElevation
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import fr.chann.pokedex.presentation.event.PokemonListEvent
import fr.chann.pokedex.presentation.viewmodel.PokemonListViewModel
import fr.chann.pokedex.presentation.viewstate.PokemonCardViewState
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
                LazyColumn(
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    items(
                        items = state.pokemonList,
                        itemContent = {
                            PokemonListItem(pokemon = it, navController = navController)
                        })
                }
            }
            is PokemonListViewState.Error -> Text(text = "Error")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalGlideComposeApi::class)
@Composable
fun PokemonListItem(pokemon: PokemonCardViewState, navController: NavController) {
    Card(
        modifier = Modifier.padding(horizontal = 8.dp, vertical = 8.dp).fillMaxWidth(),
        shape = RoundedCornerShape(corner = CornerSize(16.dp)),
        onClick = {
            navController.navigate("pokemon_detail/${pokemon.id}")
        }
    ) {
        Row {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .align(Alignment.CenterVertically)
            ) {
                Text(text = pokemon.title, style = typography.titleMedium)
                Text(text = pokemon.description, style = typography.bodyMedium)
                GlideImage(model = pokemon.image, contentDescription = null, Modifier.height(150.dp))
            }
        }
    }
}