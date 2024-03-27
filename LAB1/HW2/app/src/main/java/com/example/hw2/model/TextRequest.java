package com.example.hw2.model;

import com.google.gson.annotations.SerializedName;

public class TextRequest {

    @SerializedName("text")
    private String text;

    public TextRequest(String text) {
        this.text = text;
    }
}

