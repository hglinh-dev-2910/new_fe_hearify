package com.example.new_fe_hearify.loginView

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
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
import com.example.new_fe_hearify.R
import com.example.new_fe_hearify.data.ChatMessage
import com.example.new_fe_hearify.data.LoginRequest
import com.example.new_fe_hearify.ktorClient.ktorClient
import com.example.new_fe_hearify.ui.theme.New_fe_hearifyTheme
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType
import kotlinx.serialization.json.Json

import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable



@Serializable
data class LoginResponse(
    val token: String,
    val userId: Int,
    val message: String
)

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

        //email, password for login
        var username by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }


        InputField(
            label = "Username",
            placeholder = "Enter your username",
            onValueChange = {username = it}
        )
        Spacer(modifier = Modifier.height(8.dp))
        InputField(
            label = "Password",
            placeholder = "Enter your password",
            isPassword = true,
            onValueChange = {password = it}
        )
        val coroutineScope = rememberCoroutineScope()
        LoginButton(
            text = "Login",
            modifier = Modifier.padding(16.dp),
            onClick = {
                coroutineScope.launch {
                    // Kiểm tra giá trị email và password
                    if (username.isBlank() || password.isBlank()) {
                        println("Email and password cannot be empty!")
                        return@launch
                    }

                    // request
                    val request = LoginRequest("traditional", username, password)

                    println(request)
                    try {
                        // response
                        val response: HttpResponse = ktorClient.post("http://10.0.2.2:8080/login") {
                            contentType(ContentType.Application.Json)
                            setBody(request)
                        }.body()

                        if (response.status == HttpStatusCode.OK) {
                            val loginResponse: LoginResponse = response.body()
                            val token = loginResponse.token
                            val userId = loginResponse.userId

                            println("Token : $token")
                            println("userId : $userId")
                        }



                        navController.navigate("dashboard") // nav to dashboard
                    } catch (e: Exception) {
                        println("Login failed: ${e.message}")
                    }
                }
            }

        )
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
    label: String,
    placeholder: String,
    isPassword: Boolean = false,
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit
) {
    var text by remember { mutableStateOf("") }
    TextField(
        value = text,
        onValueChange = {
            text = it
            onValueChange(it) // callback to call value
        },
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