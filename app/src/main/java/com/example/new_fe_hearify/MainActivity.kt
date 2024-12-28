package com.example.new_fe_hearify


//import com.example.new_fe_hearify.message.MessageListScreen
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.new_fe_hearify.dashboard.DashboardScreen
import com.example.new_fe_hearify.data.Conversation
import com.example.new_fe_hearify.introView.Intro1Screen
import com.example.new_fe_hearify.introView.Intro34Screen
import com.example.new_fe_hearify.ktorClient.ktorClient
import com.example.new_fe_hearify.loginView.Login2Screen
import com.example.new_fe_hearify.message.ChattingScreen
import com.example.new_fe_hearify.message.MessagesScreen
import com.example.new_fe_hearify.registerView.RegistrationScreen
import com.example.new_fe_hearify.ui.theme.New_fe_hearifyTheme
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.header

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            New_fe_hearifyTheme {
                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = "introview1") {
                    composable("introview1") {
                        Intro1Screen(Modifier, navController)
                    }
                    composable("introview34") {
                        Intro34Screen(Modifier, navController)
                    }
                    composable("login1view") {
                        Login1Screen(Modifier, navController)
                    }
                    composable("login2view") {
                        Login2Screen(Modifier, navController)
                    }
                    composable("registerview") {
                        RegistrationScreen(Modifier, navController)
                    }
                    composable("dashboard") {
                        DashboardScreen()
                    }
                    // Add this composable route for the MessageListScreen
                    composable("messagelistview/{currentUserId}") { backStackEntry ->
                        val currentUserId = backStackEntry.arguments?.getString("currentUserId")?.toIntOrNull() ?: -1

                        var messages by remember { mutableStateOf<List<Conversation>>(emptyList()) }

                        // Lấy JWT từ SharedPreferences (thay vì hardcode)
                        val context = LocalContext.current
                        val sharedPreferences = context.getSharedPreferences("user_data", Context.MODE_PRIVATE)
                        val jwt = sharedPreferences.getString("jwt", null) // Lấy JWT từ SharedPreferences

                        LaunchedEffect(key1 = currentUserId) {
                            try {
                                messages = ktorClient.get("http://10.0.2.2:8080/messages/conversations") {
                                    header("Authorization", "Bearer $jwt") // Sử dụng JWT từ SharedPreferences
                                }.body()
                            } catch (e: Exception) {
                                Log.e("MessagesScreen", "Error fetching messages: ${e.message}")
                            }
                        }

                        MessagesScreen(navController, messages, currentUserId)
                    }
                    composable("chattingview/{currentUserId}/{receiverId}") { backStackEntry ->
                        val currentUserId = backStackEntry.arguments?.getString("currentUserId")?.toIntOrNull() ?: -1
                        val receiverId = backStackEntry.arguments?.getString("receiverId")?.toIntOrNull() ?: -1

                        val receiverName = "test" // hardcode

                        ChattingScreen(navController, currentUserId, receiverId, receiverName)
                    }
                }
            }
        }
    }
}


