package com.example.foc

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview(showBackground = true)
@Composable
fun LoginScreen(){
    val loginId = remember {
        mutableStateOf("")
    }
    val password = remember {
        mutableStateOf("")
    }
    val passwordVisible = remember { mutableStateOf(false) }

    val context= LocalContext.current
    Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize()){
        Image(painter = painterResource(id = R.drawable.foc), contentDescription ="FOC Image", modifier = Modifier
            .width(350.dp)
            .padding(bottom = 40.dp),contentScale = ContentScale.FillWidth)
        Text("Login", modifier = Modifier.padding(bottom = 40.dp), fontSize = 25.sp, fontWeight = FontWeight.Bold)
        Row(horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically,modifier = Modifier.padding(bottom = 40.dp)){
            Text("Login ID", fontSize = 18.sp, fontWeight = FontWeight.SemiBold, modifier = Modifier.width(100.dp), textAlign = TextAlign.Center)
            TextField(value = loginId.value.trim(), onValueChange = {loginId.value=it.trim()}, modifier = Modifier
                .width(250.dp)
                .border(
                    1.dp, Color.Black,
                    RoundedCornerShape(5.dp)
                ),  shape = RoundedCornerShape(5.dp),singleLine = true,                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                disabledContainerColor = Color.Transparent,
            )
            )
        }
        Row(horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(bottom = 40.dp)){
            Text("Password",fontSize = 18.sp, fontWeight = FontWeight.SemiBold,modifier = Modifier.width(100.dp), textAlign = TextAlign.Center)
            TextField(
                value = password.value.trim(),
                onValueChange = { password.value = it.trim() },
                modifier = Modifier
                    .width(250.dp)
                    .border(
                        1.dp, Color.Black,
                        RoundedCornerShape(5.dp)
                    ),
                shape = RoundedCornerShape(5.dp),
                singleLine = true,
                visualTransformation = if (passwordVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    disabledContainerColor = Color.Transparent,
                ),
                trailingIcon = {
                    val image = if (passwordVisible.value)
                        Icons.Filled.Visibility
                    else Icons.Filled.VisibilityOff

                    // Please provide localized description for accessibility services
                    val description = if (passwordVisible.value) "Hide password" else "Show password"

                    IconButton(onClick = {passwordVisible.value = !passwordVisible.value}){
                        Icon(imageVector  = image, description)
                    }
                }
            )
        }
        Button(onClick = { Toast.makeText(context,"Login Successful",Toast.LENGTH_SHORT).show()}, modifier = Modifier.size(150.dp,50.dp), enabled = !(loginId.value.trim().isEmpty() || password.value.trim().isEmpty())) {
            Text(text = "Login", fontSize = 20.sp)
        }
    }
}