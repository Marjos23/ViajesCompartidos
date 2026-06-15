
package com.example.vieajescompartidos.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vieajescompartidos.ui.theme.RutaGray
import com.example.vieajescompartidos.ui.theme.RutaGreen
import com.example.vieajescompartidos.ui.theme.RutaTextSecondary

import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.vieajescompartidos.ui.viewmodel.LoginViewModel
import com.example.vieajescompartidos.ui.viewmodel.ViewModelFactory

@Composable
fun LoginScreen(
    onLoginSuccess: () -> Unit,
    onRegisterClick: () -> Unit,
    viewModel: LoginViewModel = viewModel(factory = ViewModelFactory())
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(uiState.isLoginSuccessful) {
        if (uiState.isLoginSuccessful) {
            onLoginSuccess()
        }
    }

    LoginContent(
        email = uiState.email,
        password = uiState.password,
        isLoading = uiState.isLoading,
        errorMessage = uiState.errorMessage,
        onEmailChange = viewModel::onEmailChange,
        onPasswordChange = viewModel::onPasswordChange,
        onLoginClick = viewModel::login,
        onRegisterClick = onRegisterClick
    )
}

@Composable
fun LoginContent(
    email: String,
    password: String,
    isLoading: Boolean,
    errorMessage: String?,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onLoginClick: () -> Unit,
    onRegisterClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "🚗", fontSize = 56.sp)

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "RutaShare",
            color = RutaGreen,
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(40.dp))

        //Campo para el correco electrónico
        Text(
            text = "Correo electrónico",
            color = MaterialTheme.colorScheme.onBackground,
            fontSize = 14.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 6.dp)
        )

       OutlinedTextField(
           value = email,
           onValueChange = onEmailChange,
           placeholder = { Text("ejemplo@correo.com", color = RutaTextSecondary) },
           keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
           singleLine = true,
           enabled = !isLoading,
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

        Spacer(modifier = Modifier.height(16.dp))

        //Campo contraseña

        Text(
            text = "Contraseña",
            color = MaterialTheme.colorScheme.onBackground,
            fontSize = 14.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 6.dp)
        )

        OutlinedTextField(
            value = password,
            onValueChange = onPasswordChange,
            placeholder = { Text("••••••••", color = RutaTextSecondary) },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            singleLine = true,
            enabled = !isLoading,
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

        Spacer(modifier = Modifier.height(8.dp))

        // ¿Olvidaste tu contraseña?
        Text(
            text = "¿Olvidaste tu contraseña?",
            color = RutaGreen,
            fontSize = 13.sp,
            modifier = Modifier
                .align(Alignment.End)
                .clickable { }
        )

        errorMessage?.let {
            Text(
                text = it,
                color = MaterialTheme.colorScheme.error,
                fontSize = 12.sp,
                modifier = Modifier.padding(top = 8.dp)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Botón Iniciar sesión
        Button(
            onClick = onLoginClick,
            enabled = !isLoading,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(containerColor = RutaGreen)
        ) {
            if (isLoading) {
                CircularProgressIndicator(color = Color.White, modifier = Modifier.size(24.dp))
            } else {
                Text(
                    text = "Iniciar sesión",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Divisor
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            HorizontalDivider(modifier = Modifier.weight(1f), color = RutaGray)
            Text(
                text = "  o continúa con  ",
                color = RutaTextSecondary,
                fontSize = 13.sp
            )
            HorizontalDivider(modifier = Modifier.weight(1f), color = RutaGray)
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Link registro
        Row {
            Text(text = "¿No tienes cuenta? ", color = RutaTextSecondary, fontSize = 14.sp)
            Text(
                text = "Regístrate",
                color = RutaGreen,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.clickable { onRegisterClick() }
            )
        }
    }
}

@Preview
@Composable
fun LoginScreenPreview() {
    LoginContent(
        email = "",
        password = "",
        isLoading = false,
        errorMessage = null,
        onEmailChange = {},
        onPasswordChange = {},
        onLoginClick = {},
        onRegisterClick = {}
    )
}