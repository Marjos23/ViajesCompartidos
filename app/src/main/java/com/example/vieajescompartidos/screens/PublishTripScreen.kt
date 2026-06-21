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
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.vieajescompartidos.ui.theme.RutaGreen
import com.example.vieajescompartidos.ui.theme.RutaTextSecondary
import com.example.vieajescompartidos.ui.viewmodel.PublishTripViewModel
import com.example.vieajescompartidos.di.ViewModelFactory

@Composable
fun PublishTripScreen(
    onBackClick: () -> Unit,
    onPublishClick: () -> Unit,
    onHomeClick: () -> Unit,
    onSearchClick: () -> Unit,
    onProfileClick: () -> Unit,
    viewModel: PublishTripViewModel = viewModel(factory = ViewModelFactory())
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(uiState.isPublishSuccessful) {
        if (uiState.isPublishSuccessful) {
            onPublishClick()
        }
    }

    PublishTripContent(
        origen = uiState.origen,
        destino = uiState.destino,
        fecha = uiState.fecha,
        hora = uiState.hora,
        seats = uiState.seats,
        price = uiState.price,
        descripcion = uiState.descripcion,
        isLoading = uiState.isLoading,
        errorMessage = uiState.errorMessage,
        onOrigenChange = viewModel::onOrigenChange,
        onDestinoChange = viewModel::onDestinoChange,
        onFechaChange = viewModel::onFechaChange,
        onHoraChange = viewModel::onHoraChange,
        onSeatsChange = viewModel::onSeatsChange,
        onPriceChange = viewModel::onPriceChange,
        onDescripcionChange = viewModel::onDescripcionChange,
        onBackClick = onBackClick,
        onPublishClick = viewModel::publish,
        onHomeClick = onHomeClick,
        onSearchClick = onSearchClick,
        onProfileClick = onProfileClick
    )
}

@Composable
fun PublishTripContent(
    origen: String,
    destino: String,
    fecha: String,
    hora: String,
    seats: Int,
    price: String,
    descripcion: String,
    isLoading: Boolean = false,
    errorMessage: String? = null,
    onOrigenChange: (String) -> Unit,
    onDestinoChange: (String) -> Unit,
    onFechaChange: (String) -> Unit,
    onHoraChange: (String) -> Unit,
    onSeatsChange: (Int) -> Unit,
    onPriceChange: (String) -> Unit,
    onDescripcionChange: (String) -> Unit,
    onBackClick: () -> Unit,
    onPublishClick: () -> Unit,
    onHomeClick: () -> Unit,
    onSearchClick: () -> Unit,
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
                onValueChange = onOrigenChange,
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
                onValueChange = onDestinoChange,
                placeholder = "🏁  Ciudad de destino"
            )

            Spacer(modifier = Modifier.height(12.dp))
            HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
            Spacer(modifier = Modifier.height(12.dp))

            // Fecha y hora
            SectionLabel("Fecha y hora de salida")
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                TripOutlinedTextField(
                    value = fecha,
                    onValueChange = onFechaChange,
                    placeholder = "📅  Fecha",
                    modifier = Modifier.weight(1f)
                )
                TripOutlinedTextField(
                    value = hora,
                    onValueChange = onHoraChange,
                    placeholder = "🕐  Hora",
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(12.dp))
            HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
            Spacer(modifier = Modifier.height(12.dp))

            // Cupos
            SectionLabel("Cupos disponibles")
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, MaterialTheme.colorScheme.outlineVariant, RoundedCornerShape(10.dp))
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // workaround for clickable minus
                TextButton(
                    onClick = { if (seats > 1) onSeatsChange(seats - 1) },
                    contentPadding = PaddingValues(0.dp),
                    modifier = Modifier.size(40.dp)
                ) {
                    Text("−", color = RutaGreen, fontSize = 26.sp, fontWeight = FontWeight.Bold)
                }
                Text(
                    text = seats.toString(),
                    color = MaterialTheme.colorScheme.onBackground,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
                TextButton(
                    onClick = { if (seats < 6) onSeatsChange(seats + 1) },
                    contentPadding = PaddingValues(0.dp),
                    modifier = Modifier.size(40.dp)
                ) {
                    Text("+", color = RutaGreen, fontSize = 26.sp, fontWeight = FontWeight.Bold)
                }
            }

            Spacer(modifier = Modifier.height(12.dp))
            HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
            Spacer(modifier = Modifier.height(12.dp))

            // Aporte
            SectionLabel("Aporte sugerido por pasajero")
            TripOutlinedTextField(
                value = price,
                onValueChange = onPriceChange,
                placeholder = "💵  \$0.00",
                keyboardType = KeyboardType.Decimal
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Descripción
            SectionLabel("Descripción adicional (opcional)")
            OutlinedTextField(
                value = descripcion,
                onValueChange = onDescripcionChange,
                placeholder = { Text("Ej: parada en Daule, pago al llegar...", color = RutaTextSecondary, fontSize = 12.sp) },
                minLines = 3,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = RutaGreen,
                    unfocusedBorderColor = MaterialTheme.colorScheme.outlineVariant,
                    focusedTextColor = MaterialTheme.colorScheme.onBackground,
                    unfocusedTextColor = MaterialTheme.colorScheme.onBackground,
                    cursorColor = RutaGreen,
                    focusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                    unfocusedContainerColor = MaterialTheme.colorScheme.surfaceVariant
                ),
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier.fillMaxWidth()
            )

            errorMessage?.let {
                Text(
                    text = it,
                    color = MaterialTheme.colorScheme.error,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Publicar
            Button(
                onClick = onPublishClick,
                enabled = !isLoading,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(containerColor = RutaGreen)
            ) {
                if (isLoading) {
                    CircularProgressIndicator(color = Color.White, modifier = Modifier.size(24.dp))
                } else {
                    Text(
                        text = "🚗  Publicar viaje",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
        }

        BottomNavBar(
            activeTab = "publicar",
            onHomeClick = onHomeClick,
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
        color = MaterialTheme.colorScheme.onSurface,
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
            unfocusedBorderColor = MaterialTheme.colorScheme.outlineVariant,
            focusedTextColor = MaterialTheme.colorScheme.onBackground,
            unfocusedTextColor = MaterialTheme.colorScheme.onBackground,
            cursorColor = RutaGreen,
            focusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
            unfocusedContainerColor = MaterialTheme.colorScheme.surfaceVariant
        ),
        shape = RoundedCornerShape(10.dp),
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun PublishTripScreenPreview() {
    PublishTripContent(
        origen = "",
        destino = "",
        fecha = "",
        hora = "",
        seats = 3,
        price = "",
        descripcion = "",
        onOrigenChange = {},
        onDestinoChange = {},
        onFechaChange = {},
        onHoraChange = {},
        onSeatsChange = {},
        onPriceChange = {},
        onDescripcionChange = {},
        onBackClick = {},
        onPublishClick = {},
        onHomeClick = {},
        onSearchClick = {},
        onProfileClick = {}
    )
}
