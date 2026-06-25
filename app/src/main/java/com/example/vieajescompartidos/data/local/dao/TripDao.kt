package com.example.vieajescompartidos.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.vieajescompartidos.data.local.entities.TripEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TripDao {
    @Query("SELECT * FROM trips")
    fun getAllTrips(): Flow<List<TripEntity>>

    @Query("SELECT * FROM trips WHERE origin = :origin AND destination = :destination")
    fun getTripsByRoute(origin: String, destination: String): Flow<List<TripEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTrip(trip: TripEntity)

    @Query("SELECT * FROM trips WHERE id = :id")
    suspend fun getTripById(id: Long): TripEntity?
}
