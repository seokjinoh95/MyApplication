package com.example.andrewoh.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends Activity {

    private Button quiz;//문제풀이버튼
    private Button score;//점수보기버튼

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        quiz = (Button)findViewById(R.id.startQuiz);
        score = (Button)findViewById(R.id.showScore);

        //문제버튼클릭시 발생이벤트
        quiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //문제풀이 엑티비티 이동
                Intent intent = new Intent(MenuActivity.this,quizListActivity.class);
                startActivity(intent);
            }
        });

        score.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //스코어보드 엑티비티 이동
                Intent intent = new Intent(MenuActivity.this,scoreActivity.class);
                startActivity(intent);
            }
        });
    }
}
