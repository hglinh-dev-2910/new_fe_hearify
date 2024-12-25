package com.example.new_fe_hearify.editprofile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import com.example.new_fe_hearify.R

@Composable
fun EditProfileScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF8DADA)) // Màu nền hồng nhạt
            .padding(16.dp)
    ) {
        // Header
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { /* TODO: Xử lý khi nhấn nút quay lại */ }) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Back")
            }
            Spacer(modifier = Modifier.weight(1f))
            IconButton(onClick = { /* TODO: Xử lý khi nhấn nút check */ }) {
                Icon(Icons.Default.Check, contentDescription = "Save", tint = Color.Red)
            }
        }

        // Ảnh đại diện và VIP badge
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            contentAlignment = Alignment.Center
        ) {
            // Ảnh đại diện
            Box(
                modifier = Modifier.size(100.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.messavar), // Đường dẫn đến ảnh drawable
                    contentDescription = "Avatar",
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape) // Đảm bảo ảnh có hình tròn
                        .background(Color.Gray) // Nền màu xám nếu ảnh không tải
                )
            }

            // Badge VIP
            Box(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .background(Color.Red, shape = RoundedCornerShape(12.dp))
                    .padding(horizontal = 8.dp, vertical = 4.dp)
            ) {
                Text(
                    text = "VIP",
                    color = Color.White,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        // Tên và tuổi
        Text(
            text = "Minh, 20",
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 8.dp),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

        // Danh sách các trường chỉnh sửa
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        ) {
            ProfileField(label = "Hồ sơ hẹn hò", value = "Chỉnh sửa")
            ProfileField(label = "Ngày sinh", value = "21.12.2004")
            ProfileField(label = "Giới tính", value = "Nam")
            ProfileField(label = "Học vấn", value = "Đại học")
            ProfileField(label = "Số điện thoại", value = "0935643333")
        }
    }
}

@Composable
fun ProfileField(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .background(Color.White, shape = RoundedCornerShape(12.dp))
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Label
        Text(
            text = label,
            modifier = Modifier.weight(1f),
            fontSize = 16.sp
        )
        // Value
        Text(
            text = value,
            fontSize = 16.sp,
            color = Color.Gray
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewEditProfileScreen() {
    EditProfileScreen()
}
