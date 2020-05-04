package com.company.insta.covid19;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class logout extends AppCompatActivity {
    private Button logoutbutton;
    BottomNavigationView bottomNavigation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logout);

        bottomNavigation = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        logoutbutton= (Button) findViewById(R.id.logoutbtn);

      //when user press logout  the application heads bact to login page
        logoutbutton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(logout.this,     LoginActivity.class);
                startActivity(intent);
            }
        });
        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()) {
                    case R.id.navigation_search:
                        Intent intent = new Intent( logout.this, Search.class);
                        startActivity(intent);
                        break;
                    case R.id.navigation_graph:
                        Intent intent2 = new Intent(logout.this, Graph.class);
                        startActivity(intent2);
                        break;
                    case R.id.navigation_latestnews:
                        Intent intent3 = new Intent(logout.this, LatestNews.class);
                        startActivity(intent3);
                        break;

                    case R.id.logout:
                        Intent intent4 = new Intent(logout.this, logout.class);
                        startActivity(intent4);
                        break;


                }

                return false;
            }
        });
















    }


}
