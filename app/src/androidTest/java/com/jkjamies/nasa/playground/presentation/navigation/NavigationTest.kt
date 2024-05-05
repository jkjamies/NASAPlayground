package com.jkjamies.nasa.playground.presentation.navigation

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.jkjamies.nasa.playground.presentation.NasaPlaygroundNavHost
import com.jkjamies.nasa.playground.ui.theme.NASAPlaygroundTheme
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

// For simplicity, we are using network calls and local database calls to get the screen populated
// and because of this, we are going to use generic titles to find items for click events

@RunWith(AndroidJUnit4::class)
class NavigationTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun navigateToNeoDetail_verifyNeoDetailDisplayed() {
        // Start the app on the home screen
        composeTestRule.setContent {
            NASAPlaygroundTheme {
                NasaPlaygroundNavHost()
            }
        }

        // wait 3s for the cards to display
        composeTestRule.waitUntil(3000) {
            composeTestRule.onAllNodesWithTag("neoCard")[0].isDisplayed()
        }

        // Click on a Neo item to navigate to the Neo Detail screen
        composeTestRule.onAllNodesWithTag("neoCard")[0].performClick()

        // wait 3s for the details to display
        composeTestRule.waitUntil(3000) {
            composeTestRule.onNodeWithTag("neoDetailContent").isDisplayed()
        }

        // Verify that the Neo Detail screen is displayed
        composeTestRule.onNodeWithTag("neoDetailContent").performClick()
    }

    @Test
    fun navigateBackFromNeoDetail_verifyHomeDisplayed() {
        // Start the app on the home screen
        composeTestRule.setContent {
            NASAPlaygroundTheme {
                NasaPlaygroundNavHost()
            }
        }

        // wait 3s for the cards to display
        composeTestRule.waitUntil(3000) {
            composeTestRule.onAllNodesWithTag("neoCard")[0].isDisplayed()
        }

        // Click on a Neo item to navigate to the Neo Detail screen
        composeTestRule.onAllNodesWithTag("neoCard")[0].performClick()

        // wait 3s for the back button to display
        composeTestRule.waitUntil(3000) {
            composeTestRule.onNodeWithContentDescription("Back").isDisplayed()
        }

        // Click the back button to navigate back to the home screen
        composeTestRule.onNodeWithContentDescription("Back").performClick()

        // Verify that the home screen is displayed
        composeTestRule.onNodeWithText("Near Earth Objects").assertIsDisplayed()
    }
}
