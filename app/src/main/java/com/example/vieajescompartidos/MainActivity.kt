package com.example.vieajescompartidos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.vieajescompartidos.ui.theme.VieajesCompartidosTheme
import com.rutashare.ui.screens.HomeScreen
import com.rutashare.ui.screens.LoginScreen
import com.rutashare.ui.screens.ProfileScreen
import com.rutashare.ui.screens.PublishTripScreen
import com.rutashare.ui.screens.RegisterScreen
import com.rutashare.ui.screens.SearchResultsScreen
import com.rutashare.ui.screens.TripDetailScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VieajesCompartidosTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AppNavigation()
                }
            }
        }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            LoginScreen(
                onLoginClick = { navController.navigate("home") { popUpTo("login") { inclusive = true } } },
                onRegisterClick = { navController.navigate("register") }
            )
        }

        composable("register") {
            RegisterScreen(
                onRegisterClick = { navController.navigate("home") { popUpTo("login") { inclusive = true } } },
                onLoginClick = { navController.popBackStack() }
            )
        }

        composable("home") {
            HomeScreen(
                onSearchClick = { navController.navigate("search") },
                onPublishClick = { navController.navigate("publish") },
                onProfileClick = { navController.navigate("profile") }
            )
        }

        composable("search") {
            SearchResultsScreen(
                onBackClick = { navController.popBackStack() },
                onTripClick = { navController.navigate("trip_detail") },
                onPublishClick = { navController.navigate("publish") },
                onProfileClick = { navController.navigate("profile") }
            )
        }

        composable("trip_detail") {
            TripDetailScreen(
                onBackClick = { navController.popBackStack() },
                onJoinClick = { /* TODO */ },
                onMessageClick = { /* TODO */ },
                onPublishClick = { navController.navigate("publish") },
                onProfileClick = { navController.navigate("profile") }
            )
        }

        composable("publish") {
            PublishTripScreen(
                onBackClick = { navController.popBackStack() },
                onPublishClick = { /* TODO */ },
                onHomeClick = { navController.navigate("home") { popUpTo("home") { inclusive = true } } },
                onSearchClick = { navController.navigate("search") },
                onProfileClick = { navController.navigate("profile") }
            )
        }

        composable("profile") {
            ProfileScreen(
                onHomeClick = { navController.navigate("home") { popUpTo("home") { inclusive = true } } },
                onSearchClick = { navController.navigate("search") },
                onPublishClick = { navController.navigate("publish") }
            )
        }
    }
}