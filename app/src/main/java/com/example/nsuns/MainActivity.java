package com.example.nsuns;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.nsuns.ui.main.SectionsPagerAdapter;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public static Double TM_OHP = 0.0;
    public static Double TM_SQUAT = 0.0;
    public static Double TM_BENCH = 0.0;
    public static Double TM_DL = 0.0;

    private DrawerLayout drawer;
    private ActionBarDrawerToggle toggle;

    public static MainActivity mainActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainActivity = this;

        // update TMs

        updateTM_from_file();

        /* Create tab layouts */
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

        /* Create nav bar */
        Toolbar toolbar = findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);
        drawer = findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawer.addDrawerListener(toggle);
        try {
            getActionBar().setHomeButtonEnabled(true);
            getActionBar().setDisplayHomeAsUpEnabled(true);
        } catch (Exception e) { e.printStackTrace(); }

        /* initialise navigation view, set listener */
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        toggle.syncState();
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        toggle.onConfigurationChanged(newConfig);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.nav_item_one:
                startActivity(new Intent(this, UpdateTMs.class));
                //finish();
                break;
            case R.id.nav_item_two:
                Toast.makeText(this, "Add PB record", Toast.LENGTH_LONG).show();
                break;
            case R.id.nav_item_three:
                Toast.makeText(this, "Deprecated", Toast.LENGTH_LONG).show();
                break;
        }
        drawer.closeDrawer(GravityCompat.START); // close nav bar after clicking
        return false;
    }

    // handling back button press on nav bar
    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    /* Begin actual functions */

    // function to update TMs from file
    public void updateTM_from_file() {
        if (Arrays.asList(fileList()).contains("TMs.txt")) {
            try (BufferedReader br = new BufferedReader(new InputStreamReader(openFileInput("TMs.txt"), "UTF-8"))){
                for (String line = br.readLine(); line != null; line = br.readLine()) {
                    String[] parts = line.split(",");
                    if (parts[0].equals("ohp")) {
                        TM_OHP = Double.parseDouble(parts[1]);
                    } else if (parts[0].equals("squat")) {
                        TM_SQUAT = Double.parseDouble(parts[1]);
                    } else if (parts[0].equals("bench")) {
                        TM_BENCH = Double.parseDouble(parts[1]);
                    } else if (parts[0].equals("dl")) {
                        TM_DL = Double.parseDouble(parts[1]);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // function to kill mainactivity on reload
    public static MainActivity getInstance() {
        return mainActivity;
    }
}