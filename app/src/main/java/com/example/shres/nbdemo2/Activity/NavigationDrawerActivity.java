package com.example.shres.nbdemo2.Activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;

import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.shres.nbdemo2.Fragements.NavBar.CrimeReport;
import com.example.shres.nbdemo2.Fragements.BottomNavBar.Dashboard;
import com.example.shres.nbdemo2.Fragements.BottomNavBar.Home;
import com.example.shres.nbdemo2.Fragements.NavBar.MissingPeople;
import com.example.shres.nbdemo2.Fragements.NavBar.NearBy;
import com.example.shres.nbdemo2.Fragements.NavBar.News;
import com.example.shres.nbdemo2.Fragements.BottomNavBar.Profile;
import com.example.shres.nbdemo2.R;

public class NavigationDrawerActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private FloatingActionButton fab;

    //Bottom navigation bar activity  for calling corresponding fragments
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.flMain,new Home());
                    ft.commit();
                    return true;
                case R.id.navigation_dashboard:
                    FragmentTransaction ft1 = getSupportFragmentManager().beginTransaction();
                    ft1.replace(R.id.flMain,new Dashboard());
                    ft1.commit();
                    return true;
                case R.id.navigation_profile:
                    FragmentTransaction ft2 = getSupportFragmentManager().beginTransaction();
                    ft2.replace(R.id.flMain,new Profile());
                    ft2.commit();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //call 100
/*
        fab = (FloatingActionButton) findViewById(R.id.call100);
        fab.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override

            public void onClick(View view) {
                // to make call
                Intent callintent = new Intent(Intent.ACTION_DIAL);
                callintent.setData(Uri.parse("tel:100"));
                startActivity(callintent);
            }

        });
*/


        TextView mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //default fragment
        if (savedInstanceState == null ){
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.flMain,new Home());
        ft.commit();

        }


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation_drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.call) {
            call();
            return true;
        }
        if (id==R.id.message){

            return true;
        }


        return super.onOptionsItemSelected(item);
    }




    // selecting navigation bar items n opening corresponding fragments
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_news) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.flMain,new News());
            ft.commit();
        } else if (id == R.id.nav_missing) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.flMain,new MissingPeople());
            ft.commit();
        } else if (id == R.id.nav_crime_report) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.flMain,new CrimeReport());
            ft.commit();
        } else if (id == R.id.nav_near_by) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.flMain,new NearBy());
            ft.commit();
        } else if (id == R.id.nav_about_FAQ) {

            Intent launchActivity2 = new Intent(NavigationDrawerActivity.this, AboutandFaq.class);
            startActivity(launchActivity2 );

        } else if (id== R.id.nav_share) {
           share();
           return true;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    //share ur app with other application users-------in Navigation bar 
    private void share() {
        Intent i = new Intent(

                android.content.Intent.ACTION_SEND);

        i.setType("text/plain");

        i.putExtra(

                android.content.Intent.EXTRA_TEXT, "My new app https://play.google.com/store/search?q=Turnep");

        startActivity(Intent.createChooser(

                i,

                "Share Via"));
    }

    // call action if needed ---------for condensed menu item
    private void call() {
        Intent callintent = new Intent(Intent.ACTION_DIAL);
        callintent.setData(Uri.parse("tel:100"));
        startActivity(callintent);
    }



}
