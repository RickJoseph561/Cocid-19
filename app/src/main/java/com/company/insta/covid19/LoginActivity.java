package com.company.insta.covid19;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    private EditText userName,password;
    private Button button;
    private TextView signup;
    private static final String FILE_NAME = "example1.txt";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userName= (EditText) findViewById(R.id.username);
        password= (EditText) findViewById(R.id.password);
        button = (Button) findViewById(R.id.lgn_btn);
        signup= (TextView) findViewById(R.id.signup);

        // when the login button is press it heads  to the login function to verify user has an account
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

              login();



            }
        });
        signup.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(LoginActivity.this,     SignUpActivity.class);
                startActivity(intent);

            }
        });

    }


/* verify that the  username and password fields  are not empty

then we open a txt page with openfileinput and input stremrider with all the password and username
 to see if it exits in the text field
if it dose'nt exits   the texts field clears and the user ask to try again
*/
    private void login(){


      final   String name = userName.getText().toString();
       final String pass = password.getText().toString();

        if(TextUtils.isEmpty(name)){
            userName.setError("please fill in the blank");
            userName.requestFocus();
            return;
        }

        if(TextUtils.isEmpty(pass)){
            password.setError("please fill in the blank");
            password.requestFocus();
            return;
        }
        FileInputStream fis = null;

        try {
            fis = openFileInput(FILE_NAME);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            String line;
            StringBuilder sb = new StringBuilder();

           String example = userName.getText().toString() +" " +password.getText().toString();
            while( (line = br.readLine())!=null){

                sb.append(line).append("\n");

            if (sb.toString().contains(example)){

                    userName.getText().clear();
                    password.getText().clear();
                  fis.close();
                  Intent intent = new Intent( LoginActivity.this, Search.class);
                  startActivity(intent);
              } else {
                  userName.setError("try again");
                  userName.getText().clear();

                  password.setError("try again");
                  password.getText().clear();


              }

            }

        }catch(FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }

    }



}
