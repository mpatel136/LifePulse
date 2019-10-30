package com.saadatdevelopment.lifepulse.searchdialognew.goalsettings;

import ir.mirrajabi.searchdialog.core.Searchable;

public class SearchModel implements Searchable {

    private String mTitle;

    public SearchModel(String mTitle) {
        this.mTitle = mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }
    @Override
    public String getTitle() {
        return mTitle;
    }
}
