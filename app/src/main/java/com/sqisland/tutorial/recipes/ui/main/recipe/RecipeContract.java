package com.sqisland.tutorial.recipes.ui.main.recipe;

/**
 * Created by Luiz on 09/10/17.
 */

public class RecipeContract {

    interface View{
        void showRecipeNotFoundError();
        void setTitle(String title);
        void setDescription(String description);
        void setFavorite(boolean favorite);
    }

    interface Listener{
        void toggleFavorite();
    }

}
