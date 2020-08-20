package com.example.surveyapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.surveyapp.SurveyContract.*;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class SurveyDbHelper extends SQLiteOpenHelper {

    private static final String TAG = "SurveyDbHelper";
    private static final String DATABASE_NAME = "SurveyResults.db";
    private static final int DATABASE_VERSION = 1;
    private SQLiteDatabase db;

    public SurveyDbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        this.db = sqLiteDatabase;

        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
                SurveysTable.TABLE_NAME + " ( " +
                SurveysTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                SurveysTable.COLUMN_QUESTION + " TEXT, " +
                SurveysTable.COLUMN_TYPE + " TEXT, " +
                SurveysTable.COLUMN_OPTIONS + " TEXT, " +
                SurveysTable.COLUMN_REQUIRED + " TEXT " +
                ")";

        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + SurveysTable.TABLE_NAME);
        onCreate(db);
    }

    public void fillQuestionsTable(ArrayList<Question> questionList) {
        for (Question q: questionList){
            addQuestion(q);
        }
    }
    private void addQuestion(Question question) {

        db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(SurveysTable.COLUMN_QUESTION, question.getTitle());
        cv.put(SurveysTable.COLUMN_TYPE, question.getType());
        cv.put(SurveysTable.COLUMN_OPTIONS, question.getOptions());
        cv.put(SurveysTable.COLUMN_REQUIRED, question.isRequired());

        Log.d(TAG, "addQuestion: "+cv);
        db.insert(SurveysTable.TABLE_NAME, null, cv);
    }

    public ArrayList<Question> getAllQuestions() {
        ArrayList<Question> questionList = new ArrayList<>();
        db = this.getReadableDatabase();

        Cursor c = db.rawQuery("SELECT * FROM " + SurveysTable.TABLE_NAME, null);
        if (c.moveToFirst()) {
            do {
                Question question = new Question();
                question.setTitle(c.getString(c.getColumnIndex(SurveysTable.COLUMN_QUESTION)));
                question.setType(c.getString(c.getColumnIndex(SurveysTable.COLUMN_TYPE)));
                question.setOptions(c.getString(c.getColumnIndex(SurveysTable.COLUMN_OPTIONS)));
                question.setRequired(true);
                questionList.add(question);
            } while (c.moveToNext());
        }
        c.close();
        return questionList;
    }
}
