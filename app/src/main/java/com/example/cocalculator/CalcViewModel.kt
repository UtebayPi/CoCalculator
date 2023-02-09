package com.example.cocalculator

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class CalcViewModel : ViewModel() {
    //    private val _number1 = mutableStateOf("")
//    val number1: State<String> = _number1
//    private val _number2 = mutableStateOf("")
//    val number2: State<String> = _number2
//    private val _operation = mutableStateOf<Operations?>(null)
//    val operation: State<Operations?> = _operation
    var state by mutableStateOf(CalculatorState())
//    var number1
//        get() = state.number1
//        set(newNumber) {
//            state = state.copy(number1 = newNumber)
//        }
//    var number2
//        get() = state.number2
//        set(newNumber) {
//            state = state.copy(number2 = newNumber)
//        }

//    var propertyName = "number1"
//    val propertyValue: String = ""
//    val b = state::class.typeParameters.first { it.name == propertyName }
//    val a = state::class.java.getField(propertyName)

    //get() = state::class.memberProperties.first { it.name == propertyName }.get(state)
//        set(value) {
//            state::class.memberProperties.first { it.name == propertyName }.set(state, value)
//        }
//    fun copyObjectWithChangedProperties(original: Any, changedProperties: Map<String, Any>): Any {
//        val kClass = original::class
//        val constructor = kClass.constructors.first()
//        val properties = kClass.members
//
//        val arguments = constructor.parameters.map { parameter ->
//            val property = properties.first { it.name == parameter.name }
//            val newValue = changedProperties[parameter.name]
//            newValue
//        }
//
//        return constructor.call(*arguments.toTypedArray())
//    }

    //To know what number should be edited
    private fun validateNumber1() =
        state.number2.isEmpty() && state.operation == null && state.number1.length <= NUMBER_LIMIT

    private fun validateNumber2() =
        state.number1.isNotEmpty() && state.operation != null && state.number2.length <= NUMBER_LIMIT

    //Used the Strategy Pattern to get rid of the code duplication in couple of places.
    private fun editCorrectNumber(lambda: (getNumber: String, setNumber: (newNumber: String) -> Unit) -> Unit) =
        //i can create a local value with custom setter and getter
        when {
            validateNumber1() -> lambda(state.number1) { newNumber ->
                state = state.copy(number1 = newNumber)
            }
            validateNumber2() -> lambda(state.number2) { newNumber ->
                state = state.copy(number2 = newNumber)
            }
            else -> throw Exception("Error in number validation")
        }

    private fun ediCorrectNumber(lambda: (getNumber: String, setNumber: (newNumber: String) -> Unit) -> Unit) =
        //i can create a local value with custom setter and getter
        when {
            validateNumber1() -> lambda(state.number1) { newNumber ->
                //state = state.setPropertyValue("number1", newNumber)
            }
            validateNumber2() -> lambda(state.number2) { newNumber ->
                // state = state.setPropertyValue("number2", newNumber)
            }
            else -> throw Exception("Error in number validation")
        }

    fun numberPressed(newNumber: String) {
        editCorrectNumber { number, setNumber ->
            //So that you can't write multiple 000's as first numbers.
            if (number.firstOrNull() != '0'
                || number.take(2) == "0."
                || newNumber != "0"
            ) {
                //If the first number is 0 and not "0.", replace it with a new number.
                if (number.firstOrNull() == '0' && number.take(2) != "0.") {
                    setNumber(newNumber)
                } else
                    setNumber(number + newNumber)
            }
        }
    }

    fun dotPressed() {
        editCorrectNumber { number, setNumber ->
            if (number.isNotEmpty() && !number.contains(".")) setNumber("$number.")
        }
    }

    fun operationPressed(newOperation: Operations) {
        calculate()
        //If all three values are filled, then it will perform the calculation,
        //and assign a new operation. Otherwise it will just assign a new operation.
        state = state.copy(operation = newOperation)
    }

    fun delete() {
        when {
            //if we are deleting the operation
            state.number1.isNotEmpty()
                    && state.number2.isEmpty()
                    && state.operation != null ->
                state = state.copy(operation = null)
            else ->
                editCorrectNumber { number, setNumber ->
                    setNumber(number.dropLast(1))
                }
        }
    }

    fun allClear() {
        state = state.copy(number1 = "")
        state = state.copy(number2 = "")
        state = state.copy(operation = null)
    }

    fun calculate() {
        if (state.number1.isEmpty() || state.number2.isEmpty() || state.operation == null) return
        val num1 = state.number1.toFloatOrNull()
        val num2 = state.number2.toFloatOrNull()
        if (num1 == null || num2 == null) return
        //Everything above is just validation

        val result = when (state.operation) {
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
        state = state.copy(
            number1 = if (result.takeLast(2) == ".0") result.dropLast(2) else result
        )
        state = state.copy(number2 = "")
        state = state.copy(operation = null)
    }
}

enum class Operations(val symbol: String) {
    Multiply("*"),
    Divide("/"),
    Subtract("-"),
    Add("+")
}

const val NUMBER_LIMIT = 15