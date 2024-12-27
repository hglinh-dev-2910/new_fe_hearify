package com.example.new_fe_hearify


import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.new_fe_hearify.dashboard.DashboardScreen
import com.example.new_fe_hearify.introView.Intro1Screen
import com.example.new_fe_hearify.introView.Intro34Screen
import com.example.new_fe_hearify.loginView.Login2Screen
import com.example.new_fe_hearify.registerView.RegistrationScreen
import com.example.new_fe_hearify.ui.theme.New_fe_hearifyTheme
import com.example.new_fe_hearify.viewModel.AuthViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            New_fe_hearifyTheme {
                Log.e("MainActivity", "onCreate: ")
                val navController = rememberNavController()
                navController.navigatorProvider.addNavigator(ComposeNavigator()) // Add this line
                MainApp(navController = navController)
            }
        }
    }
}


@Composable
fun MainApp(navController: NavHostController) {

    NavHost(navController = navController, startDestination = "introview1") {
        composable("introview1") {
            Intro1Screen(Modifier, navController)
        }
        composable("introview34") {
            Intro34Screen(Modifier, navController)
        }
        composable("login1view") {
            Login1Screen( Modifier, navController)
        }
        composable("login2view") {
            Login2Screen(Modifier, navController)
        }
        composable("registerview") {
            RegistrationScreen(viewModel = AuthViewModel(),Modifier, navController)
        }
        composable("dashboard") {
            DashboardScreen()
        }
    }
}


//@Preview(showBackground = true)
//@Composable
//fun RegistrationScreenPreview() {
//    New_fe_hearifyTheme {
//        RegistrationScreen()
//    }
//}