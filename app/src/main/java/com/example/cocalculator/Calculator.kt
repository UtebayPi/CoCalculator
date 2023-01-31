package com.example.cocalculator

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun Calculator() {

}

@Preview
@Composable
fun CalculatorPreview() {
    val options = listOf("Food", "Bill Payment", "Recharges", "Outing", "Other")
    Spinner(options) { Log.d("Stuff", "The selected is: $it") }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Spinner(options: List<String>, onSelect: (String)->Unit) {
    //val options = listOf("Food", "Bill Payment", "Recharges", "Outing", "Other")

    var expanded by remember { mutableStateOf(false) }
    var selectedOptionText by remember { mutableStateOf(options[0]) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded
        }
    ) {
        TextField(
            readOnly = true,
            value = selectedOptionText,
            onValueChange = { },
            label = { Text("Categories") },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(
                    expanded = expanded
                )
            },
            colors = ExposedDropdownMenuDefaults.textFieldColors()
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                expanded = false
            }
        ) {
            options.forEach { selectionOption ->
                DropdownMenuItem(
                    onClick = {
                        selectedOptionText = selectionOption
                        expanded = false
                        onSelect(selectionOption)
                    }
                ) {
                    Text(text = selectionOption)
                }
            }
        }
    }
}