package com.example.surveyapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NumberFragment} factory method to
 * create an instance of this fragment.
 */
public class NumberFragment extends Fragment {

    private static final String TAG = "NumberFragment";

    private TextView textViewQuestionNumber;
    private Button btnContinueNextNumber;
    private EditText editTextNumber;

    private Question question;

    public NumberFragment(){ }

    public NumberFragment(Question question){
        this.question = question;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_number, container, false);

        textViewQuestionNumber = view.findViewById(R.id.text_view_question_number);
        editTextNumber = view.findViewById(R.id.editText_number);
        btnContinueNextNumber = view.findViewById(R.id.btn_continue_next_number);

        textViewQuestionNumber.setText(question.getTitle());

        btnContinueNextNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String number = editTextNumber.getText().toString();
                if (number.matches("")){
                    Toast.makeText(getContext(), "You have to enter your number first.", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getContext(), "Number :"+number, Toast.LENGTH_SHORT).show();

                    SurveyActivity surveyActivity = (SurveyActivity) getActivity();
                    if (surveyActivity != null) {
                        surveyActivity.survey_name.add(question.getType());
                        surveyActivity.survey_results.add(number);
                        surveyActivity.showNextQuestion();
                    }
                }
            }
        });

        return view;
    }


}