package com.example.quiz;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tvQuestion;
    Button btn_Opt1, btn_Opt2, btn_Opt3, btn_Opt4, btn_Submit;
    int cIndex = 0;

    Que_Structure que_structure = new Que_Structure();
    int tQ = que_structure.Questions.length;
    String selectedChoice;
    int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvQuestion = findViewById(R.id.tv_Questions);
        btn_Opt1 = findViewById(R.id.btn_opt1);
        btn_Opt2 = findViewById(R.id.btn_opt2);
        btn_Opt3 = findViewById(R.id.btn_opt3);
        btn_Opt4 = findViewById(R.id.btn_opt4);
        btn_Submit = findViewById(R.id.btn_Submit);

        btn_Opt1.setOnClickListener(this);
        btn_Opt2.setOnClickListener(this);
        btn_Opt3.setOnClickListener(this);
        btn_Opt4.setOnClickListener(this);
        btn_Submit.setOnClickListener(this);

        loadQuestion();

    }

    @Override
    public void onClick(View view) {
        btn_Opt1.setBackgroundColor(Color.rgb(244 , 67 , 54));
        btn_Opt2.setBackgroundColor(Color.rgb(244 , 67 , 54));
        btn_Opt3.setBackgroundColor(Color.rgb(244 , 67 , 54));
        btn_Opt4.setBackgroundColor(Color.rgb(244 , 67 , 54));
        Button btnClicked = (Button) view;
        if (btnClicked.getId() == R.id.btn_Submit) {

            if (selectedChoice.equals(que_structure.correctAns[cIndex])) {
                score++;
        }
            cIndex++;
            loadQuestion();
        } else {

            selectedChoice = btnClicked.getText().toString();
            btnClicked.setBackgroundColor(Color.rgb(233 , 30 , 99));
        }
    }
    void loadQuestion() {
        if (cIndex == tQ) {
            finishQuiz();
            return;
        }
        tvQuestion.setText(que_structure.Questions[cIndex]);
        btn_Opt1.setText(que_structure.choices[cIndex][0]);
        btn_Opt2.setText(que_structure.choices[cIndex][1]);
        btn_Opt3.setText(que_structure.choices[cIndex][2]);
        btn_Opt4.setText(que_structure.choices[cIndex][3]);
    }
    void finishQuiz() {
        String result = "";
        if (score > tQ*0.60) {
            result = "PASS";
        } else {
            result = "FAIL";
        }
        new AlertDialog.Builder(this)
    .setTitle("Result")
                .setMessage(result +" Your score is : " + score)
                .setPositiveButton("Restart Quiz" , ((dialogInterface, i) -> restartQuiz()))
                .setCancelable(false)
                .show();
    }
    void restartQuiz() {
        score = 0;
        cIndex = 0;
        loadQuestion();
    }
}