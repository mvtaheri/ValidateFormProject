package com.vahid.validateformproject

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vahid.validateformproject.domin.usecase.ValidateEmail
import com.vahid.validateformproject.domin.usecase.ValidatePassword
import com.vahid.validateformproject.domin.usecase.ValidateRepeatPassword
import com.vahid.validateformproject.domin.usecase.ValidateTerms
import com.vahid.validateformproject.presentation.RegisterationFormEvant
import com.vahid.validateformproject.presentation.RegisterationFormState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class MainViewModel(
    private val validateEmail: ValidateEmail = ValidateEmail(),
    private val validatePassword: ValidatePassword = ValidatePassword(),
    private val validateRepeatPassword: ValidateRepeatPassword = ValidateRepeatPassword(),
    private val validateTerms: ValidateTerms = ValidateTerms()
) : ViewModel() {
    var state by mutableStateOf(RegisterationFormState())
    private val validationEventChannel = Channel<ValidationEvent>()
    val validationEvents = validationEventChannel.receiveAsFlow()
    fun onEvent(event: RegisterationFormEvant) {
        when (event) {
            is RegisterationFormEvant.EmailChanged -> {
                Log.i("Debug", "Email Change event")
                state = state.copy(email = event.email)
            }

            is RegisterationFormEvant.PasswordChanged -> {
                state.copy(password = event.password)
            }

            is RegisterationFormEvant.RepeatPasswordChanged -> {
                state.copy(repeatedPawwsord = event.repeatPassword)
            }

            is RegisterationFormEvant.AcceptTerms -> {
                state.copy(acceptedTerms = event.isAccepted)
            }

            is RegisterationFormEvant.Submit -> {
                submitData()
            }
        }
    }

    private fun submitData() {
        Log.i("Debug", "Submit Data")
        val emailResult = validateEmail.execute(state.email)
        val passwordResult = validatePassword.execute((state.password))
        val repeatPasswordResult =
            validateRepeatPassword.execute(state.password, state.repeatedPawwsord)
        val termsResult = validateTerms.execute(state.acceptedTerms)

        val hasError = listOf(
            emailResult,
            passwordResult,
            repeatPasswordResult,
            termsResult
        ).any {
            !it.successful
        }
        if (hasError) {
            state.copy(
                emailError = emailResult.errorMessage,
                passwordError = passwordResult.errorMessage,
                repeatedPasswordError = passwordResult.errorMessage,
                termsError = termsResult.errorMessage
            )
            return
        }
        viewModelScope.launch {
            validationEventChannel.send(ValidationEvent.success)
        }
    }

    sealed class ValidationEvent {
        object success : ValidationEvent()
    }
}