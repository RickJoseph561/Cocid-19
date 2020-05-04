package com.company.insta.covid19;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.JsonArrayRequest;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Search extends AppCompatActivity {

    private EditText stateorcountry;
    private Button button;
    private CheckBox favorite;
    private TextView show, textview;

    private String addcomp;
    BottomNavigationView bottomNavigation;
    private static final String FILE_NAME = "example8.txt";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        show= (TextView) findViewById(R.id.post);
        textview= (TextView) findViewById(R.id.textView);
        stateorcountry= (EditText) findViewById(R.id.stateorcountry);
        button = (Button) findViewById(R.id.searchbtn);
        favorite= (CheckBox)  findViewById(R.id.favorite);

       bottomNavigation = (BottomNavigationView) findViewById(R.id.bottom_navigation);
       // display fav that was clicked from previous session
        showfav();
        // Search for the state when button is pressed
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {


                if(stateorcountry.getText().toString() != null) {state();}else{stateorcountry.setError("type in state");
                    stateorcountry.getText().clear();
                }

            }
        });

        // go to add to vaf if not empty
        favorite.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(stateorcountry.getText().toString() != null) {addtofav();}else{stateorcountry.setError("type in state");
                stateorcountry.getText().clear();
                }

            }
        });










        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
             public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()) {
                    case R.id.navigation_search:
                        Intent intent = new Intent( Search.this, Search.class);
                        startActivity(intent);
                         break;
                    case R.id.navigation_graph:
                        Intent intent2 = new Intent(Search.this, Graph.class);
                        startActivity(intent2);
                        break;
                    case R.id.navigation_latestnews:
                        Intent intent3 = new Intent(Search.this, LatestNews.class);
                        startActivity(intent3);
                        break;

                    case R.id.logout:
                        Intent intent4 = new Intent(Search.this, logout.class);
                        startActivity(intent4);
                        break;


                }

                                return false;
            }
        });



    }

/*
  using json to get data from the link and displaying it on the show  text view
 */


    public void state(){

        String url =  "https://corona.lmao.ninja/v2/states";

            String result;
        JsonArrayRequest request = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray jsonArray) {
                        for(int i = 0; i < jsonArray.length(); i++) {
                            try {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                String name = jsonObject.getString("state");
                                if (name.contentEquals(stateorcountry.getText().toString())){
                                    int todaydeaths = jsonObject.getInt("todayDeaths");
                                    int active = jsonObject.getInt("active");
                                    int tests = jsonObject.getInt("tests");
                                    int testperonemill = jsonObject.getInt("testsPerOneMillion");
                                    int todaycases = jsonObject.getInt("todayCases");
                                   int cases = jsonObject.getInt("cases");
                                   int death = jsonObject.getInt("deaths");

                                    show.append("State " + name +"\n" +"Cases "+cases+"   today cases "+todaycases+"\n"+ "deaths"+death+
                                            "   today deaths " +todaydeaths +"\n"+"active "+active +"\n"+ "tests "+ tests + "   test per one mill "+testperonemill);
                                       return;
                                       }

                            }
                            catch(JSONException e) {
                                e.printStackTrace();
                            }
                        }


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                });

        RequestQueue queue= Volley.newRequestQueue(this);
        queue.add(request);
    }



  //add to the txt file name of the state
    public   void addtofav(){

       String save1 =  stateorcountry.getText().toString()+ "\n";

        FileOutputStream fos = null;


        try{

            fos = openFileOutput(FILE_NAME, MODE_APPEND);
            fos.write(save1.getBytes());
            Toast.makeText(this, "Saved to " + getFilesDir() + "/" + FILE_NAME,
                    Toast.LENGTH_LONG).show();

            fos.close();
        }catch (FileNotFoundException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }




    }
// open txt file get state names and display stats with json

    public void showfav(){


        FileInputStream fis;
        textview.append("Favorites" +"\n");
        try {
            fis = openFileInput(FILE_NAME);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            String line;
            StringBuilder sb = new StringBuilder();


            while( (line = br.readLine())!=null){

                String url =  "https://corona.lmao.ninja/v2/states";


                final String result =  line;
                JsonArrayRequest request = new JsonArrayRequest(url,
                        new Response.Listener<JSONArray>() {
                            @Override
                            public void onResponse(JSONArray jsonArray) {
                                for(int i = 0; i < jsonArray.length(); i++) {
                                    try {
                                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                                        String name = jsonObject.getString("state");
                                        if (name.contentEquals(result) ){
                                            int todaydeaths = jsonObject.getInt("todayDeaths");
                                            int active = jsonObject.getInt("active");
                                            int tests = jsonObject.getInt("tests");
                                            int testperonemill = jsonObject.getInt("testsPerOneMillion");
                                            int todaycases = jsonObject.getInt("todayCases");
                                            int cases = jsonObject.getInt("cases");
                                            int death = jsonObject.getInt("deaths");

                                            textview.append("State " + name +"\n" +"Cases "+cases+"   today cases "+todaycases+"\n"+ "deaths"+death+
                                                    "   today deaths " +todaydeaths +"\n"+"active "+active +"\n"+ "tests "+ tests + "   test per one mill "+testperonemill+ "\n");
                                            return;
                                        }

                                    }
                                    catch(JSONException e) {
                                        e.printStackTrace();
                                    }
                                }


                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError volleyError) {

                            }
                        });

                RequestQueue queue= Volley.newRequestQueue(this);
                queue.add(request);






            }




        }catch(FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }



    }









}
