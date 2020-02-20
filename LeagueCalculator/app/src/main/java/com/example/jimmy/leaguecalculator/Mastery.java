package com.example.jimmy.leaguecalculator;

import android.media.Image;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * Created by Jimmy on 2016-11-01.
 * It's fucking lit
 */

public class Mastery {

    String name;
    String description;
    int id;
    int active;
    ImageButton image;
    TextView textView;

    public Mastery(String name, String description, int id, int active, ImageButton image, TextView textView) {
        this.description = description;
        this.id = id;
        this.name = name;
        this.active = active;
        this.image = image;
        this.textView = textView;
    }
}
