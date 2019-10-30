package com.saadatdevelopment.lifepulse.searchdialognew.bmihealth;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.saadatdevelopment.lifepulse.searchdialognew.databasecontrollers.DatabaseController;
import com.saadatdevelopment.lifepulse.searchdialognew.R;
import com.saadatdevelopment.lifepulse.searchdialognew.menu.DefaultMenu;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class BMI extends DefaultMenu implements View.OnClickListener {

    private Button btnLog;
    private DatabaseController databaseController;

    private SharedPreferences userSharedPref;
    private final static String USERNAME_FILE = "current_user";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi_layout);

        //Get current date
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate localDate = LocalDate.now();
        ((TextView) findViewById(R.id.txt_date)).setText(dtf.format(localDate));



        changeActionBarTitle("BMI");

        String username;
        try {
            userSharedPref = getSharedPreferences(USERNAME_FILE, MODE_PRIVATE);
            username = userSharedPref.getString("Username", "");
            databaseController = new DatabaseController(this);
            String weight = databaseController.getWeight(username);
            String height = databaseController.getHeight(username);

            Log.i("heartCount", "weight: " + 174 + ", height: " + 70.4724);

            int bmi = getBMI(kgToPounds(Double.parseDouble(weight)), cmToInches(Double.parseDouble(height)));
            Log.i("heartCount", "BMI: " + bmi);
            int count = heartCount(bmi);


            heartDisplay(count);

            TextView status = (TextView) findViewById(R.id.txt_bmi_status);
            String text = status.getText() + " " + bmi;
            status.setText(text);
        }catch (Exception e){
            Log.i("onCreate", "Error with shared preference file");
            int bmi = 0;

            int count = heartCount(bmi);


            heartDisplay(count);

            TextView status = (TextView) findViewById(R.id.txt_bmi_status);
            String text = status.getText() + " " + bmi;
            status.setText(text);
            Toast.makeText(this, "User Not Found", Toast.LENGTH_SHORT).show();
        }




    }




    private double cmToInches(double cm){
       return cm / 2.54;
    }
    private double kgToPounds(double kg){
        return kg * 2.20462;
    }

    private void heartDisplay(int hearts){

        if(hearts == 5){
            ((ImageView) findViewById(R.id.img_heart_5)).setImageDrawable(getDrawable(R.drawable.heart_rating_good));
        }
        if(hearts >= 4){
            ((ImageView) findViewById(R.id.img_heart_4)).setImageDrawable(getDrawable(R.drawable.heart_rating_good));
        }
        if(hearts >= 3){
            ((ImageView) findViewById(R.id.img_heart_3)).setImageDrawable(getDrawable(R.drawable.heart_rating_good));
        }
        if(hearts >= 2){
            ((ImageView) findViewById(R.id.img_heart_2)).setImageDrawable(getDrawable(R.drawable.heart_rating_good));
        }
        if(hearts >= 1){
            ((ImageView) findViewById(R.id.img_heart_1)).setImageDrawable(getDrawable(R.drawable.heart_rating_good));
        }

    }

    //gets the input of weight and height, sets the bmi appropriately
    private int getBMI(double weight, double height){
        return (int) ((weight * 0.453592)/((height * 0.0254) * (height * 0.0254)));
    }


    @Override
    public void onClick(View v) {

    }


    public int heartCount(int BMI){
        if(BMI >= 40){
            return 1;
        }
        else if(BMI >= 30){
            return 2;
        }
        else if(BMI >= 25 || BMI == 18){
            return 3;
        }
        else if(BMI == 24 || BMI == 19){
            return 4;
        }
        else if(BMI > 19){
            return 5;
        }
        else if(BMI >= 16){
            return 2;
        }
        else return 1;
    }
}
