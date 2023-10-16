package com.example.direction

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.After

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    private lateinit var scenario: ActivityScenario<Home>

    @Before
    fun setup() {
        scenario = ActivityScenario.launch(Home::class.java)
    }

    @After
    fun cleanup() {
        scenario.close()
    }

    @Test
    fun swipeLeft() {
        onView(withId(R.id.textView)).check(matches(withText("Home")))
        onView(withId(R.id.main)).perform(ViewActions.swipeLeft())
        onView(withId(R.id.textView)).check(matches(withText("East")))
    }

    @Test
    fun swipeLeftThenBack() {
        onView(withId(R.id.textView)).check(matches(withText("Home")))
        onView(withId(R.id.main)).perform(ViewActions.swipeLeft())
        onView(withId(R.id.textView)).check(matches(withText("East")))
        onView(withId(R.id.main)).perform(ViewActions.swipeRight())
        onView(withId(R.id.textView)).check(matches(withText("Home")))
    }
}