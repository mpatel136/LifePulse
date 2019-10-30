package com.saadatdevelopment.lifepulse.searchdialognew;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.lifepulselibrary.UserProfile;
import com.saadatdevelopment.lifepulse.searchdialognew.bmihealth.BMI;
import com.saadatdevelopment.lifepulse.searchdialognew.bmihealth.FoodDiary;
import com.saadatdevelopment.lifepulse.searchdialognew.bmihealth.RecipeList;
import com.saadatdevelopment.lifepulse.searchdialognew.databasecontrollers.DatabaseController;
import com.saadatdevelopment.lifepulse.searchdialognew.diet.DietPlanRevamp.DietPlanRevamp;

import com.saadatdevelopment.lifepulse.searchdialognew.goalsettings.GoalHomePage;
import com.saadatdevelopment.lifepulse.searchdialognew.goalsettings.ListOfExercises;
import com.saadatdevelopment.lifepulse.searchdialognew.foods.FavoriteFood;
import com.saadatdevelopment.lifepulse.searchdialognew.foods.FoodRecommendations;
import com.saadatdevelopment.lifepulse.searchdialognew.foods.FoodRestrictions;
import com.saadatdevelopment.lifepulse.searchdialognew.diet.CalorieIntakeTracker;
import com.saadatdevelopment.lifepulse.searchdialognew.diet.DailyChart;
import com.saadatdevelopment.lifepulse.searchdialognew.diet.HeartConditionExercises;
import com.saadatdevelopment.lifepulse.searchdialognew.diet.WeeklyChart;
import com.saadatdevelopment.lifepulse.searchdialognew.diet.WeightTracking;
import com.saadatdevelopment.lifepulse.searchdialognew.menu.DefaultMenu;
import com.saadatdevelopment.lifepulse.searchdialognew.notificationservice.MyReceiver;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * https://stackoverflow.com/questions/20501225/using-service-to-run-background-and-create-notification     Used to make notifications display even when app is closed
 */
public class MainActivity extends DefaultMenu {

    private Spinner activityLevelSpinner;
    private List<String> activityNames;

    private CircleImageView profilePictureNav;
    private final int CAMERA_REQUEST_CODE = 100;
    private byte[] imageByteArray;

    private NavigationView nView;

    private DatabaseController dbController;

    private List<UserProfile> users;

    private TextView txtUserName, txtGender, txtDob, txtHeight, txtWeight, txtActivityLevel, txtAvgHeartRate, txtHeartCondition;

    private String name; // name of the user

    private DrawerLayout dl;
    private ActionBarDrawerToggle toggle;
    private NavigationView nv;
    Intent newIntent;

    // shared preference
    private SharedPreferences sharedPreferences;
    private final static String USERNAME_FILE = "current_user";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActionBar actionBar = getSupportActionBar();
        // hides the title bar
        //actionBar.hide();

        setContentView(R.layout.activity_main);

        dbController = new DatabaseController(this);

        users = dbController.getUsers();

        // Retrieve the user information and show them in the main activity
        txtUserName = (TextView) findViewById(R.id.txtUserName);
        txtActivityLevel = (TextView) findViewById(R.id.txtActivityLevel);
        txtAvgHeartRate = (TextView) findViewById(R.id.txtAvgHeartRate);
        txtDob = (TextView) findViewById(R.id.txtDOB);
        txtGender = (TextView) findViewById(R.id.txtGender);
        txtHeartCondition = (TextView) findViewById(R.id.txtHeartCondition);
        txtHeight = (TextView) findViewById(R.id.txtHeight);
        txtWeight = (TextView) findViewById(R.id.txtWeight);

        nView = (NavigationView) findViewById(R.id.nav_view);
        nView.setItemIconTintList(null);


        String userName = dbController.getUsername();

        // sets the value to the textviews from the db
        txtUserName.setText(userName);
        txtGender.setText(dbController.getGender(userName));
        txtDob.setText(dbController.getDob(userName));
        txtHeight.setText(dbController.getHeight(userName) + " cm");
        txtWeight.setText(dbController.getWeight(userName) + " KG");
        txtActivityLevel.setText(dbController.getActivityLevel(userName));

