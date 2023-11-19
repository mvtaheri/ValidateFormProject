package com.vahid.validateformproject.domin.usecase

class ValidateTerms {
    fun execute(acceptedTerms: Boolean): ValidatinResult {
        if (!acceptedTerms) {
            return ValidatinResult(
                successful = false,
                errorMessage = "please accept the terms"
            )
        }
        return ValidatinResult(
            successful = true
        )
    }
}