package com.example.new_fe_hearify.message

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

data class Message(
    val sender: String,
    val text: String,
    val time: LocalDateTime = LocalDateTime.now(), // Thời gian mặc định là hiện tại
    val isUser: Boolean = false // Mặc định là tin nhắn của người khác
)

@Composable
fun MessageScreen() {
    val messages = listOf(
        Message("Như", "Anh tìm gì trên này? :)"),
        Message("Kim", "Dung"),
        Message("Cậu", "sinh năm bao nhiuuuu"),
        Message("The", "hóa ra người đẹp cũng biết buồn", time = LocalDateTime.now().minusDays(1)), // Tin nhắn cũ hơn 1 ngày
        Message("Tôi", "Chào bạn", isUser = true) // Tin nhắn của người dùng hiện tại
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        reverseLayout = true // Đảo ngược danh sách để tin nhắn mới nhất ở dưới cùng
    ) {
        items(messages) { message ->
            MessageItem(message)
        }
    }
}

@Composable
fun MessageItem(message: Message) {
    val formatter = DateTimeFormatter.ofPattern("HH:mm") // Định dạng thời gian
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalAlignment = if (message.isUser) Alignment.End else Alignment.Start
    ) {
        // Hiển thị tên người gửi nếu là tin nhắn đầu tiên trong nhóm
        if (message.isUser) {
            Text(
                text = message.sender,
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp,
                color = Color.Gray
            )
        }

        Row(
            verticalAlignment = Alignment.Bottom
        ) {
            if (!message.isUser) { // Chỉ hiển thị ảnh đại diện nếu không phải tin nhắn của người dùng
                Image(
                    painter = painterResource(id = R.drawable.avatar), // Thay thế bằng ảnh đại diện thực tế
                    contentDescription = "avatar",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                )
                Spacer(modifier = Modifier.width(8.dp))
            }

            // Khung tin nhắn
            Box(
                modifier = Modifier
                    .background(
                        color = if (message.isUser) Color.Green else Color.LightGray,
                        shape = RoundedCornerShape(16.dp)
                    )
                    .padding(12.dp)
            ) {
                Text(
                    text = message.text,
                    fontSize = 16.sp,
                    color = if (message.isUser) Color.White else Color.Black
                )
            }
        }

        // Hiển thị thời gian
        Text(
            text = message.time.format(formatter),
            fontSize = 12.sp,
            color = Color.Gray,
            modifier = Modifier.padding(top = 2.dp)
        )
    }
}