        //profilePictureNav.setImageBitmap(toBitmap(dbController.getProfilePicture(userName)));

        //Sets up notifications
        MyReceiver.setUpAlarm(getApplicationContext());

        dl = (DrawerLayout) findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(this, dl, R.string.open, R.string.close);




        dl.addDrawerListener(toggle);
        toggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        nv = (NavigationView) findViewById(R.id.nav_view);

        profilePictureNav = (CircleImageView) nv.findViewById(R.id.profile_image_nav);

//        profilePictureNav.setImageBitmap(toBitmap(dbController.getProfilePicture(dbController.getUsername())));

        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                int id = menuItem.getItemId();
                switch (id) {
                    case R.id.itmDoctorNotePage:
                        newIntent = new Intent(MainActivity.this, DoctorRecommendation.class);
                        break;
                    case R.id.itmAnnualReportPage:
                        newIntent = new Intent(MainActivity.this, AnnualReport.class);
                        break;
                    case R.id.itmBMIPage:
                        newIntent = new Intent(MainActivity.this, BMI.class);
                        break;
                    case R.id.itmExerciseList:
                        newIntent = new Intent(MainActivity.this, ListOfExercises.class);
                        break;
                    case R.id.itmStepCounter:
                        newIntent = new Intent(MainActivity.this, StepCounter.class);
                        break;
                    case R.id.itmCaloriesTrackerPage:
                        newIntent = new Intent(MainActivity.this, CalorieIntakeTracker.class);
                        break;
                    case R.id.itmDailyChartPage:
                        newIntent = new Intent(MainActivity.this, DailyChart.class);
                        break;
                    case R.id.itmDietPlanPage:
                        newIntent = new Intent(MainActivity.this, DietPlanRevamp.class);
                        break;
                    case R.id.itmExercisesPage:
                        newIntent = new Intent(MainActivity.this, HeartConditionExercises.class);
                        break;
                    case R.id.itmFavFoodPage:
                        newIntent = new Intent(MainActivity.this, FavoriteFood.class);
                        break;
                    case R.id.itmFoodLogPage:
                        newIntent = new Intent(MainActivity.this, FoodDiary.class);
                        break;
                    case R.id.itmFoodRecipesPage:
                        newIntent = new Intent(MainActivity.this, RecipeList.class);
                        break;
                    case R.id.itmFoodRecom:
                        newIntent = new Intent(MainActivity.this, FoodRecommendations.class);
                        break;
                    case R.id.itmGoalSetingsPage:
                        newIntent = new Intent(MainActivity.this, GoalHomePage.class);
                        break;
                    case R.id.itmHeartRatePage:
                        newIntent = new Intent(MainActivity.this, HeartRatePopup.class);
                        break;
                    case R.id.itmMonthlyReportPage:
                        newIntent = new Intent(MainActivity.this, MonthlyReport.class);
                        break;
                    case R.id.itmRestrictedFoodPage:
                        newIntent = new Intent(MainActivity.this, FoodRestrictions.class);
                        break;
                    case R.id.itmWeeklyChartPage:
                        newIntent = new Intent(MainActivity.this, WeeklyChart.class);
                        break;
                    case R.id.itmWeightTrackingPage:
                        newIntent = new Intent(MainActivity.this, WeightTracking.class);
                        break;
                }
                startActivity(newIntent);
                return true;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(toggle.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }

    private String getUserName(String fullName) {
        for(int i = 0; i < users.size(); i++) {
            if((users.get(i).getFullName().equals(fullName))) {
                name = users.get(i).getFullName();
            }
        }

        return name;
    }

    /**
     *  Convert byte array into image
     * @param image Byte array representation of a bird image
     * @return  Return bird image
     */
    private static Bitmap toBitmap(byte[] image) {
        return BitmapFactory.decodeByteArray(image, 0, image.length);
    }

}
