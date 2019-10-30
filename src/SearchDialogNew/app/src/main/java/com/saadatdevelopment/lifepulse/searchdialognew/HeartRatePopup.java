package com.saadatdevelopment.lifepulse.searchdialognew;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.saadatdevelopment.lifepulse.searchdialognew.R;

import org.w3c.dom.Text;

/**
 * Class to launch the heart rate checker popup
 */
public class HeartRatePopup extends AppCompatActivity implements SensorEventListener, View.OnClickListener
{
    private SensorManager manager;
    private Sensor heartRateSensor;
    private TextView heartRateNumber;
    private int bpm;
    private static final String TAG = "HeartRateSensor";
    private boolean running;
    private ImageView imgHeartRate;

    /**
     * Find the views, set on click listeners to the button
     * @param savedInstanceState Collection of key value pairs
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActionBar bar1 = getSupportActionBar();

        bar1.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.white)));
        bar1.setTitle(Html.fromHtml("<font color='#7d2709'>Heart Rate</font>"));

        //Set the layout to the XML file
        setContentView(R.layout.activity_heart_rate_popup);

        imgHeartRate = (ImageView) findViewById(R.id.imgHeartRateIcon);
        imgHeartRate.setOnClickListener(this);

        heartRateNumber = (TextView) findViewById(R.id.txtHeartRate);
        manager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        manager.registerListener(this,heartRateSensor,SensorManager.SENSOR_DELAY_FASTEST);
    }

//    /**
//     * Handles on clcik events
//     * @param v The view that will be affected by onclick
//     */
//    @Override
//    public void onClick(View v)
//    {
//        //Instantiates the fragment manager
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        //Instantiate the heart rate fragment
//        fragment_heart_rate_reminder heartRateReminder = new fragment_heart_rate_reminder();
//        heartRateReminder.setCancelable(false);
//        //Show the fragment
//        heartRateReminder.show(fragmentManager, "Heart Rate Checker");
//    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if(event.sensor.getType() == Sensor.TYPE_HEART_RATE)
        {
//            bpm++;
            if(running)
            {
                for(int i = 0; i <= event.values.length; i++)
                {
                    heartRateNumber.append(" " + (int)event.values[i]);
                    Log.i(TAG, ""+(int)event.values[i]);
                }
                heartRateNumber.append((int)event.values[0] + " BPM");
            }
//            heartRateNumber.setText(String.valueOf(event.values[0]));
        }
        else
        {
            Log.i(TAG, "Unknown sensor type");
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        Log.i(TAG, "onAccuracyChanged - accuracy: " + accuracy);
    }

    @Override
    protected void onStart() {
        super.onStart();
        //Refresh rate of the sensor
        manager.registerListener(this,heartRateSensor,SensorManager.SENSOR_DELAY_FASTEST);
    }

    @Override
    protected void onPause() {
        super.onPause();
        running = false;
        manager.unregisterListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        running = true;
        heartRateSensor = manager.getDefaultSensor(Sensor.TYPE_HEART_RATE);

        if(heartRateSensor != null) {
            manager.registerListener(this, heartRateSensor, SensorManager.SENSOR_DELAY_FASTEST);
        }
        else {
            Toast.makeText(this, "Sensor cannot be found.", Toast.LENGTH_SHORT).show();
        }
    }

    //Might not be needed, same thing done in onResume method
    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.imgHeartRateIcon)
        {
            heartRateSensor = manager.getDefaultSensor(Sensor.TYPE_HEART_RATE);
            manager.registerListener(this, heartRateSensor,SensorManager.SENSOR_DELAY_FASTEST);
        }
    }
}
