package com.example.vieajescompartidos.data.model

data class Trip(
    val id: String,
    val driverName: String,
    val driverInitials: String,
    val rating: String,
    val completedTrips: Int,
    val origin: String,
    val destination: String,
    val departureTime: String,
    val availableSeats: Int,
    val totalSeats: Int,
    val price: String,
    val vehicle: String
)
