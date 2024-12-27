package com.example.new_fe_hearify.message

import com.example.new_fe_hearify.message.Messages
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
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter






@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MessagesScreen(navController: NavHostController, messages: List<Messages>, currentUser: String) {
    val receiversWithLatestMessage = remember { mutableStateMapOf<String, Messages>() }
    messages.forEach { message ->
        val otherUser = if (message.senderId == currentUser) message.receiverId else message.senderId
        receiversWithLatestMessage[otherUser] = message
    }

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
            items(receiversWithLatestMessage.toList()) { (receiver, latestMessage) ->
                ReceiverItem(receiver, latestMessage.message) {
                    // Navigate to MessagesScreen with the selected receiver
                    navController.navigate("chattingview/$currentUser/$receiver")
                }
            }
        }
    }
}

@Composable
fun ReceiverItem(receiver: String, lastMessage: String, onReceiverClick: () -> Unit) {
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
            Text(text = receiver, fontWeight = FontWeight.Bold)
            Text(text = lastMessage, fontSize = 12.sp, color = Color.Gray)
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun MessageListScreenPreview() {
    MessagesScreen(
        navController = rememberNavController(),
        messages = listOf(
            Messages("user1", "user2", "Hello", "10:00 AM"),
            Messages("user2", "user1", "Hi", "10:01 AM"),
            Messages("user3", "user2", "Hey", "11:00 AM"),
            Messages("user1", "user2", "How are you?", "11:30 AM") // Latest message from user1
        ),
        currentUser = "user2"
    )
}