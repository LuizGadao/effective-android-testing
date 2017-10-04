package com.sqisland.tutorial.recipes.data.local;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Luiz on 03/10/17.
 */

public class SharePreferenceFavorites implements Favorites {
    SharedPreferences mPrefs;

    public SharePreferenceFavorites(Context context) {
        mPrefs = context.getSharedPreferences("my_prefs", Context.MODE_PRIVATE);
    }

    @Override
    public boolean get(String id){
        return mPrefs.getBoolean(id, false);
    }

    public void put(String id, boolean favorite){
        SharedPreferences.Editor edit = mPrefs.edit();
        if (favorite){
            edit.putBoolean(id, true);
        }else{
            edit.remove(id);
        }

        edit.apply();
    }

    @Override
    public boolean toggle(String id){
        boolean favorite = get(id);
        put(id, !favorite);
        return !favorite;
    }
}
