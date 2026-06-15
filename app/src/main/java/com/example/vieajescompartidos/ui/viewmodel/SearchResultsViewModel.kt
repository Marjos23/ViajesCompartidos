package com.example.vieajescompartidos.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.vieajescompartidos.screens.TripResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class SearchResultsUiState(
    val route: String = "Manta → Guayaquil",
    val results: List<TripResult> = listOf(
        TripResult("CM", "Carlos Mendoza", "4.8", 23, "6:00 AM", 3, "$4"),
        TripResult("LR", "Luis Rodas", "4.5", 11, "8:30 AM", 1, "$5"),
        TripResult("AP", "Ana Ponce", "4.9", 38, "10:00 AM", 2, "$4")
    ),
    val isLoading: Boolean = false
)

class SearchResultsViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(SearchResultsUiState())
    val uiState: StateFlow<SearchResultsUiState> = _uiState.asStateFlow()
}
