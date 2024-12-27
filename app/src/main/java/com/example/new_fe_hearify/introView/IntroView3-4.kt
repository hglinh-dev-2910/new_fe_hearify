package com.example.new_fe_hearify.introView

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.new_fe_hearify.R

@Composable
fun Intro34Screen(modifier: Modifier = Modifier, navController: NavHostController) {
    Box(modifier = modifier.fillMaxSize()) {
        // Background image
        Image(
            painter = painterResource(id = R.drawable.messi), // Replace with your actual image
            contentDescription = "background",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        // Top texts and buttons
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
                .align(Alignment.TopCenter)
        ) {
            // "Quay lại" and "Bỏ qua" buttons
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(y = 40.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                TextButton(onClick = { navController.popBackStack() }) {
                    Text(
                        text = "Quay lại",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                }

                TextButton(onClick = { navController.navigate("login1view") }) { // Replace with your main screen route
                    Text(
                        text = "Bỏ qua",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                }
            }

            // Heart and Close buttons
            Column(
                modifier = Modifier
                    .offset(y = 300.dp)
                    .align(Alignment.End)
            ) {
                IconButton(onClick = {  }) {
                    Icon(
                        imageVector = Icons.Default.Favorite,
                        contentDescription = "Heart",
                        tint = Color.White,
                        modifier = Modifier
                            .size(48.dp)
                            .clip(CircleShape)
                            .background(Color.Red)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                IconButton(onClick = { /* Do nothing */ }) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Close",
                        tint = Color.White,
                        modifier = Modifier
                            .size(48.dp)
                            .clip(CircleShape)
                            .background(Color.LightGray)
                    )
                }
            }
        }

        // Bottom text
        Text(
            text = "Kết nối với nhiều người bạn mới",
            color = Color.White,
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
                .align(Alignment.BottomCenter)
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun MainScreenPreview() {
    Intro34Screen(navController = rememberNavController())
}