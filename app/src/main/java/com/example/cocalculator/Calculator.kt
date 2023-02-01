package com.example.cocalculator

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun Calculator() {
    val number1: Int? = remember { null }
    val number2: Int? = remember { null }
    val operations = listOf("+","-","*","/")
    val operation: String? = remember { null }
    Column {
        Text(text = number1.toString()+operation+number2.toString())
        Row() {
            Button(onClick = { /*TODO*/ }) {
                Text(text = "AC")
            }
            Button(onClick = { /*TODO*/ }) {
                Text(text = "/")
            }
        }
    }
}

@Preview
@Composable
fun CalculatorPreview() {
    Calculator()
}