package com.sqisland.tutorial.recipes.ui.main.recipe;

import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;

import com.sqisland.tutorial.recipes.R;
import com.sqisland.tutorial.recipes.data.local.InMemoryFavorites;
import com.sqisland.tutorial.recipes.injection.TestRecipeApplication;

import org.junit.Before;
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

    public static final String CREAMED_CARROTS = "creamed_carrots";

    @Rule
    public ActivityTestRule<RecipeActivity> mActivityRule = new ActivityTestRule<RecipeActivity>(
            RecipeActivity.class,true, false);

    private InMemoryFavorites mFavorites;

    @Before
    public void clearFavorites(){
        TestRecipeApplication app = (TestRecipeApplication) InstrumentationRegistry.getTargetContext().getApplicationContext();
        mFavorites = (InMemoryFavorites) app.getFavorites();
        mFavorites.clear();
    }

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
        launchRecipe(CREAMED_CARROTS);

        onView(withId(R.id.tv_title))
                .check(matches(isDisplayed()));

        onView(withId(R.id.tv_title))
                .check(matches(withText("Creamed Carrots")))
                .check(matches(not(isSelected())))
                .perform(click())
                .check(matches(isSelected()));
    }

    @Test
    public void alreadyFavorite() throws Exception {
        mFavorites.put(CREAMED_CARROTS, true);
        launchRecipe(CREAMED_CARROTS);
        onView(withId(R.id.tv_title))
                .check(matches(isSelected()));
    }

    private void launchRecipe(String id) {
        Intent intent = new Intent();
        intent.putExtra(RecipeActivity.KEY_ID, id);

        mActivityRule.launchActivity(intent);
    }
}