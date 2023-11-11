package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView totalQuestionsTextView, questionTextView;
    Button ansAButton;
    Button ansBButton;
    Button ansCButton;
    Button ansDButton;
    Button oKButton;
    int totalQuestions = QuestionAnswer.question.length;
    String selectedAnswer = "";
    int currentQuestionIndex = 0;
    int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        totalQuestionsTextView = findViewById(R.id.total_question);
        questionTextView = findViewById(R.id.question);
        ansAButton = findViewById(R.id.ans_a);
        ansBButton = findViewById(R.id.ans_b);
        ansCButton = findViewById(R.id.ans_c);
        ansDButton = findViewById(R.id.ans_d);
        oKButton = findViewById(R.id.ok);

        // Set up click listeners
        ansAButton.setOnClickListener(this);
        ansBButton.setOnClickListener(this);
        ansCButton.setOnClickListener(this);
        ansDButton.setOnClickListener(this);
        oKButton.setOnClickListener(this);
        loadNewQuestion();
    }

    @Override
    public void onClick(View view) {
        ansAButton.setBackgroundColor(Color.WHITE);
        ansBButton.setBackgroundColor(Color.WHITE);
        ansCButton.setBackgroundColor(Color.WHITE);
        ansDButton.setBackgroundColor(Color.WHITE);

        Button button = (Button) view;
        if (button.getId() == R.id.ok) {
            if (selectedAnswer.equals(QuestionAnswer.answers[currentQuestionIndex])) {
                score++;
            }

            currentQuestionIndex++;
            loadNewQuestion();
        } else {
            selectedAnswer = button.getText().toString();
            button.setBackgroundColor(Color.GREEN);
        }
    }

    private void loadNewQuestion() {
        if (currentQuestionIndex == totalQuestions){
            finishQuiz();
            return;
        }
        questionTextView.setText(QuestionAnswer.question[currentQuestionIndex]);
        ansAButton.setText(QuestionAnswer.choices[currentQuestionIndex][0]);
        ansBButton.setText(QuestionAnswer.choices[currentQuestionIndex][1]);
        ansCButton.setText(QuestionAnswer.choices[currentQuestionIndex][2]);
        ansDButton.setText(QuestionAnswer.choices[currentQuestionIndex][3]);
    }

    private void finishQuiz() {
        String passStatus = "";
        if (score > totalQuestions * 0.6){
            passStatus = "Passed";
        }else{
            passStatus = "failed";
        }
        new AlertDialog.Builder(this)
                .setTitle(passStatus)
                .setMessage("Score is " + score + " out of " + totalQuestions)
                .setPositiveButton("restart", ((dialogInterface, i) -> restartQuiz()))
                .setCancelable(false)
                .show();
    }

    private void restartQuiz() {
        score = 0;
        currentQuestionIndex = 0;
        loadNewQuestion();
    }
}
}