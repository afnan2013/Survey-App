package com.example.surveyapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SurveyActivity extends AppCompatActivity {
    private static final String TAG = "SurveyActivity";

    TextView textView;
    Button btnContinueNext;

    private List<Question> questionList;
    private int questionCounter;
    private int questionCountTotal;
    private Question currentQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey);

        //btnContinueNext = findViewById(R.id.btn_continue_next_Text);

        Bundle bundle =  getIntent().getExtras();
        if (bundle != null) {
            questionList = (ArrayList<Question>)bundle.getSerializable("QuestionList");
            if (questionList != null) {
                questionCounter = 0;
                questionCountTotal = questionList.size();
                Collections.shuffle(questionList);

                showNextQuestion();
            }
        }

    }

    public void showNextQuestion() {
        if (questionCounter < questionCountTotal) {
            currentQuestion = questionList.get(questionCounter);
            String questionType= currentQuestion.getType();

            Fragment fragment = null;
            switch (questionType){
                case "multiple choice":
                    fragment = new MultipleChoiceFragment(currentQuestion);
                    break;
                case "dropdown":
                    fragment = new DropdownFragment(currentQuestion);
                    break;
                case "Checkbox":
                    fragment = new CheckboxFragment(currentQuestion);
                    break;
                case "text":
                    fragment = new TextFragment(currentQuestion);
                    break;
                case "number":
                    fragment = new NumberFragment(currentQuestion);
                    break;
            }
            questionCounter++;
            if (fragment != null) {
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.framelayoutFragemnt, fragment);
                ft.commit();
            }

        } else {
            finishQuiz();
        }
    }

    public void finishQuiz(){
        finish();
    }
}