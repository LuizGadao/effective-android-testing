package com.sqisland.tutorial.recipes.data.local;

import android.content.Context;
import android.content.res.AssetManager;

import com.sqisland.tutorial.recipes.data.model.Recipe;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Luiz on 27/09/17.
 */

public class RecipeStore {
    private final List<Recipe> mRecipes = new ArrayList<>();
    private Map<String, Recipe> mMap = new HashMap<>();

    public RecipeStore(Context context, String directory) {
        List<InputStream> assetStream = getAssetStream(context.getAssets(), directory);

        for (InputStream stream : assetStream){
            Recipe recipe = Recipe.readFromStream(stream);
            mRecipes.add(recipe);
            mMap.put(recipe.id, recipe);
        }
    }

    private List<InputStream> getAssetStream(AssetManager assetManager, String directory){
        String[] fileNames = getFileNames(assetManager, directory);
        List<InputStream> streams = new ArrayList<>();

        for (String fileName : fileNames){
            File file = new File(directory, fileName);
            try {
                InputStream stream = assetManager.open(file.getPath());
                if (stream != null) streams.add(stream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return streams;
    }

    private String[] getFileNames(AssetManager assetManager, String directory){
        if (directory == null) return new String[0];

        try {
            return assetManager.list(directory);
        } catch (IOException e) {
            return new String[0];
        }
    }

    public List<Recipe> getRecipes() {
        return mRecipes;
    }

    public Recipe getRecipeById(String value) {
        return mMap.get(value);
    }
}
