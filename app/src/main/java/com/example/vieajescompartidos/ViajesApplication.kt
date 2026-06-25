package com.example.vieajescompartidos

import android.app.Application
import androidx.room.Room
import com.example.vieajescompartidos.data.local.AppDatabase

class ViajesApplication : Application() {
    
    // Lazy significa que solo se creará cuando se use por primera vez
    val database: AppDatabase by lazy {
        Room.databaseBuilder(
            this,
            AppDatabase::class.java,
            "viajes_database"
        )
        // Por ahora, si cambiamos algo en la tabla, que borre y recree (solo para desarrollo inicial)
        .fallbackToDestructiveMigration()
        .build()
    }
}
