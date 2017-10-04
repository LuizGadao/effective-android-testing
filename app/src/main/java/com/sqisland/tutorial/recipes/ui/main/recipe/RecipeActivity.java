package com.sqisland.tutorial.recipes.ui.main.recipe;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.sqisland.tutorial.recipes.R;
import com.sqisland.tutorial.recipes.data.local.RecipeStore;
import com.sqisland.tutorial.recipes.data.local.SharePreferenceFavorites;
import com.sqisland.tutorial.recipes.data.model.Recipe;

public class RecipeActivity extends AppCompatActivity {

    public static final String KEY_ID = "id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        TextView tvTitle = findViewById(R.id.tv_title);
        TextView tvDescription = findViewById(R.id.tv_description);

        RecipeStore recipes = new RecipeStore(this, "recipes");
        String id = getIntent().getStringExtra(KEY_ID);

        Recipe recipe = recipes.getRecipeById(id);

        if (recipe == null){
            tvTitle.setVisibility(View.GONE);
            tvDescription.setText(R.string.recipe_not_found);
            return;
        }

        SharePreferenceFavorites mPrefsFavorite = new SharePreferenceFavorites(this);

        tvTitle.setText(recipe.title);
        tvTitle.setSelected(mPrefsFavorite.getId(id));
        tvTitle.setOnClickListener((view) -> {
            tvTitle.setSelected(mPrefsFavorite.toglle(id));
        });

        tvDescription.setText(recipe.description);
    }
}