package com.example.andrewoh.myapplication;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class quizListActivity extends Activity implements AdapterView.OnItemClickListener {

    private int score_val=0;
    private String[] datas;
    private String[] problem;
    private String[] answer;
    private ArrayList<quizListItem> data;

    private int clickableItem1 = 0;
    private int clickableItem2 = 0;
    private int clickableItem3 = 0;
    private int clickableItem4 = 0;
    private int clickableItem5 = 0;
    private int clickableItem6 = 0;
    private int clickableItem7 = 0;
    private int clickableItem8 = 0;
    private int clickableItem9 = 0;
    private int clickableItem10 = 0;
    private int clickableItem11 = 0;
    private int clickableItem12 = 0;

    ListView problemList;
    TextView score;
    quizListAdapter adapter;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizlist);

        problemList = (ListView) findViewById(R.id.questionList);
        problemList.setOnItemClickListener(quizListActivity.this);

        score = (TextView)findViewById(R.id.scoreTextView);
        score.setText(String.valueOf(score_val));

        readProblems();
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
       
        if(position==0){
           
            if(clickableItem1==0){
                
                final solveDialog dialog = new solveDialog(quizListActivity.this,problem[position],answer[12+position],score_val);
                dialog.show();
                dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        
                        score_val = PreferencesUtil.getIntPreferences(quizListActivity.this,"score");
                        
                        score.setText(String.valueOf(score_val));
                        
                        clickableItem1=1;
                    }
                });
            }else{
                Toast.makeText(this, "You have already tried this question.", Toast.LENGTH_SHORT).show();
            }
        }else if(position==1){
            if(clickableItem2==0){
                final solveDialog dialog = new solveDialog(quizListActivity.this,problem[position],answer[12+position],score_val);
                dialog.show();
                dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        score_val = PreferencesUtil.getIntPreferences(quizListActivity.this,"score");
                        score.setText(String.valueOf(score_val));
                        clickableItem2=1;
                    }
                });
            }else{
                Toast.makeText(this, "You have already tried this question.", Toast.LENGTH_SHORT).show();
            }
        }else if(position==2){
            if(clickableItem3==0){
                final solveDialog dialog = new solveDialog(quizListActivity.this,problem[position],answer[12+position],score_val);
                dialog.show();
                dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        score_val = PreferencesUtil.getIntPreferences(quizListActivity.this,"score");
                        score.setText(String.valueOf(score_val));
                        clickableItem3=1;
                    }
                });
            }else{
                Toast.makeText(this, "You have already tried this question.", Toast.LENGTH_SHORT).show();
            }
        }else if(position==3){
            if(clickableItem4==0){
                final solveDialog dialog = new solveDialog(quizListActivity.this,problem[position],answer[12+position],score_val);
                dialog.show();
                dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        score_val = PreferencesUtil.getIntPreferences(quizListActivity.this,"score");
                        score.setText(String.valueOf(score_val));
                        clickableItem4=1;
                    }
                });
            }else{
                Toast.makeText(this, "You have already tried this question.", Toast.LENGTH_SHORT).show();
            }
        }else if(position==4){
            if(clickableItem5==0){
                final solveDialog dialog = new solveDialog(quizListActivity.this,problem[position],answer[12+position],score_val);
                dialog.show();
                dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        score_val = PreferencesUtil.getIntPreferences(quizListActivity.this,"score");
                        score.setText(String.valueOf(score_val));
                        clickableItem5=1;
                    }
                });
            }else{
                Toast.makeText(this, "You have already tried this question.", Toast.LENGTH_SHORT).show();
            }
        }else if(position==5){
            if(clickableItem6==0){
                final solveDialog dialog = new solveDialog(quizListActivity.this,problem[position],answer[12+position],score_val);
                dialog.show();
                dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        score_val = PreferencesUtil.getIntPreferences(quizListActivity.this,"score");
                        score.setText(String.valueOf(score_val));
                        clickableItem6=1;
                    }
                });
            }else{
                Toast.makeText(this, "You have already tried this question.", Toast.LENGTH_SHORT).show();
            }
        }else if(position==6){
            if(clickableItem7==0){
                final solveDialog dialog = new solveDialog(quizListActivity.this,problem[position],answer[12+position],score_val);
                dialog.show();
                dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        score_val = PreferencesUtil.getIntPreferences(quizListActivity.this,"score");
                        score.setText(String.valueOf(score_val));
                        clickableItem7=1;
                    }
                });
            }else{
                Toast.makeText(this, "You have already tried this question.", Toast.LENGTH_SHORT).show();
            }
        }else if(position==7){
            if(clickableItem8==0){
                final solveDialog dialog = new solveDialog(quizListActivity.this,problem[position],answer[12+position],score_val);
                dialog.show();
                dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        score_val = PreferencesUtil.getIntPreferences(quizListActivity.this,"score");
                        score.setText(String.valueOf(score_val));
                        clickableItem8=1;
                    }
                });
            }else{
                Toast.makeText(this, "You have already tried this question.", Toast.LENGTH_SHORT).show();
            }
        }else if(position==8){
            if(clickableItem9==0){
                final solveDialog dialog = new solveDialog(quizListActivity.this,problem[position],answer[12+position],score_val);
                dialog.show();
                dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        score_val = PreferencesUtil.getIntPreferences(quizListActivity.this,"score");
                        score.setText(String.valueOf(score_val));
                        clickableItem9=1;
                    }
                });
            }else{
                Toast.makeText(this, "You have already tried this question.", Toast.LENGTH_SHORT).show();
            }
        }else if(position==9){
            if(clickableItem10==0){
                final solveDialog dialog = new solveDialog(quizListActivity.this,problem[position],answer[12+position],score_val);
                dialog.show();
                dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        score_val = PreferencesUtil.getIntPreferences(quizListActivity.this,"score");
                        score.setText(String.valueOf(score_val));
                        clickableItem10=1;
                    }
                });
            }else{
                Toast.makeText(this, "You have already tried this question.", Toast.LENGTH_SHORT).show();
            }
        }else if(position==10){
            if(clickableItem11==0){
                final solveDialog dialog = new solveDialog(quizListActivity.this,problem[position],answer[12+position],score_val);
                dialog.show();
                dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        score_val = PreferencesUtil.getIntPreferences(quizListActivity.this,"score");
                        score.setText(String.valueOf(score_val));
                        clickableItem11=1;
                    }
                });
            }else{
                Toast.makeText(this, "You have already tried this question.", Toast.LENGTH_SHORT).show();
            }
        }else if(position==11){
            if(clickableItem12==0){
                final solveDialog dialog = new solveDialog(quizListActivity.this,problem[position],answer[12+position],score_val);
                dialog.show();
                dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        score_val = PreferencesUtil.getIntPreferences(quizListActivity.this,"score");
                        score.setText(String.valueOf(score_val));
                        clickableItem12=1;
                    }
                });
            }else{
                Toast.makeText(this, "You have already tried this question.", Toast.LENGTH_SHORT).show();
            }
        }else if(position==12){
            if(clickableItem12==0){
                final solveDialog dialog = new solveDialog(quizListActivity.this,problem[position],answer[12+position],score_val);
                dialog.show();
                dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        score_val = PreferencesUtil.getIntPreferences(quizListActivity.this,"score");
                        score.setText(String.valueOf(score_val));
                        clickableItem12=1;
                    }
                });
            }else{
                Toast.makeText(this, "You have already tried this question.", Toast.LENGTH_SHORT).show();
            }
        }

        
        if(clickableItem1==1 && clickableItem2==1 && clickableItem3==1 && clickableItem4==1 && clickableItem5==1 && clickableItem6==1 && clickableItem7==1 && clickableItem8==1 && clickableItem9==1 && clickableItem10==1 && clickableItem11==1 && clickableItem12==1){
           
            Intent intent = new Intent(quizListActivity.this,finishActivity.class);
            startActivity(intent);
        }
    }

    
    private void readProblems(){
        class read extends AsyncTask<String, Void, String> {

            String result;

            @Override
            protected void onPreExecute() {
                progressDialog = ProgressDialog.show(quizListActivity.this, "Reading", "Reading Quiz Problems..");
                progressDialog.setProgressStyle(android.R.style.Widget_ProgressBar_Large);
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(String s) {
                datas = problem;

                progressDialog.dismiss();

                show(problem);

                Toast.makeText(quizListActivity.this, "Read Quiz Problem Data Completed..", Toast.LENGTH_SHORT).show();

                super.onPostExecute(s);
            }

           
            @Override
            protected String doInBackground(String... params) {
                try{

                   
                    String link = "http://seokoh14.esy.es//problem.php";

                   
                    URL url = new URL(link);
                    HttpURLConnection mUrlConnection = (HttpURLConnection) url.openConnection();
                    mUrlConnection.setDoInput(true);

                    BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), Charset.forName("UTF-8")));
                    while(true){
                        final String line = reader.readLine();
                        if(line==null){
                            break;
                        }else{
                           
                            problem = line.split("<br/>");
                          
                            answer = problem;
                        }
                    }

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }
        }

        read task = new read();
        task.execute();
    }

    
    public void show(String problem[]) {

        data = new ArrayList<>();
        for (int i = 0; i < problem.length/2; i++) {
            
            quizListItem item = new quizListItem(problem[i]);
            Log.e("data",problem[i]);
            
            data.add(item);
        }
       
        adapter = new quizListAdapter(quizListActivity.this, R.layout.listview_problem_item, data);
       
        problemList.setAdapter(adapter);

    }
}
