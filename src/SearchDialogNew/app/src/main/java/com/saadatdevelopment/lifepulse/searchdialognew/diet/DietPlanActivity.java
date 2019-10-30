package com.saadatdevelopment.lifepulse.searchdialognew.diet;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.TypedValue;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.saadatdevelopment.lifepulse.searchdialognew.R;

public class DietPlanActivity extends AppCompatActivity {

    /**
     * https://stackoverflow.com/questions/16623337/how-to-scroll-table-layout-in-horizontal-and-vertical-in-android Used to make layout scroll left
     * https://stackoverflow.com/questions/3496269/how-do-i-put-a-border-around-an-android-textview     Used to put borders in the table layout
     * https://stackoverflow.com/questions/12523005/how-set-background-drawable-programmatically-in-android     Used to set drawable background for table cells
     * https://tekeye.uk/android/examples/ui/android-portrait-landscape-screens     Used to set diet plan as landscape only because table is too big for portrait mode
     * https://stackoverflow.com/questions/2069810/how-to-assign-text-size-in-sp-value-using-java-code  Used to set text font in sp
     * https://stackoverflow.com/questions/11342975/android-create-textviews-in-tablerows-programmatically  Used to make table using java
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActionBar bar1 = getSupportActionBar();
        bar1.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.white)));
        bar1.setTitle(Html.fromHtml("<font color='#7d2709'>Diet Plan</font>"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setContentView(R.layout.activity_diet_plan);


        // not done with this activity, I need to add TextViews dynamically to save time


        TableLayout table = findViewById(R.id.tableLayout);

        TableRow.LayoutParams rowLayout = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT);

        String [] rowTitles = {"Breakfast", "Lunch", "Diner", "Supper", "Snacks"};
        int numOfRows = 5;

        for(int index = 0; index < numOfRows;index++) {

            TableRow tableRow = new TableRow(this);
            tableRow.setLayoutParams(rowLayout);

                TextView cell1 = new TextView(this);
                TextView cell2 = new TextView(this);
                TextView cell3 = new TextView(this);
                TextView cell4 = new TextView(this);
                TextView cell5 = new TextView(this);
                TextView cell6 = new TextView(this);
                TextView cell7 = new TextView(this);
                TextView cell8 = new TextView(this);

                if (index % 2 == 0) {

                    //Get values from databases and use them in setText
                    cell1.setText(rowTitles[index]);

                    //Sets attributes for blue table cells
                    cell1.setLayoutParams(rowLayout);
                    cell1.setBackgroundResource(R.drawable.table_cells_white_rows);
                    cell1.setPadding(10, 10, 0, 10);
                    cell1.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
                    cell1.setTextColor(Color.BLACK);

                    //Get values from databases and use them in setText
                    cell2.setText(R.string.lorem_table);

                    //Sets attributes for blue table cells
                    cell2.setLayoutParams(rowLayout);
                    cell2.setBackgroundResource(R.drawable.table_cells_white_rows);
                    cell2.setPadding(10, 10, 0, 10);
                    cell2.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);
                    cell2.setTextColor(Color.BLACK);

                    //Get values from databases and use them in setText
                    cell3.setText(R.string.lorem_table);

                    //Sets attributes for blue table cells
                    cell3.setLayoutParams(rowLayout);
                    cell3.setBackgroundResource(R.drawable.table_cells_white_rows);
                    cell3.setPadding(10, 10, 0, 10);
                    cell3.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);
                    cell3.setTextColor(Color.BLACK);

                    //Get values from databases and use them in setText
                    cell4.setText(R.string.lorem_table);

                    //Sets attributes for blue table cells
                    cell4.setLayoutParams(rowLayout);
                    cell4.setBackgroundResource(R.drawable.table_cells_white_rows);
                    cell4.setPadding(10, 10, 0, 10);
                    cell4.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);
                    cell4.setTextColor(Color.BLACK);

                    //Get values from databases and use them in setText
                    cell5.setText(R.string.lorem_table);

                    //Sets attributes for blue table cells
                    cell5.setLayoutParams(rowLayout);
                    cell5.setBackgroundResource(R.drawable.table_cells_white_rows);
                    cell5.setPadding(10, 10, 0, 10);
                    cell5.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);
                    cell5.setTextColor(Color.BLACK);

                    //Get values from databases and use them in setText
                    cell6.setText(R.string.lorem_table);

                    //Sets attributes for blue table cells
                    cell6.setLayoutParams(rowLayout);
                    cell6.setBackgroundResource(R.drawable.table_cells_white_rows);
                    cell6.setPadding(10, 10, 0, 10);
                    cell6.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);
                    cell6.setTextColor(Color.BLACK);

                    //Get values from databases and use them in setText
                    cell7.setText(R.string.lorem_table);

                    //Sets attributes for blue table cells
                    cell7.setLayoutParams(rowLayout);
                    cell7.setBackgroundResource(R.drawable.table_cells_white_rows);
                    cell7.setPadding(10, 10, 0, 10);
                    cell7.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);
                    cell7.setTextColor(Color.BLACK);

                    //Get values from databases and use them in setText
                    cell8.setText(R.string.lorem_table);

                    //Sets attributes for blue table cells
                    cell8.setLayoutParams(rowLayout);
                    cell8.setBackgroundResource(R.drawable.table_cells_white_rows);
                    cell8.setPadding(10, 10, 0, 10);
                    cell8.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);
                    cell8.setTextColor(Color.BLACK);

                    //Add textview to the row
                    tableRow.addView(cell1);
                    tableRow.addView(cell2);
                    tableRow.addView(cell3);
                    tableRow.addView(cell4);
                    tableRow.addView(cell5);
                    tableRow.addView(cell6);
                    tableRow.addView(cell7);
                    tableRow.addView(cell8);
                }

                else {

                    //Get values from databases and use them in setText
                    cell1.setText(rowTitles[index]);

                    //Sets attributes for blue table cells
                    cell1.setLayoutParams(rowLayout);
                    cell1.setBackgroundResource(R.drawable.table_cells_blue_rows);
                    cell1.setPadding(10, 10, 0, 10);
                    cell1.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
                    cell1.setTextColor(Color.BLACK);

                    //Get values from databases and use them in setText
                    cell2.setText(R.string.lorem_table);

                    //Sets attributes for blue table cells
                    cell2.setLayoutParams(rowLayout);
                    cell2.setBackgroundResource(R.drawable.table_cells_blue_rows);
                    cell2.setPadding(10, 10, 0, 10);
                    cell2.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);
                    cell2.setTextColor(Color.BLACK);

                    //Get values from databases and use them in setText
                    cell3.setText(R.string.lorem_table);

                    //Sets attributes for blue table cells
                    cell3.setLayoutParams(rowLayout);
                    cell3.setBackgroundResource(R.drawable.table_cells_blue_rows);
                    cell3.setPadding(10, 10, 0, 10);
                    cell3.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);
                    cell3.setTextColor(Color.BLACK);

                    //Get values from databases and use them in setText
                    cell4.setText(R.string.lorem_table);

                    //Sets attributes for blue table cells
                    cell4.setLayoutParams(rowLayout);
                    cell4.setBackgroundResource(R.drawable.table_cells_blue_rows);
                    cell4.setPadding(10, 10, 0, 10);
                    cell4.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);
                    cell4.setTextColor(Color.BLACK);

                    //Get values from databases and use them in setText
                    cell5.setText(R.string.lorem_table);

                    //Sets attributes for blue table cells
                    cell5.setLayoutParams(rowLayout);
                    cell5.setBackgroundResource(R.drawable.table_cells_blue_rows);
                    cell5.setPadding(10, 10, 0, 10);
                    cell5.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);
                    cell5.setTextColor(Color.BLACK);

                    //Get values from databases and use them in setText
                    cell6.setText(R.string.lorem_table);

                    //Sets attributes for blue table cells
                    cell6.setLayoutParams(rowLayout);
                    cell6.setBackgroundResource(R.drawable.table_cells_blue_rows);
                    cell6.setPadding(10, 10, 0, 10);
                    cell6.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);
                    cell6.setTextColor(Color.BLACK);

                    //Get values from databases and use them in setText
                    cell7.setText(R.string.lorem_table);

                    //Sets attributes for blue table cells
                    cell7.setLayoutParams(rowLayout);
                    cell7.setBackgroundResource(R.drawable.table_cells_blue_rows);
                    cell7.setPadding(10, 10, 0, 10);
                    cell7.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);
                    cell7.setTextColor(Color.BLACK);

                    //Get values from databases and use them in setText
                    cell8.setText(R.string.lorem_table);

                    //Sets attributes for blue table cells
                    cell8.setLayoutParams(rowLayout);
                    cell8.setBackgroundResource(R.drawable.table_cells_blue_rows);
                    cell8.setPadding(10, 10, 0, 10);
                    cell8.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);
                    cell8.setTextColor(Color.BLACK);

                    //Add textview to the row
                    tableRow.addView(cell1);
                    tableRow.addView(cell2);
                    tableRow.addView(cell3);
                    tableRow.addView(cell4);
                    tableRow.addView(cell5);
                    tableRow.addView(cell6);
                    tableRow.addView(cell7);
                    tableRow.addView(cell8);

                }

                //Add row to table layout
                table.addView(tableRow);
            }

    }
}
