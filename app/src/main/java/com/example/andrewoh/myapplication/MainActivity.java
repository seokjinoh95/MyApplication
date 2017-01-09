package com.example.andrewoh.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
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

        public class MainActivity extends AppCompatActivity {

            private String name;

            private EditText inputName;
            private Button login;
            private Button register;

            private ProgressDialog progressDialog;


            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputName = (EditText) findViewById(R.id.inputName);
        login = (Button) findViewById(R.id.login);
        register = (Button) findViewById(R.id.userRegister);

        
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final inputDialog dialog = new inputDialog(MainActivity.this);
                
                dialog.show();

            }
        });

        
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = inputName.getText().toString();
                login(name);
            }
        });
    }

    
    private void login(final String name) {
        class loginTask extends AsyncTask<String, Void, String> {

            String name_data = name;
            String result;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progressDialog = ProgressDialog.show(MainActivity.this, "Checking", "Checking Member..");
                progressDialog.setProgressStyle(android.R.style.Widget_ProgressBar_Large);
            }

            @Override
            protected void onPostExecute(String s) {

                
                if (result.equals("login complete")) {
                    Toast.makeText(MainActivity.this, "Checking Complete", Toast.LENGTH_SHORT).show();
                    PreferencesUtil.setPreferences(MainActivity.this, "name", name_data);
                    Intent intent = new Intent(MainActivity.this, MenuActivity.class);
                    progressDialog.dismiss();
                    startActivity(intent);
                }
              
                else if (result.equals("not registered username")) {
                    Toast.makeText(MainActivity.this, "Not registered UserName", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }

                super.onPostExecute(s);
            }

            
            @Override
            protected String doInBackground(String... params) {
                try {
                    name_data = URLEncoder.encode(name_data, "UTF-8");

                    
                    String link = "http://seokoh14.esy.es/login.php?name=" + name_data;

                    
                    URL url = new URL(link);
                    
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
        }
        loginTask task = new loginTask();
        task.execute(name);
    }
}
