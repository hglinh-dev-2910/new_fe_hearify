package com.example.new_fe_hearify

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.new_fe_hearify.ui.theme.New_fe_hearifyTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Login1Screen(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFF2DEDE))
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Logo
        Image(
            painter = painterResource(id = R.drawable.logo), // Replace with your actual logo
            contentDescription = "Heartify Logo",
            modifier = Modifier.size(100.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Login Buttons
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Button(
                onClick = { navController.navigate("login2view") }, // Navigate to LoginView2
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFCDD2))
            ) {
                Text("Đăng nhập", color = Color.Red)
            }
            Button(
                onClick = { navController.navigate("registerview") }, // Navigate to RegisterView
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 8.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFCDD2))
            ) {
                Text("Đăng ký", color = Color.Red)
            }
        }
        Spacer(modifier = Modifier.height(8.dp))

        // Guest Button
        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, Color.White, RoundedCornerShape(4.dp)),
            colors = ButtonDefaults.buttonColors(containerColor = colorResource(R.color.container_background))
        ) {
            Text("Khách", color = Color.Red)
        }
        Spacer(modifier = Modifier.height(16.dp))

        // Or
        Text("Or", fontWeight = FontWeight.Bold, fontSize = 18.sp)
        Spacer(modifier = Modifier.height(16.dp))

        // Google Login
        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color.White)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.google_logo), // Replace with Google logo
                    contentDescription = "Google Logo",
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text("Đăng nhập với tài khoản Google", color = Color.Black)
            }
        }
        Spacer(modifier = Modifier.height(8.dp))

        // Facebook Login
        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF3B5998))
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.facebook_logo), // Replace with Facebook logo
                    contentDescription = "Facebook Logo",
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text("Tiếp tục với Facebook", color = Color.White)
            }
        }
        Spacer(modifier = Modifier.height(16.dp))

        // Terms Text
        Text(
            text = "Chúng tôi sẽ không bao giờ chia sẻ bất cứ điều gì\n" +
                    "mà không có sự cho phép của bạn.",
            textAlign = TextAlign.Center,
            color = Color.Gray,
            fontSize = 12.sp
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Bằng cách đăng ký, bạn đồng ý với Điều khoản và Điều\n" +
                    "kiện của chúng tôi. Bạn cũng xác nhận rằng bạn\n" +
                    "đã đọc Chính sách cách bảo mật thông tin của chúng tôi.",
            textAlign = TextAlign.Center,
            color = Color.Gray,
            fontSize = 12.sp
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Login1LayoutPreview() {
    New_fe_hearifyTheme {
        Login1Screen(Modifier, rememberNavController())
    }
}