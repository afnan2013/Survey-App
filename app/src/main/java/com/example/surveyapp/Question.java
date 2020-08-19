package com.example.surveyapp;

import java.io.Serializable;

public class Question implements Serializable {
    private String title;
    private String type;
    private String options;
    private boolean required;

    public Question(){}

    public Question(String title, String type, String options, boolean required){
        this.title = title;
        this.type = type;
        this.options = options;
        this.required = required;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }
}
