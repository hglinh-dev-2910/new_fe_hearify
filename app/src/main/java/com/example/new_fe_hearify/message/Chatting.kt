package com.example.new_fe_hearify.message

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.new_fe_hearify.R
import com.example.new_fe_hearify.data.ChatMessage
import com.example.new_fe_hearify.ktorClient.ktorClient
import io.ktor.client.call.body
import io.ktor.client.plugins.websocket.webSocket
import io.ktor.client.plugins.websocket.webSocketSession
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.parameter
import io.ktor.http.HttpMethod
import io.ktor.websocket.Frame
import io.ktor.websocket.readText
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import io.ktor.websocket.send


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ChattingScreen(
    navController: NavHostController,
    currentUserId: Int,
    receiverId: Int,
    receiverName: String
) {
    val context = LocalContext.current
    var inputText by remember { mutableStateOf("") }
    val messageList = remember { mutableStateListOf<ChatMessage>() }
    val coroutineScope = rememberCoroutineScope()

    // Lấy JWT từ SharedPreferences
    val jwt = getJwtFromSharedPreferences(context)

    LaunchedEffect(key1 = Unit) {
        if (jwt != null) {
            initWebSocketConnection(currentUserId, receiverId, jwt, coroutineScope, messageList)
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        // Top Bar
        ChatTopAppBar(receiverName, navController)

        // Messages
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(16.dp)
        ) {
            items(messageList) { message ->
                MessageCard(message, currentUserId)
            }
        }

        // Input Area
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { /* Handle image attachment */ }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_launcher_foreground),
                    contentDescription = "Attach Image"
                )
            }
            TextField(
                value = inputText,
                onValueChange = { inputText = it },
                placeholder = { Text("Type your message") },
                modifier = Modifier.weight(1f)
            )
            IconButton(onClick = {
                if (inputText.isNotBlank()) {
                    val newMessage = ChatMessage(
                        senderId = currentUserId,
                        receiverId = receiverId,
                        content = inputText,
                        timestamp = System.currentTimeMillis() // Add timestamp to the message
                    )
                    coroutineScope.launch {
                        // Gửi tin nhắn qua WebSocket
                        ktorClient.webSocketSession().send(Json.encodeToString(newMessage))
                        // Add the message to the list after successful send
                        messageList.add(newMessage)
                    }
                    inputText = ""
                }
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_launcher_foreground),
                    contentDescription = "Send Message"
                )
            }
        }
    }
}


// Hàm lấy JWT từ SharedPreferences
private fun getJwtFromSharedPreferences(context: Context): String? {
    val sharedPreferences = context.getSharedPreferences("user_data", Context.MODE_PRIVATE)
    return sharedPreferences.getString("jwt", null)
}

// Hàm khởi tạo kết nối WebSocket và lấy lịch sử tin nhắn
private suspend fun initWebSocketConnection(
    currentUserId: Int,
    receiverId: Int,
    jwt: String,
    coroutineScope: CoroutineScope,
    messageList: MutableList<ChatMessage>
) {
    try {
        ktorClient.webSocket(
            method = HttpMethod.Get,
            host = "127.0.0.1", // Thay thế bằng host của bạn
            port = 8080, // Thay thế bằng port của bạn
            path = "/messages/connect",
            request = {
                header("Authorization", "Bearer $jwt")
                parameter("userId", currentUserId)
            }
        ) {
            coroutineScope.launch {
                fetchChatHistory(currentUserId, receiverId, messageList)
            }

            incoming.consumeEach { frame ->
                if (frame is Frame.Text) {
                    val messageText = frame.readText()
                    Log.d("WebSocket", "Received message: $messageText")

                    // Xử lý tin nhắn nhận được
                    val receivedMessage = Json.decodeFromString<ChatMessage>(messageText)
                    messageList.add(receivedMessage)
                }
            }
        }
    } catch (e: Exception) {
        Log.e("WebSocket", "Error: ${e.message}")
    }
}

// Hàm lấy lịch sử tin nhắn
private suspend fun fetchChatHistory(
    currentUserId: Int,
    receiverId: Int,
    messageList: MutableList<ChatMessage>
) {
    val history = ktorClient.get("http://10.0.2.2:8080/messages/history") { // Thay thế bằng URL của bạn
        parameter("senderId", currentUserId)
        parameter("receiverId", receiverId)
    }.body<List<ChatMessage>>()
    messageList.addAll(history)
}

// Composable cho TopAppBar
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ChatTopAppBar(receiverName: String, navController: NavHostController) {
    TopAppBar(
        title = { Text(receiverName, fontWeight = FontWeight.Bold) },
        navigationIcon = {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_launcher_foreground),
                    contentDescription = "Back"
                )
            }
        }
    )
}


@Composable
fun MessageCard(message: ChatMessage, currentUserId: Int) {
    val isCurrentUser = message.senderId == currentUserId

    // Format timestamp
    val formattedTimestamp = LocalDateTime.ofInstant(
        Instant.ofEpochMilli(message.timestamp),
        ZoneId.systemDefault()
    ).format(DateTimeFormatter.ofPattern("hh:mm a"))

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = if (isCurrentUser) Arrangement.End else Arrangement.Start
    ) {
        if (!isCurrentUser) {
            Image(
                painter = painterResource(R.drawable.ic_launcher_foreground),
                contentDescription = "Avatar",
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.width(8.dp))
        }

        Column {
            Text(
                text = message.content,
                modifier = Modifier
                    .background(
                        color = if (isCurrentUser) colorResource(R.color.container_background) else colorResource(
                            R.color.app_name_color
                        ),
                        shape = RoundedCornerShape(16.dp)
                    )
                    .padding(8.dp)
            )

            Text(
                text = formattedTimestamp,
                fontSize = 12.sp,
                color = Color.Gray
            )
        }
    }
}

//@RequiresApi(Build.VERSION_CODES.O)
//@Preview(showBackground = true)
//@Composable
//fun ChattingScreenPreview() {
//    ChattingScreen(
//        navController = rememberNavController(),
//        currentUserId = 1,
//        receiverId = 2,
//        receiverName = "John Doe"
//    )
//}