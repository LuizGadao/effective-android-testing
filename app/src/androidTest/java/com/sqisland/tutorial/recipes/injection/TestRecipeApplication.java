package com.sqisland.tutorial.recipes.injection;

import com.sqisland.tutorial.recipes.data.local.Favorites;
import com.sqisland.tutorial.recipes.data.local.InMemoryFavorites;

/**
 * Created by Luiz on 04/10/17.
 */

public class TestRecipeApplication extends RecipeApplication {

    Favorites mFavorites = new InMemoryFavorites();

    @Override
    public Favorites getFavorites() {
        return mFavorites;
    }
}
