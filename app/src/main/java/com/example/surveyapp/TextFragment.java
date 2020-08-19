package com.example.surveyapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TextFragment factory method to
 * create an instance of this fragment.
 */
public class TextFragment extends Fragment {

    private static final String TAG = "TextFragment";

    private TextView textViewQuestionT;

    private Question question;

    public TextFragment(){}

    public TextFragment(Question question){
        this.question = question;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_text, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        textViewQuestionT = view.findViewById(R.id.text_view_question_text);

        Log.d(TAG, "onViewCreated: "+question.getTitle()+" "+question.getOptions()+"  "+question.getType());
        textViewQuestionT.setText(question.getTitle().toString());

    }
}