package com.vahid.validateformproject.domin.usecase

import android.util.Patterns
import java.util.regex.Pattern

class ValidateEmail {
    fun execute(email: String): ValidatinResult {
        if (email.isBlank()) {
            return ValidatinResult(
                successful = false,
                errorMessage = "the email can not be blank"
            )
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return ValidatinResult(
                successful = false,
                errorMessage = "that is not valid email"
            )
        }
        return ValidatinResult(
            successful = true
        )
    }

}