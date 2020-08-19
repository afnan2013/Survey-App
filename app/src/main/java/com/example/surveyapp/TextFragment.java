package com.example.surveyapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TextFragment factory method to
 * create an instance of this fragment.
 */
public class TextFragment extends Fragment {

    private static final String TAG = "TextFragment";

    private TextView textViewQuestionT;
    private Button btnContinueNextText;
    private EditText editTextAnswer;

    private Question question;


    public TextFragment(){}
    public TextFragment(Question question){
        this.question = question;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_text, container, false);

        textViewQuestionT = view.findViewById(R.id.text_view_question_text);
        editTextAnswer = view.findViewById(R.id.editText_text_answer);
        btnContinueNextText = view.findViewById(R.id.btn_continue_next_Text);

        Log.d(TAG, "onViewCreated: "+question.getTitle()+" "+question.getOptions()+"  "+question.getType());
        textViewQuestionT.setText(question.getTitle().toString());

        btnContinueNextText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = editTextAnswer.getText().toString();
                if (text.matches("")){
                    Toast.makeText(getContext(), "You have to select first.", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getContext(), "Text :"+text, Toast.LENGTH_SHORT).show();
                    SurveyActivity surveyActivity = (SurveyActivity) getActivity();
                    surveyActivity.showNextQuestion();
                }
            }
        });

        return view;
    }
}