package com.company.insta.covid19;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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

public class LatestNews extends AppCompatActivity {
    private WebView webview;
    BottomNavigationView bottomNavigation;
    private TextView post1;
    private EditText  numview;
    private Button button;
    private int n =0;
    private String sarray1;
    private String sarray2;
    private String sarray3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_latest_news);

        bottomNavigation = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        post1 = (TextView) findViewById(R.id.post1);
      numview=(EditText) findViewById(R.id.editext);
      button=(Button) findViewById(R.id.button);
        webview = (WebView) findViewById(R.id.Webview1);
        // displays title of the articles
       news();


       //when user submit there option with number  it displays the selected article with webview
       button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                String h = numview.getText().toString();

                if(h.contains("1")){
                    webview.setWebViewClient(new WebViewClient());
                    webview.loadUrl(sarray1);}


              else   if(h.contains("2")){
                    webview.setWebViewClient(new WebViewClient());
                    webview.loadUrl(sarray2);}

              else   if(h.contains("3")){
                    webview.setWebViewClient(new WebViewClient());
                    webview.loadUrl(sarray3);}




            }
        });

        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()) {
                    case R.id.navigation_search:
                        Intent intent = new Intent( LatestNews.this, Search.class);
                        startActivity(intent);
                        break;
                    case R.id.navigation_graph:
                        Intent intent2 = new Intent(LatestNews.this, Graph.class);
                        startActivity(intent2);
                        break;
                    case R.id.navigation_latestnews:
                        Intent intent3 = new Intent(LatestNews.this, LatestNews.class);
                        startActivity(intent3);
                        break;

                    case R.id.logout:
                        Intent intent4 = new Intent(LatestNews.this, logout.class);
                        startActivity(intent4);
                        break;


                }

                return false;
            }
        });







    }



// retrieve   the tile articles from url using json
    public void news() {

        String url = "https://covidtracking.com/api/press";


        JsonArrayRequest request = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray jsonArray) {
                        for (int i = 0; i < jsonArray.length(); i++) {
                            try {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                String name = jsonObject.getString("title");
                               String geturl = jsonObject.getString("url");

                                n++;





                                post1.append( n + "  "+name + "\n\n\n");


                                if (i == 0 ){sarray1=geturl;}
                                if (i == 1 ){sarray2=geturl;}
                                if (i == 2 ){sarray3=geturl;}



                                if(i==2){return;}

                            } catch (JSONException e) {
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

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);


    }


}