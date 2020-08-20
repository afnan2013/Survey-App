package com.example.surveyapp;

import android.provider.BaseColumns;

public final class SurveyContract {

    private SurveyContract(){

    }
    public static class SurveysTable implements BaseColumns {
        public static final String TABLE_NAME = "survey_questions";
        public static final String COLUMN_QUESTION = "question";
        public static final String COLUMN_TYPE = "type";
        public static final String COLUMN_OPTIONS= "options";
        public static final String COLUMN_REQUIRED = "required";
    }
}
