package com.sqisland.tutorial.recipes.data.model;

import android.annotation.SuppressLint;
import android.os.Build;
import android.support.annotation.RequiresApi;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

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
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        List<String> lines = reader.lines().collect(Collectors.toList());
        // java.lang.IllegalStateException: stream has already been operated upon or closed
        String id = getLine(ID_PREFIX, lines);
        String title = getLine(TITLE_PREFIX, lines);
        String description = getDescription(lines);

        return new Recipe(id, title, description);
    }

    @SuppressLint("NewApi")
    private static String getDescription(List<String> lines) {
        String description = lines.stream().skip(2).collect(Collectors.joining("\n"));
        return description;
    }

    @SuppressLint("NewApi")
    private static String getLine(String prefixLine, List<String> lines) {
        return lines.stream()
                .filter(s -> s.startsWith(prefixLine))
                .map(s -> s.substring(prefixLine.length()))
                .collect(Collectors.toList()).get(0);
    }


}
