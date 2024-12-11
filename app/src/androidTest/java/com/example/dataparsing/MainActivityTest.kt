package com.example.dataparsing

import android.widget.TextView
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.hamcrest.Matchers.allOf

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun testCurrencyListDisplayed() {
        onView(withId(R.id.currencyList)).check(matches(isDisplayed()))
    }

    @Test
    fun testLoadingPlaceholderDisplayed() {
        onView(withText("Loading currencies...")).check(matches(isDisplayed()))
    }

    @Test
    fun testCurrencySearchFilterCorrectCurrencyDisplayed() {
        val expectedText = "EUR - 0.94669152"

        onView(allOf(isAssignableFrom(TextView::class.java), withText(expectedText)))
            .check(matches(isDisplayed()))
    }

    @Test
    fun testCurrencySearchFunctionality() {

        val searchQuery = "EUR"
        onView(withId(R.id.searchField)).perform(typeText(searchQuery))

        onView(withText("EUR - 0.94669152")).check(matches(isDisplayed()))
    }

    @Test
    fun testCurrencySearchNoResults() {
        val searchQuery = "XYZ"
        onView(withId(R.id.searchField)).perform(typeText(searchQuery))

        onView(withText("No results found")).check(matches(isDisplayed()))
    }
}
