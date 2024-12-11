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
        val scenario = ActivityScenario.launch(MainActivity::class.java)

        onView(withId(R.id.currencyList)).check(matches(isDisplayed()))
    }

    @Test
    fun testFilteringCurrencies() {
        ActivityScenario.launch(MainActivity::class.java)
        onView(withId(R.id.searchField)).perform(typeText("USD"), closeSoftKeyboard())

        onView(withId(R.id.currencyList))
            .check(matches(hasDescendant(withText(containsString("USD")))))
    }

    @Test
    fun testParsedDataDisplayed() {
        ActivityScenario.launch(MainActivity::class.java)

        Thread.sleep(2000)

        onView(withId(R.id.currencyList))
            .check(matches(hasMinimumChildCount(1)))
    }

    @Test
    fun useAppContext() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assert(appContext.packageName == "com.example.dataparsing")
    }
}
