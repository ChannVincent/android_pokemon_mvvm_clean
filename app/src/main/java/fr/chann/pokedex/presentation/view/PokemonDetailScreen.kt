package fr.chann.pokedex.presentation.view

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import fr.chann.pokedex.R
import fr.chann.pokedex.presentation.event.PokemonDetailEvent
import fr.chann.pokedex.presentation.event.PokemonListEvent
import fr.chann.pokedex.presentation.view.component.TopBar
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
        when (val state = viewState.value) {
            is PokemonDetailViewState.Loading -> {
                TopBar(
                    title = {
                        Text(
                            stringResource(R.string.pokedex),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )},
                    onBack = {
                        navController.popBackStack()
                    }
                )
                CircularProgressIndicator(
                    modifier = Modifier.width(100.dp),
                    color = MaterialTheme.colorScheme.secondary,
                    trackColor = MaterialTheme.colorScheme.surfaceVariant,
                )
            }
            is PokemonDetailViewState.Content -> {
                val pokemon = state.pokemon
                val pagerState = rememberPagerState(pageCount = {
                    2
                })
                TopBar(
                    title = {
                        Text(
                            "#${pokemon.id} ${pokemon.title}",
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )},
                    onBack = {
                        navController.popBackStack()
                    }
                )
                Column (modifier = Modifier.verticalScroll(rememberScrollState())) {
                    HorizontalPager(state = pagerState) { page ->
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(MaterialTheme.colorScheme.secondaryContainer)
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
                    Column (
                        modifier = Modifier.padding(20.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Text(text = stringResource(R.string.title_description), fontSize = 20.sp)
                        Text(text = stringResource(R.string.lorem_ipsum), fontSize = 14.sp)
                        Text(text = stringResource(R.string.title_abilities), fontSize = 20.sp)
                        Text(text = stringResource(R.string.lorem_ipsum), fontSize = 14.sp)
                    }
                }
            }
            is PokemonDetailViewState.Error -> {
                TopBar(
                    title = {
                        Text(
                            stringResource(R.string.pokedex),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )},
                    onBack = {
                        navController.popBackStack()
                    }
                )
                Box (
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                ) {
                    Text(text = "Internet error")
                    Button(onClick = {
                        viewModel.onEvent(PokemonDetailEvent.GetPokemon(pokemonId))
                    }) {
                        Text(text = "Retry")
                    }
                }
            }
        }
    }
}