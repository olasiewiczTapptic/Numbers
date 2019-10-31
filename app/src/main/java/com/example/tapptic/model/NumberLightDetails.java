package com.example.tapptic.model;

import com.google.gson.annotations.SerializedName;

public class NumberLightDetails {

    @SerializedName("name")
    private String name;

    @SerializedName("image")
    private String imagePath;

    @SerializedName("text")
    private String description;

    public String getName() {
        return name;
    }

    public String getImagePath() {
        return imagePath;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}

