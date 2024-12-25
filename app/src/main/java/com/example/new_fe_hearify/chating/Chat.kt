package com.example.new_fe_hearify.chating

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
//import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.graphics.Offset
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.new_fe_hearify.R
import com.example.new_fe_hearify.ui.theme.New_fe_hearifyTheme
import kotlin.math.roundToInt


@Composable
fun ChatScreen() {
    var messageText by remember { mutableStateOf("") }

    // Dữ liệu giả lập cho tin nhắn
    val messages = remember { mutableStateListOf<Message>(
        Message("Hiiii ~ tớ 2k4, mình làm quen nhaeee", false, "07:25 PM"),
        Message("Chào cậu, tớ cũng 2k4 <3", true, "07:25 PM"),
        Message("Cuối tuần này cậu đi xem phim không?? Đợi bạn học yêu", false, "07:25 PM"),
        Message("Được chứ", true, "07:25 PM")
    )}

    // Gradient background
    Box(modifier = Modifier
        .fillMaxSize()
        .background(
            brush = Brush.linearGradient(
                colors = listOf(Color(0xFFFFC0CB), Color(0xFFFFB6C1)),
                start = Offset.Zero,
                end = Offset.Infinite
            )
        )
    ) {

        Column(modifier = Modifier.fillMaxSize()) {
            // Header (sử dụng Column, Box, Row)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            painter = painterResource(id = R.drawable.anhtuyet), // Thay thế bằng ảnh thật
                            contentDescription = "Avatar",
                            modifier = Modifier
                                .size(48.dp)
                                .clip(CircleShape)
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Column {
                            Text(text = "Ánh Tuyết", fontWeight = FontWeight.Bold)
                            Text(text = "Online", fontSize = 12.sp, color = Color.Green)
                        }
                    }
                    Row {
                        IconButton(onClick = { /* Xử lý cuộc gọi */ }) {
                            Icon(imageVector = Icons.Filled.Call, contentDescription = "Call")
                        }
                        IconButton(onClick = { /* Xử lý tùy chọn */ }) {
                            Icon(imageVector = Icons.Filled.MoreVert, contentDescription = "Options")
                        }
                    }
                }
            }

            // Chat area
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                reverseLayout = true
            ) {
                items(messages.reversed()) { message ->
                    MessageItem(message)
                }
            }

            // Input area
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { /* Xử lý đính kèm */ }) {
                    Icon(imageVector = Icons.Filled.FavoriteBorder, contentDescription = "Attach")
                }

                TextField(
                    value = messageText,
                    onValueChange = { messageText = it },
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 8.dp),
                    placeholder = { Text("Nhập tin nhắn của bạn") },
                    shape = RoundedCornerShape(20.dp), // Bo tròn góc TextField
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.White // Màu nền trắng cho TextField
                    )
                )

                IconButton(
                    onClick = {
                        if (messageText.isNotBlank()) {
                            messages.add(Message(messageText, true, "07:25 PM"))
                            messageText = ""
                        }
                    },
                    modifier = Modifier.then(Modifier.size(32.dp))
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.Send,
                        contentDescription = "Send",
                        tint = Color.Red
                    )
                }
            }
        }
    }
}

@Composable
fun MessageItem(message: Message) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp)
    ) {
        if (message.isSent) {
            Box(
                modifier = Modifier
                    .align(Alignment.End)
                    .background(Color.Red, RoundedCornerShape(16.dp))
                    .padding(8.dp)
            ) {
                Text(text = message.content, color = Color.White)
            }
        } else {
            Box(
                modifier = Modifier
                    .align(Alignment.Start)
                    .background(Color.White, RoundedCornerShape(16.dp))
                    .padding(8.dp)
            ) {
                Text(text = message.content, color = Color.Black)
            }
        }
        Text(
            text = message.timestamp,
            fontSize = 10.sp,
            modifier = Modifier.align(if (message.isSent) Alignment.End else Alignment.Start)
        )
    }
}

data class Message(val content: String, val isSent: Boolean, val timestamp: String)
@Preview(showSystemUi = true, showBackground = true)
@Composable
fun Preview1(){
    New_fe_hearifyTheme {
        ChatScreen()
    }
}