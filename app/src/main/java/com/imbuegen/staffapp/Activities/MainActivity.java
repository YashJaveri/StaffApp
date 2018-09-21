package com.imbuegen.staffapp.Activities;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.imbuegen.staffapp.R;
import com.imbuegen.staffapp.fragments.HomeFragment;
import com.imbuegen.staffapp.fragments.NotificationFragment;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNav;
    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNav=(BottomNavigationView)findViewById(R.id.bottom_nav);
        bottomNav.setOnNavigationItemSelectedListener(myNavigationSelector);

        bottomNav.setSelectedItemId(0);

        fragmentManager = getSupportFragmentManager();

        FragmentTransaction ft = fragmentManager.beginTransaction();

        Fragment home = new HomeFragment();
        ft.add(R.id.fragment_placeholder,home);
        ft.commit();

    }

    public BottomNavigationView.OnNavigationItemSelectedListener myNavigationSelector = new BottomNavigationView.OnNavigationItemSelectedListener() {


        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

            FragmentTransaction ft =fragmentManager.beginTransaction();
            switch (menuItem.getItemId()){
                case R.id.nav_home:
                   Fragment home=new HomeFragment();

                   ft.replace(R.id.fragment_placeholder,home);
                   ft.commit();
                    Toast.makeText(MainActivity.this, "you clicked home", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.nav_events:
                    Toast.makeText(MainActivity.this, "you clicked events", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.nav_notification:
                    Fragment Notification=new NotificationFragment();

                    ft.replace(R.id.fragment_placeholder,Notification);
                    ft.commit();
                    Toast.makeText(MainActivity.this, "you clicked notifications", Toast.LENGTH_SHORT).show();
                    return true;
            }
            return false;

        }
    };
}
