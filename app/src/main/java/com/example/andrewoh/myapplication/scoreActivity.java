package com.example.andrewoh.myapplication;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;


public class scoreActivity extends Activity {

   
    private String[] name; 
    private String[] score; 

    private ListView listview;
    private ListViewAdapter adapter; 
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        listview = (ListView) findViewById(R.id.scoreList1);

        getScore();
    }

    
    private void getScore(){
        class score extends AsyncTask<String,Void,String>{

            @Override
            protected void onPreExecute() {
                progressDialog = ProgressDialog.show(scoreActivity.this, "LOADING", "Reading Scores..");
                progressDialog.setProgressStyle(android.R.style.Widget_ProgressBar_Large);
                super.onPreExecute();
            }

           
            @Override
            protected void onPostExecute(String s) {

                ArrayList<ListViewItem> data = new ArrayList<>();
                ListViewItem item =null;
                Log.e("len", String.valueOf(name.length));

               
                if(name.length==6){
                    ListViewItem item1 = new ListViewItem(name[0],score[5]);
                    data.add(item1);

                    Log.e("DATA",name[0]+score[5]);

                    ListViewAdapter adapter = new ListViewAdapter(scoreActivity.this,R.layout.listview_item,data);
                    listview.setAdapter(adapter);
                }else if(name.length==7){
                    ListViewItem item1 = new ListViewItem(name[0],score[5]);
                    ListViewItem item2 = new ListViewItem(name[1],score[6]);
                    data.add(item1);
                    data.add(item2);

                    ListViewAdapter adapter = new ListViewAdapter(scoreActivity.this,R.layout.listview_item,data);
                    listview.setAdapter(adapter);
                }else if(name.length==8){
                    ListViewItem item1 = new ListViewItem(name[0],score[5]);
                    ListViewItem item2 = new ListViewItem(name[1],score[6]);
                    ListViewItem item3 = new ListViewItem(name[2],score[7]);

                    data.add(item1);
                    data.add(item2);
                    data.add(item3);

                    ListViewAdapter adapter = new ListViewAdapter(scoreActivity.this,R.layout.listview_item,data);
                    listview.setAdapter(adapter);
                }else if(name.length==9){
                    ListViewItem item1 = new ListViewItem(name[0],score[5]);
                    ListViewItem item2 = new ListViewItem(name[1],score[6]);
                    ListViewItem item3 = new ListViewItem(name[2],score[7]);
                    ListViewItem item4 = new ListViewItem(name[3],score[8]);

                    data.add(item1);
                    data.add(item2);
                    data.add(item3);
                    data.add(item4);

                    ListViewAdapter adapter = new ListViewAdapter(scoreActivity.this,R.layout.listview_item,data);
                    listview.setAdapter(adapter);
                }else if(name.length==10){
                    ListViewItem item1 = new ListViewItem(name[0],score[5]);
                    ListViewItem item2 = new ListViewItem(name[1],score[6]);
                    ListViewItem item3 = new ListViewItem(name[2],score[7]);
                    ListViewItem item4 = new ListViewItem(name[3],score[8]);
                    ListViewItem item5 = new ListViewItem(name[4],score[9]);

                    data.add(item1);
                    data.add(item2);
                    data.add(item3);
                    data.add(item4);
                    data.add(item5);

                    ListViewAdapter adapter = new ListViewAdapter(scoreActivity.this,R.layout.listview_item,data);
                    listview.setAdapter(adapter);
                }
                progressDialog.dismiss();
                Toast.makeText(scoreActivity.this, "Loaded Scores!", Toast.LENGTH_SHORT).show();
                super.onPostExecute(s);
            }

            @Override
            protected String doInBackground(String... params) {

                try{

                    URL url = new URL("http://seokoh14.esy.es/score.php");
                    HttpURLConnection mUrlConnection = (HttpURLConnection) url.openConnection();
                    mUrlConnection.setDoInput(true);

                    
                    BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), Charset.forName("UTF-8")));
                    while(true){
                        final String line = reader.readLine();
                        if(line==null){
                            break;
                        }else{
                            name = line.split("<br/>");
                            score = name;

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
        score task = new score();
        task.execute();
    }
}
