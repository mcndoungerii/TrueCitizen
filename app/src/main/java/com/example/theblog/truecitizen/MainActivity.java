package com.example.theblog.truecitizen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;

import com.example.theblog.truecitizen.databinding.ActivityMainBinding;
import com.example.theblog.truecitizen.model.Question;
import com.google.android.material.snackbar.Snackbar;

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


        //true button
        binding.trueButton.setOnClickListener(v -> showAnswer(true));

        //false button
        binding.falseButton.setOnClickListener(v -> showAnswer(false));
    }

    private void showAnswer(boolean userChoseCorrect) {
        boolean answerIsCorrect = questionArray[currentQuestionIndex].isAnswerTrue();

        int messageId;

        if(answerIsCorrect == userChoseCorrect) {
            messageId = R.string.correct_answer;
        }
        else {
            messageId = R.string.wrong_answer;
        }

        //set snackbar
        Snackbar.make(binding.imageViewId,messageId,Snackbar.LENGTH_LONG).show();
    }

    private void updateQuestion() {
        binding.textViewId.setText(questionArray[currentQuestionIndex].getAnswerResId());
    }
}