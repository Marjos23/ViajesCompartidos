package com.example.vieajescompartidos.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vieajescompartidos.data.repository.TripRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class SearchResultsUiState(
    val route: String = "Manta → Guayaquil",
    val results: List<com.example.vieajescompartidos.data.model.Trip> = emptyList(),
    val isLoading: Boolean = false
)

class SearchResultsViewModel(private val tripRepository: TripRepository) : ViewModel() {
    private val _uiState = MutableStateFlow(SearchResultsUiState(isLoading = true))
    val uiState: StateFlow<SearchResultsUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            val result = tripRepository.getTrips("Manta", "Guayaquil")
            result.onSuccess { trips ->
                _uiState.update { it.copy(results = trips, isLoading = false) }
            }.onFailure {
                _uiState.update { it.copy(isLoading = false) }
            }
        }
    }
}
