package com.example.surveyapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DropdownFragment} factory method to
 * create an instance of this fragment.
 */
public class DropdownFragment extends Fragment {

    private static final String TAG = "DropdownFragment";

    TextView textViewQuestionD;
    Spinner spinnerDropdown;
    Button btnContinueNextDropdown;

    private Question question;



    public DropdownFragment(){}
    public DropdownFragment(Question question){
        this.question = question;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_dropdown, container, false);

        textViewQuestionD = view.findViewById(R.id.text_View_question_dropdown);
        spinnerDropdown = view.findViewById(R.id.spinner_dropdown);
        btnContinueNextDropdown = view.findViewById(R.id.btn_continue_next_dropdown);


        Log.d(TAG, "onViewCreated: "+question.getTitle()+" "+question.getOptions()+"  "+question.getType());
        textViewQuestionD.setText(question.getTitle().toString());
        String options = question.getOptions();
        List<String> convertedOptions = Arrays.asList(options.split(",", -1));

        ArrayAdapter<CharSequence> adapter = new ArrayAdapter(getContext(),android.R.layout.simple_spinner_item, convertedOptions);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDropdown.setAdapter(adapter);


        spinnerDropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btnContinueNextDropdown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String selected= null;
                if (spinnerDropdown != null && spinnerDropdown.getSelectedItem() !=null ) {
                    selected = (String)spinnerDropdown.getSelectedItem();
                    Toast.makeText(getContext(), "Answer : "+selected, Toast.LENGTH_SHORT).show();
                    SurveyActivity surveyActivity = (SurveyActivity) getActivity();
                    surveyActivity.showNextQuestion();
                } else  {
                    Toast.makeText(getContext(), "You have to select first.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }


}