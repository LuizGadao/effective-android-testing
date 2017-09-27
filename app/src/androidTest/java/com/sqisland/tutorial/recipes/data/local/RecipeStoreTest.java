package com.sqisland.tutorial.recipes.data.local;

import android.content.Context;
import android.support.test.InstrumentationRegistry;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Luiz on 27/09/17.
 */
public class RecipeStoreTest {

    @Test
    public void nullDirectory() throws Exception {
        Context mContext = InstrumentationRegistry.getTargetContext();
        RecipeStore mRecipeStore = new RecipeStore(mContext, null);

        assertNotNull(mRecipeStore);
        assertNotNull(mRecipeStore.getRecipes());
        assertEquals(0, mRecipeStore.getRecipes().size());
    }

    @Test
    public void checkTotalFiles() throws Exception {
        Context mContext = InstrumentationRegistry.getTargetContext();
        RecipeStore mRecipeStore = new RecipeStore(mContext, "recipes");

        assertNotNull(mRecipeStore);
        assertNotNull(mRecipeStore.getRecipes());
        assertEquals(4, mRecipeStore.getRecipes().size());
    }

    @Test
    public void getRecipeById() throws Exception {
        Context mContext = InstrumentationRegistry.getTargetContext();
        RecipeStore mRecipeStore = new RecipeStore(mContext, "recipes");

        String id = "chocolate_pudding";
        String title = "Chocolate Pudding";

        assertEquals(id, mRecipeStore.getRecipeById(id).id);
        assertEquals(title, mRecipeStore.getRecipeById(id).title);
    }
}