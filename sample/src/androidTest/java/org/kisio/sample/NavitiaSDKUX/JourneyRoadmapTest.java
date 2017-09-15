package org.kisio.sample.NavitiaSDKUX;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.IdlingResource;
import android.support.test.espresso.ViewInteraction;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.facebook.litho.LithoView;
import com.facebook.litho.LithoViewTestHelper;
import com.facebook.litho.testing.espresso.ComponentHostMatchers;
import com.facebook.litho.testing.espresso.LithoActivityTestRule;
import com.facebook.litho.testing.espresso.LithoViewMatchers;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kisio.NavitiaSDK.models.Journey;
import org.kisio.NavitiaSDKUX.Controllers.JourneySolutionRoadmapActivity;
import org.kisio.NavitiaSDKUX.Controllers.JourneySolutionsActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withTagValue;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.facebook.litho.testing.espresso.ComponentHostMatchers.componentHost;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class JourneyRoadmapTest {
    @Rule
    public ActivityTestRule<MainActivity> mainActivity = new ActivityTestRule<>(MainActivity.class);
    @Rule
    public LithoActivityTestRule<JourneySolutionsActivity> solutionsActivity = new LithoActivityTestRule<>(JourneySolutionsActivity.class);
    @Rule
    public LithoActivityTestRule<JourneySolutionRoadmapActivity> roadmapActivity = new LithoActivityTestRule<>(JourneySolutionRoadmapActivity.class);

    @Test
    public void testRoadmapIsAccessible() {
        // Given I am on the home screen
        //   And I click on the "Open SDK UX" button
        ViewInteraction openSDKUXButton = onView(
            withId(R.id.sdk_open)
        );
        openSDKUXButton.perform(click());

        // When I click on the first solution
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ViewInteraction firstJourneyResult = onView(
            LithoViewMatchers.withTestKey("result-1")
        );
        firstJourneyResult.perform(click());

        // Then I am on the roadmap of the journey
        ViewInteraction roadmap = onView(
            LithoViewMatchers.withTestKey("roadmap")
        );
        roadmap.check(matches(isDisplayed()));
    }

    @Test
    public void testSummaryIsPresent() {
        // Given I am on the home screen
        //   And I click on the "Open SDK UX" button
        ViewInteraction openSDKUXButton = onView(
            withId(R.id.sdk_open)
        );
        openSDKUXButton.perform(click());

        // When I click on the first solution
        ViewInteraction firstJourneyResult = onView(
            LithoViewMatchers.withTestKey("result-1")
        );
        firstJourneyResult.perform(click());

        // Then the journey summary is visible
        ViewInteraction journeySummary = onView(
            LithoViewMatchers.withTestKey("summary")
        );
        journeySummary.check(matches(isDisplayed()));
    }

    @Test
    public void testSectionHasAllInformation() {
        // Given I am on the home screen
        //   And I click on the "Open SDK UX" button
        ViewInteraction openSDKUXButton = onView(
            withId(R.id.sdk_open)
        );
        openSDKUXButton.perform(click());

        // When I click on the first solution
        ViewInteraction firstJourneyResult = onView(
            LithoViewMatchers.withTestKey("result-1")
        );
        firstJourneyResult.perform(click());
    }
}
