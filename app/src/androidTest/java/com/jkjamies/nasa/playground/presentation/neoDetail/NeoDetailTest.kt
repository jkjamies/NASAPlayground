package com.jkjamies.nasa.playground.presentation.neoDetail

import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
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
class NeoDetailTest {
    @get:Rule
    val composeTestRule = createComposeRule()

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

        // Verify the earth animation lottie is displayed
        composeTestRule.onNodeWithTag("earthAnimationLottie").isDisplayed()

        // Verify the neo animation lottie is displayed
        composeTestRule.onNodeWithTag("neoAnimationLottie").isDisplayed()

        // Etc?
    }
}
