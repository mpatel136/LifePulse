package com.saadatdevelopment.lifepulse.searchdialognew.foods;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lifepulselibrary.Food;
import com.example.lifepulselibrary.FoodModel;
import com.saadatdevelopment.lifepulse.searchdialognew.apiController.APIController;
import com.saadatdevelopment.lifepulse.searchdialognew.apiController.FoodAPIJsonParser;
import com.saadatdevelopment.lifepulse.searchdialognew.databasecontrollers.DatabaseController;
import com.saadatdevelopment.lifepulse.searchdialognew.goalsettings.SearchModel;
import com.saadatdevelopment.lifepulse.searchdialognew.R;
import com.saadatdevelopment.lifepulse.searchdialognew.core.ApiFoodAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import ir.mirrajabi.searchdialog.SimpleSearchDialogCompat;
import ir.mirrajabi.searchdialog.core.BaseSearchDialogCompat;
import ir.mirrajabi.searchdialog.core.SearchResultListener;
import ir.mirrajabi.searchdialog.core.Searchable;

/**
 * Class to show users what food they should not eat
 */
public class FoodRestrictions extends AppCompatActivity implements View.OnClickListener
{
    //Declare variables
    private RecyclerView recyclerView;

    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<FoodModel> foodModelList;
    private ImageButton filtersFood;
    Switch changeLayoutsDynamically;
    TextView changeViewsDynamically;

    Dialog dialog;
    int index;
    SharedPreferences radioButtonIndicator;
    private RadioGroup rg;
    private static final String RADIO_BUTTON_CHECKED = "RadioButtonCheckedRestrictedFood";
    private static final String MY_PREFERENCES = "MyPreferences";
    private final String TAG = "APIRestrictedFood";

    /**
     * Locates the views, sets the adapter and layout manager of the recycler view, and sorts the items in the list
     * @param savedInstanceState Collection of key value pairs
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActionBar bar1 = getSupportActionBar();

        bar1.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.white)));
        bar1.setTitle(Html.fromHtml("<font color='#7d2709'>Foods Restrictions</font>"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setContentView(R.layout.activity_food_restrictions);

        //Find the views
        changeLayoutsDynamically= (Switch) findViewById(R.id.switchLayoutsDynamically);
        //Set OnClick listener for the switch
        changeLayoutsDynamically.setOnClickListener(this);

        changeViewsDynamically = (TextView) findViewById(R.id.changeViewsDynamically);
        //Set the text of the switch
        changeViewsDynamically.setText(R.string.listView);

        filtersFood = (ImageButton) (findViewById(R.id.filtersFood));

        this.recyclerView = (RecyclerView) findViewById(R.id.restrictedFoodRecyclerView);
        recyclerView.setHasFixedSize(true);
        this.layoutManager = new LinearLayoutManager(this);

        foodModelList = new ArrayList<>();

        this.adapter = new ApiFoodAdapter(foodModelList, this);

        this.recyclerView.setAdapter(adapter);
        this.recyclerView.setLayoutManager(layoutManager);

        // starts the intent and sends the key to the food page.
//        final Intent intentToFood = new Intent(this, .class);

        //Set on click listener of the search bar
        findViewById(R.id.btnSearchFoodRestrictions).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new SimpleSearchDialogCompat(FoodRestrictions.this, "Search...", "Search for food...", null,
                        initData(), new SearchResultListener<Searchable>() {
                    @Override
                    public void onSelected(BaseSearchDialogCompat baseSearchDialogCompat, Searchable searchable, int i) {
                        Toast.makeText(FoodRestrictions.this, ""+searchable.getTitle(), Toast.LENGTH_SHORT).show();
                        recyclerView.smoothScrollToPosition(i);
                        baseSearchDialogCompat.dismiss();
                    }
                }).show();
            }
        });

        runAsyncTask();
        sortArrayList();

//        ItemTouchHelper touchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT) {
//            @Override
//            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder viewHolder1) {
//                return false;
//            }
//
//            @Override
//            public void onSwiped(RecyclerView.ViewHolder target, int i) {
//                int position = target.getAdapterPosition();
//                Log.i("SizeOfFoodsBefore","" + foods.size());
//                while(foods.size() > 0)
//                {
//                    foods.remove(position);
//                }
//                adapter.notifyItemRemoved(position);
//                adapter.notifyItemRangeChanged(position, foods.size());
//                Log.i("SizeOfFoodsAfter","" + foods.size());
//            }
//        });
//        touchHelper.attachToRecyclerView(recyclerView);
    }

    /**
     * Sorts food items alphabetically
     */
    private void sortArrayList()
    {
        this.layoutManager = new LinearLayoutManager(this);

        List<FoodModel> foodNames = foodModelList;

        //Call the collection sort method on the food
        Collections.sort(foodNames, new Comparator<FoodModel>() {
            @Override
            public int compare(FoodModel f1, FoodModel f2) {
                if(f1.getName().compareTo(f2.getName()) > 0)
                {
                    return +1;
                }
                else if (f1.getName().compareTo(f2.getName()) < 0)
                {
                    return -1;
                }
                else
                {
                    return 0;
                }
            }
        });

        //Creates the adapter
        this.adapter = new ApiFoodAdapter(foodNames, this);

        //Sets the adapter
        this.recyclerView.setAdapter(adapter);
        //Sets the layout manager
        this.recyclerView.setLayoutManager(layoutManager);

        //Notify the adapter of change in data
        adapter.notifyDataSetChanged();
    }

