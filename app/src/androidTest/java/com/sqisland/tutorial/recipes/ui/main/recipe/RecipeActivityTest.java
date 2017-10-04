package com.sqisland.tutorial.recipes.ui.main.recipe;

import android.content.Intent;
import android.support.test.rule.ActivityTestRule;

import com.sqisland.tutorial.recipes.R;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isSelected;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.IsNot.not;

/**
 * Created by Luiz on 03/10/17.
 */
public class RecipeActivityTest {

    @Rule
    public ActivityTestRule<RecipeActivity> mActivityRule = new ActivityTestRule<RecipeActivity>(
            RecipeActivity.class,true, false);

    @Test
    public void recipeNotFound() throws Exception {
        mActivityRule.launchActivity(null);

        onView(withId(R.id.tv_description))
                .check(matches(withText(R.string.recipe_not_found)));
        onView(withId(R.id.tv_title))
                .check(matches(not(isDisplayed())));
    }

    @Test
    public void clickToFavorite() throws Exception {
        Intent intent = new Intent();
        intent.putExtra(RecipeActivity.KEY_ID, "creamed_carrots");

        mActivityRule.launchActivity(intent);

        onView(withId(R.id.tv_title))
                .check(matches(isDisplayed()));

        onView(withId(R.id.tv_title))
                .check(matches(withText("Creamed Carrots")))
                .check(matches(not(isSelected())))
                .perform(click())
                .check(matches(isSelected()));
    }
}