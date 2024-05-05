package com.jkjamies.nasa.playground.presentation.home

import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
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
class HomeScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun homeScreenLoad_verifyApodAndNeosAreDisplayed() {
        // Start the app on the home screen
        composeTestRule.setContent {
            NASAPlaygroundTheme {
                NasaPlaygroundNavHost()
            }
        }

        // wait 3s for the home screen to display
        composeTestRule.waitUntil(3000) {
            composeTestRule.onAllNodesWithTag("neoCard")[0].isDisplayed()
        }

        // Verify the Neo items (at least one) is displayed
        composeTestRule.onAllNodesWithTag("neoCard")[0].isDisplayed()

        // Verify the Apod image is displayed
        composeTestRule.onNodeWithTag("apodImage").isDisplayed()

        // Click to toggle showing the Apod explanation
        composeTestRule.onNodeWithTag("apodExplanationToggle").performClick()

        // Verify the Apod image explanation is displayed
        composeTestRule.onNodeWithTag("apodExplanation").isDisplayed()

        // Etc?
    }
}
