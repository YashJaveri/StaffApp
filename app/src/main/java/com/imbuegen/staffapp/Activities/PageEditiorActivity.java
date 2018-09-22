package com.imbuegen.staffapp.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.imbuegen.staffapp.R;

public class PageEditiorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_editior);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_post_editior,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.update:
                //update here
                finishActivity(MainActivity.EDITIOR_REQUEST_CODE);
                break;

            case R.id.delete:
                //delete here
                finishActivity(MainActivity.EDITIOR_REQUEST_CODE);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
