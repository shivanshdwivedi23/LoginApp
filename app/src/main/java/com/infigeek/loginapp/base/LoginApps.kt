package com.infigeek.loginapp.base

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import com.infigeek.loginapp.screens.HomeScreen
import com.infigeek.loginapp.home.HomeViewModel
import com.infigeek.loginapp.navigation.common
import com.infigeek.loginapp.navigation.Screen
import com.infigeek.loginapp.navigation.common.navigateTo
import com.infigeek.loginapp.screens.LoginScreen
import com.infigeek.loginapp.screens.SignUpScreen

@Composable
fun LoginApps(homeViewModel: HomeViewModel = viewModel()) {
    homeViewModel.checkForActiveSession()

        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Color.White
        ) {

            if (homeViewModel.isUserLoggedIn.value == true) {
                navigateTo(Screen.HomeScreen)
            }

            Crossfade(targetState = common.currentScreen) { currentState ->
                when (currentState.value) {
                    is Screen.SignUpScreen -> {
                        SignUpScreen()
                    }

                    is Screen.LoginScreen -> {
                        LoginScreen()
                    }

                    is Screen.HomeScreen -> {
                        HomeScreen()
                    }
                }
            }

        }
    }