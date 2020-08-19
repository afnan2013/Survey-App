package com.example.surveyapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CheckboxFragment} factory method to
 * create an instance of this fragment.
 */
public class CheckboxFragment extends Fragment {

    private static final String TAG = "CheckboxFragment";

    TextView textViewQuestionCheckbox;
    CheckBox checkBoxYes,checkBoxNo;
    Button btnViewContinueNextCheckbox;

    private Question question;

    public CheckboxFragment(){}

    public CheckboxFragment(Question question){
        this.question = question;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_checkbox, container, false);

        textViewQuestionCheckbox = view.findViewById(R.id.text_view_question_checkbox);
        checkBoxYes = view.findViewById(R.id.checkBoxYes);
        checkBoxNo = view.findViewById(R.id.checkBoxNo);
        btnViewContinueNextCheckbox = view.findViewById(R.id.btn_continue_next_checkbox);

        textViewQuestionCheckbox.setText(question.getTitle());
        btnViewContinueNextCheckbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkBoxYes.isChecked() || checkBoxNo.isChecked()){
                    if (checkBoxYes.isChecked()){
                        Toast.makeText(getContext(), "Yes", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getContext(), "No", Toast.LENGTH_SHORT).show();
                    }
                    SurveyActivity surveyActivity = (SurveyActivity) getActivity();
                    surveyActivity.showNextQuestion();
                }else{
                    Toast.makeText(getContext(), "Please select an answer", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }
}