package com.google.developers.mojimaster2;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.preference.PreferenceManager;

import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;

import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.Matchers.is;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class UITestforFabButton {

    @Rule
    public ActivityTestRule mactvityrule =new ActivityTestRule<>(MainActivity.class);


    @Test
    public void fabButtonTest(){
        onView(withId(R.id.fab)).perform(click());
        onView(withId(R.id.emoji_char)).check(matches(isDisplayed()));
    }



    @Test
    public void SharedPrefTest(){

        int chCount;
        String key = "pref_key_answers";

        int[] value = new int[3];
        value[0]=2;
        value[1]=3;
        value[2]=4;

        String[] str =mactvityrule.getActivity().getResources().getStringArray(R.array.number_smileys_answers);



        Context context = getInstrumentation().getTargetContext();
        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());

        onView(withText("Settings"))
                .perform(click());


        SharedPreferences preferencesEditor;

        //loop from here
        int i;
        for(i=0;i<3;i++) {
            onView(withId(androidx.preference.R.id.recycler_view)).perform(RecyclerViewActions.actionOnItemAtPosition(1, click()));

            //onView(withText(str[i])).perform(click());
            onData(is(str[i])).perform(click());


            preferencesEditor = PreferenceManager.getDefaultSharedPreferences(context);

            chCount = (Integer.valueOf(preferencesEditor.getString(key, "2")));
           // chCount = chCount+1;


            Log.i("itsthomahere", "SharedPrefTest: " + chCount);

            assertTrue(chCount==value[i]);

        }
    }
}
