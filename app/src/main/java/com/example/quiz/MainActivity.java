package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    TextView totalQuestionsTextView;
    TextView questionTextView;

    Button ansAButton;
    Button ansBButton;
    Button ansCButton;
    Button ansDButton;
    Button enterButton;

    String selectedAnswer = "";
    int score = 0;
    int currentQuestionIndex = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ansAButton = findViewById(R.id.ans1);
        ansBButton = findViewById(R.id.ans2);
        ansCButton = findViewById(R.id.ans3);
        ansDButton = findViewById(R.id.ans4);
        questionTextView = findViewById(R.id.question);
        enterButton = findViewById(R.id.enter);
    }

    @Override
    public void onClick(View view) {
        Button button = (Button) view;
        if (button.getId() == enterButton.getId()) {
            if (selectedAnswer.equals(QuestionAnswers.answers[currentQuestionIndex])) {
                ++score;
            }
            if (currentQuestionIndex == QuestionAnswers.questions.length - 1) {
                resetQuestions();
                return;
            }
            ++currentQuestionIndex;
            resetQuestions();
            loadNewQuestion();
        } else {

            selectedAnswer = button.getText().toString();
            button.setBackgroundColor(Color.GREEN);
        }
    }

    private void loadNewQuestion() {
        questionTextView.setText(QuestionAnswers.questions[currentQuestionIndex]);
        ansAButton.setText(QuestionAnswers.choices[currentQuestionIndex][0]);
        ansBButton.setText(QuestionAnswers.choices[currentQuestionIndex][1]);
        ansCButton.setText(QuestionAnswers.choices[currentQuestionIndex][2]);
        ansDButton.setText(QuestionAnswers.choices[currentQuestionIndex][3]);
    }

    private void resetQuestions() {
        ansAButton.setBackgroundColor(Color.WHITE);
        ansBButton.setBackgroundColor(Color.WHITE);
        ansCButton.setBackgroundColor(Color.WHITE);
        ansDButton.setBackgroundColor(Color.WHITE);
    }
}