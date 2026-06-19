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
import com.example.vieajescompartidos.data.model.Trip
import com.example.vieajescompartidos.ui.theme.RutaGreen
import com.example.vieajescompartidos.ui.theme.RutaTextSecondary
import com.example.vieajescompartidos.ui.viewmodel.SearchResultsViewModel
import com.example.vieajescompartidos.ui.viewmodel.ViewModelFactory

private val RutaGreenLight2 = Color(0xFFF0FDF4)
private val RutaGreenBorder2 = Color(0xFF86EFAC)
private val RutaGreenDark2 = Color(0xFF166534)

@Composable
fun SearchResultsScreen(
    onBackClick: () -> Unit,
    onTripClick: () -> Unit,
    onHomeClick: () -> Unit = {},
    onPublishClick: () -> Unit,
    onProfileClick: () -> Unit,
    viewModel: SearchResultsViewModel = viewModel(factory = ViewModelFactory())
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    SearchResultsContent(
        route = uiState.route,
        results = uiState.results,
        onBackClick = onBackClick,
        onTripClick = onTripClick,
        onHomeClick = onHomeClick,
        onPublishClick = onPublishClick,
        onProfileClick = onProfileClick
    )
}

@Composable
fun SearchResultsContent(
    route: String,
    results: List<Trip>,
    onBackClick: () -> Unit,
    onTripClick: () -> Unit,
    onHomeClick: () -> Unit,
    onPublishClick: () -> Unit,
    onProfileClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        // Top Bar
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background)
                .padding(horizontal = 16.dp, vertical = 12.dp)
        ) {
            Text(
                text = "← $route",
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.CenterStart)
            )
        }
        HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)

        // Filter chips
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            FilterChipItem(label = "Hoy", selected = true)
            FilterChipItem(label = "1+ cupos", selected = true)
            FilterChipItem(label = "< \$5", selected = true)
            FilterChipItem(label = "Filtros ▼", selected = false)
        }

        Text(
            text = "${results.size} viajes disponibles",
            color = RutaTextSecondary,
            fontSize = 13.sp,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
        )

        // List
        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Spacer(modifier = Modifier.height(4.dp))
            results.forEach { trip ->
                TripResultCard(trip = trip, onViewClick = onTripClick)
            }
            Spacer(modifier = Modifier.height(8.dp))
        }

        BottomNavBar(activeTab = "buscar", onHomeClick = onHomeClick, onPublishClick = onPublishClick, onProfileClick = onProfileClick)
    }
}

@Composable
fun FilterChipItem(label: String, selected: Boolean) {
    Surface(
        shape = RoundedCornerShape(20.dp),
        color = if (selected) RutaGreenLight2 else MaterialTheme.colorScheme.surfaceVariant,
        border = BorderStroke(1.dp, if (selected) RutaGreenBorder2 else Color.Transparent)
    ) {
        Text(
            text = label,
            color = if (selected) RutaGreenDark2 else MaterialTheme.colorScheme.onSurfaceVariant,
            fontSize = 11.sp,
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp)
        )
    }
}

@Composable
fun TripResultCard(trip: Trip, onViewClick: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Avatar
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .size(42.dp)
                        .background(RutaGreenLight2, CircleShape)
                ) {
                    Text(
                        text = trip.driverInitials,
                        color = RutaGreenDark2,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp
                    )
                }
                Spacer(modifier = Modifier.width(10.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = trip.driverName,
                        color = MaterialTheme.colorScheme.onBackground,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "⭐ ${trip.rating}  •  ${trip.completedTrips} viajes",
                        color = RutaTextSecondary,
                        fontSize = 12.sp
                    )
                }
                Button(
                    onClick = onViewClick,
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = RutaGreen),
                    contentPadding = PaddingValues(horizontal = 14.dp, vertical = 4.dp),
                    modifier = Modifier.height(32.dp)
                ) {
                    Text(text = "Ver", color = Color.White, fontSize = 13.sp, fontWeight = FontWeight.Bold)
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "🕐 ${trip.departureTime}  •  💺 ${trip.availableSeats} cupo${if (trip.availableSeats != 1) "s" else ""}  •  💵 ${trip.price}",
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = 12.sp
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SearchResultsScreenPreview() {
    SearchResultsContent(
        route = "Manta → Guayaquil",
        results = listOf(Trip("1", "Carlos Mendoza", "CM", "4.8", 23, "Manta", "Guayaquil", "6:00 AM", 3, 4, "$4", "Toyota Corolla")),
        onBackClick = {},
        onTripClick = {},
        onHomeClick = {},
        onPublishClick = {},
        onProfileClick = {}
    )
}
