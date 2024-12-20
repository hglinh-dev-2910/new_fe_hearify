package com.example.new_fe_hearify

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.new_fe_hearify.ui.theme.New_fe_hearifyTheme
import com.example.new_fe_hearify.registerView.RegistrationScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            New_fe_hearifyTheme {
                RegistrationScreen()
            }
        }
    }
}




@Preview(showBackground = true)
@Composable
fun RegistrationScreenPreview() {
    New_fe_hearifyTheme {
        RegistrationScreen()
    }
}