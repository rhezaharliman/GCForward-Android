package com.rheza.gcforward

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    val mMainActivity = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun showProgressBarOnSearch() {
        onView(withId(R.id.btn_search)).perform(ViewActions.click())
        onView(withId(R.id.progress_circular)).check(matches(isDisplayed()))
    }

    // TODO: tests below is not yet implemented
    /*
     * Test when the search is finished
     * Test when the edit text is empty
     * Test when the progress bar is gone
     * etc
     */
}