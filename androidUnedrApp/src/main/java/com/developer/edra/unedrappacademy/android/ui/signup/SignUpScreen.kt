package com.developer.edra.unedrappacademy.android.ui.signup

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.ArrowDropDown
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.developer.edra.unedrappacademy.android.R
import com.developer.edra.unedrappacademy.android.ui.navigation.NavScreen

@Composable
fun SignUpScreen(navController: NavController) {
    val name = remember { mutableStateOf("") }
    val campus = remember { mutableStateOf("") }
    val career = remember { mutableStateOf("") }
    val enrollment = remember { mutableStateOf("") }
    val phone = remember { mutableStateOf("") }
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val confirmPassword = remember { mutableStateOf("") }

    val textFieldModifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 8.dp)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        LazyColumn(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Image(
                    painter = painterResource(id = R.drawable.logo_white_background),
                    contentDescription = "Logo",
                    modifier = Modifier.size(100.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Registro Estudiantil",
                    style = TextStyle(
                        color = Color(0xFFE45351),
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))
            }

            item {
                OutlinedTextField(
                    value = name.value,
                    onValueChange = { name.value = it },
                    textStyle = TextStyle(fontSize = 18.sp),
                    label = { Text("Nombre Completo") },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Outlined.Person,
                            contentDescription = "Icon Person"
                        )
                    },
                    modifier = textFieldModifier
                )
            }

            item {
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
                    modifier = textFieldModifier
                )
            }

            item {
                OutlinedTextField(
                    value = password.value,
                    onValueChange = { password.value = it },
                    textStyle = TextStyle(fontSize = 18.sp),
                    label = { Text("Contraseña") },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Outlined.Lock,
                            contentDescription = "Icon Password"
                        )
                    },
                    modifier = textFieldModifier,
                    visualTransformation = PasswordVisualTransformation()
                )
            }

            item {
                OutlinedTextField(
                    value = confirmPassword.value,
                    onValueChange = { confirmPassword.value = it },
                    textStyle = TextStyle(fontSize = 18.sp),
                    label = { Text("Confirmar Contraseña") },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Outlined.Lock,
                            contentDescription = "Icon Confirm Password"
                        )
                    },
                    modifier = textFieldModifier,
                    visualTransformation = PasswordVisualTransformation()
                )
            }

            item {
                OutlinedTextField(
                    value = enrollment.value,
                    onValueChange = { enrollment.value = it },
                    textStyle = TextStyle(fontSize = 18.sp),
                    label = { Text("Matrícula") },
                    leadingIcon = {
                        Icon(
                            painter =painterResource( R.drawable.confirmation_number),
                            contentDescription = "Icon Enrollment"
                        )
                    },
                    modifier = textFieldModifier
                )
            }

            item {
                OutlinedTextField(
                    value = phone.value,
                    onValueChange = { phone.value = it },
                    textStyle = TextStyle(fontSize = 18.sp),
                    label = { Text("Teléfono") },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Outlined.Phone,
                            contentDescription = "Icon Phone"
                        )
                    },
                    modifier = textFieldModifier
                )
            }

            item {
                OutlinedTextField(
                    value = campus.value,
                    onValueChange = { campus.value = it },
                    textStyle = TextStyle(fontSize = 18.sp),
                    label = { Text("Recinto") },
                    leadingIcon = {
                        Icon(
                            painter = painterResource(R.drawable.location_apartament ),
                            contentDescription = "Icon Campus"
                        )
                    },
                    modifier = textFieldModifier
                )
            }

            item {
                var expanded by remember { mutableStateOf(false) }
                val careers =
                    listOf("Ingeniería de Software", "Medicina", "Derecho", "Arquitectura")

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                        .clickable { expanded = !expanded }
                ) {
                    OutlinedTextField(
                        value = career.value,
                        onValueChange = { career.value = it },
                        textStyle = TextStyle(fontSize = 18.sp),
                        label = { Text("Carrera") },
                        trailingIcon = {
                            Icon(
                                imageVector = Icons.Outlined.ArrowDropDown,
                                contentDescription = "Dropdown Icon",
                                modifier = Modifier.clickable { expanded = !expanded }
                            )
                        },
                        modifier = Modifier.fillMaxWidth(),
                        readOnly = true
                    )

                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        careers.forEach { careerOption ->
                            DropdownMenuItem(
                                text = { Text(text = careerOption) },
                                onClick = {
                                    career.value = careerOption
                                    expanded = false
                                }
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))
            }

            item {
                Button(
                    onClick = { navController.navigate(NavScreen.DashboardScreen.name) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    shape = RoundedCornerShape(24.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFE45351)
                    )
                ) {
                    Text("Registrarme", color = Color.White)
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Login Text
                Row {
                    Text(text = "¿Ya tengo una cuenta?")
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "Iniciar Sesión",
                        color = Color(0xFFE45351),
                        modifier = Modifier.clickable { navController.navigate(NavScreen.LoginScreen.name) }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SignUpScreenPreview() {
    SignUpScreen(rememberNavController())
}