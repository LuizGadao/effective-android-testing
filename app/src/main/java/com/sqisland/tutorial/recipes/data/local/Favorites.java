package com.sqisland.tutorial.recipes.data.local;

/**
 * Created by Luiz on 04/10/17.
 */

public interface Favorites {
    boolean get(String id);
    boolean toggle(String id);
}
