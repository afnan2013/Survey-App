package com.example.surveyapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private static final String URL = "https://example-response.herokuapp.com/getSurvey";

    RequestQueue requestQueue;
    private List<Question> questionList = null;

    Button btnTakeSurvey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        questionList = new ArrayList<>();
        btnTakeSurvey = findViewById(R.id.btn_take_survey);

        requestQueue = Volley.newRequestQueue(this);
        //Toast.makeText(this, "The size of the questions are "+Integer.toString(questionList.size()), Toast.LENGTH_SHORT).show();


        btnTakeSurvey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, URL, null, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Question question = null;
                        for (int i=0; i< response.length(); i++) {
                            try {
                                JSONObject jsonObject = response.getJSONObject(i);
                                if (jsonObject != null) {
                                    String title = jsonObject.getString("question");
                                    String type = jsonObject.getString("type");
                                    String options = jsonObject.getString("options");
                                    boolean required =  jsonObject.getBoolean("required");
                                    question = new Question(title, type, options, required);
                                    Log.d(TAG, "onResponse: " + title+" "+type+" "+options+" "+required);
                                    questionList.add(question);
                                    Log.d(TAG, "onResponse: " + jsonObject.getString("question")+"   " +Integer.toString(questionList.size()));
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                        }
                        Intent intent = new Intent(getApplicationContext(), SurveyActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("QuestionList", (Serializable) questionList); //where questionList is object of  ArrayList<Question>

                        intent.putExtras(bundle);
                        startActivity(intent);
                        finish();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d(TAG, "onErrorResponse: ");
                    }
                });

                requestQueue.add(jsonArrayRequest);
            }
        });

    }
}