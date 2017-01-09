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

        
        score = PreferencesUtil.getIntPreferences(finishActivity.this,"score");

        scoreText = (TextView)findViewById(R.id.score_text);
        scoreText.setText(String.valueOf(score));

        setScore(score);
    }

    
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

               
                if(result.equals("finish")){
                    progressDialog.dismiss();
                    Toast.makeText(finishActivity.this, "Registered Player Score To Server!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(finishActivity.this,MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();
                }else{
                    progressDialog.dismiss();
                    Toast.makeText(finishActivity.this, "error occurred!", Toast.LENGTH_SHORT).show();
                }

                super.onPostExecute(s);
            }

            
            @Override
            protected String doInBackground(String... params) {

                try {
                    
                    String name = PreferencesUtil.getPreferences(finishActivity.this,"name");
                    Log.e("id",name);
                    
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
