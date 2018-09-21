package com.imbuegen.staffapp.Activities;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.imbuegen.staffapp.R;
import com.imbuegen.staffapp.fragments.ViewProfileFragment;

public class ProfileActivity extends AppCompatActivity {

    FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.add(R.id.fragment_profile_placeHolder,new ViewProfileFragment());
        ft.commit();
    }
}
