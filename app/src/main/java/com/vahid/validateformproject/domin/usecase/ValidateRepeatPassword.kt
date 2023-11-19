package com.vahid.validateformproject.domin.usecase

class ValidateRepeatPassword {
    fun execute(password: String, repeatPassword: String): ValidatinResult {
        if (password != repeatPassword) {
            return ValidatinResult(
                successful = false,
                errorMessage = "the password do not match"
            )
        }
        return ValidatinResult(
            successful = true
        )
    }

}