package com.example.covid_19helpline;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TabHost;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class CovidHomePage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    Toolbar toolbar;
    NavigationView navigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_covid_home_page);

        toolbar = findViewById(R.id.draw_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Covid-19 Helpline");
        drawerLayout=findViewById(R.id.drawer);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        actionBarDrawerToggle.syncState();
        navigationView=findViewById(R.id.navigationView);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.dos);
        getSupportFragmentManager().beginTransaction().replace(R.id.container_fragment,new CountFragment()).commit();
        getSupportActionBar().setDisplayShowTitleEnabled(true);


        }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mymenu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        switch (id)
        {
            case R.id.about:
                startActivity(new Intent(getApplicationContext(),AboutUsActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.home:
                getSupportFragmentManager().beginTransaction().replace(R.id.container_fragment,new CountFragment()).commit();
                getSupportActionBar().setDisplayShowTitleEnabled(true);

                break;
            case R.id.dos:
                getSupportFragmentManager().beginTransaction().replace(R.id.container_fragment,new DoFragment()).commit();
                getSupportActionBar().setDisplayShowTitleEnabled(true);
                break;
            case R.id.dont:
                getSupportFragmentManager().beginTransaction().replace(R.id.container_fragment,new DontFragment()).commit();
                break;

            case R.id.district:
                getSupportFragmentManager().beginTransaction().replace(R.id.container_fragment,new DistrictsMainFragment()).commit();
                break;

            case R.id.count:
                getSupportFragmentManager().beginTransaction().replace(R.id.container_fragment,new CountIndiaFragment()).commit();
                break;
            case R.id.FAQ:
                getSupportFragmentManager().beginTransaction().replace(R.id.container_fragment,new HomeFragment()).commit();
                break;
           case R.id.share:
                //Toast.makeText(this,"Share",Toast.LENGTH_SHORT).show();
                       Intent shareIntent= new Intent(Intent.ACTION_SEND);
                       shareIntent.setType("text/plain");
                       shareIntent.putExtra(Intent.EXTRA_TEXT,"Covid-19 HelpLine App");
                       //shareIntent.putExtra(Intent.EXTRA_TEXT,body);
                       startActivity(Intent.createChooser(shareIntent,"Share Via"));
                break;

            case R.id.helpline:
                getSupportFragmentManager().beginTransaction().replace(R.id.container_fragment,new HelpLineFragment()).commit();
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
