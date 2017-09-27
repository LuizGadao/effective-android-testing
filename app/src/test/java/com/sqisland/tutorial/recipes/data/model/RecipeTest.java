package com.sqisland.tutorial.recipes.data.model;

import org.junit.Test;

import java.io.InputStream;

import static junit.framework.Assert.assertEquals;
import static junit.framework.TestCase.assertNotNull;

/**
 * Created by Luiz on 27/09/17.
 */
public class RecipeTest {
    @Test
    public void water() throws Exception {
        InputStream stream = RecipeTest.class.getResourceAsStream("/recipes/water.txt");
        Recipe recipe = Recipe.readFromStream(stream);

        assertNotNull(recipe);
        assertEquals("water", recipe.id);
        assertEquals("Water", recipe.title);
        assertEquals("Put glass under tap. Open tap. Close tap. Drink.", recipe.description);
    }

    @Test
    public void mixed() throws Exception {
        InputStream stream = RecipeTest.class.getResourceAsStream("/recipes/mixed.txt");
        Recipe recipe = Recipe.readFromStream(stream);

        String strExpected = "Juice of 3 lemons\n" +
                "1 orange\n" +
                "1 pint grape juice\n" +
                "1 cup sugar\n" +
                "1 cup water\n" +
                "1 pine apple juice\n" +
                "Mix all together and strain. Add large piece of ice.";

        assertNotNull(recipe);
        assertEquals("punch", recipe.id);
        assertEquals("Punch", recipe.title);
        assertEquals(strExpected, recipe.description);
    }
}