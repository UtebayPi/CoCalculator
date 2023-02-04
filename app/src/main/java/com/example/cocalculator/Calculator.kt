package com.example.cocalculator

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cocalculator.ui.theme.CoCalculatorTheme

@Composable
fun Calculator() {
    val viewModel = viewModel<CalcViewModel>()
    val spacing = 8.dp
    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Bottom) {
        Text(text = viewModel.number1 + (viewModel.operation?.symbol ?: "") + viewModel.number2)
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
            CalculatorButton(
                onClick = { viewModel.allClear() },
                modifier = Modifier.weight(2F),
                content = "AC"
            )
            CalculatorButton(
                onClick = { viewModel.delete() },
                modifier = Modifier.weight(1F),
                content = "del"
            )
            CalculatorButton(
                onClick = { viewModel.operationPressed(Operations.Divide) },
                modifier = Modifier.weight(1F),
                content = "/"
            )

        }
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
            CalculatorButton(
                modifier = Modifier.weight(1F),
                onClick = { viewModel.numberPressed("7") },
                content = "7"
            )
            CalculatorButton(
                modifier = Modifier.weight(1F),
                onClick = { viewModel.numberPressed("8") },
                content = "8"
            )
            CalculatorButton(
                modifier = Modifier.weight(1F),
                onClick = { viewModel.numberPressed("9") },
                content = "9"
            )
            CalculatorButton(
                modifier = Modifier.weight(1F),
                onClick = { viewModel.operationPressed(Operations.Multiply) }, content = "*"
            )
        }
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
            CalculatorButton(
                modifier = Modifier.weight(1F),
                onClick = { viewModel.numberPressed("4") }, content = "4"
            )
            CalculatorButton(
                modifier = Modifier.weight(1F),
                onClick = { viewModel.numberPressed("5") }, content = "5"
            )
            CalculatorButton(
                modifier = Modifier.weight(1F),
                onClick = { viewModel.numberPressed("6") }, content = "6"
            )
            CalculatorButton(
                modifier = Modifier.weight(1F),
                onClick = { viewModel.operationPressed(Operations.Subtract) }, content = "-"
            )
        }
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
            CalculatorButton(
                modifier = Modifier.weight(1F),
                onClick = { viewModel.numberPressed("1") }, content = "1"
            )
            CalculatorButton(
                modifier = Modifier.weight(1F),
                onClick = { viewModel.numberPressed("2") }, content = "2"
            )
            CalculatorButton(
                modifier = Modifier.weight(1F),
                onClick = { viewModel.numberPressed("3") }, content = "3"
            )
            CalculatorButton(
                modifier = Modifier.weight(1F),
                onClick = { viewModel.operationPressed(Operations.Add) }, content = "+"
            )
        }
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
            CalculatorButton(
                modifier = Modifier.weight(2F),
                onClick = { viewModel.numberPressed("0") }, content = "0"
            )
            CalculatorButton(modifier = Modifier.weight(1F), onClick = { /*TODO*/ }, content = ".")
            CalculatorButton(
                modifier = Modifier.weight(1F),
                onClick = { viewModel.calculate() },
                content = "="
            )
        }
    }

}

@Preview
@Composable
fun CalculatorPreview() {
    CoCalculatorTheme {
        Calculator()
    }
}