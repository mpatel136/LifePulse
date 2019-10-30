package com.saadatdevelopment.lifepulse.searchdialognew.core;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lifepulselibrary.Diary;
import com.saadatdevelopment.lifepulse.searchdialognew.R;

import java.util.List;

public class DiaryAdapter extends RecyclerView.Adapter<DiaryViewHolder> {

    // list of diaries
    private List<Diary> diaries;

    // context of the adapter
    private Context context;

    /**
     * Constructor that sets the diaries list and the context
     * @param diaries lis of diaries
     * @param ctx main activity context
     */
    public DiaryAdapter(List<Diary> diaries, Context ctx) {
        this.diaries = diaries;
        this.context = ctx;
    }


    @Override
    public DiaryViewHolder onCreateViewHolder( ViewGroup parent, int i) {

        // Inflate the layout_item
        View view = LayoutInflater.from(context).inflate(R.layout.layout_food_log_structure, parent, false);

        final DiaryViewHolder diaryViewHolder = new DiaryViewHolder(view, this, diaries, context);

        return diaryViewHolder;

    }

    @Override
    public void onBindViewHolder( DiaryViewHolder diaryViewHolder, int i) {

        Diary diary = diaries.get(i);

        diaryViewHolder.txtName.setText("Name: " + diary.getName());
        diaryViewHolder.txtDate.setText("Date: " + diary.getDate());
        diaryViewHolder.txtGuiltLevel.setText("Guilt Level: " + diary.getGuiltLevel());
        diaryViewHolder.txtDescription.setText(diary.getDescription());
        diaryViewHolder.imgFoodDiary.setImageBitmap(toBitmap(diary.getImage()));

    }

    /**
     * Return the size of the list
     * @return
     */
    @Override
    public int getItemCount() {
        return diaries.size();
    }


    /**
     *  Converts a byte array into an image
     * @param image Image represented in a byte array
     * @return  Returns image as a Bitmap
     */
    private static Bitmap toBitmap(byte[] image) {
        return BitmapFactory.decodeByteArray(image, 0, image.length);
    }
}
