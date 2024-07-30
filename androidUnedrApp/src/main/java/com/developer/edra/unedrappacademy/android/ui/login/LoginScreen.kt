package com.developer.edra.unedrappacademy.android.ui.login

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.developer.edra.unedrappacademy.android.R
import com.developer.edra.unedrappacademy.android.ui.components.IndeterminateCircularIndicator
import com.developer.edra.unedrappacademy.android.ui.main.MainViewModel
import com.developer.edra.unedrappacademy.android.ui.models.ValidationResultField
import com.developer.edra.unedrappacademy.android.ui.navigation.NavScreen

@Composable
fun LoginScreen(
    navController: NavController,
    loginViewModel: LoginViewModel,
    mainViewModel: MainViewModel
) {
    val context = LocalContext.current
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    var loading by remember { mutableStateOf(false) }

    LoginScreen(
        email = email,
        password = password,
        loading = loading,
        onLoginClick = {
            val result = validateSignUpFields(
                email.value,
                password.value
            )

            if (result.isValid) {
                loading = true
                loginViewModel.email = email.value
                loginViewModel.password = password.value
                loginViewModel.login { success ->
                    loading = false
                    if (success) {
                        mainViewModel.getCurrentUser()
                        if (mainViewModel.userLogged.value.email == email.value) {
                            navController.navigate(NavScreen.DashboardScreen.name)
                        }
                    } else {
                        Toast.makeText(
                            context,
                            context.getString(R.string.credtial_invalid),
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            } else {
                Toast.makeText(context, result.message, Toast.LENGTH_LONG).show()

            }
        },
        onRegisterClick = {
            navController.navigate(NavScreen.SignUpScreen.name)
        }
    )

}


@Composable
fun LoginScreen(
    email: MutableState<String>,
    password: MutableState<String>,
    loading: Boolean,
    onRegisterClick: () -> Unit,
    onLoginClick: () -> Unit,
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo_white_background),
                contentDescription = "Logo",
                modifier = Modifier.size(100.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Inicio de Sesión",
                style = TextStyle(
                    color = Color(0xFFE45351),
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
            )

            Spacer(modifier = Modifier.height(56.dp))

            // Email TextField
            OutlinedTextField(
                value = email.value,
                onValueChange = { email.value = it },
                textStyle = TextStyle(fontSize = 18.sp),
                label = { Text("Correo Electrónico") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.Email,
                        contentDescription = "Icon Email"
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Password TextField
            OutlinedTextField(
                value = password.value,
                onValueChange = { password.value = it },
                textStyle = TextStyle(fontSize = 18.sp),
                label = { Text("Contraseña") },
                visualTransformation = PasswordVisualTransformation(),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.Lock,
                        contentDescription = "Icon Lock"
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = onLoginClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                shape = RoundedCornerShape(24.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFE45351)
                ),
                enabled = !loading
            ) {
                Text("Iniciar Sesión", color = Color.White)
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row {
                Text(text = "No tengo una cuenta?")
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "Registrarme",
                    color = Color.Red,
                    modifier = Modifier.clickable { onRegisterClick() }
                )
            }
        }
        IndeterminateCircularIndicator(loading = loading)
    }

}


fun validateSignUpFields(
    email: String,
    password: String,
): ValidationResultField {

    if (email.isEmpty() || password.isEmpty()) {
        return ValidationResultField(false, "Todos los campos deben estar llenos.")
    }

    if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
        return ValidationResultField(false, "La dirección de correo electrónico debe ser válida.")
    }

    if (password.length < 8) {
        return ValidationResultField(false, "La contraseña debe tener al menos 8 caracteres.")
    }

    return ValidationResultField(true, "Validación exitosa.")
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen(rememberNavController(), viewModel(), viewModel())
}