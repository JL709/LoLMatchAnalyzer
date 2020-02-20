package com.example.jimmy.leaguecalculator;

/**
 * Created by Jimmy on 2017-02-03.
 */

public class Runes {
    int id;
    String name;
    String description;
    float value;
    int active;

    public Runes(int id, String name, String description, float value, int active) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.value = value;
        this.active = active;
    }
}
