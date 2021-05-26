package com.example.theblog.truecitizen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;

import com.example.theblog.truecitizen.databinding.ActivityMainBinding;
import com.example.theblog.truecitizen.model.Question;

public class MainActivity extends AppCompatActivity {

    private Question[] questionArray = new Question[] {

            new Question(R.string.question_amendments, false), //correct: 27
            new Question(R.string.question_constitution, true),
            new Question(R.string.question_declaration, true),
            new Question(R.string.question_independence_rights, true),
            new Question(R.string.question_religion, true),
            new Question(R.string.question_government, false),
            new Question(R.string.question_government_feds, false),
            new Question(R.string.question_government_senators, true),
    };

    private ActivityMainBinding binding;

    private int currentQuestionIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);

        binding.textViewId.setText(questionArray[0].getAnswerResId());

        //next btn
        binding.nextButton.setOnClickListener(v -> {
            //go to the next qns

            //currentQuestionIndex++;

            //To check to avoid ArrayOutOfBoundIndex error.Do this, The next index after the end of array, will show the first index

            currentQuestionIndex = (currentQuestionIndex + 1) % questionArray.length;
            updateQuestion();

        });

        //prev btn
        binding.prevButton.setOnClickListener(v -> {


            //To avoid Error, java.lang.ArrayIndexOutOfBoundsException: length=8; index=-1, do the following
            if(currentQuestionIndex > 0) {
                currentQuestionIndex = (currentQuestionIndex - 1) % questionArray.length;
                updateQuestion();
            }

        });
    }

    private void updateQuestion() {
        binding.textViewId.setText(questionArray[currentQuestionIndex].getAnswerResId());
    }
}