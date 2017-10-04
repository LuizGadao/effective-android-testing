package com.sqisland.tutorial.recipes.data.local;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Luiz on 03/10/17.
 */

public class SharePreferenceFavorites {
    SharedPreferences mPrefs;

    public SharePreferenceFavorites(Context context) {
        mPrefs = context.getSharedPreferences("my_prefs", Context.MODE_PRIVATE);
    }

    public boolean getId(String id){
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

    public boolean toglle(String id){
        boolean favorite = getId(id);
        put(id, !favorite);
        return !favorite;
    }
}
