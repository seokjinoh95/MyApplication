package com.example.andrewoh.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends Activity {

    private Button quiz;
    private Button score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        quiz = (Button)findViewById(R.id.startQuiz);
        score = (Button)findViewById(R.id.showScore);

        
        quiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               
                Intent intent = new Intent(MenuActivity.this,quizListActivity.class);
                startActivity(intent);
            }
        });

        score.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
                Intent intent = new Intent(MenuActivity.this,scoreActivity.class);
                startActivity(intent);
            }
        });
    }
}
