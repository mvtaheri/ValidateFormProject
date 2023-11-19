package com.vahid.validateformproject.presentation

sealed class RegisterationFormEvant {
    data class EmailChanged(val email: String) : RegisterationFormEvant()
    data class PasswordChanged(val password: String) : RegisterationFormEvant()
    data class RepeatPasswordChanged(val repeatPassword: String) :
        RegisterationFormEvant()

    data class AcceptTerms(val isAccepted: Boolean) : RegisterationFormEvant()
    object Submit : RegisterationFormEvant()
}