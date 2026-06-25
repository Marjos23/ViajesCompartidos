package com.example.vieajescompartidos.data.repository

import com.example.vieajescompartidos.data.local.dao.TripDao
import com.example.vieajescompartidos.data.local.entities.TripEntity
import com.example.vieajescompartidos.data.model.Trip
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext

interface TripRepository {
    suspend fun getTrips(origin: String, destination: String): Result<List<Trip>>
    suspend fun getTripDetail(tripId: String): Result<Trip>
    suspend fun publishTrip(trip: Trip): Result<Boolean>
}

class RoomTripRepository(private val tripDao: TripDao) : TripRepository {

    override suspend fun getTrips(origin: String, destination: String): Result<List<Trip>> = withContext(Dispatchers.IO) {
        try {
            // Usamos la nueva función del DAO para filtrar por ruta
            val entities = tripDao.getTripsByRoute(origin, destination).first()
            Result.success(entities.map { it.toExternalModel() })
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getTripDetail(tripId: String): Result<Trip> = withContext(Dispatchers.IO) {
        try {
            val idLong = tripId.toLongOrNull() ?: return@withContext Result.failure(Exception("ID de viaje inválido"))
            val entity = tripDao.getTripById(idLong)
            if (entity != null) {
                Result.success(entity.toExternalModel())
            } else {
                Result.failure(Exception("Viaje no encontrado"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun publishTrip(trip: Trip): Result<Boolean> = withContext(Dispatchers.IO) {
        try {
            tripDao.insertTrip(trip.toEntity())
            Result.success(true)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}

// Mappers
fun TripEntity.toExternalModel() = Trip(
    id = id.toString(),
    driverName = driverName,
    driverInitials = driverInitials,
    rating = rating,
    completedTrips = completedTrips,
    origin = origin,
    destination = destination,
    departureTime = departureTime,
    availableSeats = availableSeats,
    totalSeats = totalSeats,
    price = price,
    vehicle = vehicle
)

fun Trip.toEntity() = TripEntity(
    id = id.toLongOrNull() ?: 0L,
    driverName = driverName,
    driverInitials = driverInitials,
    rating = rating,
    completedTrips = completedTrips,
    origin = origin,
    destination = destination,
    departureTime = departureTime,
    availableSeats = availableSeats,
    totalSeats = totalSeats,
    price = price,
    vehicle = vehicle
)

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
