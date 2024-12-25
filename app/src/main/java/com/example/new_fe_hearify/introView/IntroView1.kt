package com.example.new_fe_hearify.introView

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.new_fe_hearify.R

@Composable
fun Intro1Screen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(colorResource(R.color.login_background)), // Màu nền hồng nhạt
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo), // Thay bằng ID hình ảnh của bạn
            contentDescription = "Heartify Logo",
            modifier = Modifier.size(200.dp) // Điều chỉnh kích thước logo
        )
        Spacer(modifier = Modifier.height(16.dp)) // Khoảng cách giữa logo và text

    }
}

@Preview(showSystemUi = true)
@Composable
fun Intro1Preview() {
    Intro1Screen()
}