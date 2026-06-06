
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vieajescompartidos.ui.theme.RutaCard
import com.example.vieajescompartidos.ui.theme.RutaDark
import com.example.vieajescompartidos.ui.theme.RutaGray
import com.example.vieajescompartidos.ui.theme.RutaGreen
import com.example.vieajescompartidos.ui.theme.RutaTextSecondary

@Composable
fun LoginScreen(
    onLoginClick: () -> Unit,
    onRegisterClick: () -> Unit
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(RutaDark)
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
            color = Color.White,
            fontSize = 14.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 6.dp)
        )

       OutlinedTextField(
           value = email,
           onValueChange = { email = it },
           placeholder = { Text("ejemplo@correo.com", color = RutaTextSecondary) },
           keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
           singleLine = true,
           colors = OutlinedTextFieldDefaults.colors(
               focusedBorderColor = RutaGreen,
               unfocusedBorderColor = RutaGray,
               focusedTextColor = Color.White,
               unfocusedTextColor = Color.White,
               cursorColor = RutaGreen,
               focusedContainerColor = RutaCard,
               unfocusedContainerColor = RutaCard
           ),
           shape = RoundedCornerShape(10.dp),
           modifier = Modifier.fillMaxWidth()
       )

        Spacer(modifier = Modifier.height(16.dp))

        //Campo contraseña

        Text(
            text = "Contraseña",
            color = Color.White,
            fontSize = 14.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 6.dp)
        )

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            placeholder = { Text("••••••••", color = RutaTextSecondary) },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = RutaGreen,
                unfocusedBorderColor = RutaGray,
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                cursorColor = RutaGreen,
                focusedContainerColor = RutaCard,
                unfocusedContainerColor = RutaCard
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

        Spacer(modifier = Modifier.height(24.dp))

        // Botón Iniciar sesión
        Button(
            onClick = onLoginClick,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(containerColor = RutaGreen)
        ) {
            Text(
                text = "Iniciar sesión",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Divisor
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Divider(modifier = Modifier.weight(1f), color = RutaGray)
            Text(
                text = "  o continúa con  ",
                color = RutaTextSecondary,
                fontSize = 13.sp
            )
            Divider(modifier = Modifier.weight(1f), color = RutaGray)
        }

        Spacer(modifier = Modifier.height(20.dp))

        //------
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
    LoginScreen(onLoginClick = {}, onRegisterClick = {})
}