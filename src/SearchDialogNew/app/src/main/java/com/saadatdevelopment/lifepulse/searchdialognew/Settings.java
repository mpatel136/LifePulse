package com.saadatdevelopment.lifepulse.searchdialognew;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.provider.MediaStore;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lifepulselibrary.UserProfile;
import com.saadatdevelopment.lifepulse.searchdialognew.databasecontrollers.DatabaseController;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class Settings extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    private EditText etUsername, etDob, etHeight, etWeight;
    private Button btnSave;
    private TextView txtUsername, txtSteps, txtCalories;
    private Spinner genderSpinner, activityLevelSpinner;

    private CircleImageView profilePictureSetting;
    private final int CAMERA_REQUEST_CODE = 100;
    private byte[] imageByteArray;

    // values for update
    private String username, dob, strHeight, strWeight, gender, activityLevel;
    private double height, weight;

    // values from the spinner
    private String strGender, strActivityLevel;

    private List<String> genders; // to store the genders in spinner
    private List<String> activityLevels;

    private ArrayAdapter<String> genderArrayAdapter;
    private ArrayAdapter<String> activityLevelArrayAdapter;

    // db controller
    private DatabaseController dbController;

    // shared preference
    private SharedPreferences userSharedPref;
    private final static String USERNAME_FILE = "current_user";

    // snackbar
    private Snackbar snackbar;

    // shared preference
    private SharedPreferences sharedPreferences;

    static final int REQUEST_IMAGE_CAPTURE = 1;

    UserProfile user;

    // check

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar bar1 = getSupportActionBar();

        bar1.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.white)));
        bar1.setTitle(Html.fromHtml("<font color='#7d2709'>Settings</font>"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setContentView(R.layout.activity_settings);

        dbController = new DatabaseController(this);

        txtUsername = (TextView) findViewById(R.id.txtUsernameSetting);
        etUsername = (EditText) findViewById(R.id.etUsernameSettings);
        etDob = (EditText) findViewById(R.id.etDobSettings);
        etHeight = (EditText) findViewById(R.id.etHeightSettings);
        etWeight = (EditText) findViewById(R.id.etWeightSettings);

        profilePictureSetting = (CircleImageView) findViewById(R.id.profile_image_settings);
        profilePictureSetting.setOnClickListener(this);


        // initialize the spinners
        genderSpinner = (Spinner) findViewById(R.id.spinnerGenderSettings);
        activityLevelSpinner = (Spinner) findViewById(R.id.spinnerActivityLevelSettings);

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

        // buttons
        btnSave = (Button) findViewById(R.id.btnSaveSettings);
        btnSave.setOnClickListener(this);

        // shows the values
        username = dbController.getUsername();
        profilePictureSetting.setImageBitmap(toBitmap(dbController.getProfilePicture(username)));
        dob = dbController.getDob(username);
        strWeight = dbController.getWeight(username);
            weight = Double.parseDouble(strWeight);
        strHeight = dbController.getHeight(username);
            height = Double.parseDouble(strHeight);

        // Shared Preference
        sharedPreferences = getSharedPreferences(USERNAME_FILE, MODE_PRIVATE);
        txtUsername.setText(sharedPreferences.getString("Username", ""));

        etUsername.setText(username);
        etWeight.setText(strWeight);
        etHeight.setText(strHeight);
        etDob.setText(dob);

        gender = dbController.getGender(username);
        genderSpinner.setSelection(((ArrayAdapter<String>) genderSpinner.getAdapter()).getPosition(gender));

        activityLevel = dbController.getActivityLevel(username);
        activityLevelSpinner.setSelection(((ArrayAdapter<String>) activityLevelSpinner.getAdapter()).getPosition(activityLevel));
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
    public void onClick(View v) {
        if(v.getId() == R.id.btnSaveSettings) {

            String newUsername, newGender, newDob, newActivitylevel, newStrHeight, newStrWeight;
            double newHeight, newWeight;

            newUsername = etUsername.getText().toString();
            newGender = genderSpinner.getSelectedItem().toString();
            newDob = etDob.getText().toString();
            newActivitylevel = activityLevelSpinner.getSelectedItem().toString();
            newStrHeight = etHeight.getText().toString();
                newHeight = Double.parseDouble(newStrHeight);
            newStrWeight = etWeight.getText().toString();
                newWeight = Double.parseDouble(newStrWeight);

            user = new UserProfile(newUsername, newGender, newDob, newActivitylevel, newHeight, newWeight, imageByteArray);

            dbController.updateUser(user, username);

            txtUsername.setText(newUsername);

            // Snack bar;
            snackbar = snackbar = Snackbar.make(findViewById(R.id.settingCoordinatorLayout), R.string.setting_snackbar_title, Snackbar.LENGTH_INDEFINITE)
                    .setAction(R.string.setting_snackbar_action, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            snackbar.dismiss();
                        }
                    });
            snackbar.show();

            // Shared preference

            try {
                userSharedPref = getSharedPreferences(USERNAME_FILE, MODE_PRIVATE);
            }
            catch (Exception e) {
                Log.i("Test", "Error with shared preference file");
            }

            SharedPreferences.Editor sharedEditor = userSharedPref.edit();
            sharedEditor.putString("Username", newUsername);
            sharedEditor.putString("Gender", newGender);
            sharedEditor.putString("DOB", newDob);
            sharedEditor.putString("Height", String.valueOf(newHeight));
            sharedEditor.putString("Weight", String.valueOf(newWeight));
            sharedEditor.putString("Activity Level", newActivitylevel);

            Log.i("Test", "Shared preference committed successfully");
            sharedEditor.commit();
        }
        else if(v.getId() == R.id.profile_image_settings) {
            Toast.makeText(this, "click", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {

            try{

                Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                imageByteArray = getImageByteArray(bitmap);
                profilePictureSetting.setImageBitmap(bitmap);
                dbController.updateUser(user, username);
            }
            catch (Exception e){
                Log.v("nullPicture", "Picture taking failed");
                e.printStackTrace();
            }
        }
    }

    /**
     *  Convert byte array into image
     * @param image Byte array representation of a bird image
     * @return  Return bird image
     */
    private static Bitmap toBitmap(byte[] image) {
        return BitmapFactory.decodeByteArray(image, 0, image.length);
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
}
