package com.example.vieajescompartidos.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "trips")
data class TripEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
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
