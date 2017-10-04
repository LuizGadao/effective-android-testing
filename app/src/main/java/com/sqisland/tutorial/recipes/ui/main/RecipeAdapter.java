package com.sqisland.tutorial.recipes.ui.main;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sqisland.tutorial.recipes.R;
import com.sqisland.tutorial.recipes.data.local.RecipeStore;
import com.sqisland.tutorial.recipes.data.model.Recipe;

/**
 * Created by Luiz on 03/10/17.
 */

public class RecipeAdapter extends RecyclerView.Adapter<RecipeViewHolder> {

    private final RecipeStore mStore;

    public RecipeAdapter(RecipeStore store) {
        mStore = store;
    }

    @Override
    public RecipeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new RecipeViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecipeViewHolder holder, int position) {
        Recipe recipe = mStore.getRecipes().get(position);
        holder.onBind(recipe);
    }

    @Override
    public int getItemCount() {
        return mStore.getRecipes().size();
    }
}
