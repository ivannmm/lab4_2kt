package com.example.myapplication

import android.app.Activity
import android.content.pm.ActivityInfo
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso
import androidx.test.espresso.NoActivityResumedException
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.Matchers.not
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class NavigationTest {
    @Test
    fun testAbout() {
        launchActivity<MainActivity>()
        openAbout()
        Espresso.onView(withId(R.id.activity_about))
            .check(matches(isDisplayed()))
    }

    @Test
    fun myTest1() {
        launchActivity<MainActivity>()
        Espresso.onView(withId(R.id.bnToSecond)).perform(click())
        Espresso.onView(withId(R.id.fragment2)).check(matches(isDisplayed()))
    }

    @Test
    fun myTest2() {
        launchActivity<MainActivity>()
        Espresso.onView(withId(R.id.bnToSecond)).perform(click())
        Espresso.onView(withId(R.id.fragment2)).check(matches(isDisplayed()))

        Espresso.onView(withId(R.id.bnToThird)).perform(click())
        Espresso.onView(withId(R.id.fragment3)).check(matches(isDisplayed()))
    }

    @Test
    fun myTest3() {
        launchActivity<MainActivity>()
        Espresso.onView(withId(R.id.bnToSecond)).check(matches(isDisplayed()))

        Espresso.onView(withId(R.id.bnToSecond)).perform(click())
        Espresso.onView(withId(R.id.fragment2)).check(matches(isDisplayed()))
        Espresso.onView(withId(R.id.bnToFirst)).check(matches(isDisplayed()))
        Espresso.onView(withId(R.id.bnToThird)).check(matches(isDisplayed()))

        Espresso.onView(withId(R.id.bnToThird)).perform(click())
        Espresso.onView(withId(R.id.fragment3)).check(matches(isDisplayed()))
        Espresso.onView(withId(R.id.bnToFirst)).check(matches(isDisplayed()))
        Espresso.onView(withId(R.id.bnToSecond)).check(matches(isDisplayed()))

        Espresso.pressBack()
        Espresso.onView(withId(R.id.fragment2)).check(matches(isDisplayed()))

        Espresso.pressBack()
        Espresso.onView(withId(R.id.fragment1)).check(matches(isDisplayed()))

        openAbout()
        Espresso.onView(withId(R.id.activity_about)).check(matches(isDisplayed()))
    }

    @Test
    fun myTest4() {
        launchActivity<MainActivity>()
        Espresso.onView(withId(R.id.bnToSecond)).perform(click())
        Espresso.onView(withId(R.id.fragment2)).check(matches(isDisplayed()))

        openAbout()
        Espresso.onView(withId(R.id.activity_about)).check(matches(isDisplayed()))

        Espresso.pressBack()
        Espresso.onView(withId(R.id.fragment2)).check(matches(isDisplayed()))

        Espresso.pressBack()
        Espresso.onView(withId(R.id.fragment1)).check(matches(isDisplayed()))
    }

    @Test
    fun myTest5() {
        launchActivity<MainActivity>()
        Espresso.onView(withId(R.id.bnToSecond)).perform(click())
        Espresso.onView(withId(R.id.fragment2)).check(matches(isDisplayed()))

        Espresso.onView(withId(R.id.bnToThird)).perform(click())
        Espresso.onView(withId(R.id.fragment3)).check(matches(isDisplayed()))

        openAbout()
        Espresso.onView(withId(R.id.activity_about)).check(matches(isDisplayed()))

        Espresso.pressBack()
        Espresso.onView(withId(R.id.fragment3)).check(matches(isDisplayed()))

        Espresso.pressBack()
        Espresso.onView(withId(R.id.fragment2)).check(matches(isDisplayed()))

        Espresso.pressBack()
        Espresso.onView(withId(R.id.fragment1)).check(matches(isDisplayed()))
    }

    @Test
    fun myTest6() {
        val activity = launchActivity<MainActivity>()
        Espresso.onView(withId(R.id.bnToSecond)).perform(click())
        Espresso.onView(withId(R.id.fragment2)).check(matches(isDisplayed()))

        Espresso.onView(withId(R.id.bnToThird)).perform(click())
        Espresso.onView(withId(R.id.fragment3)).check(matches(isDisplayed()))
        Espresso.onView(withId(R.id.bnToFirst)).check(matches(isDisplayed()))
        Espresso.onView(withId(R.id.bnToSecond)).check(matches(isDisplayed()))

        activity.onActivity { activity ->
            activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        }
        Thread.sleep(3000)

        Espresso.onView(withId(R.id.fragment3)).check(matches(isDisplayed()))
        Espresso.onView(withId(R.id.bnToFirst)).check(matches(isDisplayed()))
        Espresso.onView(withId(R.id.bnToSecond)).check(matches(isDisplayed()))

        activity.onActivity { activity ->
            activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        }
        Thread.sleep(3000)

        Espresso.onView(withId(R.id.fragment3)).check(matches(isDisplayed()))
        Espresso.onView(withId(R.id.bnToFirst)).check(matches(isDisplayed()))
        Espresso.onView(withId(R.id.bnToSecond)).check(matches(isDisplayed()))
    }

    @Test
    fun myTest7() {
        val activity = launchActivity<MainActivity>()
        Espresso.onView(withId(R.id.bnToSecond)).perform(click())

        Espresso.onView(withId(R.id.fragment2)).check(matches(isDisplayed()))
        Espresso.onView(withId(R.id.bnToFirst)).check(matches(isDisplayed()))
        Espresso.onView(withId(R.id.bnToThird)).check(matches(isDisplayed()))

        activity.onActivity { activity ->
            activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        }
        Thread.sleep(3000)

        Espresso.onView(withId(R.id.fragment2)).check(matches(isDisplayed()))
        Espresso.onView(withId(R.id.bnToFirst)).check(matches(isDisplayed()))
        Espresso.onView(withId(R.id.bnToThird)).check(matches(isDisplayed()))

        activity.onActivity { activity ->
            activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        }
        Thread.sleep(3000)

        Espresso.onView(withId(R.id.fragment2)).check(matches(isDisplayed()))
        Espresso.onView(withId(R.id.bnToFirst)).check(matches(isDisplayed()))
        Espresso.onView(withId(R.id.bnToThird)).check(matches(isDisplayed()))
    }

    @Test
    fun myTest8() {
        val activity = launchActivity<MainActivity>()

        Espresso.onView(withId(R.id.fragment1)).check(matches(isDisplayed()))
        Espresso.onView(withId(R.id.bnToSecond)).check(matches(isDisplayed()))

        activity.onActivity { activity ->
            activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        }
        Thread.sleep(3000)

        Espresso.onView(withId(R.id.fragment1)).check(matches(isDisplayed()))
        Espresso.onView(withId(R.id.bnToSecond)).check(matches(isDisplayed()))

        activity.onActivity { activity ->
            activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        }
        Thread.sleep(3000)

        Espresso.onView(withId(R.id.fragment1)).check(matches(isDisplayed()))
        Espresso.onView(withId(R.id.bnToSecond)).check(matches(isDisplayed()))
    }

    @get:Rule
    val activityScenario = ActivityScenarioRule(MainActivity::class.java)

    // Backstack test

    @Test
    fun myTest9() {
        launchActivity<MainActivity>()
        Espresso.pressBackUnconditionally()
        assertEquals(activityScenario.scenario.state, Lifecycle.State.DESTROYED)
    }

    @Test
    fun myTest10() {
        launchActivity<MainActivity>()
        Espresso.onView(withId(R.id.bnToSecond)).perform(click())
        Espresso.pressBack()
        Espresso.pressBackUnconditionally()
        assertEquals(activityScenario.scenario.state, Lifecycle.State.DESTROYED)
    }

    @Test
    fun myTest11() {
        launchActivity<MainActivity>()
        Espresso.onView(withId(R.id.bnToSecond)).perform(click())
        Espresso.onView(withId(R.id.bnToThird)).perform(click())
        Espresso.pressBack()
        Espresso.pressBack()
        Espresso.pressBackUnconditionally()
        assertEquals(activityScenario.scenario.state, Lifecycle.State.DESTROYED)
    }

    @Test
    fun myTest12() {
        launchActivity<MainActivity>()
        Espresso.onView(withId(R.id.bnToSecond)).perform(click())
        Espresso.onView(withId(R.id.bnToThird)).perform(click())
        openAbout()
        Espresso.pressBack()
        Espresso.pressBack()
        Espresso.pressBack()
        Espresso.pressBackUnconditionally()
        assertEquals(activityScenario.scenario.state, Lifecycle.State.DESTROYED)
    }

    @Test
    fun myTest13() {
        launchActivity<MainActivity>()
        Espresso.onView(withId(R.id.bnToSecond)).perform(click())
        openAbout()
        Espresso.pressBack()
        Espresso.pressBack()
        Espresso.pressBackUnconditionally()
        assertEquals(activityScenario.scenario.state, Lifecycle.State.DESTROYED)
    }

    @Test
    fun myTest14() {
        launchActivity<MainActivity>()
        openAbout()
        Espresso.pressBack()
        Espresso.pressBackUnconditionally()
        assertEquals(activityScenario.scenario.state, Lifecycle.State.DESTROYED)
    }

    @Test
    fun myTest15() {
        Espresso.onView(withId(R.id.bnToSecond)).perform(click())
        Espresso.onView(withId(R.id.bnToThird)).perform(click())
        openAbout()
        Espresso.pressBack()
        Espresso.pressBack()
        Espresso.pressBackUnconditionally()
        assertFalse(activityScenario.scenario.state == Lifecycle.State.DESTROYED)
    }

    //Up test

    @Test
    fun myTest16() {
        try {
            Espresso.onView(withContentDescription(R.string.nav_app_bar_navigate_up_description)).perform(click())
            assert(false)
        } catch (e : Exception) {
            assert(true)
        }
    }

    @Test
    fun myTest17() {
        Espresso.onView(withId(R.id.bnToSecond)).perform(click())
        Espresso.onView(withContentDescription(R.string.nav_app_bar_navigate_up_description)).perform(click())
        Espresso.onView(withId(R.id.fragment1)).check(matches(isDisplayed()))

        myTest16()
    }

    @Test
    fun myTest18() {
        Espresso.onView(withId(R.id.bnToSecond)).perform(click())
        Espresso.onView(withId(R.id.bnToThird)).perform(click())
        Espresso.onView(withContentDescription(R.string.nav_app_bar_navigate_up_description)).perform(click())
        Espresso.onView(withId(R.id.fragment2)).check(matches(isDisplayed()))
        Espresso.onView(withContentDescription(R.string.nav_app_bar_navigate_up_description)).perform(click())
        Espresso.onView(withId(R.id.fragment1)).check(matches(isDisplayed()))

        myTest16()
    }

    @Test
    fun myTest19() {
        openAbout()
        Espresso.onView(withId(R.id.activity_about)).check(matches(isDisplayed()))
        Espresso.onView(withContentDescription(R.string.nav_app_bar_navigate_up_description)).perform(click())
        Espresso.onView(withId(R.id.fragment1)).check(matches(isDisplayed()))
        myTest16()

        Espresso.onView(withId(R.id.bnToSecond)).perform(click())
        openAbout()
        Espresso.onView(withId(R.id.activity_about)).check(matches(isDisplayed()))
        Espresso.onView(withContentDescription(R.string.nav_app_bar_navigate_up_description)).perform(click())
        Espresso.onView(withId(R.id.fragment2)).check(matches(isDisplayed()))
        Espresso.onView(withContentDescription(R.string.nav_app_bar_navigate_up_description)).perform(click())
        Espresso.onView(withId(R.id.fragment1)).check(matches(isDisplayed()))
        myTest16()


        Espresso.onView(withId(R.id.bnToSecond)).perform(click())
        Espresso.onView(withId(R.id.bnToThird)).perform(click())
        openAbout()
        Espresso.onView(withId(R.id.activity_about)).check(matches(isDisplayed()))
        Espresso.onView(withContentDescription(R.string.nav_app_bar_navigate_up_description)).perform(click())
        Espresso.onView(withId(R.id.fragment3)).check(matches(isDisplayed()))
        Espresso.onView(withContentDescription(R.string.nav_app_bar_navigate_up_description)).perform(click())
        Espresso.onView(withId(R.id.fragment2)).check(matches(isDisplayed()))
        Espresso.onView(withContentDescription(R.string.nav_app_bar_navigate_up_description)).perform(click())
        Espresso.onView(withId(R.id.fragment1)).check(matches(isDisplayed()))
        myTest16()
    }
}