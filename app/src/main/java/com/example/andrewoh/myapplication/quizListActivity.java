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

    private int score_val=0; //점수
    private String[] datas; //문제 및 정답이 저장되어있는 배열
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

    //이 메소드를 통해서 각 아이템당 문제를 풀 수 있는데, 아이템에 대한 문제를 풀면 이 문제를 다시 푸는
    //것에 대해서 처리를 해줘야하는데 처리를 못해줬다.

    //결국에는 각 아이템 인덱스에 대한 클릭이 되었는지를 따지기 위해서 int형 변수들을 각 하나씩 선언해주고 한번
    //클릭이벤트를 받으면 변수를 다른 수로 변하게 해서 방지를 시키게 되었다.
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
       //첫번째 줄에있는 아이템을 클릭시에
        if(position==0){
            //첫클릭시의 경우
            if(clickableItem1==0){
                //문제를 푸는 다이얼로그를 실행한다.
                final solveDialog dialog = new solveDialog(quizListActivity.this,problem[position],answer[12+position],score_val);
                dialog.show();
                dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        //이 문제를 풀고난 점수값을 받는다.
                        score_val = PreferencesUtil.getIntPreferences(quizListActivity.this,"score");
                        //스코어를 표시
                        score.setText(String.valueOf(score_val));
                        //clickableitem을 1로 바꿔줌으로써, 다시는 클릭을 못하도록 처리한다.
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

        //해당문제들을 다 풀어 더이상 풀 수 있는 문제가 없을 경우
        if(clickableItem1==1 && clickableItem2==1 && clickableItem3==1 && clickableItem4==1 && clickableItem5==1 && clickableItem6==1 && clickableItem7==1 && clickableItem8==1 && clickableItem9==1 && clickableItem10==1 && clickableItem11==1 && clickableItem12==1){
            //점수를 출력하고 서버에 점수를 전송하는 액티비티로 이동한다.
            Intent intent = new Intent(quizListActivity.this,finishActivity.class);
            startActivity(intent);
        }
    }

    //웹서버에서 문제를 읽어옴(스레드 사용)
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

            //문제 웹서버 접속해 문제 읽어오는 메소드
            @Override
            protected String doInBackground(String... params) {
                try{

                    //문제들을 랜덤으로 출력해주는 페이지이다.
                    String link = "http://seokoh14.esy.es//problem.php";

                    //서버에 연결을 한다.
                    URL url = new URL(link);
                    HttpURLConnection mUrlConnection = (HttpURLConnection) url.openConnection();
                    mUrlConnection.setDoInput(true);

                    BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), Charset.forName("UTF-8")));
                    while(true){
                        final String line = reader.readLine();
                        if(line==null){
                            break;
                        }else{
                            //문제들과 답을 한번에 다 불러와서 problem에 저장을 한다.
                            problem = line.split("<br/>");
                            //answer배열에 problem을 저장한다.
                            answer = problem;
                        }
                    }//여기 while문을 통해서 문제들을 배열안에 저장을 합니다.

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

    //문제들을 리스트뷰로 나타낸다.
    public void show(String problem[]) {

        data = new ArrayList<>();
        for (int i = 0; i < problem.length/2; i++) {
            //for문에 왜 문제배열의 길이의 반까지만 루프를 돌리냐면, 서버에서 불러오는것은 일단 문제만이 아니다.
            //정답도 함께 불러오기때문에 배열의 길이를 그대로 불러오면 리스트에 정답까지 나타나기때문에 일부러 길이의 절반까지만 루프를 돌리는것이다.

            //리스트뷰에 추가시켜줄 아이템안에 문제들을 저장한다.
            quizListItem item = new quizListItem(problem[i]);
            Log.e("data",problem[i]);
            //arraylist의 데이터배열 안에 아이템들을 저장한다.
            data.add(item);
        }
        //adapter을 초기화한다.
        adapter = new quizListAdapter(quizListActivity.this, R.layout.listview_problem_item, data);
        //adapter의 정보를 리스트뷰에 적용시킵니다.
        problemList.setAdapter(adapter);

    }
}
