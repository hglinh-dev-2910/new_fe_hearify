package com.example.new_fe_hearify.intro2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.new_fe_hearify.Login2Screen
//import com.example.myapp.R // Thay thế bằng R của ứng dụng bạn
import com.example.new_fe_hearify.R
import com.example.new_fe_hearify.ui.theme.New_fe_hearifyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OnboardingScreen()
        }
    }
}

@Composable
fun OnboardingScreen() {
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround
        ) {

            // Hình ảnh (thay thế bằng hình ảnh của bạn)
            Image(
                painter = painterResource(id = R.drawable.intro2), // Thay thế onboarding_image bằng id hình ảnh của bạn
                contentDescription = "Onboarding Image",
                modifier = Modifier.size(200.dp) // Điều chỉnh kích thước nếu cần
            )

            // Tiêu đề
            Text(
                text = "Kết nối với nhiều người bạn mới",
                style = MaterialTheme.typography.h6,
                textAlign = TextAlign.Center
            )

            // Các nút "giả" (thay thế bằng nội dung bạn muốn)
            Column {
                Button(onClick = {}, enabled = false) { // enabled = false để nút không hoạt động
                    Text("Nút 1")
                }
                Spacer(modifier = Modifier.height(8.dp))
                Button(onClick = {}, enabled = false) {
                    Text("Nút 2")
                }
            }


            // Nút Bỏ qua
            Button(onClick = {
                // Xử lý sự kiện khi nút Bỏ qua được nhấn
                // Ví dụ: chuyển sang màn hình tiếp theo
            }) {
                Text("Bỏ qua", fontSize = 16.sp)
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    OnboardingScreen()
}


