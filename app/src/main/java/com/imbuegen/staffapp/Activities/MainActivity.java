package com.imbuegen.staffapp.Activities;

import android.Manifest;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.imbuegen.staffapp.R;
import com.imbuegen.staffapp.fragments.EventsFragment;
import com.imbuegen.staffapp.fragments.HomeFragment;
import com.google.firebase.iid.FirebaseInstanceId;
import com.imbuegen.staffapp.fragments.NotificationFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    BottomNavigationView bottomNav;
    FragmentManager fragmentManager;

    public static final int PROFILEREQUESTCODE=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNav=(BottomNavigationView)findViewById(R.id.bottom_nav);
        bottomNav.setOnNavigationItemSelectedListener(myNavigationSelector);

        bottomNav.setSelectedItemId(0);

        fragmentManager = getSupportFragmentManager();

        FragmentTransaction ft = fragmentManager.beginTransaction();
        String token = FirebaseInstanceId.getInstance().getToken();
        Log.d("MYTAG",token);
        Fragment home = new HomeFragment();
        Bundle arg = new Bundle();
        arg.putBoolean("showRelated",false);
        home.setArguments(arg);
        ft.add(R.id.fragment_placeholder,home);
        ft.commit();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.menu_activity_main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId()==R.id.user_profile){
            //start an profile activity here
            Intent intent=new Intent(MainActivity.this,ProfileActivity.class);
            startActivityForResult(intent,PROFILEREQUESTCODE);
        }
        return super.onOptionsItemSelected(item);
    }

    public BottomNavigationView.OnNavigationItemSelectedListener myNavigationSelector = new BottomNavigationView.OnNavigationItemSelectedListener() {


        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

            FragmentTransaction ft =fragmentManager.beginTransaction();
            Bundle args =new Bundle();
            switch (menuItem.getItemId()){
                case R.id.nav_home:
                   Fragment home=new HomeFragment();
                   args.putBoolean("showRelated",false);
                   home.setArguments(args);
                   ft.replace(R.id.fragment_placeholder,home);
                   ft.commit();
                    Toast.makeText(MainActivity.this, "you clicked home", Toast.LENGTH_SHORT).show();
                    return true;


                case R.id.nav_events:
                    Fragment eventTab = new EventsFragment();
                    args.putBoolean("showRelated",false);
                    eventTab.setArguments(args);
                    ft.replace(R.id.fragment_placeholder,eventTab);
                    ft.commit();
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


    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.post_up_button:

                break;
            case R.id.post_down_button:

                break;
            case R.id.btn_comment:

                break;
        }
        Toast.makeText(this, "thumbs up!!!", Toast.LENGTH_SHORT).show();
    }
}
