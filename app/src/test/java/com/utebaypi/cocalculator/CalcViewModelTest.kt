package com.utebaypi.cocalculator

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class CalcViewModelTest {

    private val viewModel = CalcViewModel()

    @Test
    fun calcViewModel_DotPressed_NotShown() = runTest {
        viewModel.dotPressed()
        val displayedText = viewModel.result.first()
        assert(displayedText == "")
    }

    @Test
    fun calcViewModel_ZeroPressedTwice_OnlyOneZeroShown() = runTest {
        viewModel.numberPressed("0")
        viewModel.numberPressed("0")
        val displayedText = viewModel.result.first()
        assert(displayedText == "0")
    }

    @Test
    fun calcViewModel_writeAllValuesAndPerformCalculation_CorrectResultIsShown() = runTest {
        viewModel.numberPressed("2")
        viewModel.numberPressed("5")
        viewModel.operationPressed(Operations.Divide)
        viewModel.numberPressed("4")
        viewModel.calculate()
        val displayedText = viewModel.result.first()
        assert(displayedText == "6.25")
    }
}