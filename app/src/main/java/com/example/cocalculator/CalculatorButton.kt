package com.example.cocalculator

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp

@Composable
fun CalculatorButton(content: String, modifier: Modifier, color: Color = MaterialTheme.colorScheme.onSurfaceVariant, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier.then(modifier),
        colors = ButtonDefaults.buttonColors(
            containerColor = color
        )
    ) {
        Text(text = content, fontSize = 36.sp, maxLines = 1)
    }
}