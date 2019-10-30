package com.saadatdevelopment.lifepulse.searchdialognew.diet.DietPlanRevamp;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.saadatdevelopment.lifepulse.searchdialognew.R;

import java.util.Objects;

public class DayOfTheWeekPicker extends DialogFragment implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    Spinner day;
    Button ok;
    View view;
    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if(dialog!=null){
            int height = ViewGroup.LayoutParams.WRAP_CONTENT;
            int width = ViewGroup.LayoutParams.WRAP_CONTENT;
            dialog.getWindow().setLayout(width,height);
        }
    }

    public DayOfTheWeekPicker(){

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.Theme_AppCompat_Dialog);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dialog_day_picker, container, false);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.DKGRAY));

        this.view = view;
        day = (Spinner) view.findViewById(R.id.day_spinner);
        ok = (Button) view.findViewById(R.id.btn_ok);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(Objects.requireNonNull(this.getActivity()), R.array.days, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        day.setAdapter(adapter);
        day.setOnItemSelectedListener(this);

        ok.setOnClickListener(this);

        return this.view;

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        return super.onCreateDialog(savedInstanceState);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == ok.getId()){
            IDateTimePickerResult activity = (IDateTimePickerResult) getActivity();
            activity.onGetDay(day.getSelectedItemPosition());
            dismiss();
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
