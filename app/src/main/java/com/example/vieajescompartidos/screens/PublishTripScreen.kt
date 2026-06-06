package com.example.vieajescompartidos.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vieajescompartidos.ui.theme.RutaCard
import com.example.vieajescompartidos.ui.theme.RutaGray
import com.example.vieajescompartidos.ui.theme.RutaGreen
import com.example.vieajescompartidos.ui.theme.RutaTextSecondary

@Composable
fun PublishTripScreen(
    onBackClick: () -> Unit,
    onPublishClick: () -> Unit,
    onHomeClick: () -> Unit,
    onSearchClick: () -> Unit,
    onProfileClick: () -> Unit
) {
    var origen by remember { mutableStateOf("") }
    var destino by remember { mutableStateOf("") }
    var fecha by remember { mutableStateOf("") }
    var hora by remember { mutableStateOf("") }
    var seats by remember { mutableIntStateOf(3) }
    var price by remember { mutableStateOf("") }
    var descripcion by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Top bar
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(RutaGreen)
                .padding(horizontal = 16.dp, vertical = 12.dp)
        ) {
            Text(
                text = "← Nuevo viaje",
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp)
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            // Ruta
            SectionLabel("Ruta del viaje")
            TripOutlinedTextField(
                value = origen,
                onValueChange = { origen = it },
                placeholder = "📍  Ciudad de origen"
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "↕",
                color = RutaTextSecondary,
                fontSize = 18.sp,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(4.dp))
            TripOutlinedTextField(
                value = destino,
                onValueChange = { destino = it },
                placeholder = "🏁  Ciudad de destino"
            )

            Spacer(modifier = Modifier.height(12.dp))
            Divider(color = Color(0xFFE5E7EB))
            Spacer(modifier = Modifier.height(12.dp))

            // Fecha y hora
            SectionLabel("Fecha y hora de salida")
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                TripOutlinedTextField(
                    value = fecha,
                    onValueChange = { fecha = it },
                    placeholder = "📅  Fecha",
                    modifier = Modifier.weight(1f)
                )
                TripOutlinedTextField(
                    value = hora,
                    onValueChange = { hora = it },
                    placeholder = "🕐  Hora",
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(12.dp))
            Divider(color = Color(0xFFE5E7EB))
            Spacer(modifier = Modifier.height(12.dp))

            // Cupos
            SectionLabel("Cupos disponibles")
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, Color(0xFFE5E7EB), RoundedCornerShape(10.dp))
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // workaround for clickable minus
                TextButton(
                    onClick = { if (seats > 1) seats-- },
                    contentPadding = PaddingValues(0.dp),
                    modifier = Modifier.size(40.dp)
                ) {
                    Text("−", color = RutaGreen, fontSize = 26.sp, fontWeight = FontWeight.Bold)
                }
                Text(
                    text = seats.toString(),
                    color = Color(0xFF111827),
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
                TextButton(
                    onClick = { if (seats < 6) seats++ },
                    contentPadding = PaddingValues(0.dp),
                    modifier = Modifier.size(40.dp)
                ) {
                    Text("+", color = RutaGreen, fontSize = 26.sp, fontWeight = FontWeight.Bold)
                }
            }

            Spacer(modifier = Modifier.height(12.dp))
            Divider(color = Color(0xFFE5E7EB))
            Spacer(modifier = Modifier.height(12.dp))

            // Aporte
            SectionLabel("Aporte sugerido por pasajero")
            TripOutlinedTextField(
                value = price,
                onValueChange = { price = it },
                placeholder = "💵  \$0.00",
                keyboardType = KeyboardType.Decimal
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Descripción
            SectionLabel("Descripción adicional (opcional)")
            OutlinedTextField(
                value = descripcion,
                onValueChange = { descripcion = it },
                placeholder = { Text("Ej: parada en Daule, pago al llegar...", color = RutaTextSecondary, fontSize = 12.sp) },
                minLines = 3,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = RutaGreen,
                    unfocusedBorderColor = RutaGray,
                    focusedTextColor = Color(0xFF111827),
                    unfocusedTextColor = Color(0xFF111827),
                    cursorColor = RutaGreen,
                    focusedContainerColor = Color(0xFFF9FAFB),
                    unfocusedContainerColor = Color(0xFFF9FAFB)
                ),
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Publicar
            Button(
                onClick = onPublishClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(containerColor = RutaGreen)
            ) {
                Text(
                    text = "🚗  Publicar viaje",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
        }

        BottomNavBar(
            activeTab = "publicar",
            onSearchClick = onSearchClick,
            onPublishClick = {},
            onProfileClick = onProfileClick
        )
    }
}

@Composable
fun SectionLabel(text: String) {
    Text(
        text = text,
        color = Color(0xFF374151),
        fontSize = 13.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(bottom = 6.dp)
    )
}

@Composable
fun TripOutlinedTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier.fillMaxWidth(),
    keyboardType: KeyboardType = KeyboardType.Text
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = { Text(placeholder, color = Color(0xFF9CA3AF), fontSize = 13.sp) },
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = RutaGreen,
            unfocusedBorderColor = Color(0xFFD1D5DB),
            focusedTextColor = Color(0xFF111827),
            unfocusedTextColor = Color(0xFF111827),
            cursorColor = RutaGreen,
            focusedContainerColor = Color(0xFFF9FAFB),
            unfocusedContainerColor = Color(0xFFF9FAFB)
        ),
        shape = RoundedCornerShape(10.dp),
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun PublishTripScreenPreview() {
    PublishTripScreen(
        onBackClick = {},
        onPublishClick = {},
        onHomeClick = {},
        onSearchClick = {},
        onProfileClick = {}
    )
}