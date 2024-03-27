package com.example.hw2.model;

import com.google.gson.annotations.SerializedName;

public class ClassificationResponse {
    @SerializedName("sentiment")
    private String sentiment;

    public String getSentiment() {
        return sentiment;
    }
}
