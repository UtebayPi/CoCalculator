package com.example.cocalculator

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Backspace
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cocalculator.ui.theme.CoCalculatorTheme

@Composable
fun Calculator() {
    val viewModel = viewModel<CalcViewModel>()
    val spacing = 8.dp
    val scrollState = rememberScrollState(0)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(PaddingValues(spacing)),
        verticalArrangement = Arrangement
            .spacedBy(spacing, alignment = Alignment.Bottom)
    ) {
        Box(
            modifier = Modifier
                .weight(1F)
                .align(Alignment.End)
        ) {
            Text(
                text = viewModel.number1 +
                        (viewModel.operation?.symbol ?: "") +
                        viewModel.number2,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    //.weight(1F)
                    .verticalScroll(scrollState),
                fontSize = 70.sp,
                textAlign = TextAlign.End,
                //maxLines = 2,
                lineHeight = 70.sp,
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(spacing)
        ) {
            CalculatorButton(
                onClick = { viewModel.allClear() },
                modifier = Modifier
                    .aspectRatio(2.1F)
                    .weight(2.1F),
                content = "AC"
            )
            Icon(
                imageVector = Icons.Filled.Backspace,
                tint = MaterialTheme.colorScheme.onPrimary,
                contentDescription = "Delete",
                modifier = Modifier
                    .clip(CircleShape)
                    .aspectRatio(1F)
                    .weight(1F)
                    .background(MaterialTheme.colorScheme.primary)
                    .clickable { viewModel.delete() }
                    .scale(0.5F)
                    .offset(x = (-3).dp)
            )
            CalculatorButton(
                onClick = { viewModel.operationPressed(Operations.Divide) },
                modifier = Modifier
                    .aspectRatio(1F)
                    .weight(1F),
                content = "/"
            )

        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(spacing)
        ) {
            CalculatorButton(
                modifier = Modifier
                    .aspectRatio(1F)
                    .weight(1F),
                onClick = { viewModel.numberPressed("7") },
                content = "7"
            )
            CalculatorButton(
                modifier = Modifier
                    .aspectRatio(1F)
                    .weight(1F),
                onClick = { viewModel.numberPressed("8") },
                content = "8"
            )
            CalculatorButton(
                modifier = Modifier
                    .aspectRatio(1F)
                    .weight(1F),
                onClick = { viewModel.numberPressed("9") },
                content = "9"
            )
            CalculatorButton(
                modifier = Modifier
                    .aspectRatio(1F)
                    .weight(1F),
                onClick = { viewModel.operationPressed(Operations.Multiply) }, content = "*"
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(spacing)
        ) {
            CalculatorButton(
                modifier = Modifier
                    .aspectRatio(1F)
                    .weight(1F),
                onClick = { viewModel.numberPressed("4") },
                content = "4"
            )
            CalculatorButton(
                modifier = Modifier
                    .aspectRatio(1F)
                    .weight(1F),
                onClick = { viewModel.numberPressed("5") },
                content = "5"
            )
            CalculatorButton(
                modifier = Modifier
                    .aspectRatio(1F)
                    .weight(1F),
                onClick = { viewModel.numberPressed("6") },
                content = "6"
            )
            CalculatorButton(
                modifier = Modifier
                    .aspectRatio(1F)
                    .weight(1F),
                onClick = { viewModel.operationPressed(Operations.Subtract) },
                content = "-"
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(spacing)
        ) {
            CalculatorButton(
                modifier = Modifier
                    .aspectRatio(1F)
                    .weight(1F),
                onClick = { viewModel.numberPressed("1") },
                content = "1"
            )
            CalculatorButton(
                modifier = Modifier
                    .aspectRatio(1F)
                    .weight(1F),
                onClick = { viewModel.numberPressed("2") },
                content = "2"
            )
            CalculatorButton(
                modifier = Modifier
                    .aspectRatio(1F)
                    .weight(1F),
                onClick = { viewModel.numberPressed("3") },
                content = "3"
            )
            CalculatorButton(
                modifier = Modifier
                    .aspectRatio(1F)
                    .weight(1F),
                onClick = { viewModel.operationPressed(Operations.Add) },
                content = "+"
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(spacing)
        ) {
            CalculatorButton(
                modifier = Modifier
                    .aspectRatio(2.1F)
                    .weight(2.1F),
                onClick = { viewModel.numberPressed("0") },
                content = "0"
            )
            CalculatorButton(
                modifier = Modifier
                    .aspectRatio(1F)
                    .weight(1F),
                onClick = { viewModel.dotPressed() },
                content = "."
            )
            CalculatorButton(
                modifier = Modifier
                    .aspectRatio(1F)
                    .weight(1F),
                onClick = { viewModel.calculate() },
                content = "="
            )
        }
    }
}

//}

@Preview
@Composable
fun CalculatorPreview() {
    CoCalculatorTheme {
        Calculator()
    }
}