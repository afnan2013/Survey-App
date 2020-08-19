package com.example.surveyapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the  factory method to
 * create an instance of this fragment.
 */
public class MultipleChoiceFragment extends Fragment {

    private static final String TAG = "MultipleChoiceFragment";

    private TextView textViewQuestionMC;
    private RadioGroup rbGroup;
    private RadioButton rb1;
    private RadioButton rb2;
    private RadioButton rb3;
    private RadioButton rb4;
    private RadioButton rb5;

    private Question question;
    public MultipleChoiceFragment() {
        // Required empty public constructor
    }

    public MultipleChoiceFragment(Question question) {
        this.question = question;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_multiple_choice, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        textViewQuestionMC = view.findViewById(R.id.text_view_question_multiple_choice);
        rbGroup = view.findViewById(R.id.radio_group);
        rb1 = view.findViewById(R.id.radio_button1);
        rb2 = view.findViewById(R.id.radio_button2);
        rb3 = view.findViewById(R.id.radio_button3);
        rb4 = view.findViewById(R.id.radio_button4);
        rb5 = view.findViewById(R.id.radio_button5);

        Log.d(TAG, "onViewCreated: "+question.getTitle()+" "+question.getOptions()+"  "+question.getType());
        textViewQuestionMC.setText(question.getTitle().toString());
        String options = question.getOptions();
        List<String> convertedOptions = Arrays.asList(options.split(",", -1));

        if (convertedOptions.size() == 5) {
            rb1.setText(convertedOptions.get(0));
            rb2.setText(convertedOptions.get(1));
            rb3.setText(convertedOptions.get(2));
            rb4.setText(convertedOptions.get(3));
            rb5.setText(convertedOptions.get(4));
        }else{
            Log.d(TAG, "onViewCreated: Some options are missing in Multiple Choice Question");
        }

    }
}