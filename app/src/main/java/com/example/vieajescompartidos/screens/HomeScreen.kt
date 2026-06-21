package com.example.vieajescompartidos.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
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
import com.example.vieajescompartidos.ui.viewmodel.HomeViewModel
import com.example.vieajescompartidos.di.ViewModelFactory

val RutaGreenLight = Color(0xFFF0FDF4)
val RutaGreenBorder = Color(0xFF86EFAC)
val RutaGreenDark = Color(0xFF166534)

@Composable
fun HomeScreen(
    onHomeClick: () -> Unit,
    onSearchClick: () -> Unit,
    onPublishClick: () -> Unit,
    onProfileClick: () -> Unit,
    viewModel: HomeViewModel = viewModel(factory = ViewModelFactory())
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    HomeContent(
        userName = uiState.userName,
        origen = uiState.origen,
        destino = uiState.destino,
        recentSearches = uiState.recentSearches,
        onOrigenChange = viewModel::onOrigenChange,
        onDestinoChange = viewModel::onDestinoChange,
        onHomeClick = onHomeClick,
        onSearchClick = onSearchClick,
        onPublishClick = onPublishClick,
        onProfileClick = onProfileClick
    )
}

@Composable
fun HomeContent(
    userName: String,
    origen: String,
    destino: String,
    recentSearches: List<String>,
    onOrigenChange: (String) -> Unit,
    onDestinoChange: (String) -> Unit,
    onHomeClick: () -> Unit,
    onSearchClick: () -> Unit,
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
                .background(RutaGreen)
                .padding(horizontal = 16.dp, vertical = 12.dp)
        ) {
            Text(
                text = "🚗 RutaShare",
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.CenterStart)
            )
            Text(
                text = "🔔",
                fontSize = 20.sp,
                modifier = Modifier.align(Alignment.CenterEnd)
            )
        }

        // Scrollable content
        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp)
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            // Saludo
            Text(
                text = "Hola, $userName 👋",
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "¿A dónde viajas hoy?",
                color = RutaTextSecondary,
                fontSize = 14.sp
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Caja de búsqueda
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
                border = CardDefaults.outlinedCardBorder()
            ) {
                Column(modifier = Modifier.padding(12.dp)) {
                    Text(
                        text = "Origen",
                        color = RutaTextSecondary,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    OutlinedTextField(
                        value = origen,
                        onValueChange = onOrigenChange,
                        placeholder = { Text("📍 Manta, Manabí", color = RutaTextSecondary, fontSize = 13.sp) },
                        singleLine = true,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = RutaGreen,
                            unfocusedBorderColor = MaterialTheme.colorScheme.outlineVariant,
                            focusedTextColor = MaterialTheme.colorScheme.onBackground,
                            unfocusedTextColor = MaterialTheme.colorScheme.onBackground,
                            cursorColor = RutaGreen,
                            focusedContainerColor = MaterialTheme.colorScheme.surface,
                            unfocusedContainerColor = MaterialTheme.colorScheme.surface
                        ),
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = "Destino",
                        color = RutaTextSecondary,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    OutlinedTextField(
                        value = destino,
                        onValueChange = onDestinoChange,
                        placeholder = { Text("🏁 Guayaquil, Guayas", color = RutaTextSecondary, fontSize = 13.sp) },
                        singleLine = true,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = RutaGreen,
                            unfocusedBorderColor = MaterialTheme.colorScheme.outlineVariant,
                            focusedTextColor = MaterialTheme.colorScheme.onBackground,
                            unfocusedTextColor = MaterialTheme.colorScheme.onBackground,
                            cursorColor = RutaGreen,
                            focusedContainerColor = MaterialTheme.colorScheme.surface,
                            unfocusedContainerColor = MaterialTheme.colorScheme.surface
                        ),
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Botón buscar
            Button(
                onClick = onSearchClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(containerColor = RutaGreen)
            ) {
                Text(
                    text = "🔍  Buscar viajes",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Fecha chip
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = "📅  Fecha:", color = RutaTextSecondary, fontSize = 13.sp)
                Spacer(modifier = Modifier.width(8.dp))
                Surface(
                    shape = RoundedCornerShape(20.dp),
                    color = RutaGreenLight,
                    border = androidx.compose.foundation.BorderStroke(1.dp, RutaGreenBorder)
                ) {
                    Text(
                        text = "Hoy, Sáb 26 abr",
                        color = RutaGreenDark,
                        fontSize = 12.sp,
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
            HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
            Spacer(modifier = Modifier.height(12.dp))

            // Últimas búsquedas
            Text(
                text = "Últimas búsquedas",
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))

            recentSearches.forEach { search ->
                RecentSearchItem(text = search)
                Spacer(modifier = Modifier.height(8.dp))
            }

            Spacer(modifier = Modifier.height(16.dp))
        }

        // Bottom Navigation Bar
        BottomNavBar(activeTab = "inicio", onHomeClick = onHomeClick, onSearchClick = onSearchClick, onPublishClick = onPublishClick, onProfileClick = onProfileClick)
    }
}

@Composable
fun RecentSearchItem(text: String) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant)
    ) {
        Text(
            text = text,
            color = MaterialTheme.colorScheme.onSurface,
            fontSize = 13.sp,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 10.dp)
        )
    }
}

@Composable
fun BottomNavBar(
    activeTab: String,
    onHomeClick: () -> Unit = {},
    onSearchClick: () -> Unit = {},
    onPublishClick: () -> Unit = {},
    onProfileClick: () -> Unit = {}
) {
    val activeColor = RutaGreen
    val inactiveColor = MaterialTheme.colorScheme.onSurfaceVariant

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface)
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        NavBarItem(icon = "🏠", label = "Inicio", color = if (activeTab == "inicio") activeColor else inactiveColor, onClick = onHomeClick)
        NavBarItem(icon = "🔍", label = "Buscar", color = if (activeTab == "buscar") activeColor else inactiveColor, onClick = onSearchClick)
        NavBarItem(icon = "➕", label = "Publicar", color = if (activeTab == "publicar") activeColor else inactiveColor, onClick = onPublishClick)
        NavBarItem(icon = "👤", label = "Perfil", color = if (activeTab == "perfil") activeColor else inactiveColor, onClick = onProfileClick)
    }
}

@Composable
fun NavBarItem(icon: String, label: String, color: Color, onClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clickable { onClick() }
            .padding(8.dp)
    ) {
        Text(text = icon, fontSize = 20.sp)
        Text(text = label, color = color, fontSize = 10.sp)
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeContent(
        userName = "Valentina",
        origen = "",
        destino = "",
        recentSearches = listOf("📍 Manta → Guayaquil · hace 2 días"),
        onOrigenChange = {},
        onDestinoChange = {},
        onHomeClick = {},
        onSearchClick = {},
        onPublishClick = {},
        onProfileClick = {}
    )
}
