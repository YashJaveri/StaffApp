package com.imbuegen.staffapp.Activities;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.imbuegen.staffapp.R;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNav=(BottomNavigationView)findViewById(R.id.bottom_nav);
        bottomNav.setOnNavigationItemSelectedListener(myNavigationSelector);

    }

    public BottomNavigationView.OnNavigationItemSelectedListener myNavigationSelector = new BottomNavigationView.OnNavigationItemSelectedListener() {

        Fragment tab;
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

            switch (menuItem.getItemId()){
                case R.id.nav_home:
                   // menuItem.setCheckable(true);
                    Toast.makeText(MainActivity.this, "you clicked home", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.nav_events:
                    Toast.makeText(MainActivity.this, "you clicked events", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.nav_notification:
                    Toast.makeText(MainActivity.this, "you clicked notifications", Toast.LENGTH_SHORT).show();
                    return true;
            }
            return false;

        }
    };
}
