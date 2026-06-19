package com.example.vieajescompartidos.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vieajescompartidos.data.model.Trip
import com.example.vieajescompartidos.data.repository.TripRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class PublishTripUiState(
    val origen: String = "",
    val destino: String = "",
    val fecha: String = "",
    val hora: String = "",
    val seats: Int = 3,
    val price: String = "",
    val descripcion: String = "",
    val isLoading: Boolean = false,
    val isPublishSuccessful: Boolean = false,
    val errorMessage: String? = null
)

class PublishTripViewModel(private val tripRepository: TripRepository) : ViewModel() {
    private val _uiState = MutableStateFlow(PublishTripUiState())
    val uiState: StateFlow<PublishTripUiState> = _uiState.asStateFlow()

    fun onOrigenChange(origen: String) { _uiState.update { it.copy(origen = origen) } }
    fun onDestinoChange(destino: String) { _uiState.update { it.copy(destino = destino) } }
    fun onFechaChange(fecha: String) { _uiState.update { it.copy(fecha = fecha) } }
    fun onHoraChange(hora: String) { _uiState.update { it.copy(hora = hora) } }
    fun onSeatsChange(seats: Int) { _uiState.update { it.copy(seats = seats) } }
    fun onPriceChange(price: String) { _uiState.update { it.copy(price = price) } }
    fun onDescripcionChange(descripcion: String) { _uiState.update { it.copy(descripcion = descripcion) } }

    fun publish() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }
            val state = _uiState.value
            val trip = Trip(
                id = "0",
                driverName = "Valentina Torres",
                driverInitials = "VT",
                rating = "5.0",
                completedTrips = 0,
                origin = state.origen,
                destination = state.destino,
                departureTime = "${state.fecha} ${state.hora}",
                availableSeats = state.seats,
                totalSeats = state.seats,
                price = "$${state.price}",
                vehicle = "Por confirmar"
            )
            val result = tripRepository.publishTrip(trip)
            result.onSuccess {
                _uiState.update { it.copy(isLoading = false, isPublishSuccessful = true) }
            }.onFailure { error ->
                _uiState.update { it.copy(isLoading = false, errorMessage = error.message) }
            }
        }
    }
}
