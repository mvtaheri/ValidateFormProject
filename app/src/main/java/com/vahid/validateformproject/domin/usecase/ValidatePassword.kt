package com.vahid.validateformproject.domin.usecase

import android.util.Patterns

class ValidatePassword {

    fun execute(password: String): ValidatinResult {
        if (password.length < 8) {
            return ValidatinResult(
                successful = false,
                errorMessage = "the need to consist of at least 8 characers"
            )
        }
        val containsLettersAndDigits = password.any {
            it.isDigit()
        } && password.any { it.isLetter() }
        if (!containsLettersAndDigits) {
            return ValidatinResult(
                successful = false,
                errorMessage = "the password needs to contais at least one letter and digit"
            )
        }
        return ValidatinResult(
            successful = true
        )
    }

}

