package com.example.new_fe_hearify

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.new_fe_hearify.loginView.Login1Screen
import com.example.new_fe_hearify.ui.theme.New_fe_hearifyTheme
import com.example.new_fe_hearify.viewModel.AuthViewModel


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            New_fe_hearifyTheme {
                Login1Screen(AuthViewModel(), Modifier)
            }
        }
    }
}







