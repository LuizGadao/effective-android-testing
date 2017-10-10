package com.sqisland.tutorial.recipes.ui.main.recipe;

import com.sqisland.tutorial.recipes.data.local.Favorites;
import com.sqisland.tutorial.recipes.data.local.RecipeStore;
import com.sqisland.tutorial.recipes.data.model.Recipe;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.InputStream;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Luiz on 09/10/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class RecipePresenterTest {

    @Mock
    Favorites mFavorites;
    @Mock
    RecipeStore mStore;
    @Mock
    RecipeContract.View mView;

    RecipePresenter mPresenter;

    @Before
    public void setUp() throws Exception {
        mPresenter = new RecipePresenter(mStore, mView, mFavorites);
    }

    @Test
    public void recipeNotFound() throws Exception {
        when(mStore.getRecipeById(anyString())).thenReturn(null);
        mPresenter.loadRecipe("some-id");
        verify(mView).showRecipeNotFoundError();
    }

    @Test(expected = IllegalStateException.class)
    public void toggleWithoutLoad() {
        mPresenter.toggleFavorite();
    }

    @Test
    public void loadWaterAndFavorite() throws Exception {
        InputStream inputStream = RecipePresenter.class.getResourceAsStream("/recipes/water.txt");
        Recipe recipe = Recipe.readFromStream(inputStream);

        when(mStore.getRecipeById(anyString())).thenReturn(recipe);
        when(mFavorites.toggle(anyString())).thenReturn(true);

        mPresenter.loadRecipe("water");
        mPresenter.toggleFavorite();

        ArgumentCaptor<Boolean> captor = ArgumentCaptor.forClass(Boolean.class);
        verify(mView, times(2)).setFavorite(captor.capture());

        assertEquals(false, captor.getAllValues().get(0));
        assertEquals(true, captor.getAllValues().get(1));
    }
}