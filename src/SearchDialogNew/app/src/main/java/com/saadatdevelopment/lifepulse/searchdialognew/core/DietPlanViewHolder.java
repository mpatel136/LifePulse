package com.saadatdevelopment.lifepulse.searchdialognew.core;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.example.lifepulselibrary.DietModel;
import com.saadatdevelopment.lifepulse.searchdialognew.R;
import com.saadatdevelopment.lifepulse.searchdialognew.databasecontrollers.othercontroller.DietController;
import com.saadatdevelopment.lifepulse.searchdialognew.diet.DietPlanRevamp.NotificationReceiver;

import java.util.Calendar;

public class DietPlanViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private TextView description;
    private TextView time;
    private Switch aSwitch;
    private int position;
    private final String[] days = {"","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"};
    private DietController controller;
    private Context context;
    private ImageView delete;
    public DietPlanViewHolder(@NonNull View itemView) {
        super(itemView);
        description = (TextView) itemView.findViewById(R.id.txt_food_choice);
        time = (TextView) itemView.findViewById(R.id.txt_notification_time);
        aSwitch = (Switch) itemView.findViewById(R.id.switch_on_off);
        delete = (ImageView) itemView.findViewById(R.id.btn_delete_diet_plan);
        delete.setOnClickListener(this);
        aSwitch.setOnClickListener(this);
    }


    public void setDisplay(DietModel dietModel, int position, Context context){
        description.setText(dietModel.getDescription());
        aSwitch.setChecked(dietModel.isOn());
        this.dietModel = dietModel;
        this.position = position;
        this.context = context;
        if(dietModel.getDay() == 0) {
            time.setText(dietModel.getHourOfTheDay()+":"+dietModel.getMin());
        }
        else {
            String text = days[dietModel.getDay()] + "\t" + dietModel.getHourOfTheDay() + ":" + dietModel.getMin();
            time.setText(text);
        }

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, dietModel.getHourOfTheDay());
        calendar.set(Calendar.MINUTE, dietModel.getMin());
        calendar.set(Calendar.SECOND, 00);
        calendar.set(Calendar.MILLISECOND, 00);

        if(dietModel.getDay() != 0);
        //check on this
        calendar.set(Calendar.DAY_OF_WEEK, dietModel.getDay()-1);

        if(dietModel.isOn()) {
            Intent intent = new Intent(context.getApplicationContext(), NotificationReceiver.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(context.getApplicationContext(),1,intent,PendingIntent.FLAG_UPDATE_CURRENT);

            intent.putExtra("diet", dietModel);
            AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
            alarmManager.setRepeating(AlarmManager.RTC,calendar.getTimeInMillis(),AlarmManager.INTERVAL_DAY, pendingIntent);


        }
    }

    DietModel dietModel;
    @Override
    public void onClick(View v) {
        if(v.getId() == aSwitch.getId()){
        controller = new DietController(context);
        controller.updateDiet(dietModel, position);

        }
        if(v.getId() == delete.getId()){
            controller = new DietController(context);
            controller.removeDiet(dietModel);
        }

    }

}