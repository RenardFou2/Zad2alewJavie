package com.example.zad1alewjavie;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private int currentIndex = 0;
    private boolean answerWasShown = false;
    private static final String KEY_CURRENT_INDEX = "currentIndex";
    private static final int REQUEST_CODE_PROMPT = 0;
    public static final String KEY_EXTRA_ANSWER = "com.example.zad1alewjavie.correctAnswer";

    private Button trueButton;
    private Button falseButton;
    private Button nextButton;
    private Button tipButton;
    private TextView questionTextView;
    private Question[] questions = new Question[]{
            new Question(R.string.q_activity, true),
            new Question(R.string.q_find_resources, false),
            new Question(R.string.q_listener, true),
            new Question(R.string.q_resources, true),
            new Question(R.string.q_version, false)
    };

    private void checkAnswerCorrectness(boolean userAnswer){
        boolean correctAnswer = questions[currentIndex].getCorrectAnswer();
        int resultMessageId=0;
        if (answerWasShown){
            resultMessageId = R.string.answer_was_shown;
        }
        else {
            if (userAnswer == correctAnswer){
                resultMessageId = R.string.correct_answer;
            }
            else{
                resultMessageId = R.string.incorrect_answer;
            }
        }
        Toast.makeText(this, resultMessageId, Toast.LENGTH_SHORT).show();
    }
    private void setNextQuestion(){
        questionTextView.setText(questions[currentIndex].getQuestionId());
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d("Editable", "Wywołana została metoda: onSaveInstanceState");
        outState.putInt(KEY_CURRENT_INDEX, currentIndex);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("Editable", "onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("Editable", "onStop");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) { return ;}
        if (resultCode == REQUEST_CODE_PROMPT){
            if (data == null) { return; }
            answerWasShown = data.getBooleanExtra(PromptActivity.KEY_EXTRA_ANSWER_SHOWN, false);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("Editable", "onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("Editable", "onResume");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("Editable", "onDestroy");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("Editable", "Wywołana została metoda: onCreate");

        questionTextView= findViewById(R.id.question_textView);
        setNextQuestion();
        trueButton= findViewById(R.id.true_button);
        falseButton= findViewById(R.id.false_button);
        nextButton= findViewById(R.id.next_button);
        tipButton= findViewById(R.id.tip_button);

        if(savedInstanceState != null){
            currentIndex = savedInstanceState.getInt(KEY_CURRENT_INDEX);
        }
        trueButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                checkAnswerCorrectness(true);
            }
        });
        falseButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                checkAnswerCorrectness(false);
            }
        });
        tipButton.setOnClickListener((v) -> {
           Intent intent = new Intent(MainActivity.this, PromptActivity.class);
           boolean correctAnswer = questions[currentIndex].getCorrectAnswer();

           intent.putExtra(KEY_EXTRA_ANSWER, correctAnswer);
           startActivityForResult(intent, REQUEST_CODE_PROMPT);
        });

        nextButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                currentIndex=(currentIndex+1)%questions.length;
                answerWasShown = false;
                setNextQuestion();
            }
        });
    }
}