package com.example.vieajescompartidos.ui.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class TripDetailUiState(
    val driverName: String = "Carlos Mendoza",
    val driverInitials: String = "CM",
    val rating: String = "4.8",
    val completedTrips: Int = 23,
    val origin: String = "Manta — Centro comercial",
    val destination: String = "Guayaquil — Terminal TV",
    val departureTime: String = "6:00 AM",
    val availableSeats: Int = 3,
    val totalSeats: Int = 4,
    val price: String = "$4.00",
    val vehicle: String = "Toyota Corolla · ABC-1234"
)

class TripDetailViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(TripDetailUiState())
    val uiState: StateFlow<TripDetailUiState> = _uiState.asStateFlow()
}
