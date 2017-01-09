package com.example.andrewoh.myapplication;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class solveDialog extends Dialog {

    private String problem;
    private String answer;
    private int score;

    private Context mContext;
    private TextView problemText;
    private TextView answerText;
    private EditText inputAnswer; //정답입력받는 EditText
    private Button btnCheck; // 체크버튼
    private Button cheat; //정답보이기
    private Button skip; //스킵

    public solveDialog(Context context,String problem, String answer,int score) {
        super(context);
        mContext = context;
        this.problem = problem;
        this.answer = answer;
        this.score = score;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_solve_dialog);

        inputAnswer = (EditText)findViewById(R.id.editAnswer);
        btnCheck = (Button)findViewById(R.id.checkBtn);
        problemText = (TextView)findViewById(R.id.textProblem);
        answerText = (TextView)findViewById(R.id.textAnswer);

        problemText.setText(problem);
        answerText.setText("");

        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("inputted",inputAnswer.getText().toString());
                Log.e("answer",answer);
                //문제의 정답을 맞추었을 경우
                if(inputAnswer.getText().toString().equals(answer)){
                    Toast.makeText(mContext, "Correct!", Toast.LENGTH_SHORT).show();
                    //점수 100점추가
                    score = score+100;
                    //점수를 score라는 키값 안에 저장한다.
                    PreferencesUtil.setIntPreferences(mContext,"score",score);
                }//오답의 경우
                else{
                    Toast.makeText(mContext, "Wrong!", Toast.LENGTH_SHORT).show();
                    //점수를 없는 것으로 하고, 현제 점수를 score 키값 안에 저장한다.
                    PreferencesUtil.setIntPreferences(mContext,"score",score);
                }
                dismiss();
            }
        });

        cheat = (Button)findViewById(R.id.cheat);
        cheat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //정담을 보여준다.
                answerText.setText(answer);
                //정답입력을 못하도록 입력칸을 사라지게 한다.
                inputAnswer.setVisibility(View.INVISIBLE);
                new Handler().postDelayed(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        //현제점수를 score키 값 안에 저장
                        PreferencesUtil.setIntPreferences(mContext,"score",score);
                        //다이얼로그 종료
                        dismiss();
                    }
                }, 2000);// 2초 정도 딜레이를 준 후 시작
            }
        });

        skip = (Button)findViewById(R.id.skip);
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PreferencesUtil.setIntPreferences(mContext,"score",score);
                dismiss();
            }
        });
    }
}
