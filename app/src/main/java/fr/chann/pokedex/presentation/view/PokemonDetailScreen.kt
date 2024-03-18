package fr.chann.pokedex.presentation.view

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import fr.chann.pokedex.presentation.event.PokemonDetailEvent
import fr.chann.pokedex.presentation.viewmodel.PokemonDetailViewModel
import fr.chann.pokedex.presentation.viewstate.PokemonDetailViewState
import kotlin.math.absoluteValue

@OptIn(ExperimentalGlideComposeApi::class,
    ExperimentalFoundationApi::class
)
@Composable
fun PokemonDetailScreen(navController: NavController, pokemonId: String, viewModel: PokemonDetailViewModel = hiltViewModel()) {
    LaunchedEffect(Unit) {
        viewModel.onEvent(PokemonDetailEvent.GetPokemon(pokemonId))
    }
    val viewState = viewModel.viewState.collectAsState()
    Column {
        Button(onClick = {
            navController.popBackStack()
        }) {
            Text("< Back")
        }
        when (val state = viewState.value) {
            is PokemonDetailViewState.Loading -> Text(text = "Loading")
            is PokemonDetailViewState.Content -> {
                val pokemon = state.pokemon
                val pagerState = rememberPagerState(pageCount = {
                    2
                })
                HorizontalPager(state = pagerState) { page ->
                    Card(
                        shape = RoundedCornerShape(corner = CornerSize(0.dp)),
                        modifier = Modifier
                            .fillMaxWidth()
                            .graphicsLayer {
                                val pageOffset = (
                                        (pagerState.currentPage - page) + pagerState
                                            .currentPageOffsetFraction
                                        ).absoluteValue
                                alpha = lerp(
                                    start = 0.5f,
                                    stop = 1f,
                                    fraction = 1f - pageOffset.coerceIn(0f, 1f)
                                )
                            }
                    ) {
                        GlideImage(
                            model = if (page == 0) pokemon.image else pokemon.imageShiny,
                            contentDescription = null,
                            modifier = Modifier.aspectRatio(ratio = 1f)
                        )
                    }
                }
                Text(text = "#${pokemon.id} ${pokemon.title}", fontSize = 25.sp)
            }
            is PokemonDetailViewState.Error -> Text(text = "Error")
        }
    }
}