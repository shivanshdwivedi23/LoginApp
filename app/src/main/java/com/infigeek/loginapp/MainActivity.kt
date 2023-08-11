package com.infigeek.loginapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.infigeek.loginapp.base.LoginApps
import com.infigeek.loginapp.screens.HomeScreen
import com.infigeek.loginapp.screens.LoginScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
               LoginApps()
            }
    }
}

