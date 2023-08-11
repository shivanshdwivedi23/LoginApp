package com.infigeek.loginapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.infigeek.loginapp.login.LoginUIEvent
import com.infigeek.loginapp.login.LoginViewModel
import com.infigeek.loginapp.R
import com.infigeek.loginapp.components.ButtonComponent
import com.infigeek.loginapp.components.ClickableLoginTextComponent
import com.infigeek.loginapp.components.DividerTextComponent
import com.infigeek.loginapp.components.MyTextFieldComponent
import com.infigeek.loginapp.components.PasswordTextFieldComponent
//import com.infigeek.loginapp.components.TextFieldComponent
import com.infigeek.loginapp.navigation.BackButton
import com.infigeek.loginapp.navigation.Screen
import com.infigeek.loginapp.components.UnderLinedTextComponent
import com.infigeek.loginapp.navigation.common

//import com.infigeek.loginapp.navigation.Navigator.navigateTo


@Composable
fun LoginScreen(loginViewModel: LoginViewModel = viewModel()){
    val manFontFamily = FontFamily(Font(R.font.manropefont))
    val ETextStyle = TextStyle(
        fontFamily = manFontFamily,
        fontSize = 17.sp, // Adjust the font size as needed
        fontWeight = FontWeight.Bold, // Use the bold font
        color = Color.Black,
    )
    val exoFontFamily = FontFamily(Font(R.font.exo2))
    val titleTextStyle = TextStyle(
        fontFamily = exoFontFamily,
        fontSize = 25.sp, // Adjust the font size as needed
        fontWeight = FontWeight.Bold, // Use the bold font
        color = Color.Black,
    )
    Surface {
        val colorStart = Color(0xFFE5E5)
        val colorEnd = Color(0xFFE5E5FF)

        val gradientBrush = Brush.verticalGradient(
            colors = listOf(colorStart, colorEnd)
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(brush = gradientBrush)
        ) {
            Column(
                modifier = Modifier
                    .padding(top = 80.dp, start = 40.dp, end = 40.dp)
                    .fillMaxSize()
            ) {
                Text(
                    text = "Login",
                    style = titleTextStyle,
                )
                Spacer(modifier = Modifier.height(1.dp)) // Add a space of 1.dp between the texts
                Text(
                    text = "Please sign in to continue.",
                    style = ETextStyle,
                )
                Spacer(modifier = Modifier.height(10.dp))
                Card(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .offset(y = 4.dp)
                        .shadow(8.dp, RoundedCornerShape(24.dp))
                        .background(Color.White),
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Spacer(modifier = Modifier.height(20.dp))
                        MyTextFieldComponent(
                            labelValue = stringResource(id = R.string.email),
                            painterResource(id = R.drawable.message),
                            onTextChanged = {
                                loginViewModel.onEvent(LoginUIEvent.EmailChanged(it))
                            },
                            errorStatus = loginViewModel.loginUIState.value.emailError
                        )

                        PasswordTextFieldComponent(
                            labelValue = stringResource(id = R.string.password),
                            painterResource(id = R.drawable.lock),
                            onTextSelected = {
                                loginViewModel.onEvent(LoginUIEvent.PasswordChanged(it))
                            },
                            errorStatus = loginViewModel.loginUIState.value.passwordError
                        )

                        Spacer(modifier = Modifier.height(10.dp))
                        UnderLinedTextComponent(value = stringResource(id = R.string.forgot_password))

                        ButtonComponent(
                            value = stringResource(id = R.string.login),
                            onButtonClicked = {
                                loginViewModel.onEvent(LoginUIEvent.LoginButtonClicked)
                            },
                            isEnabled = loginViewModel.allValidationsPassed.value,
                            buttonWidth = 500.dp,
                            horizontalPadding = 110.dp
                        )

                        Spacer(modifier = Modifier.height(20.dp))

                        DividerTextComponent()

                        ClickableLoginTextComponent(tryingToLogin = false, onTextSelected = {
                            common.navigateTo(Screen.SignUpScreen)
                        })
                    }


                if (loginViewModel.loginInProgress.value) {
                    CircularProgressIndicator()
                }
            }
                    }
                }

            }
        }

