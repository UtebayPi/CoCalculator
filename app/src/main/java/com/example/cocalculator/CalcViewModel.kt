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

    fun numberPressed(newNumber: String) {
        when {
            number2.isEmpty() && operation == null -> number1 += newNumber
            number1.isNotEmpty() && operation != null -> number2 += newNumber
        }
    }

    fun operationPressed(newOperation: Operations) {
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
        number1 = when (operation) {
            Operations.Multiply ->
                number1.toInt() * number2.toInt()
            Operations.Divide -> number1.toInt() / number2.toInt()
            Operations.Subtract -> number1.toInt() - number2.toInt()
            Operations.Add -> number1.toInt() + number2.toInt()
            null -> return
        }.toString()
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