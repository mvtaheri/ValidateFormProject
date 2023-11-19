package com.vahid.validateformproject.domin.usecase

data class ValidatinResult(
    val successful: Boolean,
    val errorMessage: String = ""
)