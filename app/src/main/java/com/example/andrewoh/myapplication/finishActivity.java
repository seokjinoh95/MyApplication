package com.example.andrewoh.myapplication;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;


public class finishActivity extends Activity {

    TextView scoreText;
    ProgressDialog progressDialog;

    int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish);

        //score키값에 저장된 변수값을 가져온다.
        score = PreferencesUtil.getIntPreferences(finishActivity.this,"score");

        scoreText = (TextView)findViewById(R.id.score_text);
        scoreText.setText(String.valueOf(score));

        setScore(score);
    }

    //점수 웹서버에 전송 메소드(스레드 사용)
    public void setScore(final int score){
        class set extends AsyncTask<String,Void,String> {

            final int sc = score;
            String result;

            @Override
            protected void onPreExecute() {
                progressDialog = ProgressDialog.show(finishActivity.this, "Registering", "Registering Player Score To Server..");
                progressDialog.setProgressStyle(android.R.style.Widget_ProgressBar_Large);

                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(String s) {

                //응답값이 finish일 경우(성공)
                if(result.equals("finish")){
                    progressDialog.dismiss();
                    Toast.makeText(finishActivity.this, "Registered Player Score To Server!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(finishActivity.this,MainActivity.class); //mainactivity로 이동(로그인창)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); //넘어가게되는 액티비티 이전의 액티비티을 다 삭제함
                    startActivity(intent);//액티비티시작
                    finish();
                }else{
                    progressDialog.dismiss();
                    Toast.makeText(finishActivity.this, "error occurred!", Toast.LENGTH_SHORT).show();
                }

                super.onPostExecute(s);
            }

            //웹서버에 서버 전송 및 REQUEST값 리턴
            @Override
            protected String doInBackground(String... params) {

                try {
                    //플레이어의 이름을 가져온다.
                    String name = PreferencesUtil.getPreferences(finishActivity.this,"name");
                    Log.e("id",name);
                    //테스트종료 후에 해당 플레이어에게 점수를 부여하기 위해서 이 사이트를 이용한다.
                    //request finish:점수반영 성공시
                    String link = "http://seokoh14.esy.es/clearTest.php?name="+name+"&score="+sc;

                    URL url = new URL(link);
                    HttpURLConnection mUrlConnection = (HttpURLConnection) url.openConnection();
                    mUrlConnection.setDoInput(true);

                    BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), Charset.forName("UTF-8")));
                    while(true){
                        final String line = reader.readLine();
                        if(line==null){
                            break;
                        }else{
                            //result라는 string안에 응답값을 저장한다.
                            result = line.toString();
                            Log.i("request info ",line);
                        }
                    }
                    return null;
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                    return new String("ERR" + e.getMessage());

                } catch (IOException e) {
                    e.printStackTrace();
                    return new String("ERR" + e.getMessage());

                }

            }
        }
        set task = new set();
        task.execute();
    }
}
