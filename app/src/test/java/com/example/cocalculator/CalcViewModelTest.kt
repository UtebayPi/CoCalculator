package com.example.cocalculator

import org.junit.Test

class CalcViewModelTest {
    private val viewModel = CalcViewModel()

    @Test
    fun calcViewModel_DotPressed_NotShown() {
        viewModel.dotPressed()
        val displayedText = viewModel.number1.value +
                (viewModel.operation.value?.symbol ?: "") +
                viewModel.number2.value
        assert(displayedText == "")
    }

    @Test
    fun calcViewModel_ZeroPressedTwice_OnlyOneZeroShown() {
        viewModel.numberPressed("0")
        viewModel.numberPressed("0")
        val displayedText = viewModel.number1.value +
                (viewModel.operation.value?.symbol ?: "") +
                viewModel.number2.value
        assert(displayedText == "0")
    }

    @Test
    fun calcViewModel_writeAllValuesAndPerformCalculation_CorrectResultIsShown() {
        viewModel.numberPressed("2")
        viewModel.numberPressed("5")
        viewModel.operationPressed(Operations.Divide)
        viewModel.numberPressed("4")
        viewModel.calculate()
        val displayedText = viewModel.number1.value +
                (viewModel.operation.value?.symbol ?: "") +
                viewModel.number2.value
        assert(displayedText == "6.25")
    }
}