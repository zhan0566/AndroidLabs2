package com.cst2335.androidlabs2;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class TestToolbar extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_toolbar);

        Toolbar tBar =(Toolbar)findViewById(R.id.toolbar);
       //setSupportActionBar(tBar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer,
                tBar, R.string.open, R.string.close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        
        NavigationView navigationView= findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        String message = null;

        switch(item.getItemId())
        {
            case R.id.chat_item:
                message = "You clicked on share";
                Intent i = new Intent (getApplicationContext(), ChatRoomActivity.class);
                startActivity(i);
                break;
            case R.id.weatherForcast_item:
                message = "You clicked on the search";
                Intent ii = new Intent (getApplicationContext(), WeatherForcastActivity.class);
                startActivity(ii);
                break;
            case R.id.goBackLogin_item:
                message = "You clicked on video";
                Intent iii = new Intent (getApplicationContext(), MainActivity.class);
                startActivity(iii);
        }

        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        drawerLayout.closeDrawer(GravityCompat.START);

        if ( message != null ) {
            Toast.makeText(this, "NavigationDrawer: " + message, Toast.LENGTH_LONG).show();
        }
        return false;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menumenu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        String message = null;
        //Look at your menu XML file. Put a case for every id in that file:
        switch(item.getItemId())
        {
            //what to do when the menu item is selected:
            case R.id.choice1:
                message = "You clicked on item 1";
                break;
            case R.id.choice2:
                message = "You clicked on item2";
                break;
            case R.id.choice3:
                message = "You clicked on the overflow menu";
                break;
        }

        if ( message != null ) {
            Toast.makeText(this, message, Toast.LENGTH_LONG).show();
        }
        return true;
    }

}