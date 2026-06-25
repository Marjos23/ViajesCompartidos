package com.example.vieajescompartidos.data.remote

import com.example.vieajescompartidos.data.model.Trip
import com.example.vieajescompartidos.data.remote.model.ProfileDto
import com.example.vieajescompartidos.data.remote.model.TripDto
import com.example.vieajescompartidos.data.repository.TripRepository
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.postgrest.query.Columns
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SupabaseTripRepository : TripRepository {

    override suspend fun getTrips(origin: String, destination: String): Result<List<Trip>> = withContext(Dispatchers.IO) {
        try {
            val tripDtos = SupabaseProvider.client.postgrest
                .from("trips")
                .select(Columns.raw("*")) {
                    if (origin.isNotBlank() && destination.isNotBlank()) {
                        filter {
                            eq("origen", origin)
                            eq("destino", destination)
                        }
                    }
                }
                .decodeList<TripDto>()

            val trips = tripDtos.map { dto ->
                val driver = getDriverProfile(dto.driverId)
                dto.toTrip(driver?.fullName ?: "Conductor")
            }

            Result.success(trips)
        } catch (e: Exception) {
            Result.failure(Exception("Error al buscar viajes: ${e.message}"))
        }
    }

    override suspend fun getTripDetail(tripId: String): Result<Trip> = withContext(Dispatchers.IO) {
        try {
            val dto = SupabaseProvider.client.postgrest
                .from("trips")
                .select(Columns.raw("*")) {
                    filter { eq("id", tripId.toLongOrNull() ?: 0) }
                }
                .decodeSingleOrNull<TripDto>()
                ?: return@withContext Result.failure(Exception("Viaje no encontrado"))

            val driver = getDriverProfile(dto.driverId)
            Result.success(dto.toTrip(driver?.fullName ?: "Conductor"))
        } catch (e: Exception) {
            Result.failure(Exception("Error al cargar viaje: ${e.message}"))
        }
    }

    override suspend fun publishTrip(trip: Trip): Result<Boolean> = withContext(Dispatchers.IO) {
        try {
            val currentUser = SupabaseProvider.client.auth.currentUserOrNull()
                ?: return@withContext Result.failure(Exception("Debes iniciar sesión"))

            val dto = TripDto(
                driverId = currentUser.id,
                origen = trip.origin,
                destino = trip.destination,
                fecha = trip.departureTime.split(" ").firstOrNull() ?: "",
                hora = trip.departureTime.split(" ").getOrNull(1) ?: trip.departureTime,
                seats = trip.totalSeats,
                price = trip.price.removePrefix("$").toDoubleOrNull() ?: 0.0,
                descripcion = ""
            )

            SupabaseProvider.client.postgrest.from("trips").insert(dto)
            Result.success(true)
        } catch (e: Exception) {
            Result.failure(Exception("Error al publicar viaje: ${e.message}"))
        }
    }

    private suspend fun getDriverProfile(driverId: String): ProfileDto? {
        return try {
            SupabaseProvider.client.postgrest
                .from("profiles")
                .select(Columns.raw("*")) {
                    filter { eq("id", driverId) }
                }
                .decodeSingleOrNull<ProfileDto>()
        } catch (_: Exception) {
            null
        }
    }
}

private fun TripDto.toTrip(driverName: String): Trip {
    val names = driverName.split(" ", limit = 2)
    val initials = "${names.firstOrNull()?.firstOrNull() ?: '?'}${names.getOrNull(1)?.firstOrNull() ?: '?'}"
    val priceStr = "$${String.format("%.2f", price)}"

    return Trip(
        id = id.toString(),
        driverName = driverName,
        driverInitials = initials,
        rating = "5.0",
        completedTrips = 0,
        origin = origen,
        destination = destino,
        departureTime = "$fecha $hora",
        availableSeats = seats,
        totalSeats = seats,
        price = priceStr,
        vehicle = "Por confirmar"
    )
}
