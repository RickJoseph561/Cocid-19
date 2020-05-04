package com.company.insta.covid19;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class SignUpActivity extends AppCompatActivity {
    private EditText eusername;
    private EditText cpassword1;
    private EditText cpassword2;
    private Button button;
    private static final String FILE_NAME = "example1.txt";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        eusername = (EditText) findViewById(R.id.enterusername);
        cpassword1 =  (EditText) findViewById(R.id.cpassword1);
        cpassword2=(EditText) findViewById(R.id.cpassword2);
        button = (Button) findViewById(R.id.lgn_btn);

    /*    when user press back to user button; the the two password filds will check
        if they are equal it will save to files and  go back to login activity
        if not equal it will clear filds and tell user to try again

    */
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

            final  String p1 =   cpassword1.getText().toString();
            final  String p2 =  cpassword2.getText().toString();


                if(p1.contentEquals(p2)){

                    filesignup();

                }else{



                        eusername.setError("please fill in the blank");
                        eusername.requestFocus();




                        cpassword1.setError("please fill in the blank");
                        cpassword1.requestFocus();



                        cpassword2.setError("please fill in the blank");
                        cpassword2.requestFocus();




                }



            }
        });






    }

    /*
     save inpu to file and go to login screen
     */

    public void filesignup(){


            FileOutputStream fos = null;

            String text =  eusername.getText().toString() + " " +cpassword1.getText().toString() + "\n";
            try{



                fos = openFileOutput(FILE_NAME, MODE_APPEND);
                fos.write(text.getBytes());


                eusername.getText().clear();
                cpassword1.getText().clear();
                cpassword2.getText().clear();
                Toast.makeText(this, "Saved to " + getFilesDir() + "/" + FILE_NAME,
                        Toast.LENGTH_LONG).show();

                Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(intent);
            }catch (FileNotFoundException e) {
                e.printStackTrace();

            } catch (IOException e) {
                e.printStackTrace();
            }   //Nothing
        }
            //Do something






    }















































