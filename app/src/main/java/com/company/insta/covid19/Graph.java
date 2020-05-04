package com.company.insta.covid19;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.core.content.ContextCompat;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;



import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Graph extends AppCompatActivity {
    private WebView webview;
    Button openLinkButton;
    BottomNavigationView bottomNavigation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);
        // load url through webview
        final String url="https://coronavirus.app/map";
        bottomNavigation = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        webview = (WebView) findViewById(R.id.Webview);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setDomStorageEnabled(true);
        webview.setWebViewClient(new WebViewClient());
        webview.loadUrl(url);

        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()) {
                    case R.id.navigation_search:
                        Intent intent = new Intent( Graph.this, Search.class);
                        startActivity(intent);
                        break;
                    case R.id.navigation_graph:
                        Intent intent2 = new Intent(Graph.this, Graph.class);
                        startActivity(intent2);
                        break;
                    case R.id.navigation_latestnews:
                        Intent intent3 = new Intent(Graph.this, LatestNews.class);
                        startActivity(intent3);
                        break;

                    case R.id.logout:
                        Intent intent4 = new Intent(Graph.this, logout.class);
                        startActivity(intent4);
                        break;


                }

                return false;
            }
        });








    }




}
