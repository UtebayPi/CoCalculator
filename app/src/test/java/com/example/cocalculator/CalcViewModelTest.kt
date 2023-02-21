package com.example.cocalculator

import app.cash.turbine.test
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class CalcViewModelTest {

    private val viewModel = CalcViewModel()
    private val testScope = TestScope()

    @Test
    fun calcViewModel_DotPressed_NotShown()= runTest {
        viewModel.dotPressed()
        val displayedText = viewModel.result.stateIn(testScope).value
        assert(displayedText == "")
    }

    @Test
    fun calcViewModel_ZeroPressedTwice_OnlyOneZeroShown() = runTest {
        viewModel.numberPressed("0")
        viewModel.numberPressed("0")
        val displayedText = viewModel.result.stateIn(testScope).value
        assert(displayedText == "0")
    }

    @Test
    fun calcViewModel_writeAllValuesAndPerformCalculation_CorrectResultIsShown()= runTest {
        viewModel.numberPressed("2")
        viewModel.numberPressed("5")
        viewModel.operationPressed(Operations.Divide)
        viewModel.numberPressed("4")
        viewModel.calculate()
        val displayedText = viewModel.result.stateIn(testScope).value
        assert(displayedText == "6.25")
    }
}