package com.sqisland.tutorial.recipes.data.model;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Luiz on 27/09/17.
 */

public class Recipe {

    public static final String ID_PREFIX = "id=";
    public static final String TITLE_PREFIX = "title=";
    public final String id;
    public final String title;
    public final String description;

    public Recipe(String id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static Recipe readFromStream(InputStream inputStream) {
        String id = null;
        String title = null;
        StringBuilder description = new StringBuilder();

        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        try {
            while (reader.ready()){
                String line = reader.readLine();
                if (line.startsWith(ID_PREFIX)) {
                    id = line.substring(ID_PREFIX.length());
                    continue;
                }
                if (line.startsWith(TITLE_PREFIX)){
                    title = line.substring(TITLE_PREFIX.length());
                    continue;
                }

                if (description.length() > 0) description.append("\n");

                description.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Recipe(id, title, description.toString());
    }


}
