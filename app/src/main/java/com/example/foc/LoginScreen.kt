package com.example.foc

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.*
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*

@Preview(showBackground = true)
@Composable
fun LoginScreen(modifier: Modifier = Modifier) {
    val scrollState = rememberScrollState()
    val loginId = remember {
        mutableStateOf("")
    }
    val password = remember {
        mutableStateOf("")
    }
    val passwordVisible = remember { mutableStateOf(false) }
    val context = LocalContext.current
    var invalidLoginId by rememberSaveable {
        mutableStateOf(false)
    }
    var invalidPassword by rememberSaveable {
        mutableStateOf(false)
    }
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {
        Image(
            painter = painterResource(id = R.drawable.foc),
            contentDescription = "FOC Image",
            modifier = Modifier
                .width(350.dp)
                .padding(bottom = 40.dp),
            contentScale = ContentScale.FillWidth
        )
        Text(
            "Login",
            modifier = Modifier.padding(bottom = 40.dp),
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold
        )
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(bottom = 40.dp)
        ) {

            OutlinedTextField(
                value = loginId.value.trim(),
                onValueChange = {
                    loginId.value = it.trim()
                    invalidLoginId = false
                },
                modifier = Modifier
                    .width(350.dp),
                singleLine = true,
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
                label = { Text(text = "Login ID", fontSize = 15.sp) },
                isError = invalidLoginId,
                supportingText = {
                    if (invalidLoginId) {
                        Text("Invalid Login ID")
                    }
                }

            )
        }
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(bottom = 40.dp)
        ) {

            OutlinedTextField(
                value = password.value.trim(),
                onValueChange = {
                    password.value = it.trim()
                    invalidPassword = false
                },
                modifier = Modifier
                    .width(350.dp),
                singleLine = true,
                visualTransformation = if (passwordVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                trailingIcon = {
                    val image = if (passwordVisible.value)
                        Icons.Filled.Visibility
                    else Icons.Filled.VisibilityOff
                    val description =
                        if (passwordVisible.value) "Hide password" else "Show password"

                    IconButton(onClick = { passwordVisible.value = !passwordVisible.value }) {
                        Icon(imageVector = image, description)
                    }
                },
                label = { Text(text = "Password", fontSize = 15.sp) },
                isError = invalidPassword,
                supportingText = {
                    if (invalidPassword) {
                        Text("Invalid Password")
                    }
                }

            )
        }
        Text(
            text = "Forgot Password",
            fontSize = 16.sp,
            modifier = Modifier
                .padding(bottom = 40.dp)
                .clickable(
                    true,
                    onClick = {
                        Toast
                            .makeText(context, "Forgot Password", Toast.LENGTH_SHORT)
                            .show()
                    }, role = Role.Button
                ),
            color = Color.Blue,
            textDecoration = TextDecoration.Underline
        )
        Button(
            onClick = {
                invalidLoginId = loginId.value.trim().isEmpty()
                invalidPassword = password.value.trim().isEmpty()
                if (!invalidLoginId && !invalidPassword) {
                    login(context)
                }
            },
            modifier = Modifier.size(150.dp, 50.dp),
            /* enabled = !(loginId.value.trim().isEmpty() || password.value.trim().isEmpty()) */
        ) {
            Text(text = "Login", fontSize = 20.sp)
        }
    }
}


fun login(context: Context) {
    Toast.makeText(context, "Login Successful", Toast.LENGTH_SHORT).show()
}