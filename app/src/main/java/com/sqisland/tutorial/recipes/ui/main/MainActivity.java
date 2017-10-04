package com.sqisland.tutorial.recipes.ui.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.sqisland.tutorial.recipes.R;
import com.sqisland.tutorial.recipes.data.local.RecipeStore;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecipeStore recipes = new RecipeStore(this, "recipes");
        RecyclerView mRecyclerView = findViewById(R.id.rv_recipes);
        mRecyclerView.setAdapter(new RecipeAdapter(recipes));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}