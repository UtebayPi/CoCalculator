package com.example.cocalculator

data class CalculatorState(
    val number1: String = "",
    val number2: String = "",
    val operation: Operations? = null
) {

    fun getCalculatorText() = number1 + (operation?.symbol ?: "") + number2

}