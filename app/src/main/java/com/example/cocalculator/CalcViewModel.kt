package com.example.cocalculator

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class CalcViewModel : ViewModel() {
    var number1 by mutableStateOf("")
        private set

    var number2 by mutableStateOf("")
        private set

    var operation by mutableStateOf<Operations?>(null)
        private set

    fun validateNumber1() =
        number2.isEmpty() && operation == null && number1.length <= NUMBER_LIMIT

    fun validateNumber2() =
        number1.isNotEmpty() && operation != null && number2.length <= NUMBER_LIMIT

    fun numberPressed(newNumber: String) {
        when {
            validateNumber1() -> {
                if (number1.firstOrNull() != '0' || number1.take(2) == "0.")
                    number1 += newNumber
            }
            validateNumber2() -> {
                if (number2.firstOrNull() != '0' || number2.take(2) == "0.")
                    number2 += newNumber
            }
        }
    }

    fun dotPressed() {
        when {
            validateNumber1() && number1.isNotEmpty() && !number1.contains(".") -> number1 += "."
            validateNumber2() && number2.isNotEmpty() && !number2.contains(".") -> number2 += "."
        }
    }

    fun operationPressed(newOperation: Operations) {
        if (number1.isNotEmpty() && number2.isNotEmpty() && operation != null) calculate()
        operation = newOperation
    }

    fun delete() {
        when {
            number2.isNotEmpty() -> number2 = number2.dropLast(1)
            number2.isEmpty() && operation != null -> operation = null
            number1.isNotEmpty() && operation == null && number2.isEmpty() -> number1 =
                number1.dropLast(1)
        }
    }

    fun allClear() {
        number1 = ""
        number2 = ""
        operation = null
    }

    fun calculate() {
        if (number1.isEmpty() || number2.isEmpty() || operation == null) return
        val num1 = number1.toFloatOrNull()
        val num2 = number2.toFloatOrNull()
        if (num1 == null || num2 == null) return
        val result = when (operation) {
            Operations.Multiply -> num1 * num2
            Operations.Divide -> {
                if (num2 == 0F) return else num1 / num2
            }
            Operations.Subtract -> num1 - num2
            Operations.Add -> num1 + num2
            null -> return
        }.toString()
        number1 = if (result.takeLast(2) == ".0") result.dropLast(2) else result
        number2 = ""
        operation = null
    }
}

enum class Operations(val symbol: String) {
    Multiply("*"),
    Divide("/"),
    Subtract("-"),
    Add("+")
}

const val NUMBER_LIMIT = 15