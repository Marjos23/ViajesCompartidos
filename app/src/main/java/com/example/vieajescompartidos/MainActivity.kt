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
        enableEdgeToEdge()
        setContent {
            VieajesCompartidosTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AppNavigation(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun AppNavigation(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = "login"
    ) {
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
                onHomeClick = { navController.navigate("home") { popUpTo("home") { inclusive = true } } },
                onSearchClick = { navController.navigate("search") },
                onPublishClick = { navController.navigate("publish") },
                onProfileClick = { navController.navigate("profile") }
            )
        }

        composable("search") {
            SearchResultsScreen(
                onBackClick = { navController.popBackStack() },
                onTripClick = { navController.navigate("trip_detail") },
                onHomeClick = { navController.navigate("home") { popUpTo("home") { inclusive = true } } },
                onPublishClick = { navController.navigate("publish") },
                onProfileClick = { navController.navigate("profile") }
            )
        }

        composable("trip_detail") {
            TripDetailScreen(
                onBackClick = { navController.popBackStack() },
                onJoinClick = { /* TODO */ },
                onMessageClick = { /* TODO */ },
                onHomeClick = { navController.navigate("home") { popUpTo("home") { inclusive = true } } },
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