package com.example.surveyapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SurveyActivity extends AppCompatActivity {
    private static final String TAG = "SurveyActivity";

    TextView textView;
    Button btnContinueNext;

    private ArrayList<Question> questionList;
    private int questionCounter;
    private int questionCountTotal;
    private Question currentQuestion;

    public ArrayList<String> survey_name, survey_results = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey);

        survey_name = new ArrayList<>();
        survey_results = new ArrayList<>();
        //btnContinueNext = findViewById(R.id.btn_continue_next_Text);

        Bundle bundle =  getIntent().getExtras();
        if (bundle != null) {
            questionList = (ArrayList<Question>)bundle.getSerializable("QuestionList");
            if (questionList != null) {

                SurveyDbHelper surveyDbHelper = new SurveyDbHelper(this);
                surveyDbHelper.fillQuestionsTable(questionList);

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
            for (int i=0; i< survey_name.size();i++){
                Log.d(TAG, "showNextQuestion: Finally The Survey Results Name ="+ survey_name.get(i)+" and Value="+survey_results.get(i));
                Toast.makeText(this, "Finally The Survey Results Name ="+ survey_name.get(i)+" and Value="+survey_results.get(i), Toast.LENGTH_SHORT).show();
            }
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            //finishQuiz();
        }
    }

    public void finishQuiz(){
        finish();
    }
}