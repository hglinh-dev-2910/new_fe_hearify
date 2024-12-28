package com.example.new_fe_hearify.dashboard

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomAppBar
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.new_fe_hearify.R

// This function is used for navigation from DashboardScreen to MessageList


@Composable
fun DashboardScreen(
    navController: NavHostController, // Receive navController as a parameter
) {
    // Màu nền chủ đạo
    val backgroundColor = Color(0xFFFFF0F5) // Màu hồng nhạt

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Dashboard") },
                backgroundColor = backgroundColor,
                actions = {
                    // Icon người dùng
                    IconButton(onClick = { /* Xử lý sự kiện */ }) {
                        Icon(
                            painter = painterResource(id = R.drawable.anhtuyet), // Thay bằng icon người dùng
                            contentDescription = "User",
                            tint = Color.Black // Màu đen cho icon
                        )
                    }
                }
            )
        },
        bottomBar = {
            // Bottom bar (có thể bỏ nếu không cần)
            BottomAppBar(backgroundColor = backgroundColor) {
                // Nội dung của bottom bar (nếu có)
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(backgroundColor)
                .padding(paddingValues)
        ) {
            // Header
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween, // Canh đều 2 bên
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Vi, 20", fontWeight = FontWeight.Bold)
                // Icon trái tim
                IconButton(onClick = { /* Xử lý sự kiện */ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.heart8bit), // Thay bằng icon trái tim
                        contentDescription = "Heart",
                        tint = Color.Red // Màu đỏ cho icon
                    )
                }
            }

            // Ảnh chính giữa
            Image(
                painter = painterResource(id = R.drawable.dashboard), // Thay bằng ảnh thật
                contentDescription = "Main Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp) // Điều chỉnh chiều cao theo ý muốn
                    .clip(RoundedCornerShape(16.dp)), // Bo góc ảnh
                contentScale = ContentScale.Crop // Cắt ảnh để vừa với khung hình
            )

            Spacer(modifier = Modifier.height(16.dp)) // Khoảng cách

            // Nút "Đã Match bạn"
            Button(
                onClick = { /* Xử lý sự kiện */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFFFE4E1)) // Màu nền nút
            ) {
                Text("Đã Match bạn", color = Color.Red)
                Spacer(modifier = Modifier.weight(1f)) // Đẩy icon sang phải
                Icon(
                    painter = painterResource(id = R.drawable.anhtuyet), // Thay bằng icon trái tim
                    contentDescription = "Heart",
                    tint = Color.Red // Màu đỏ cho icon
                )
            }

            Spacer(modifier = Modifier.height(8.dp)) // Khoảng cách

            val sharedPreferences =
                LocalContext.current.getSharedPreferences("user_data", Context.MODE_PRIVATE)
            val currentUserId = sharedPreferences.getInt("userId", -1)
            // Nút "Danh sách ghép đôi"
            Button(
                onClick = {
                    navController.navigate(
                        "messagelistview/$currentUserId"
                    )
                }, // Call the lambda function for navigation
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFFFE4E1)) // Màu nền nút
            ) {
                Text("Danh sách ghép đôi", color = Color.Red)
                Spacer(modifier = Modifier.weight(1f)) // Đẩy icon sang phải
                Icon(
                    painter = painterResource(id = R.drawable.heart8bit), // Thay bằng icon trái tim
                    contentDescription = "Heart",
                    tint = Color.Red // Màu đỏ cho icon
                )
            }
        }
    }
}