package com.skhproject.classifiedapp.ui.fragment.home

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.skhproject.classifiedapp.R
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class HomeFragmentTest {
    @Test fun testEventFragment() {
        val scenario = launchFragmentInContainer<HomeFragment>()
        onView(withId(R.id.classifiedRv)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(12))

    }
}
