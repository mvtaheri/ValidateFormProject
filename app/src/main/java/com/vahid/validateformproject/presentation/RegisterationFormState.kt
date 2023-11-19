package com.vahid.validateformproject.presentation

data class RegisterationFormState(
    val email: String = "",
    val emailError: String = "",
    val password: String = "",
    val passwordError: String? = "",
    val repeatedPawwsord: String = "",
    val repeatedPasswordError: String? = "",
    val acceptedTerms: Boolean = false,
    val termsError: String? = null
)
