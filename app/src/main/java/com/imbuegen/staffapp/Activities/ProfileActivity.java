package com.imbuegen.staffapp.Activities;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.imbuegen.staffapp.R;
import com.imbuegen.staffapp.fragments.EventsFragment;
import com.imbuegen.staffapp.fragments.HomeFragment;

public class ProfileActivity extends AppCompatActivity  implements View.OnClickListener{
    ImageView profilePic;
    BottomNavigationView tabsNav;
    FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();

        profilePic=(ImageView)findViewById(R.id.img_profile_pic);
        profilePic.setImageResource(R.drawable.ic_person_black_24dp);

        tabsNav=(BottomNavigationView)findViewById(R.id.profile_tabs);
        tabsNav.setOnNavigationItemSelectedListener(mNavigationSelector);


        Fragment home = new HomeFragment();
        Bundle arg = new Bundle();
        arg.putBoolean("showRelated",true);
        home.setArguments(arg);
        ft.add(R.id.view_fragment_holder,home);
        ft.commit();
    }


    public BottomNavigationView.OnNavigationItemSelectedListener mNavigationSelector = new BottomNavigationView.OnNavigationItemSelectedListener() {


        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

            FragmentTransaction ft =fragmentManager.beginTransaction();
            Bundle args=new Bundle();

            switch (menuItem.getItemId()){
                case R.id.profile_posts:
                    Fragment home=new HomeFragment();
                    args.putBoolean("showRelated",true);
                    home.setArguments(args);
                    ft.replace(R.id.view_fragment_holder,home);
                    ft.commit();
                    return true;

                case R.id.profile_events:
                    Fragment eventTab = new EventsFragment();
                    args.putBoolean("showRelated",true);
                    eventTab.setArguments(args);
                    ft.replace(R.id.view_fragment_holder,eventTab);
                    ft.commit();
                    return true;


            }
            return false;
        }
    };

    @Override
    public void onClick(View view) {
        Toast.makeText(this, "thumbs up!!!", Toast.LENGTH_SHORT).show();
    }
}
