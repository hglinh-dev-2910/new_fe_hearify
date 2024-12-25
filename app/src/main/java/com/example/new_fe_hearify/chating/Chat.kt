package com.example.new_fe_hearify.chating

import android.content.res.Resources.Theme
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
//import com.example.new_fe_hearify.Login1Screen
import com.example.new_fe_hearify.R
import com.example.new_fe_hearify.ui.theme.New_fe_hearifyTheme

@Composable
fun Chatting(modifier: Modifier = Modifier) {
    Column(modifier.fillMaxSize()) {
        Box(
            Modifier
                .height(30.dp)
                .width(30.dp)
                .background(color = colorResource(R.color.container_background))
        ) {
            Text(text = "Mai Tấn Giáp Đep Trai")
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun ChattingPreview(){
    New_fe_hearifyTheme {
        Chatting(Modifier)  // Changed to Login1Screen
    }
}