package com.example.cocalculator

import org.junit.Test

class CalcViewModelTest {
    private val viewModel = CalcViewModel()

    @Test
    fun calcViewModel_DotPressed_NotShown() {
        viewModel.dotPressed()
        assert(viewModel.state.getCalculatorText() == "")
    }

    @Test
    fun calcViewModel_ZeroPressedTwice_OnlyOneZeroShown() {
        viewModel.numberPressed("0")
        viewModel.numberPressed("0")
        assert(viewModel.state.getCalculatorText() == "0")
    }

    @Test
    fun calcViewModel_writeAllValuesAndPerformCalculation_CorrectResultIsShown() {
        viewModel.numberPressed("2")
        viewModel.numberPressed("5")
        viewModel.operationPressed(Operations.Divide)
        viewModel.numberPressed("4")
        viewModel.calculate()
        assert(viewModel.state.getCalculatorText() == "6.25")
    }
}