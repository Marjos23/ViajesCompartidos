package com.example.vieajescompartidos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.vieajescompartidos.ui.theme.VieajesCompartidosTheme
import com.example.vieajescompartidos.screens.HomeScreen
import com.example.vieajescompartidos.screens.LoginScreen
import com.example.vieajescompartidos.screens.ProfileScreen
import com.example.vieajescompartidos.screens.PublishTripScreen
import com.example.vieajescompartidos.screens.RegisterScreen
import com.example.vieajescompartidos.screens.SearchResultsScreen
import com.example.vieajescompartidos.screens.TripDetailScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // 1. Obtenemos la base de datos y el repo
        val app = application as ViajesApplication
        val tripRepository = com.example.vieajescompartidos.data.repository.RoomTripRepository(app.database.tripDao())
        
        // 2. Creamos el Factory
        val factory = com.example.vieajescompartidos.di.ViewModelFactory(tripRepository)

        enableEdgeToEdge()
        setContent {
            com.example.vieajescompartidos.ui.theme.VieajesCompartidosTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    // 3. Pasamos el factory a la navegación
                    AppNavigation(
                        factory = factory,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun AppNavigation(
    factory: com.example.vieajescompartidos.di.ViewModelFactory,
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = "login"
    ) {
        composable("login") {
            LoginScreen(
                factory = factory,
                onLoginSuccess = { navController.navigate("home") { popUpTo("login") { inclusive = true } } },
                onRegisterClick = { navController.navigate("register") }
            )
        }

        composable("register") {
            RegisterScreen(
                factory = factory,
                onRegisterClick = { navController.navigate("home") { popUpTo("login") { inclusive = true } } },
                onLoginClick = { navController.popBackStack() }
            )
        }

        composable("home") {
            HomeScreen(
                factory = factory,
                onHomeClick = { navController.navigate("home") { popUpTo("home") { inclusive = true } } },
                onSearchClick = { origin, destination -> 
                    navController.navigate("search/$origin/$destination") 
                },
                onPublishClick = { navController.navigate("publish") },
                onProfileClick = { navController.navigate("profile") }
            )
        }

        composable("search/{origin}/{destination}") { backStackEntry ->
            val origin = backStackEntry.arguments?.getString("origin") ?: ""
            val destination = backStackEntry.arguments?.getString("destination") ?: ""
            
            SearchResultsScreen(
                origin = origin,
                destination = destination,
                factory = factory,
                onBackClick = { navController.popBackStack() },
                onTripClick = { navController.navigate("trip_detail") },
                onHomeClick = { navController.navigate("home") { popUpTo("home") { inclusive = true } } },
                onSearchClick = { o, d -> 
                    navController.navigate("search/$o/$d") 
                },
                onPublishClick = { navController.navigate("publish") },
                onProfileClick = { navController.navigate("profile") }
            )
        }

        composable("trip_detail") {
            TripDetailScreen(
                factory = factory,
                onBackClick = { navController.popBackStack() },
                onJoinClick = { /* TODO */ },
                onMessageClick = { /* TODO */ },
                onHomeClick = { navController.navigate("home") { popUpTo("home") { inclusive = true } } },
                onSearchClick = { origin, destination -> 
                    navController.navigate("search/$origin/$destination") 
                },
                onPublishClick = { navController.navigate("publish") },
                onProfileClick = { navController.navigate("profile") }
            )
        }

        composable("publish") {
            PublishTripScreen(
                factory = factory,
                onBackClick = { navController.popBackStack() },
                onPublishClick = { /* TODO */ },
                onHomeClick = { navController.navigate("home") { popUpTo("home") { inclusive = true } } },
                onSearchClick = { origin, destination -> 
                    navController.navigate("search/$origin/$destination") 
                },
                onProfileClick = { navController.navigate("profile") }
            )
        }

        composable("profile") {
            ProfileScreen(
                factory = factory,
                onHomeClick = { navController.navigate("home") { popUpTo("home") { inclusive = true } } },
                onSearchClick = { origin, destination -> 
                    navController.navigate("search/$origin/$destination") 
                },
                onPublishClick = { navController.navigate("publish") }
            )
        }
    }
}