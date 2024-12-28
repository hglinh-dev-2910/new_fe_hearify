package com.example.new_fe_hearify.message

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.new_fe_hearify.R
import com.example.new_fe_hearify.data.ChatMessage
import com.example.new_fe_hearify.data.Conversation
import kotlinx.serialization.Serializable
import java.time.Instant
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter




@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("RestrictedApi")
@Composable
fun MessagesScreen(
    navController: NavHostController,
    messages: List<ChatMessage>, // Now takes a list of ChatMessage
    currentUserId: Int
) {
    // Group messages by senderId
    val groupedMessages = messages.groupBy { it.senderId }

    // Get the latest message for each sender
    val latestMessages = groupedMessages.map { (_, messages) ->
        messages.maxByOrNull { it.timestamp }
    }.filterNotNull()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Messages", fontWeight = FontWeight.Bold) },
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
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            items(latestMessages) { message -> // Use latestMessages here
                ReceiverItem(message.senderId, message.content) {
                    navController.navigate("chattingview/$currentUserId/${message.senderId}")
                }
            }
        }
    }
}


@Composable
fun ReceiverItem(receiverId: Int, lastMessage: String, onReceiverClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onReceiverClick() }
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(R.drawable.ic_launcher_foreground), // Replace with user avatar
            contentDescription = "Avatar",
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Text(text = receiverId.toString(), fontWeight = FontWeight.Bold)
            Text(text = lastMessage, fontSize = 12.sp, color = Color.Gray)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MessagesScreenPreview() {
    val navController = rememberNavController()
    val messages = listOf(
        ChatMessage(1, 123, 1, "Hello", Instant.now().toEpochMilli()),
        ChatMessage(2, 456, 1, "How are you?", Instant.now().toEpochMilli()),
        ChatMessage(3, 789, 1, "What's up?", Instant.now().toEpochMilli()),
        ChatMessage(4, 123, 1, "This is another message from 123", Instant.now().toEpochMilli() + 1000)
    )
    MessagesScreen(navController, messages, 1)
}