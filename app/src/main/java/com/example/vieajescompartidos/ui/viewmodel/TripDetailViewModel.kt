package com.example.vieajescompartidos.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vieajescompartidos.data.repository.TripRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class TripDetailUiState(
    val driverName: String = "",
    val driverInitials: String = "",
    val rating: String = "",
    val completedTrips: Int = 0,
    val origin: String = "",
    val destination: String = "",
    val departureTime: String = "",
    val availableSeats: Int = 0,
    val totalSeats: Int = 0,
    val price: String = "",
    val vehicle: String = "",
    val isLoading: Boolean = false
)

class TripDetailViewModel(private val tripRepository: TripRepository) : ViewModel() {
    private val _uiState = MutableStateFlow(TripDetailUiState())
    val uiState: StateFlow<TripDetailUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            val result = tripRepository.getTripDetail("1")
            result.onSuccess { trip ->
                _uiState.update {
                    it.copy(
                        driverName = trip.driverName,
                        driverInitials = trip.driverInitials,
                        rating = trip.rating,
                        completedTrips = trip.completedTrips,
                        origin = trip.origin,
                        destination = trip.destination,
                        departureTime = trip.departureTime,
                        availableSeats = trip.availableSeats,
                        totalSeats = trip.totalSeats,
                        price = trip.price,
                        vehicle = trip.vehicle,
                        isLoading = false
                    )
                }
            }.onFailure {
                _uiState.update { it.copy(isLoading = false) }
            }
        }
    }
}
