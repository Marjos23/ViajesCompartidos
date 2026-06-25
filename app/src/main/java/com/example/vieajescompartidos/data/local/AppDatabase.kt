package com.example.vieajescompartidos.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.vieajescompartidos.data.local.dao.TripDao
import com.example.vieajescompartidos.data.local.entities.TripEntity

@Database(entities = [TripEntity::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun tripDao(): TripDao
}