    /**
     * Sorts food alphabetically in reverse
     */
    private void reverseSortArrayList()
    {
        this.layoutManager = new LinearLayoutManager(this);

        List<FoodModel> foodNames = foodModelList;

        //Call the collection sort method on the food
        Collections.sort(foodNames, new Comparator<FoodModel>() {
            @Override
            public int compare(FoodModel f1, FoodModel f2) {
                if(f1.getName().compareTo(f2.getName()) > 0)
                {
                    return -1;
                }
                else if (f1.getName().compareTo(f2.getName()) < 0)
                {
                    return +1;
                }
                else
                {
                    return 0;
                }
            }
        });
//
//        //Creates the adapter
        this.adapter = new ApiFoodAdapter(foodNames, this);
//
//        //Sets the adapter
        this.recyclerView.setAdapter(adapter);
//        //Sets the layout manager
        this.recyclerView.setLayoutManager(layoutManager);
//
//        //Notify the adapter of change in data
        adapter.notifyDataSetChanged();
    }

    /**
     * Initialize the data to be put in the search bar
     * @return the list of food names
     */
    private ArrayList<SearchModel> initData() {
        // items in the arraylist
        ArrayList<SearchModel> foodList = new ArrayList<>();
        List<FoodModel> foods = foodModelList;
        for(int i=0; i< foods.size(); i++)
        {
            foodList.add(new SearchModel(foods.get(i).getName()));
        }
        return foodList;
    }

    /**
     * Handles events for onclick
     * @param v The view of the item that will be affected
     */
    @Override
    public void onClick(View v) {
        //If the view is the switch layout button
        if(v.getId() == R.id.switchLayoutsDynamically)
        {
            //If the button is checked/toggled
            if(changeLayoutsDynamically.isChecked())
            {
                //Set the text to grid
                changeViewsDynamically.setText(R.string.gridView);
                //Set the layout manager to staggered grid view
                this.layoutManager = new StaggeredGridLayoutManager(2, LinearLayoutManager.HORIZONTAL);
                //this.layoutManager = new GridLayoutManager(this, 2);
                //Set the layoutmanager of the recycler view to the layout manager object
                this.recyclerView.setLayoutManager(layoutManager);
            }
            else
            {
                //Set the text to list
                changeViewsDynamically.setText(R.string.listView);
                //Set the layout manager to staggered grid view
                this.layoutManager = new LinearLayoutManager(this);
                //Set the layoutmanager of the recycler view to the layout manager object
                this.recyclerView.setLayoutManager(layoutManager);
            }
        }
    }

