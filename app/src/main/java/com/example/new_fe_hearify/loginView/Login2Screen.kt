package com.example.new_fe_hearify

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.new_fe_hearify.ui.theme.New_fe_hearifyTheme

@Composable
fun Login2Screen(modifier: Modifier = Modifier, navController: NavHostController) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(colorResource(R.color.login_background))
            .padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Logo(modifier = Modifier.size(100.dp))
        Greeting(
            input = "Welcome Back!",
            style = TextStyle(fontSize = 24.sp),
            modifier = Modifier.padding(16.dp)
        )
        InputField(
            label = "Email",
            placeholder = "Enter your email"
        )
        Spacer(modifier = Modifier.height(8.dp))
        InputField(
            label = "Password",
            placeholder = "Enter your password",
            isPassword = true
        )
        LoginButton(
            text = "Login",
            modifier = Modifier.padding(16.dp)
        ) {
            // Handle login logic here
        }
        Spacer(modifier = Modifier.height(16.dp))

        // Signup Button
        Button(
            onClick = { navController.navigate("registerview") }, // Navigate to RegisterView
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFCDD2))
        ) {
            Text("Sign Up", color = Color.Red)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Forgot Password Button
        TextButton(onClick = { /* Handle forgot password logic */ }) {
            Text("Forgot?", color = Color.Red)
        }
    }
}

@Composable
fun Logo(modifier: Modifier) {
    Image(
        modifier = modifier,
        painter = painterResource(id = R.drawable.logo), // Replace with your actual logo
        contentDescription = null,
        contentScale = ContentScale.Fit
    )
}

@Composable
fun Greeting(input: String, style: TextStyle, modifier: Modifier) {
    Text(
        text = input, modifier = modifier, style = style
    )
}

@Composable
fun LoginButton(text: String, modifier: Modifier = Modifier, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = modifier
    ) {
        Text(text)
    }
}

@Composable
fun InputField(
    label: String, placeholder: String, isPassword: Boolean = false, modifier: Modifier = Modifier
) {
    var text by remember { mutableStateOf("") }
    TextField(
        value = text,
        onValueChange = { text = it },
        label = { Text(label) },
        placeholder = { Text(placeholder) },
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        singleLine = true,
        colors = TextFieldDefaults.colors(
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            errorIndicatorColor = Color.Red,
            focusedLeadingIconColor = colorResource(id = R.color.app_name_color), // Replace with your actual color resource
            focusedTrailingIconColor = colorResource(id = R.color.app_name_color), // Replace with your actual color resource
            unfocusedLabelColor = Color.White,
            focusedLabelColor = colorResource(id = R.color.app_name_color), // Replace with your actual color resource
            cursorColor = colorResource(id = R.color.app_name_color), // Replace with your actual color resource
            unfocusedContainerColor = colorResource(id = R.color.unfocused_container_background), // Replace with your actual color resource
            focusedContainerColor = colorResource(id = R.color.container_background) // Replace with your actual color resource
        ),
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = if (isPassword) ImeAction.Done else ImeAction.Next
        ),
        keyboardActions = KeyboardActions.Default
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LoginLayoutPreview() {
    New_fe_hearifyTheme {
        Login2Screen(Modifier, rememberNavController())
    }
}