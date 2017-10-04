package com.sqisland.tutorial.recipes.data.local;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Luiz on 04/10/17.
 */

public class InMemoryFavorites implements Favorites {

    private Map<String, Boolean> mMap = new HashMap<>();

    @Override
    public boolean get(String id) {
        return mMap.get(id) != null;
    }

    @Override
    public boolean toggle(String id) {
        boolean value = !get(id);
        put(id, value);
        return value;
    }

    public void put(String id, boolean value){
        mMap.put(id, value);
    }
}
