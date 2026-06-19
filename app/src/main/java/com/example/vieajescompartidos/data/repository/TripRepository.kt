package com.example.vieajescompartidos.data.repository

import com.example.vieajescompartidos.data.model.Trip
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

interface TripRepository {
    suspend fun getTrips(origin: String, destination: String): Result<List<Trip>>
    suspend fun getTripDetail(tripId: String): Result<Trip>
    suspend fun publishTrip(trip: Trip): Result<Boolean>
}

class FakeTripRepository : TripRepository {

    private val mockTrips = listOf(
        Trip("1", "Carlos Mendoza", "CM", "4.8", 23, "Manta", "Guayaquil", "6:00 AM", 3, 4, "$4.00", "Toyota Corolla · ABC-1234"),
        Trip("2", "Luis Rodas", "LR", "4.5", 11, "Manta", "Guayaquil", "8:30 AM", 1, 4, "$5.00", "Chevrolet Aveo · XYZ-5678"),
        Trip("3", "Ana Ponce", "AP", "4.9", 38, "Manta", "Guayaquil", "10:00 AM", 2, 3, "$4.00", "Hyundai Accent · LMN-9012")
    )

    override suspend fun getTrips(origin: String, destination: String): Result<List<Trip>> = withContext(Dispatchers.IO) {
        delay(1000)
        Result.success(mockTrips)
    }

    override suspend fun getTripDetail(tripId: String): Result<Trip> = withContext(Dispatchers.IO) {
        delay(800)
        val trip = mockTrips.find { it.id == tripId }
            ?: return@withContext Result.failure(Exception("Viaje no encontrado"))
        Result.success(trip)
    }

    override suspend fun publishTrip(trip: Trip): Result<Boolean> = withContext(Dispatchers.IO) {
        delay(1500)
        Result.success(true)
    }
}
