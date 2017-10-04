package com.sqisland.tutorial.recipes.injection;

import android.app.Application;

import com.sqisland.tutorial.recipes.data.local.Favorites;
import com.sqisland.tutorial.recipes.data.local.SharePreferenceFavorites;

/**
 * Created by Luiz on 04/10/17.
 */

public class RecipeApplication extends Application {

    private Favorites mFavorites;

    public Favorites getFavorites() {
        if (mFavorites == null){
            mFavorites = new SharePreferenceFavorites(this);
        }

        return mFavorites;
    }
}
