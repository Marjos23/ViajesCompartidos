package com.example.vieajescompartidos.ui.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class PublishTripUiState(
    val origen: String = "",
    val destino: String = "",
    val fecha: String = "",
    val hora: String = "",
    val seats: Int = 3,
    val price: String = "",
    val descripcion: String = ""
)

class PublishTripViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(PublishTripUiState())
    val uiState: StateFlow<PublishTripUiState> = _uiState.asStateFlow()

    fun onOrigenChange(origen: String) { _uiState.update { it.copy(origen = origen) } }
    fun onDestinoChange(destino: String) { _uiState.update { it.copy(destino = destino) } }
    fun onFechaChange(fecha: String) { _uiState.update { it.copy(fecha = fecha) } }
    fun onHoraChange(hora: String) { _uiState.update { it.copy(hora = hora) } }
    fun onSeatsChange(seats: Int) { _uiState.update { it.copy(seats = seats) } }
    fun onPriceChange(price: String) { _uiState.update { it.copy(price = price) } }
    fun onDescripcionChange(descripcion: String) { _uiState.update { it.copy(descripcion = descripcion) } }
}
