package com.saadatdevelopment.lifepulse.searchdialognew;

import android.graphics.drawable.ColorDrawable;
import android.graphics.pdf.PdfDocument;
import android.print.PrintAttributes;
import android.print.pdf.PrintedPdfDocument;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

import com.saadatdevelopment.lifepulse.searchdialognew.databasecontrollers.DatabaseController;

import java.io.IOException;
import java.io.InputStream;

public class DoctorRecommendation extends AppCompatActivity {

    private TextView txtDoctorNote, txtPatientName;

    private DatabaseController dbController;

    private PrintedPdfDocument pdfDocument;
    private PrintAttributes printAttributes;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActionBar bar1 = getSupportActionBar();

        bar1.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.white)));
        bar1.setTitle(Html.fromHtml("<font color='#7d2709'>Doctor Recommendation</font>"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setContentView(R.layout.activity_doctor_recommendation);

        txtDoctorNote = (TextView) findViewById(R.id.txtDoctorNote);
        txtPatientName = (TextView) findViewById(R.id.txtPatientName);

        dbController = new DatabaseController(this);

        txtPatientName.setText("Dear " + dbController.getUsername() + ": ");

        String doctorText = "";

        try {
            InputStream file = getAssets().open("doctor_note.txt");
            int size = file.available();
            byte[] buffer = new byte[size];
            file.read(buffer);
            file.close();
            doctorText = new String(buffer);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        txtDoctorNote.setText(doctorText);

    }

}