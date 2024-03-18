package fr.chann.pokedex.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.chann.pokedex.business.AddPokemonToFavoriteUseCase
import fr.chann.pokedex.business.IsPokemonFavoriteUseCase
import fr.chann.pokedex.business.PokemonRepository
import fr.chann.pokedex.business.SearchInPokemonListUseCase
import fr.chann.pokedex.presentation.event.PokemonListEvent
import fr.chann.pokedex.presentation.viewstate.PokemonCardViewState
import fr.chann.pokedex.presentation.viewstate.PokemonListViewState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.reflect.typeOf

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    private val repository : PokemonRepository,
    private val searchInPokemonListUseCase: SearchInPokemonListUseCase,
    private val isPokemonFavoriteUseCase: IsPokemonFavoriteUseCase,
    private val addPokemonFavoriteUseCase: AddPokemonToFavoriteUseCase,
    ): ViewModel() {

    private val _viewState = MutableStateFlow<PokemonListViewState>(PokemonListViewState.Loading)
    val viewState: StateFlow<PokemonListViewState> = _viewState

    private fun updateViewState(newState: PokemonListViewState) {
        viewModelScope.launch {
            _viewState.value = newState
        }
    }

    fun onEvent(events: PokemonListEvent) {
        when (events) {
            is PokemonListEvent.GetAllPokemon -> {
                viewModelScope.launch {
                    refreshPokemonList()
                    getAllPokemon()
                }
            }
            is PokemonListEvent.SearchPokemon -> {
                viewModelScope.launch {
                    refreshSearchedPokemonList(events.searchTerm)
                    getSearchedPokemon()
                }
            }

            is PokemonListEvent.AddPokemonToFavorite -> {
                viewModelScope.launch {
                    addPokemonFavoriteUseCase.execute(events.pokemonId, events.grade)
                }
            }
        }
    }

    private suspend fun refreshPokemonList() {
        try {
            updateViewState(PokemonListViewState.Loading)
            repository.refreshPokemonList()
        } catch (e: Exception) {
            updateViewState(PokemonListViewState.Error)
        }
    }

    private suspend fun getAllPokemon() {
        repository.pokemonList.collect { pokemonTableList ->
            updateViewState(PokemonListViewState.Content(
                pokemonTableList.map {
                    PokemonCardViewState(
                        id = it.id,
                        title = "#${it.id} ${it.name}",
                        description = "",
                        image = it.image,
                        favorite = isPokemonFavoriteUseCase.execute(it.id),
                    )
                }
            ))
        }
    }

    private suspend fun refreshSearchedPokemonList(searchTerm: String) {
        try {
            updateViewState(PokemonListViewState.Loading)
            searchInPokemonListUseCase.execute(searchTerm)
        } catch (e: Exception) {
            updateViewState(PokemonListViewState.Error)
        }
    }

    private suspend fun getSearchedPokemon() {
        repository.searchedPokemonList.collect { pokemonTableList ->
            if (pokemonTableList.isNotEmpty()) {
                updateViewState(PokemonListViewState.Content(
                    pokemonTableList.map {
                        PokemonCardViewState(
                            id = it.id,
                            title = it.name,
                            description = "",
                            image = it.image,
                            favorite = isPokemonFavoriteUseCase.execute(it.id),
                        )
                    }
                ))
            }
        }
    }

}

