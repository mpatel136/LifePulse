package com.saadatdevelopment.lifepulse.searchdialognew.bmihealth;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lifepulselibrary.Diary;
import com.saadatdevelopment.lifepulse.searchdialognew.R;
import com.saadatdevelopment.lifepulse.searchdialognew.databasecontrollers.DiaryDBController;

import java.io.ByteArrayOutputStream;
import java.util.Calendar;

public class CreateFoodDiary extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "WriteDiary";
    private EditText etName, etGuiltLevel, etDescription;
    private Button btnSaveDiary;

    private FloatingActionButton fabPlus, fabCamera;
    private Animation FabOpen, FabClose, FabClockwise, FabAntiClockwise;
    boolean isFabOpen = false;

    private final int CAMERA_REQUEST_CODE = 100;

    private Calendar cal;
    private int year, month, day, hour, minute;

    private byte[] imageByteArray;

    private Snackbar snackbar;

    private DiaryDBController dbController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActionBar bar1 = getSupportActionBar();

        bar1.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.white)));
        bar1.setTitle(Html.fromHtml("<font color='#7d2709'>Write Your Diary</font>"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setContentView(R.layout.activity_create_food_diary);

        // calender values
        cal = Calendar.getInstance();
        year = cal.get(Calendar.YEAR);
        month = cal.get(Calendar.MONTH);
        day = cal.get(Calendar.DAY_OF_MONTH);
        hour = cal.get(Calendar.HOUR);
        minute = cal.get(Calendar.MINUTE);

        // UI content
        etName = (EditText) findViewById(R.id.etDiaryName);
        etGuiltLevel = (EditText) findViewById(R.id.etDiaryGuiltLevel);
        etDescription = (EditText) findViewById(R.id.etDiaryDescription);

        fabPlus = (FloatingActionButton) findViewById(R.id.fabPlusDiary);
        fabCamera = (FloatingActionButton) findViewById(R.id.fabCameraDiary);

        FabOpen = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_open);
        FabClose = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_close);
        FabClockwise = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_clockwise);
        FabAntiClockwise = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_anticlockwise);

        fabPlus.setOnClickListener(this);
        fabCamera.setOnClickListener(this);

        btnSaveDiary = (Button) findViewById(R.id.btnSaveDiary);
        btnSaveDiary.setOnClickListener(this);

        dbController = new DiaryDBController(this);


    }

    @Override
    public void onClick(View v) {

        if(v.getId() == R.id.btnSaveDiary) {
            String name = etName.getText().toString();
            String date = day + "-" + month + "-" + year + ", at " + hour + ":" + minute;
            String guiltLevel = etGuiltLevel.getText().toString();
            String description = etDescription.getText().toString();

            if (name.equals("") || guiltLevel.equals("") || description.equals("")) {
                Toast.makeText(this, "Please fill the required fields!", Toast.LENGTH_SHORT).show();
            } else {

                Diary newDiary = new Diary(name, guiltLevel, date, description, imageByteArray);

                dbController.createDiary(newDiary);

                clearField();

                // Snack bar;
                snackbar = Snackbar.make(findViewById(R.id.diaryCoordinatorLayout), R.string.diary_snackbar_title, Snackbar.LENGTH_INDEFINITE)
                        .setAction(R.string.diary_snackbar_action, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                snackbar.dismiss();
                            }
                        });
                snackbar.show();
            }
        }
        //Retrieves image through camera
        else if(v.getId() == R.id.fabPlusDiary) {
            if(isFabOpen) {
                fabCamera.startAnimation(FabClose);
                fabPlus.startAnimation(FabAntiClockwise);

                fabCamera.setClickable(false);

                isFabOpen = false;
            }
            else {
                fabCamera.startAnimation(FabOpen);
                fabPlus.startAnimation(FabClockwise);

                fabCamera.setClickable(true);

                isFabOpen = true;
            }
        }
        //Opens camera app to take a picture
        else if(v.getId() == R.id.fabCameraDiary) {

            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

            try{
                startActivityForResult(intent, CAMERA_REQUEST_CODE);
            }
            catch (Exception e){
                Toast.makeText(this, "Error taking picture", Toast.LENGTH_SHORT);
            }
        }
    }

    //Retrieves image from camera
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        try{
            Bitmap bitmap = (Bitmap)data.getExtras().get("data");
            imageByteArray = getImageByteArray(bitmap);

        }
        catch (Exception e){
            Log.v(TAG, "Picture taking failed");
            e.printStackTrace();
        }
    }

    /**
     * Convert image bitmap to byte array to store in database.
     * @param bitmap iamge bitmap value
     * @return bitmap representation of the image
     */
    public static byte[] getImageByteArray(Bitmap bitmap) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, outputStream);
        return outputStream.toByteArray();
    }

    private void clearField() {
        etName.setText("");
        etGuiltLevel.setText("");
        etDescription.setText("");
    }
}
