package com.sqisland.tutorial.recipes.ui.main.recipe;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.sqisland.tutorial.recipes.R;
import com.sqisland.tutorial.recipes.data.local.Favorites;
import com.sqisland.tutorial.recipes.data.local.RecipeStore;
import com.sqisland.tutorial.recipes.injection.RecipeApplication;

public class RecipeActivity extends AppCompatActivity implements RecipeContract.View{

    public static final String KEY_ID = "id";
    private TextView tvTitle;
    private TextView tvDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        tvTitle = findViewById(R.id.tv_title);
        tvDescription = findViewById(R.id.tv_description);

        RecipeStore recipes = new RecipeStore(this, "recipes");
        String id = getIntent().getStringExtra(KEY_ID);

        RecipeApplication app = (RecipeApplication) getApplication();
        Favorites mPrefsFavorite = app.getFavorites();

        RecipePresenter mPresenter = new RecipePresenter(recipes, this, mPrefsFavorite);
        mPresenter.loadRecipe(id);


        tvTitle.setOnClickListener((view) -> {
            mPresenter.toggleFavorite();
        });

    }

    @Override
    public void showRecipeNotFoundError() {
        tvTitle.setVisibility(View.GONE);
        tvDescription.setText(R.string.recipe_not_found);
    }

    @Override
    public void setTitle(String title) {
        tvTitle.setText(title);
    }

    @Override
    public void setDescription(String description) {
        tvDescription.setText(description);
    }

    @Override
    public void setFavorite(boolean favorite) {
        tvTitle.setSelected(favorite);
    }
}