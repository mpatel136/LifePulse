package com.saadatdevelopment.lifepulse.searchdialognew.menu;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.saadatdevelopment.lifepulse.searchdialognew.R;
import com.saadatdevelopment.lifepulse.searchdialognew.Settings;

public class DefaultMenu extends AppCompatActivity {
    ActionBar actionBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        actionBar = getSupportActionBar();
        //Changes the actions bar color for everything that extends this class.
        actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.white)));
    }

    protected void changeActionBarTitle(String title){
        actionBar.setTitle(title);
    }

    //MENU
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu_layout, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //Toast.makeText(this, "Selected Item" + item.getTitle(), Toast.LENGTH_LONG).show();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        switch (item.getItemId()){

            case R.id.about_page:
                DialogFragment aboutFragment = new aboutFragment();
                aboutFragment.show(ft, "About Fragment");
                return true;
            case R.id.settings_page:
                Intent settings_intent = new Intent(this, Settings.class);
                startActivity(settings_intent);
                return true;
            case R.id.help_page:
                DialogFragment helpFragment = new helpFragment();
                helpFragment.show(ft, "Help Fragment");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

