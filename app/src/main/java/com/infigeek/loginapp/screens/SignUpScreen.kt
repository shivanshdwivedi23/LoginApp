package com.infigeek.loginapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.infigeek.loginapp.components.ButtonComponent
import com.infigeek.loginapp.components.ClickableLoginTextComponent
import com.infigeek.loginapp.components.DividerTextComponent
import com.infigeek.loginapp.components.MyTextFieldComponent
import com.infigeek.loginapp.components.PasswordTextFieldComponent
//import com.infigeek.loginapp.navigation.Navigator.navigateTo
import com.infigeek.loginapp.navigation.Screen
import com.infigeek.loginapp.signup.SignupUIEvent
import com.infigeek.loginapp.signup.SignupViewModel
import com.infigeek.loginapp.R
import com.infigeek.loginapp.components.*
import com.infigeek.loginapp.navigation.common
import com.infigeek.loginapp.navigation.common.navigateTo


@Composable
fun SignUpScreen(signupViewModel: SignupViewModel = viewModel()) {

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
                    .padding(28.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(modifier = Modifier.fillMaxSize()) {

                    NormalTextComponent(value = stringResource(id = R.string.hello))
                    HeadingTextComponent(value = stringResource(id = R.string.create_account))
                    Spacer(modifier = Modifier.height(20.dp))

                    MyTextFieldComponent(
                        labelValue = stringResource(id = R.string.first_name),
                        painterResource(id = R.drawable.profile),
                        onTextChanged = {
                            signupViewModel.onEvent(SignupUIEvent.FirstNameChanged(it))
                        },
                        errorStatus = signupViewModel.registrationUIState.value.firstNameError
                    )

                    MyTextFieldComponent(
                        labelValue = stringResource(id = R.string.last_name),
                        painterResource = painterResource(id = R.drawable.profile),
                        onTextChanged = {
                            signupViewModel.onEvent(SignupUIEvent.LastNameChanged(it))
                        },
                        errorStatus = signupViewModel.registrationUIState.value.lastNameError
                    )

                    MyTextFieldComponent(
                        labelValue = stringResource(id = R.string.email),
                        painterResource = painterResource(id = R.drawable.message),
                        onTextChanged = {
                            signupViewModel.onEvent(SignupUIEvent.EmailChanged(it))
                        },
                        errorStatus = signupViewModel.registrationUIState.value.emailError
                    )

                    PasswordTextFieldComponent(
                        labelValue = stringResource(id = R.string.password),
                        painterResource = painterResource(id = R.drawable.ic_lock),
                        onTextSelected = {
                            signupViewModel.onEvent(SignupUIEvent.PasswordChanged(it))
                        },
                        errorStatus = signupViewModel.registrationUIState.value.passwordError
                    )

                    Spacer(modifier = Modifier.height(40.dp))

                    ButtonComponent(
                        value = stringResource(id = R.string.register),
                        onButtonClicked = {
                            signupViewModel.onEvent(SignupUIEvent.RegisterButtonClicked)
                        },
                        isEnabled = signupViewModel.allValidationsPassed.value,
                        horizontalPadding = 110.dp,
                        buttonWidth = 500.dp
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    DividerTextComponent()

                    ClickableLoginTextComponent(tryingToLogin = true, onTextSelected = {
                        common.navigateTo(Screen.LoginScreen)
                    })
                }

            }
        }

        if(signupViewModel.signUpInProgress.value) {
            CircularProgressIndicator()
        }

}
