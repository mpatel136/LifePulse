package com.saadatdevelopment.lifepulse.searchdialognew;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.lifepulselibrary.UserProfile;
import com.saadatdevelopment.lifepulse.searchdialognew.databasecontrollers.DatabaseController;
import com.saadatdevelopment.lifepulse.searchdialognew.R;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class User extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    private DatabaseController databaseController;

    private EditText etFullName, etHeight, etWeight, etDob;
    private Spinner genderSpinner, activityLevelSpinner;
    private Button btnRegister;

    private byte[] imageByteArray;

    private FloatingActionButton fabPlus, fabCamera;
    private Animation FabOpen, FabClose, FabClockwise, FabAntiClockwise;
    boolean isFabOpen = false;

    private final int CAMERA_REQUEST = 10;

    // values from the spinner
    private String strGender, strActivityLevel;

    private List<String> genders; // to store the genders in spinner
    private List<String> activityLevels;

    private ArrayAdapter<String> genderArrayAdapter;
    private ArrayAdapter<String> activityLevelArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActionBar bar = getSupportActionBar();
        bar.setTitle("User Profile");

        setContentView(R.layout.activity_user);

        // gets the value
        etFullName = (EditText) findViewById(R.id.etFullName);
        etHeight = (EditText) findViewById(R.id.etHeight);
        etWeight = (EditText) findViewById(R.id.etWeight);
        etDob = (EditText) findViewById(R.id.etDob);

        // fab
        fabPlus = (FloatingActionButton) findViewById(R.id.fabPlusUser);
        fabCamera = (FloatingActionButton) findViewById(R.id.fabCameraUser);

        FabOpen = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_open);
        FabClose = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_close);
        FabClockwise = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_clockwise);
        FabAntiClockwise = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_anticlockwise);

        fabPlus.setOnClickListener(this);
        fabCamera.setOnClickListener(this);

        // initialize the spinners
        genderSpinner = (Spinner) findViewById(R.id.genderSpinner);
        activityLevelSpinner = (Spinner) findViewById(R.id.activityLevelSpinner);

        // sets adapter to the gender spinner
        genderArray();
        genderArrayAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, genders);
        genderSpinner.setAdapter(genderArrayAdapter);
        genderSpinner.setOnItemSelectedListener(this);

        // sets the adapter to the activity level spinner
        activityLevelArray();
        activityLevelArrayAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, activityLevels);
        activityLevelSpinner.setAdapter(activityLevelArrayAdapter);
        activityLevelSpinner.setOnItemSelectedListener(this);

        // register button onclick event
        btnRegister = (Button) findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(this);



        databaseController = new DatabaseController(this);

    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnRegister) {
            String fullName, dateOfBirth, gender, activityLevel;
            double height, weight;

            fullName = etFullName.getText().toString();

            dateOfBirth = etDob.getText().toString();

            String heightString = etHeight.getText().toString();
            height = Double.parseDouble(heightString);

            String weightString = etWeight.getText().toString();
            weight = Double.parseDouble(weightString);

            gender = genderSpinner.getSelectedItem().toString();
            activityLevel = activityLevelSpinner.getSelectedItem().toString();

            if(fullName.equals("") || dateOfBirth.equals("") || etHeight.getText().toString().equals("") ||
                etWeight.getText().toString().equals("")) {
                Toast.makeText(this, "Please fill all the required fields.", Toast.LENGTH_SHORT).show();
            }
            else {

                UserProfile newUser = new UserProfile(fullName, gender, dateOfBirth, activityLevel, height, weight, imageByteArray);

                databaseController.createUser(newUser);

                Log.i("userProfile", "Registered");
                Toast.makeText(this, "Registration Successful", Toast.LENGTH_SHORT).show();

                // puts everything to the default position
                etFullName.setText("");
                etWeight.setText("");
                etHeight.setText("");
                etDob.setText("");
                genderSpinner.setSelection(0);
                activityLevelSpinner.setSelection(0);
            }
        }
        //Retrieves image through camera
        else if(v.getId() == R.id.fabPlusUser) {
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
        else if(v.getId() == R.id.fabCameraUser) {

            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

            try{
                startActivityForResult(intent, CAMERA_REQUEST);
            }
            catch (Exception e){
                Toast.makeText(this, "Error taking picture", Toast.LENGTH_SHORT);
            }
        }
    }

    // Gender Array
    private void genderArray() {

        // adds the year to the list
        genders = new ArrayList<>();
        genders.add("Male");
        genders.add("Female");
        genders.add("Other");

    }

    // ActivityLevel Array
    private void activityLevelArray() {

        // adds the year to the list
        activityLevels = new ArrayList<>();
        activityLevels.add("Athlete");
        activityLevels.add("Moderate");
        activityLevels.add("Average");
        activityLevels.add("Poor");

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(view.getId() == R.id.genderSpinner) {
            switch (position) {
                case 0:
                    strGender = "Male";
                    break;
                case 1:
                    strGender = "Female";
                    break;
                case 2:
                    strGender = "Other";
                    break;
                default:
                    strGender = "Nothing to show";
                    break;
            }
        }
        else if(view.getId() == R.id.activityLevelSpinner) {
            switch (position) {
                case 0:
                    strActivityLevel = "Athlete";
                    break;
                case 1:
                    strActivityLevel = "Moderate";
                    break;
                case 2:
                    strActivityLevel = "Average";
                    break;
                case 3:
                    strActivityLevel = "Poor";
                    break;
                default:
                    strActivityLevel = "Nothing to show";
                    break;
            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

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
            Log.v("nullPicture", "Picture taking failed");
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
}
