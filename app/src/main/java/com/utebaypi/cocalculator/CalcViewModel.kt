package com.utebaypi.cocalculator

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine

class CalcViewModel : ViewModel() {
    private val number1 = MutableStateFlow("")
    private val number2 = MutableStateFlow("")
    private val operation = MutableStateFlow<Operations?>(null)
    val result = combine(number1, number2, operation) { num1, num2, op ->
        num1 + (op?.symbol ?: "") + num2
    }

    //To know what number should be edited
    private fun validateNumber1() =
        number2.value.isEmpty() && operation.value == null

    private fun validateNumber2() =
        number1.value.isNotEmpty() && operation.value != null

    //Used the Strategy Pattern to get rid of the code duplication in couple of places.
    private fun editCorrectNumber(lambda: (MutableStateFlow<String>) -> Unit) = when {
        validateNumber1() -> lambda(number1)
        validateNumber2() -> lambda(number2)
        else -> {}

    }

    fun numberPressed(newNumber: String) {
        editCorrectNumber { number ->
            if(number.value.length > NUMBER_LIMIT) return@editCorrectNumber
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
        operation.value = newOperation
    }

    fun delete() {
        when {
            //if we are deleting the operation
            number1.value.isNotEmpty()
                    && number2.value.isEmpty()
                    && operation.value != null ->
                operation.value = null
            else ->
                editCorrectNumber { number ->
                    number.value = number.value.dropLast(1)
                }
        }
    }

    fun allClear() {
        number1.value = ""
        number2.value = ""
        operation.value = null
    }

    fun calculate() {
        if (number1.value.isEmpty() || number2.value.isEmpty() || operation.value == null) return
        val num1 = number1.value.toFloatOrNull()
        val num2 = number2.value.toFloatOrNull()
        if (num1 == null || num2 == null) return
        //Everything above is just validation

        val result = when (operation.value) {
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
        number1.value = if (result.takeLast(2) == ".0") result.dropLast(2) else result
        number2.value = ""
        operation.value = null
    }
}

enum class Operations(val symbol: String) {
    Multiply("*"),
    Divide("/"),
    Subtract("-"),
    Add("+")
}

const val NUMBER_LIMIT = 15