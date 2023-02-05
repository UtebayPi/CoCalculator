package com.example.cocalculator

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class CalcViewModel : ViewModel() {
    private val _number1 = mutableStateOf("")
    val number1: State<String> = _number1
    private val _number2 = mutableStateOf("")
    val number2: State<String> = _number2
    private val _operation = mutableStateOf<Operations?>(null)
    val operation: State<Operations?> = _operation

    //To know what number should be edited
    private fun validateNumber1() =
        _number2.value.isEmpty() && _operation.value == null && _number1.value.length <= NUMBER_LIMIT

    private fun validateNumber2() =
        _number1.value.isNotEmpty() && _operation.value != null && _number2.value.length <= NUMBER_LIMIT

    //Used the Strategy Pattern to get rid of the code duplication in couple of places.
    private fun editCorrectNumber(lambda: (MutableState<String>) -> Unit) = when {
        validateNumber1() -> lambda(_number1)
        validateNumber2() -> lambda(_number2)
        else -> throw Exception("Error in numberValidation")

    }

    fun numberPressed(newNumber: String) {
        editCorrectNumber { number ->
            //So that you can't write multiple 000's as first numbers.
            if (number.value.firstOrNull() != '0'
                || number.value.take(2) == "0."
                || newNumber != "0"
            ) {
                //If the first number is 0 and not "0.", replace it with a new number.
                if (number.value.firstOrNull() == '0' && number.value.take(2) != "0.") {
                    number.value = newNumber
                } else
                    number.value += newNumber
            }
        }
    }

    fun dotPressed() {
        editCorrectNumber { number ->
            if (number.value.isNotEmpty() && !number.value.contains(".")) number.value += "."
        }
    }

    fun operationPressed(newOperation: Operations) {
        calculate()
        //If all three values are filled, then it will perform the calculation,
        //and assign a new operation. Otherwise it will just assign a new operation.
        _operation.value = newOperation
    }

    fun delete() {
        when {
            //if we are deleting the operation
            _number1.value.isNotEmpty()
                    && _number2.value.isEmpty()
                    && _operation.value != null ->
                _operation.value = null
            else ->
                editCorrectNumber { number ->
                    number.value = number.value.dropLast(1)
                }
        }
    }

    fun allClear() {
        _number1.value = ""
        _number2.value = ""
        _operation.value = null
    }

    fun calculate() {
        if (_number1.value.isEmpty() || _number2.value.isEmpty() || _operation.value == null) return
        val num1 = _number1.value.toFloatOrNull()
        val num2 = _number2.value.toFloatOrNull()
        if (num1 == null || num2 == null) return
        //Everything above is just validation

        val result = when (_operation.value) {
            Operations.Multiply -> num1 * num2
            Operations.Divide -> {
                //so that you can't divide by 0
                if (num2 == 0F) return else num1 / num2
            }
            Operations.Subtract -> num1 - num2
            Operations.Add -> num1 + num2
            null -> return
        }.toString()

        //if the result ends with .0, remove it.
        _number1.value = if (result.takeLast(2) == ".0") result.dropLast(2) else result
        _number2.value = ""
        _operation.value = null
    }
}

enum class Operations(val symbol: String) {
    Multiply("*"),
    Divide("/"),
    Subtract("-"),
    Add("+")
}

const val NUMBER_LIMIT = 15