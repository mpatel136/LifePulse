package com.saadatdevelopment.lifepulse.searchdialognew.bmihealth;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.saadatdevelopment.lifepulse.searchdialognew.R;
import com.saadatdevelopment.lifepulse.searchdialognew.menu.DefaultMenu;
//ignore, this is test class
public class BMIMainActivity extends DefaultMenu implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);





    }

    @Override
    public void onClick(View v) {
        startActivity(new Intent(this, BMI.class));

    }


    public void startInputDialog(View v){
        Toast.makeText(this, "Availble on the next update", Toast.LENGTH_LONG).show();
    }
}
