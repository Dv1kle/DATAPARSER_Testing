package com.example.dataparsing

import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Test
import org.junit.runner.RunWith
import org.hamcrest.Matchers.containsString

@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    @Test
    fun appLaunchesSuccessfully() {
        // Launch MainActivity
        val scenario = ActivityScenario.launch(MainActivity::class.java)

        // Verify MainActivity is displayed
        onView(withId(R.id.currencyList)).check(matches(isDisplayed()))
    }

    @Test
    fun testFilteringCurrencies() {
        // Launch MainActivity
        ActivityScenario.launch(MainActivity::class.java)

        // Type "USD" in the search field
        onView(withId(R.id.searchField)).perform(typeText("USD"), closeSoftKeyboard())

        // Verify that the list shows filtered results
        onView(withId(R.id.currencyList))
            .check(matches(hasDescendant(withText(containsString("USD")))))
    }

    @Test
    fun testParsedDataDisplayed() {
        // Launch MainActivity
        ActivityScenario.launch(MainActivity::class.java)

        // Wait for data to load
        Thread.sleep(2000) // Temporary wait; use IdlingResources in real tests

        // Verify that the list is populated
        onView(withId(R.id.currencyList))
            .check(matches(hasMinimumChildCount(1)))
    }

    @Test
    fun useAppContext() {
        // Context of the app under test
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assert(appContext.packageName == "com.example.dataparsing")
    }
}
