package com.example.vieajescompartidos.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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

private val ProfileGreenLight = Color(0xFFD1FAE5)
private val ProfileGreenDark = Color(0xFF065F46)
private val ProfileYellow = Color(0xFFFEF3C7)
private val ProfileYellowBorder = Color(0xFFFCD34D)
private val ProfileYellowText = Color(0xFF92400E)
private val ProfileRed = Color(0xFFDC2626)

@Composable
fun ProfileScreen(
    onHomeClick: () -> Unit,
    onSearchClick: () -> Unit,
    onPublishClick: () -> Unit,
    onEditProfileClick: () -> Unit = {},
    onVehicleClick: () -> Unit = {},
    onNotificationsClick: () -> Unit = {},
    onHistoryClick: () -> Unit = {},
    onSecurityClick: () -> Unit = {},
    onLogoutClick: () -> Unit = {}
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
                .background(RutaGreen)
                .padding(horizontal = 16.dp, vertical = 14.dp)
        ) {
            Text(
                text = "Mi perfil",
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.Center)
            )
        }

        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(20.dp))

            // Avatar
            Box(contentAlignment = Alignment.BottomEnd) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .size(72.dp)
                        .background(ProfileGreenLight, CircleShape)
                ) {
                    Text(
                        text = "VT",
                        color = ProfileGreenDark,
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp
                    )
                }
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .size(22.dp)
                        .background(Color.White, CircleShape)
                        .clickable { onEditProfileClick() }
                ) {
                    Text(text = "✏️", fontSize = 12.sp)
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "Valentina Torres",
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "valentina.torres@email.com",
                color = RutaTextSecondary,
                fontSize = 13.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Badges
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Badge(text = "✓ Verificado", bgColor = ProfileGreenLight, borderColor = Color(0xFF34D399), textColor = ProfileGreenDark)
                Badge(text = "⭐ 4.7", bgColor = ProfileYellow, borderColor = ProfileYellowBorder, textColor = ProfileYellowText)
            }

            Spacer(modifier = Modifier.height(16.dp))
            Divider(color = MaterialTheme.colorScheme.outlineVariant)
            Spacer(modifier = Modifier.height(12.dp))

            // Estadísticas
            Text(
                text = "Estadísticas",
                color = RutaTextSecondary,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.Start)
            )
            Spacer(modifier = Modifier.height(8.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(10.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 12.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    StatItem(value = "12", label = "viajes")
                    StatItem(value = "4.7", label = "calificación")
                    StatItem(value = "3", label = "pendientes")
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
            Divider(color = MaterialTheme.colorScheme.outlineVariant)
            Spacer(modifier = Modifier.height(12.dp))

            // Ajustes
            Text(
                text = "Ajustes del perfil",
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.Start)
            )
            Spacer(modifier = Modifier.height(8.dp))

            ProfileMenuItem(icon = "✏️", label = "Editar datos personales", onClick = onEditProfileClick)
            ProfileMenuItem(icon = "🚗", label = "Mi vehículo", onClick = onVehicleClick)
            ProfileMenuItem(icon = "🔔", label = "Notificaciones", onClick = onNotificationsClick)
            ProfileMenuItem(icon = "🕐", label = "Historial de viajes", onClick = onHistoryClick)
            ProfileMenuItem(icon = "🔒", label = "Seguridad y privacidad", onClick = onSecurityClick)
            ProfileMenuItem(icon = "🚪", label = "Cerrar sesión", onClick = onLogoutClick, isDestructive = true)

            Spacer(modifier = Modifier.height(16.dp))
        }

        BottomNavBar(
            activeTab = "perfil",
            onHomeClick = onHomeClick,
            onSearchClick = onSearchClick,
            onPublishClick = onPublishClick
        )
    }
}

@Composable
fun Badge(text: String, bgColor: Color, borderColor: Color, textColor: Color) {
    Surface(
        shape = RoundedCornerShape(20.dp),
        color = bgColor,
        border = BorderStroke(1.dp, borderColor)
    ) {
        Text(
            text = text,
            color = textColor,
            fontSize = 12.sp,
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
        )
    }
}

@Composable
fun StatItem(value: String, label: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = value, color = RutaGreen, fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Text(text = label, color = RutaTextSecondary, fontSize = 11.sp)
    }
}

@Composable
fun ProfileMenuItem(
    icon: String,
    label: String,
    onClick: () -> Unit,
    isDestructive: Boolean = false
) {
    val textColor = if (isDestructive) Color(0xFFDC2626) else MaterialTheme.colorScheme.onSurface

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 3.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "$icon  $label", color = textColor, fontSize = 13.sp)
            Text(text = "›", color = MaterialTheme.colorScheme.onSurfaceVariant, fontSize = 18.sp, fontWeight = FontWeight.Light)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    ProfileScreen(onHomeClick = {}, onSearchClick = {}, onPublishClick = {})
}