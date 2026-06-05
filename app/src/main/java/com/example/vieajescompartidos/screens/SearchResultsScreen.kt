package com.rutashare.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vieajescompartidos.ui.theme.RutaGreen
import com.example.vieajescompartidos.ui.theme.RutaTextSecondary

private val RutaGreenLight2 = Color(0xFFF0FDF4)
private val RutaGreenBorder2 = Color(0xFF86EFAC)
private val RutaGreenDark2 = Color(0xFF166534)
private val RutaBorder2 = Color(0xFFE5E7EB)
private val RutaFilterBg = Color(0xFFF3F4F6)
private val RutaFilterText = Color(0xFF374151)

data class TripResult(
    val initials: String,
    val driverName: String,
    val rating: String,
    val trips: Int,
    val hour: String,
    val seats: Int,
    val price: String
)

@Composable
fun SearchResultsScreen(
    route: String = "Manta → Guayaquil",
    onBackClick: () -> Unit,
    onTripClick: () -> Unit,
    onPublishClick: () -> Unit,
    onProfileClick: () -> Unit
) {
    val results = listOf(
        TripResult("CM", "Carlos Mendoza", "4.8", 23, "6:00 AM", 3, "$4"),
        TripResult("LR", "Luis Rodas", "4.5", 11, "8:30 AM", 1, "$5"),
        TripResult("AP", "Ana Ponce", "4.9", 38, "10:00 AM", 2, "$4")
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Top Bar
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(horizontal = 16.dp, vertical = 12.dp)
        ) {
            Text(
                text = "← $route",
                color = Color(0xFF111827),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.CenterStart)
            )
        }
        Divider(color = RutaBorder2)

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
            text = "3 viajes disponibles",
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

        BottomNavBar(activeTab = "buscar", onPublishClick = onPublishClick, onProfileClick = onProfileClick)
    }
}

@Composable
fun FilterChipItem(label: String, selected: Boolean) {
    Surface(
        shape = RoundedCornerShape(20.dp),
        color = if (selected) RutaGreenLight2 else RutaFilterBg,
        border = BorderStroke(1.dp, if (selected) RutaGreenBorder2 else Color.Transparent)
    ) {
        Text(
            text = label,
            color = if (selected) RutaGreenDark2 else RutaFilterText,
            fontSize = 11.sp,
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp)
        )
    }
}

@Composable
fun TripResultCard(trip: TripResult, onViewClick: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = BorderStroke(1.dp, RutaBorder2)
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
                        text = trip.initials,
                        color = RutaGreenDark2,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp
                    )
                }
                Spacer(modifier = Modifier.width(10.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = trip.driverName,
                        color = Color(0xFF111827),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "⭐ ${trip.rating}  •  ${trip.trips} viajes",
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
                text = "🕐 ${trip.hour}  •  💺 ${trip.seats} cupo${if (trip.seats != 1) "s" else ""}  •  💵 ${trip.price}",
                color = Color(0xFF374151),
                fontSize = 12.sp
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SearchResultsScreenPreview() {
    SearchResultsScreen(onBackClick = {}, onTripClick = {}, onPublishClick = {}, onProfileClick = {})
}