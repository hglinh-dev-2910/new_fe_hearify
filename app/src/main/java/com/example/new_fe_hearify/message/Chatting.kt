package com.example.new_fe_hearify.message

import android.annotation.SuppressLint
import android.os.Build
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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.new_fe_hearify.R
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

data class Messages(
    val senderId: String,
    val receiverId: String,
    val message: String,
    val timestamp: String,
)

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ChattingScreen(messages: List<Messages>, currentUser: String, receiver: String) {
    var inputText by remember { mutableStateOf("") }
    var messageList by remember { mutableStateOf(messages) }

    Column(modifier = Modifier.fillMaxSize()) {
        // Top Bar
        TopAppBar(
            title = { Text(receiver, fontWeight = FontWeight.Bold) },
            actions = {


            },
            navigationIcon = {
                IconButton(onClick = { /* Handle back navigation */ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_launcher_foreground), // Replace with your back arrow icon
                        contentDescription = "Back"
                    )
                }
            }
        )

        // Messages
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(16.dp)
        ) {
            items(messageList) { message ->
                MessageCard(message, currentUser)
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
                    painter = painterResource(id = R.drawable.ic_launcher_foreground), // Replace with your attach image icon
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
                    val newMessage = Messages(
                        senderId = currentUser,
                        receiverId = receiver,
                        message = inputText,
                        timestamp = LocalDateTime.now()
                            .format(DateTimeFormatter.ofPattern("hh:mm a"))
                    )
                    messageList = messageList + newMessage
                    inputText = ""
                }
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_launcher_foreground), // Replace with your send message icon
                    contentDescription = "Send Message"
                )
            }
        }
    }
}

@Composable
fun MessageCard(message: Messages, currentUser: String) {
    val isCurrentUser = message.senderId == currentUser

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = if (isCurrentUser) Arrangement.End else Arrangement.Start
    ) {
        if (!isCurrentUser) {
            Image(
                painter = painterResource(R.drawable.ic_launcher_foreground), // Replace with your user avatar
                contentDescription = "Avatar",
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.width(8.dp))
        }

        Column {
            Text(
                text = message.message,
                modifier = Modifier
                    .background(
                        color = if (isCurrentUser) colorResource(R.color.container_background) else colorResource(R.color.app_name_color),
                        shape = RoundedCornerShape(16.dp)
                    )
                    .padding(8.dp)
            )

            Text(
                text = message.timestamp,
                fontSize = 12.sp,
                color = Color.Gray
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun MessageScreenPreview() {
    ChattingScreen(
        messages = listOf(),
        currentUser = "user2",
        receiver = "user1"
    )
}

