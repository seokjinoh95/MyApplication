package com.example.andrewoh.myapplication;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Rect;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;

 다이얼로그

    
    public class inputDialog extends Dialog {

        private Context mContext;
        private EditText inputName;
        private Button btnAdd;
        private ProgressDialog progressDialog;
    private String name;

    public inputDialog(Context context) {
        super(context);
        mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_add_dialog);

        inputName = (EditText) findViewById(R.id.editName);
        btnAdd = (Button) findViewById(R.id.addBtn);

        btnAdd.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                name = inputName.getText().toString();
                addUser();
                dismiss();
                return false;
            }
        });
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Rect dialogBounds = new Rect();
        getWindow().getDecorView().getHitRect(dialogBounds);
        if (!dialogBounds.contains((int) ev.getX(), (int) ev.getY())) {
            return false;
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        cancel();
    }

    
    private void addUser() {
        class addTask extends AsyncTask<Void, Void, String> {

            String result;

            @Override
            protected void onPreExecute() {
                progressDialog = ProgressDialog.show(mContext, "ADDING", "Adding UserName");
                progressDialog.setProgressStyle(android.R.style.Widget_ProgressBar_Large);
                super.onPreExecute();
            }

            @Override
            protected String doInBackground(Void... params) {
                try {

                    name = URLEncoder.encode(name, "UTF-8");

                    
                    URL url = new URL("http://seokoh14.esy.es/memberRegister.php?name="+name);
                    HttpURLConnection mUrlConnection = (HttpURLConnection) url.openConnection();
                    mUrlConnection.setDoInput(true);

                    
                    BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), Charset.forName("UTF-8")));

                    while (true) {
                        final String line = reader.readLine();
                        if (line == null) {
                            break;
                        } else {
                            result = line.toString();
                            Log.i("request : ", line);
                        }
                    }
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                return null;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
               
                progressDialog.dismiss();
                
                Toast.makeText(mContext, result, Toast.LENGTH_SHORT).show();
            }
        }
        addTask task = new addTask();
        task.execute();
    }
}
