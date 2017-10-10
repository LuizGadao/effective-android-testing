package com.sqisland.tutorial.recipes.ui.main.recipe;

import com.sqisland.tutorial.recipes.data.local.Favorites;
import com.sqisland.tutorial.recipes.data.local.RecipeStore;
import com.sqisland.tutorial.recipes.data.model.Recipe;

/**
 * Created by Luiz on 09/10/17.
 */

public class RecipePresenter implements RecipeContract.Listener{

    private final RecipeContract.View mView;
    private final Favorites mFavorites;
    private RecipeStore mStore;
    private Recipe recipe;

    public RecipePresenter(RecipeStore store, RecipeContract.View view, Favorites favorites) {
        mStore = store;
        mView = view;
        mFavorites = favorites;
    }

    public void loadRecipe(String id){
        recipe = mStore.getRecipeById(id);

        if (recipe == null){
            mView.showRecipeNotFoundError();
        }else {
            mView.setTitle(recipe.title);
            mView.setDescription(recipe.description);
            mView.setFavorite(mFavorites.get(recipe.id));
        }

    }

    @Override
    public void toggleFavorite() {
        if (recipe == null){
            throw new IllegalStateException();
        }

        boolean toggle = mFavorites.toggle(recipe.id);
        mView.setFavorite(toggle);
    }
}
