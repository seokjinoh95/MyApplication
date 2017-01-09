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
            private Button login; //로그인버튼
            private Button register; //회원가입 버튼

            private ProgressDialog progressDialog;//로딩형태의 다이얼로그


            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputName = (EditText) findViewById(R.id.inputName);
        login = (Button) findViewById(R.id.login);
        register = (Button) findViewById(R.id.userRegister);

        //register 버튼 눌렀을 경우
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final inputDialog dialog = new inputDialog(MainActivity.this);
                //이름을 입력하는 다이얼로그를 실행한다.
                dialog.show();

            }
        });

        //login 버튼을 눌렀을 경우
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = inputName.getText().toString();
                login(name);
            }
        });
    }

    //웹통신을 통하여 db에 해당 이름이 등록되어있는지 확인하고 확인이 되면 로그인하는 형태의 메소드
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

                //통신을 통하여 얻은 결과값의 경우에 따라서 반응을 달리한다.
                //로그인 성공 경우
                if (result.equals("login complete")) {
                    Toast.makeText(MainActivity.this, "Checking Complete", Toast.LENGTH_SHORT).show();
                    PreferencesUtil.setPreferences(MainActivity.this, "name", name_data);
                    Intent intent = new Intent(MainActivity.this, MenuActivity.class);
                    progressDialog.dismiss();
                    startActivity(intent);
                }
                //서버의 DB에 이름이 등록이 되지 않은 경우
                else if (result.equals("not registered username")) {
                    Toast.makeText(MainActivity.this, "Not registered UserName", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }

                super.onPostExecute(s);
            }

            //로그인 서버에 접속 및 로그인 결과값 리턴
            @Override
            protected String doInBackground(String... params) {
                try {
                    name_data = URLEncoder.encode(name_data, "UTF-8");

                    //입력받은 이름값을 login을하는 서버에 값을 전송합니다.
                    //로그인 성공시에는 login complete라는 응답값을, 실패시에는 not registered username이라는 응답값을 받게된다.
                    String link = "http://seokoh14.esy.es/login.php?name=" + name_data;

                    //link값을 통한 URL 함수를 초기화해준다.
                    URL url = new URL(link);
                    //HttpUrlConnection을 이용해서 서버에 연결을 시도한다.
                    HttpURLConnection mUrlConnection = (HttpURLConnection) url.openConnection();
                    mUrlConnection.setDoInput(true);

                    BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), Charset.forName("UTF-8")));

                    //서버 연결을 통해 출력이 된 응답값을 받는다.
                    while (true) {
                        //응답값이 1줄이상이라면 개행을 하면서 추가 응답값을 line에 넣어준다.
                        final String line = reader.readLine();
                        if (line == null) {
                            break;
                        } else {
                            //응답값을 result 변수에 넣어준다.
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
