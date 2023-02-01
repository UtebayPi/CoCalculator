package com.example.cocalculator

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun CalculatorButton(content: String, onClick: ()->Unit) {
    Button(onClick = onClick) {
        Text(text = content)
    }
}