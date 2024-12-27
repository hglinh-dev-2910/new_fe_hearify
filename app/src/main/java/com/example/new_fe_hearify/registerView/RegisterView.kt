package com.example.new_fe_hearify.registerView

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.new_fe_hearify.R
import com.example.new_fe_hearify.viewModel.AuthViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistrationScreen(
    viewModel: AuthViewModel = viewModel(),
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    var email by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val registrationResult by viewModel.registrationResult.observeAsState(null)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = colorResource(R.color.login_background), shape = RectangleShape
            )
            .border(width = 5.dp, color = Color(0xFF6200EE))
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = null,
                contentScale = ContentScale.Fit
            )
            Spacer(modifier = Modifier.width(8.dp))
            Box(
                Modifier
                    .background(colorResource(R.color.white))
                    .padding(8.dp)
            ) {
                Text(
                    text = "Thông tin",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = colorResource(id = R.color.black),
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        CustomTextField(
            placeholder = "Email",
            value = email,
            onValueChange = { email = it },
            keyboardType = KeyboardType.Email
        )

        Spacer(modifier = Modifier.height(8.dp))

        CustomTextField(
            placeholder = "Username",
            value = username,
            onValueChange = { username = it }
        )

        Spacer(modifier = Modifier.height(8.dp))

        CustomTextField(
            placeholder = "Password",
            value = password,
            onValueChange = { password = it },
            keyboardType = KeyboardType.Password
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                // TODO: Handle registration using viewModel
                // with email, username, and password
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(id = R.color.white)
            )
        ) {
            Text("Đăng ký", color = Black)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTextField(
    placeholder: String,
    value: String,
    onValueChange: (String) -> Unit,
    keyboardType: KeyboardType = KeyboardType.Text
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(placeholder) },
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = colorResource(id = R.color.field_white),
            focusedContainerColor = colorResource(id = R.color.container_background)
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
            imeAction = ImeAction.Next
        ),
        keyboardActions = KeyboardActions(
            onNext = { /* TODO: Focus on the next field */ }
        )
    )
}

@Preview(showBackground = true)
@Composable
fun RegistrationScreenPreview() {
    RegistrationScreen(
        viewModel = AuthViewModel(),
        modifier = Modifier,
        navController = rememberNavController()
    )
}