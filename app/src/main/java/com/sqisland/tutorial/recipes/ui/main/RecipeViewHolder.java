package com.sqisland.tutorial.recipes.ui.main;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.sqisland.tutorial.recipes.data.model.Recipe;
import com.sqisland.tutorial.recipes.ui.main.recipe.RecipeActivity;

/**
 * Created by Luiz on 03/10/17.
 */

public class RecipeViewHolder extends RecyclerView.ViewHolder {

    TextView mTextView;
    private Recipe mRecipe;

    public RecipeViewHolder(View itemView) {
        super(itemView);
        mTextView = (TextView) itemView;
        mTextView.setOnClickListener((view)-> gotoRecipeActivity());
    }

    private void gotoRecipeActivity() {
        Context context = mTextView.getContext();
        Intent intent = new Intent(context, RecipeActivity.class);
        intent.putExtra(RecipeActivity.KEY_ID, mRecipe.id);

        context.startActivity(intent);
    }

    public void onBind(Recipe recipe) {
        mRecipe = recipe;
        mTextView.setText(recipe.title);
    }
}
