package com.example.tapptic.model;

import com.google.gson.annotations.SerializedName;

public class NumberLight {

    @SerializedName("name")
    private String name;

    @SerializedName("image")
    private String imagePath;

    public NumberLight(String name, String imagePath) {
        this.name = name;
        this.imagePath = imagePath;
    }

    public String getName() {
        return name;
    }

    public String getImagePath() {
        return imagePath;
    }

}

