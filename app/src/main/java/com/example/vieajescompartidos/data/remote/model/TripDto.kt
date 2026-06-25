package com.example.vieajescompartidos.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TripDto(
    val id: Long = 0,
    @SerialName("driver_id") val driverId: String = "",
    val origen: String = "",
    val destino: String = "",
    val fecha: String = "",
    val hora: String = "",
    val seats: Int = 0,
    val price: Double = 0.0,
    val descripcion: String? = null,
    @SerialName("created_at") val createdAt: String = ""
)
