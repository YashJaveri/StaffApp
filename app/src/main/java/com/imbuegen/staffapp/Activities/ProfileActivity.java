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
import android.widget.TextView;
import android.widget.Toast;

import com.imbuegen.staffapp.Interfaces.fragmentCallback;
import com.imbuegen.staffapp.JavaObjects.UserObject;
import com.imbuegen.staffapp.R;
import com.imbuegen.staffapp.fragments.CommentsFragment;
import com.imbuegen.staffapp.fragments.EventsFragment;
import com.imbuegen.staffapp.fragments.HomeFragment;

import org.json.JSONArray;
import org.json.JSONObject;

public class ProfileActivity extends AppCompatActivity  implements fragmentCallback{
    ImageView profilePic;
    BottomNavigationView tabsNav;
    FragmentManager fragmentManager;

    UserObject user;

    TextView name,email,points,DoB,anniv,joiningDate,department,position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        if(getIntent().hasExtra("user")) {
            user = (UserObject) getIntent().getSerializableExtra("user");
        }else{
            initialize();
        }

        fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();

        profilePic=(ImageView)findViewById(R.id.img_profile_pic);
        profilePic.setImageResource(R.drawable.ic_person_black_24dp);

        tabsNav=(BottomNavigationView)findViewById(R.id.profile_tabs);
        tabsNav.setOnNavigationItemSelectedListener(mNavigationSelector);

        name=(TextView)findViewById(R.id.txt_profile_name);
        points=(TextView)findViewById(R.id.view_points);
        DoB=(TextView)findViewById(R.id.view_dob);
        anniv=(TextView)findViewById(R.id.view_anniv_date);
        joiningDate=(TextView)findViewById(R.id.view_joining_date);
        department=(TextView)findViewById(R.id.view_department);
        position=(TextView)findViewById(R.id.view_position);
        email=(TextView)findViewById(R.id.view_email);

      // initialize();

        name.setText(user.getName());
        points.setText(Integer.toString(user.getPoints()));
        DoB.setText(user.getDOB());

        if(user.getStatus().equals("married")) {
            anniv.setText(user.getAnnivDATE());
        }else{
            anniv.setVisibility(View.GONE);
        }

        email.setText(user.getEmail());
        joiningDate.setText(user.getJoiningDate());
        department.setText(user.getDepartment());
        position.setText(user.getposition());

        Fragment home = new HomeFragment();
        Bundle arg = new Bundle();
        arg.putBoolean("showRelated",true);
        home.setArguments(arg);
        ft.add(R.id.view_fragment_holder,home);
        ft.commit();
    }

    public void initialize(){

        UserObject user=new UserObject();
        user.setName("yash");
        user.setEmployeeID(1);
        user.setAnnivDATE("10/10/2000");
        user.setJoiningDate("2/2/2010");
        user.setDepartment("IT Department");
        user.setDOB("1/1/2010");
        user.setEmail("ekNoZhatu@gmail.com");
        user.setPoints(19);
        user.setStatus("married");
        user.setposition("developer");



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
    public void showComments(JSONArray commantrr) {
        Fragment comments = new CommentsFragment();
        Bundle bundle = new Bundle();
        bundle.putString("comments",commantrr.toString());
        comments.setArguments(bundle);
        FragmentTransaction ft =fragmentManager.beginTransaction();
        ft.replace(R.id.fragment_placeholder,comments);
        ft.commit();
    }

    @Override
    public void showUser(JSONObject user) {

    }
}
