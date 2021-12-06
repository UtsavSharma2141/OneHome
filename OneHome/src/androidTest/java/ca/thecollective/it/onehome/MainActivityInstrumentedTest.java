package ca.thecollective.it.onehome;

import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.pressBack;
import static androidx.test.espresso.action.ViewActions.pressMenuKey;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withResourceName;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static java.util.regex.Pattern.matches;

@RunWith(AndroidJUnit4.class)
public class MainActivityInstrumentedTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityScenarioRule = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void isButtonClicked(){
        onView(withId(R.id.review_us))
                .perform(click());
    }

    @Test
    public void isTextCorrected(){
        onView(withText("Review Us")).perform(click());
    }

    @Test
    public void isImageClicked(){
        onView(withId(R.id.welcome_img)).perform(click());
    }

    @Test
    public void isTextSelected(){
        onView(withId(R.id.welcome_screen_message)).perform(click());
    }

    @Test
    public void contactUsSelected(){
        onView(withId(R.id.menu_contactus)).perform(click());
    }

}