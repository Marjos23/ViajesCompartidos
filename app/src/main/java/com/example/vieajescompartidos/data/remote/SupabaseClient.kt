package com.example.vieajescompartidos.data.remote

import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.realtime.Realtime

object SupabaseProvider {
    val client: SupabaseClient = createSupabaseClient(
        supabaseUrl = "https://ugqpylluccfmkbzvjpkc.supabase.co",
        supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InVncXB5bGx1Y2NmbWtienZqcGtjIiwicm9sZSI6ImFub24iLCJpYXQiOjE3ODIzMDU3MDQsImV4cCI6MjA5Nzg4MTcwNH0.UyIUcDJHYr803Iu05ZB-7FJkwVeLP5lf3PKsXOlABdI"
    ) {
        install(Auth)
        install(Postgrest)
        install(Realtime)
    }
}
