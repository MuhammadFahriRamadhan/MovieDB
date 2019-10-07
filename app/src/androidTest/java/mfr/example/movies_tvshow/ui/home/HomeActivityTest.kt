package mfr.example.movies_tvshow.ui.home


import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import mfr.example.movies_tvshow.R
import mfr.example.movies_tvshow.utils.EspressoIdlingResource
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class HomeActivityTest {

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.espressoIdlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.espressoIdlingResource)
    }

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(HomeActivity::class.java)

    @Test
    fun homeActivityTest() {
        val bottomNavigationItemView = onView(
            allOf(
                withId(R.id.Nav_Movie), withContentDescription("Movies"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.navigation),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        bottomNavigationItemView.perform(click())

        val bottomNavigationItemView2 = onView(
            allOf(
                withId(R.id.Nav_Movie), withContentDescription("Movies"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.navigation),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        bottomNavigationItemView2.perform(click())

        val cardView = onView(
            allOf(
                withId(R.id.crdviewmovies),
                childAtPosition(
                    allOf(
                        withId(R.id.rcv_movies),
                        childAtPosition(
                            withClassName(`is`("android.widget.RelativeLayout")),
                            0
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        cardView.perform(click())

        val floatingActionButton = onView(
            allOf(
                withId(R.id.floatdetail),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    3
                ),
                isDisplayed()
            )
        )
        floatingActionButton.perform(click())
        pressBack()

        val bottomNavigationItemView3 = onView(
            allOf(
                withId(R.id.Nav_TvShows), withContentDescription("Tv Shows"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.navigation),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        bottomNavigationItemView3.perform(click())

        val cardView2 = onView(
            allOf(
                childAtPosition(
                    allOf(
                        withId(R.id.rcv_tvshows),
                        childAtPosition(
                            withClassName(`is`("android.widget.RelativeLayout")),
                            0
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        cardView2.perform(click())

        val floatingActionButton2 = onView(
            allOf(
                withId(R.id.floatdetail),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    3
                ),
                isDisplayed()
            )
        )
        floatingActionButton2.perform(click())
        pressBack()
        val bottomNavigationItemView4 = onView(
            allOf(
                withId(R.id.Nav_Fav), withContentDescription("Favorite"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.navigation),
                        0
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        bottomNavigationItemView4.perform(click())

        val cardView3 = onView(
            allOf(
                withId(R.id.crdviewmovies),
                childAtPosition(
                    allOf(
                        withId(R.id.rcv_movies),
                        childAtPosition(
                            withClassName(`is`("android.widget.RelativeLayout")),
                            0
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        cardView3.perform(click())

        pressBack()

        val tabView = onView(
            allOf(
                withContentDescription("TV SHOWS"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.tabLayouts),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        tabView.perform(click())

        val cardView4 = onView(
            allOf(
                childAtPosition(
                    allOf(
                        withId(R.id.rcv_tvshows),
                        childAtPosition(
                            withClassName(`is`("android.widget.RelativeLayout")),
                            0
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        cardView4.perform(click())
        pressBack()

    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
}
