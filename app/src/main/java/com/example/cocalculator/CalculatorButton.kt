package com.example.cocalculator

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp

@Composable
fun CalculatorButton(content: String, modifier: Modifier, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier.then(modifier)
    ) {
        Text(text = content, fontSize = 36.sp, maxLines = 1)
    }
}