    /**
     * On press on the filter icon, open a popup to sort the food list
     * @param v The view of the image
     */
    public void imageOnClick(View v)
    {
//        //Instantiates the fragment manager
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        //Instantiate the nearby locations fragment
//        FilterFood foodFilterPopUp = new FilterFood();
//        foodFilterPopUp.setCancelable(false);
//        //Show the fragment
//        foodFilterPopUp.show(fragmentManager, "Filter Food");
//        //Toast.makeText(this, "Pressed Filters", Toast.LENGTH_LONG).show();

        radioButtonIndicator = getSharedPreferences(MY_PREFERENCES, MODE_PRIVATE);

        int savedRadioIndex = radioButtonIndicator.getInt(RADIO_BUTTON_CHECKED, 0);
        Log.i("radioButtons", "Saved index = " +savedRadioIndex);

        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.fragment_filter_food);


        Button okButton = (Button)dialog.findViewById(R.id.btnOk);

        rg = (RadioGroup)dialog.findViewById(R.id.radioGroup);

        RadioButton radioButtonChecked = (RadioButton) rg.getChildAt(savedRadioIndex);
        radioButtonChecked.setChecked(true);

        //Dismiss the dialog on ok button press
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v.getId() == R.id.btnOk)
                {
                    index = rg.indexOfChild(dialog.findViewById(rg.getCheckedRadioButtonId()));
                    Log.i("radioButtons","Current index is: " + index);
                    SharedPreferences.Editor editor = radioButtonIndicator.edit();
                    editor.putInt(RADIO_BUTTON_CHECKED, index);
                    editor.apply();
                    dialog.dismiss();
                    if(index == 0)
                    {
                        sortArrayList();
                    }
                    else
                    {
                        reverseSortArrayList();
                    }
                }
            }
        });

        //Show the dialog
        dialog.show();
    }

    /**
     * Get all foods in the database
     * @return The list of food items in the database
     */
    private List<Food> getFoods()
    {
        List<Food> foods;
        DatabaseController dbController = new DatabaseController(this);
        foods = dbController.getAllFoods();
        return foods;
    }

//    //Go to the specified food's page
//    public void selectFood(View view) {
//
//        // creates an intent to send image id to exercise list activity
////        Intent sendIntent = new Intent(this, .class);
//
//        String food = "Food";
//
//        // Sends image id to exercise list activity based on their id
//        if(view.getId() == R.id.restrictedFoodItem1) {
//            Toast.makeText(this, "Food1 is selected", Toast.LENGTH_SHORT).show();
////            sendIntent.putExtra(food, "Food Item 1");
//        }
//        else if(view.getId() == R.id.restrictedFoodItem2) {
//            Toast.makeText(this, "Food2 is selected", Toast.LENGTH_SHORT).show();
////            sendIntent.putExtra(food, "Food Item 2");
//        }
//        else if(view.getId() == R.id.restrictedFoodItem3) {
//            Toast.makeText(this, "Food3 is selected", Toast.LENGTH_SHORT).show();
////            sendIntent.putExtra(food, "Food Item 3");
//        }
//        //       startActivity(sendIntent);
//    }

    private void runAsyncTask()
    {
        String url = "https://www.saadatdevelopment.com/apis/lifepulse/restricted_foods.json";
        APIAsyncTask newTask = new APIAsyncTask();
        newTask.execute(url);
    }

    private class APIAsyncTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            String response = "";
            APIController apiController = new APIController();
            response = apiController.makeApiCall(strings[0]);
            Log.i(TAG, response);
            return response;
        }

        @Override
        protected void onPostExecute(String result)
        {
            super.onPostExecute(result);
            parseJSONResponse(result);
            populateRecyclerView();
        }
    }

    private void parseJSONResponse(String result)
    {
        FoodAPIJsonParser jsonParser = new FoodAPIJsonParser();
        List<FoodModel> foods = jsonParser.parse(result);
        foodModelList = foods;
    }

    private void populateRecyclerView(){
        //Recycler View here
        this.adapter = new ApiFoodAdapter(foodModelList, this);

        this.recyclerView.setAdapter(adapter);
    }
}
