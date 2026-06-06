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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vieajescompartidos.ui.theme.RutaGreen
import com.example.vieajescompartidos.ui.theme.RutaTextSecondary

private val GreenLight = Color(0xFFF0FDF4)
private val GreenBorder = Color(0xFF34D399)
private val GreenDark = Color(0xFF065F46)
private val BorderColor = Color(0xFFE5E7EB)
private val BgGray = Color(0xFFF9FAFB)

@Composable
fun TripDetailScreen(
    onBackClick: () -> Unit,
    onJoinClick: () -> Unit,
    onMessageClick: () -> Unit,
    onPublishClick: () -> Unit,
    onProfileClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Top bar
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(horizontal = 16.dp, vertical = 12.dp)
        ) {
            Text(
                text = "← Detalle del viaje",
                color = Color(0xFF111827),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Divider(color = BorderColor)

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
                            text = "CM",
                            color = GreenDark,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        )
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Column {
                        Text(
                            text = "Carlos Mendoza",
                            color = Color(0xFF111827),
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "⭐ 4.8 · 23 viajes completados",
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
            Divider(color = BorderColor)
            Spacer(modifier = Modifier.height(12.dp))

            // Detalles del viaje
            Text(
                text = "Detalles del viaje",
                color = Color(0xFF6B7280),
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(10.dp),
                colors = CardDefaults.cardColors(containerColor = BgGray),
                border = BorderStroke(1.dp, BorderColor)
            ) {
                Column(modifier = Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    TripDetailRow(text = "📍  Salida: Manta — Centro comercial")
                    TripDetailRow(text = "🏁  Llegada: Guayaquil — Terminal TV")
                    TripDetailRow(text = "🕕  Hora de salida: 6:00 AM")
                    TripDetailRow(text = "💺  Cupos disponibles: 3 de 4")
                    TripDetailRow(text = "💵  Aporte por pasajero: \$4.00", isGreen = true)
                    TripDetailRow(text = "🚗  Toyota Corolla · ABC-1234")
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
            Divider(color = BorderColor)
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

        BottomNavBar(activeTab = "buscar", onPublishClick = onPublishClick, onProfileClick = onProfileClick)
    }
}

@Composable
fun TripDetailRow(text: String, isGreen: Boolean = false) {
    Text(
        text = text,
        color = if (isGreen) RutaGreen else Color(0xFF374151),
        fontSize = 13.sp,
        fontWeight = if (isGreen) FontWeight.SemiBold else FontWeight.Normal
    )
}

@Preview(showBackground = true)
@Composable
fun TripDetailScreenPreview() {
    TripDetailScreen(onBackClick = {}, onJoinClick = {}, onMessageClick = {}, onPublishClick = {}, onProfileClick = {})
}