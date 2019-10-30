package com.saadatdevelopment.lifepulse.searchdialognew.bmihealth.fragscustomerecipe.dynamicview;

import android.content.Context;
import android.graphics.Color;
import android.text.InputType;
import android.view.ViewGroup;
import android.widget.EditText;

/**
 *
 */
public class DynamicViews {
    Context ctx;
    int id = 0;

    /**
     *
     * @param ctx
     */
    public DynamicViews(Context ctx){
        this.ctx = ctx;
    }

    public EditText ingredientEditText(Context ctx){
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        EditText editText = new EditText(ctx);
        editText.setId(id);
        id++;
        editText.setMinEms(2);
        editText.setTextColor(Color.rgb(0,0,0));

        editText.setInputType(InputType.TYPE_CLASS_TEXT);

        return editText;

    }


    //Returns the amount of editText generated.
    public int getSize(){
        return id;
    }
}
