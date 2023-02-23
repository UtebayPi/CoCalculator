package com.utebaypi.cocalculator

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.utebaypi.cocalculator.ui.theme.CoCalculatorTheme
import org.junit.Rule
import org.junit.Test

class CalculatorUITest {

    @get:Rule
    val rule = createComposeRule()

    private fun getClickableTextMatcher(text: String) = hasText(text) and hasClickAction()

    @Test
    fun calculatorComposable_WriteInputAndPerformCalculation_CorrectResultShown() {
        rule.setContent {
            CoCalculatorTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Calculator()
                }
            }
        }

        rule.onNode(getClickableTextMatcher("2")).performClick()
        rule.onNode(getClickableTextMatcher("2")).performClick()
        rule.onNodeWithText("22").assertExists()
        rule.onNodeWithText("-").performClick()
        rule.onNodeWithText("3").performClick()
        rule.onNodeWithText("7").performClick()
        rule.onNodeWithText("=").performClick()
        rule.onNodeWithText("-15").assertExists()
    }
}