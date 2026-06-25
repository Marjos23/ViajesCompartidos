package com.example.vieajescompartidos.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.vieajescompartidos.ui.theme.RutaGreen
import com.example.vieajescompartidos.ui.theme.RutaTextSecondary
import com.example.vieajescompartidos.ui.viewmodel.TripDetailViewModel
import com.example.vieajescompartidos.di.ViewModelFactory

private val GreenLight = Color(0xFFF0FDF4)
private val GreenBorder = Color(0xFF34D399)
private val GreenDark = Color(0xFF065F46)

@Composable
fun TripDetailScreen(
    onBackClick: () -> Unit,
    onJoinClick: () -> Unit,
    onMessageClick: () -> Unit,
    onHomeClick: () -> Unit = {},
    onSearchClick: (String, String) -> Unit = { _, _ -> },
    onPublishClick: () -> Unit,
    onProfileClick: () -> Unit,
    factory: androidx.lifecycle.ViewModelProvider.Factory,
    viewModel: TripDetailViewModel = viewModel(factory = factory)
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    TripDetailContent(
        driverName = uiState.driverName,
        driverInitials = uiState.driverInitials,
        rating = uiState.rating,
        completedTrips = uiState.completedTrips,
        origin = uiState.origin,
        destination = uiState.destination,
        departureTime = uiState.departureTime,
        availableSeats = uiState.availableSeats,
        totalSeats = uiState.totalSeats,
        price = uiState.price,
        vehicle = uiState.vehicle,
        onBackClick = onBackClick,
        onJoinClick = onJoinClick,
        onMessageClick = onMessageClick,
        onHomeClick = onHomeClick,
        onSearchClick = onSearchClick,
        onPublishClick = onPublishClick,
        onProfileClick = onProfileClick
    )
}

@Composable
fun TripDetailContent(
    driverName: String,
    driverInitials: String,
    rating: String,
    completedTrips: Int,
    origin: String,
    destination: String,
    departureTime: String,
    availableSeats: Int,
    totalSeats: Int,
    price: String,
    vehicle: String,
    onBackClick: () -> Unit,
    onJoinClick: () -> Unit,
    onMessageClick: () -> Unit,
    onHomeClick: () -> Unit,
    onSearchClick: (String, String) -> Unit,
    onPublishClick: () -> Unit,
    onProfileClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        // Top bar
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background)
                .padding(horizontal = 16.dp, vertical = 12.dp)
        ) {
            Text(
                text = "← Detalle del viaje",
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }
        HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)

        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp)
        ) {
            Spacer(modifier = Modifier.height(12.dp))

            // Driver card
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = GreenLight),
                border = BorderStroke(1.dp, GreenBorder)
            ) {
                Row(
                    modifier = Modifier.padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Avatar
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .size(50.dp)
                            .background(GreenLight, CircleShape)
                    ) {
                        Text(
                            text = driverInitials,
                            color = GreenDark,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        )
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Column {
                        Text(
                            text = driverName,
                            color = MaterialTheme.colorScheme.onBackground,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "⭐ $rating · $completedTrips viajes completados",
                            color = RutaTextSecondary,
                            fontSize = 12.sp
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Surface(
                            shape = RoundedCornerShape(20.dp),
                            color = GreenLight,
                            border = BorderStroke(1.dp, GreenBorder)
                        ) {
                            Text(
                                text = "Verificado ✓",
                                color = GreenDark,
                                fontSize = 11.sp,
                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 3.dp)
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
            HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
            Spacer(modifier = Modifier.height(12.dp))

            // Detalles del viaje
            Text(
                text = "Detalles del viaje",
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(10.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant)
            ) {
                Column(modifier = Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    TripDetailRow(text = "📍  Salida: $origin")
                    TripDetailRow(text = "🏁  Llegada: $destination")
                    TripDetailRow(text = "🕕  Hora de salida: $departureTime")
                    TripDetailRow(text = "💺  Cupos disponibles: $availableSeats de $totalSeats")
                    TripDetailRow(text = "💵  Aporte por pasajero: $price", isGreen = true)
                    TripDetailRow(text = "🚗  $vehicle")
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
            HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
            Spacer(modifier = Modifier.height(16.dp))

            // Botón principal
            Button(
                onClick = onJoinClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(containerColor = RutaGreen)
            ) {
                Text(
                    text = "✅  Solicitar unirme al viaje",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Botón secundario
            OutlinedButton(
                onClick = onMessageClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(46.dp),
                shape = RoundedCornerShape(10.dp),
                border = BorderStroke(1.5.dp, RutaGreen)
            ) {
                Text(
                    text = "💬  Mensaje al conductor",
                    color = RutaGreen,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Comparte tu ubicación al conductor →",
                color = RutaTextSecondary,
                fontSize = 12.sp,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(16.dp))
        }

        BottomNavBar(
            activeTab = "buscar",
            onHomeClick = onHomeClick,
            onSearchClick = { o, d -> onSearchClick(o, d) },
            onPublishClick = onPublishClick,
            onProfileClick = onProfileClick
        )
    }
}

@Composable
fun TripDetailRow(text: String, isGreen: Boolean = false) {
    Text(
        text = text,
        color = if (isGreen) RutaGreen else MaterialTheme.colorScheme.onSurface,
        fontSize = 13.sp,
        fontWeight = if (isGreen) FontWeight.SemiBold else FontWeight.Normal
    )
}

@Preview(showBackground = true)
@Composable
fun TripDetailScreenPreview() {
    TripDetailContent(
        driverName = "Carlos Mendoza",
        driverInitials = "CM",
        rating = "4.8",
        completedTrips = 23,
        origin = "Manta",
        destination = "Guayaquil",
        departureTime = "6:00 AM",
        availableSeats = 3,
        totalSeats = 4,
        price = "$4.00",
        vehicle = "Toyota Corolla",
        onBackClick = {},
        onJoinClick = {},
        onMessageClick = {},
        onHomeClick = {},
        onSearchClick = { _, _ -> },
        onPublishClick = {},
        onProfileClick = {}
    )
}
