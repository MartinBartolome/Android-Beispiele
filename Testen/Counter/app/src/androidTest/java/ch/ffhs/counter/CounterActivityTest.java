package ch.ffhs.counter;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class CounterActivityTest {
    @Rule
    public ActivityTestRule<CounterActivity> mActivityRule
            = new ActivityTestRule<CounterActivity>(CounterActivity.class);

    @Test
    public void testStart() {
        try {
            Thread.sleep(1000);

            onView(withId(R.id.btn_up)).perform(click());
            onView(withId(R.id.txt_value)).check(matches(withText("1")));
            Thread.sleep(1000);


            onView(withId(R.id.btn_up)).perform(click());
            onView(withId(R.id.txt_value)).check(matches(withText("2")));
            Thread.sleep(1000);

            onView(withId(R.id.btn_up)).perform(click());
            onView(withId(R.id.txt_value)).check(matches(withText("3")));
            Thread.sleep(1000);

            onView(withId(R.id.btn_up)).perform(click());
            onView(withId(R.id.txt_value)).check(matches(withText("4")));
            Thread.sleep(1000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
