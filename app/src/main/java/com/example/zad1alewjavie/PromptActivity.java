package com.example.zad1alewjavie;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PromptActivity extends AppCompatActivity {

    public static final String KEY_EXTRA_ANSWER_SHOWN = "com.example.zad1alewjavie.answerShown";
    private boolean correctAnswer;
    private TextView answerTextView;

    private Button showAnswerButton;
    public void setAnswerShownResult(boolean answerWasShown){
        Intent resultIntent = new Intent();
        resultIntent.putExtra(KEY_EXTRA_ANSWER_SHOWN, answerWasShown);
        setResult(RESULT_OK, resultIntent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prompt);

        correctAnswer = getIntent().getBooleanExtra(MainActivity.KEY_EXTRA_ANSWER, true);
        showAnswerButton = findViewById(R.id.show_answer_button);
        answerTextView = findViewById(R.id.answerTextView);

        showAnswerButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                int answer = correctAnswer ? R.string.button_true : R.string.button_false;
                answerTextView.setText(answer);
                setAnswerShownResult(true);
            }
        });

    }
}