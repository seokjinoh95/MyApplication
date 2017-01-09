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
    private EditText inputAnswer;
    private Button btnCheck;
    private Button cheat;
    private Button skip;

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
                
                if(inputAnswer.getText().toString().equals(answer)){
                    Toast.makeText(mContext, "Correct!", Toast.LENGTH_SHORT).show();
                    
                    score = score+100;
                   
                    PreferencesUtil.setIntPreferences(mContext,"score",score);
                }
                else{
                    Toast.makeText(mContext, "Wrong!", Toast.LENGTH_SHORT).show();
                    
                    PreferencesUtil.setIntPreferences(mContext,"score",score);
                }
                dismiss();
            }
        });

        cheat = (Button)findViewById(R.id.cheat);
        cheat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               
                answerText.setText(answer);
               
                inputAnswer.setVisibility(View.INVISIBLE);
                new Handler().postDelayed(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        
                        PreferencesUtil.setIntPreferences(mContext,"score",score);
                       
                        dismiss();
                    }
                }, 2000);
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
