package fr.chann.pokedex.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.chann.pokedex.business.IsPokemonFavoriteUseCase
import fr.chann.pokedex.business.PokemonRepository
import fr.chann.pokedex.presentation.event.PokemonDetailEvent
import fr.chann.pokedex.presentation.viewstate.PokemonCardViewState
import fr.chann.pokedex.presentation.viewstate.PokemonDetailViewState
import fr.chann.pokedex.presentation.viewstate.PokemonViewState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonDetailViewModel @Inject constructor(
    private val repository : PokemonRepository,
    private val isPokemonFavoriteUseCase : IsPokemonFavoriteUseCase,
): ViewModel() {

    private val _viewState = MutableStateFlow<PokemonDetailViewState>(PokemonDetailViewState.Loading)
    val viewState: StateFlow<PokemonDetailViewState> = _viewState

    private fun updateViewState(newState: PokemonDetailViewState) {
        viewModelScope.launch {
            _viewState.value = newState
        }
    }

    fun onEvent(events: PokemonDetailEvent) {
        when (events) {
            is PokemonDetailEvent.GetPokemon -> {
                viewModelScope.launch {
                    getPokemon(events.pokemonId)
                }
            }
        }
    }

    private suspend fun getPokemon(pokemonId: String) {
        repository.getPokemon(pokemonId).collect { pokemonTable ->
            updateViewState(PokemonDetailViewState.Content(
                    PokemonViewState(
                        pokemonTable.id,
                        pokemonTable.name,
                        "",
                        pokemonTable.image,
                        pokemonTable.imageShiny,
                        pokemonTable.icon,
                        pokemonTable.iconBack,
                        isPokemonFavoriteUseCase.execute(pokemonId),
                    )
            ))
        }
    }

}
