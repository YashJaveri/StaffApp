package com.imbuegen.staffapp.Activities;

import android.Manifest;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
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

import com.imbuegen.staffapp.Constants;
import com.imbuegen.staffapp.Controllers.DataController;

import com.imbuegen.staffapp.Interfaces.fragmentCallback;
import com.imbuegen.staffapp.JavaObjects.UserObject;
import com.imbuegen.staffapp.R;
import com.imbuegen.staffapp.fragments.CommentsFragment;
import com.imbuegen.staffapp.fragments.EventsFragment;
import com.imbuegen.staffapp.fragments.HomeFragment;
import com.google.firebase.iid.FirebaseInstanceId;
import com.imbuegen.staffapp.fragments.NotificationFragment;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;

public class MainActivity extends AppCompatActivity implements fragmentCallback {

    BottomNavigationView bottomNav;
    FragmentManager fragmentManager;
    FloatingActionButton fab;

    public static final int PROFILE_REQUEST_CODE=1;
    public static final int EDITIOR_REQUEST_CODE=2;
    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNav=(BottomNavigationView)findViewById(R.id.bottom_nav);
        bottomNav.setOnNavigationItemSelectedListener(myNavigationSelector);

        bottomNav.setSelectedItemId(0);

        fragmentManager = getSupportFragmentManager();

        fab=(FloatingActionButton)findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //start post editior
                Intent i = new Intent(MainActivity.this,PageEditiorActivity.class);
                startActivityForResult(i,EDITIOR_REQUEST_CODE);
            }
        });

        FragmentTransaction ft = fragmentManager.beginTransaction();
        Fragment home = new HomeFragment();
        Bundle arg = new Bundle();
        arg.putBoolean("showRelated",false);
        home.setArguments(arg);
        ft.add(R.id.fragment_placeholder,home);
        ft.commit();
        //FOR TEST PURPOSE:-
            DataController dataController = new DataController(this);
            dataController.onCreate();
            dataController.updatePost("5ba54bb13e5186a14fe3f06e", "Updated bc");
            AsyncHttpClient client = new AsyncHttpClient();
            RequestParams params = new RequestParams();
            client.addHeader("Authorization", "token eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6IjViYTUxMTc5OTgxNWIxNzEyM2NiOGQ3NSJ9.y5xePFWUWJB_WC7xLyQKYbJ_3JbiUTENvA1pauCmhbg");
            client.addHeader("content","BHENCHOD");
            client.post("http://tsec-18.herokuapp.com/post/5ba54bb13e5186a14fe3f06e/update", new TextHttpResponseHandler() {
                @Override
                public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {

                }

                @Override
                public void onSuccess(int statusCode, Header[] headers, String responseString) {
                    Log.d("MyApp", "Success");
                    Log.d("MyApp", responseString);
                }
            });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

       /* MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.menu_activity_main,menu);*/
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId()==R.id.user_profile){
            //start an profile activity here
            Intent intent=new Intent(MainActivity.this,ProfileActivity.class);
           // Bundle bundle = new Bundle();

            //pass the current user
          //  bundle.putSerializable("user",user);
            startActivityForResult(intent,PROFILE_REQUEST_CODE);
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
    public void showComments(JSONArray commentarr) {
        Fragment comments = new CommentsFragment();
        Bundle bundle = new Bundle();
        bundle.putString("comments",commentarr.toString());
        comments.setArguments(bundle);
        FragmentTransaction ft =fragmentManager.beginTransaction();
        ft.replace(R.id.fragment_placeholder,comments);
        ft.commit();
    }

    @Override
    public void showUser(JSONObject user) {
        Intent intent = new Intent(MainActivity.this,ProfileActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("user",user.toString());
        startActivityForResult(intent,PROFILE_REQUEST_CODE,bundle);
    }


}
