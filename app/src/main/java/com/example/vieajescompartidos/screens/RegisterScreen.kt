package com.example.vieajescompartidos.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.vieajescompartidos.ui.theme.RutaGreen
import com.example.vieajescompartidos.ui.theme.RutaTextSecondary
import com.example.vieajescompartidos.ui.viewmodel.RegisterViewModel
import com.example.vieajescompartidos.ui.viewmodel.ViewModelFactory

@Composable
fun RegisterScreen(
    onRegisterClick: () -> Unit,
    onLoginClick: () -> Unit,
    viewModel: RegisterViewModel = viewModel(factory = ViewModelFactory())
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(uiState.isRegistrationSuccessful) {
        if (uiState.isRegistrationSuccessful) {
            onRegisterClick()
        }
    }

    RegisterContent(
        nombres = uiState.nombres,
        apellidos = uiState.apellidos,
        cedula = uiState.cedula,
        fechaNacimiento = uiState.fechaNacimiento,
        telefono = uiState.telefono,
        email = uiState.email,
        password = uiState.password,
        confirmPassword = uiState.confirmPassword,
        onNombresChange = viewModel::onNombresChange,
        onApellidosChange = viewModel::onApellidosChange,
        onCedulaChange = viewModel::onCedulaChange,
        onFechaNacimientoChange = viewModel::onFechaNacimientoChange,
        onTelefonoChange = viewModel::onTelefonoChange,
        onEmailChange = viewModel::onEmailChange,
        onPasswordChange = viewModel::onPasswordChange,
        onConfirmPasswordChange = viewModel::onConfirmPasswordChange,
        onRegisterClick = viewModel::register,
        onLoginClick = onLoginClick
    )
}

@Composable
fun RegisterContent(
    nombres: String,
    apellidos: String,
    cedula: String,
    fechaNacimiento: String,
    telefono: String,
    email: String,
    password: String,
    confirmPassword: String,
    onNombresChange: (String) -> Unit,
    onApellidosChange: (String) -> Unit,
    onCedulaChange: (String) -> Unit,
    onFechaNacimientoChange: (String) -> Unit,
    onTelefonoChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onConfirmPasswordChange: (String) -> Unit,
    onRegisterClick: () -> Unit,
    onLoginClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(48.dp))

        Text(text = "🚗", fontSize = 48.sp)

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = "Crear cuenta",
            color = RutaGreen,
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(24.dp))

        RegisterField(
            label = "Nombres",
            value = nombres,
            onValueChange = onNombresChange,
            placeholder = "Ej: Valentina",
            keyboardType = KeyboardType.Text
        )

        Spacer(modifier = Modifier.height(14.dp))

        RegisterField(
            label = "Apellidos",
            value = apellidos,
            onValueChange = onApellidosChange,
            placeholder = "Ej: Torres",
            keyboardType = KeyboardType.Text
        )

        Spacer(modifier = Modifier.height(14.dp))

        RegisterField(
            label = "Cédula",
            value = cedula,
            onValueChange = onCedulaChange,
            placeholder = "1234567890",
            keyboardType = KeyboardType.Number
        )

        Spacer(modifier = Modifier.height(14.dp))

        RegisterField(
            label = "Fecha de nacimiento",
            value = fechaNacimiento,
            onValueChange = onFechaNacimientoChange,
            placeholder = "DD/MM/AAAA",
            keyboardType = KeyboardType.Number
        )

        Spacer(modifier = Modifier.height(14.dp))

        RegisterField(
            label = "Teléfono",
            value = telefono,
            onValueChange = onTelefonoChange,
            placeholder = "+593 99 999 9999",
            keyboardType = KeyboardType.Phone
        )

        Spacer(modifier = Modifier.height(14.dp))

        RegisterField(
            label = "Correo electrónico",
            value = email,
            onValueChange = onEmailChange,
            placeholder = "ejemplo@correo.com",
            keyboardType = KeyboardType.Email
        )

        Spacer(modifier = Modifier.height(14.dp))

        RegisterField(
            label = "Contraseña",
            value = password,
            onValueChange = onPasswordChange,
            placeholder = "••••••••",
            keyboardType = KeyboardType.Password,
            isPassword = true
        )

        Spacer(modifier = Modifier.height(14.dp))

        RegisterField(
            label = "Confirmar contraseña",
            value = confirmPassword,
            onValueChange = onConfirmPasswordChange,
            placeholder = "••••••••",
            keyboardType = KeyboardType.Password,
            isPassword = true
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Al registrarte confirmas que eres mayor de 18 años.",
            color = RutaTextSecondary,
            fontSize = 12.sp,
            modifier = Modifier.padding(top = 4.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = onRegisterClick,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(containerColor = RutaGreen)
        ) {
            Text(
                text = "Crear cuenta",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = "¿Ya tienes cuenta? ", color = RutaTextSecondary, fontSize = 14.sp)
            Text(
                text = "Inicia sesión",
                color = RutaGreen,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.clickable { onLoginClick() }
            )
        }

        Spacer(modifier = Modifier.height(32.dp))
    }
}

@Composable
private fun RegisterField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    keyboardType: KeyboardType,
    isPassword: Boolean = false
) {
    Text(
        text = label,
        color = MaterialTheme.colorScheme.onBackground,
        fontSize = 14.sp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 6.dp)
    )

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = { Text(placeholder, color = RutaTextSecondary) },
        visualTransformation = if (isPassword) PasswordVisualTransformation() else androidx.compose.ui.text.input.VisualTransformation.None,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        singleLine = true,
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
}

@Preview
@Composable
fun RegisterScreenPreview() {
    RegisterContent(
        nombres = "",
        apellidos = "",
        cedula = "",
        fechaNacimiento = "",
        telefono = "",
        email = "",
        password = "",
        confirmPassword = "",
        onNombresChange = {},
        onApellidosChange = {},
        onCedulaChange = {},
        onFechaNacimientoChange = {},
        onTelefonoChange = {},
        onEmailChange = {},
        onPasswordChange = {},
        onConfirmPasswordChange = {},
        onRegisterClick = {},
        onLoginClick = {}
    )
}
