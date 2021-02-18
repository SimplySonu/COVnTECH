package com.example.covntech;


import android.content.Context;

import androidx.loader.content.AsyncTaskLoader;

import java.util.List;
public class IndiaLoader extends AsyncTaskLoader<List<dataFormat>> {

    private static final String LOG_TAG = worldLoader.class.getName();
    private String mUrl;

    public IndiaLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }
    protected void onStartLoading() {
        forceLoad();
    }
    @Override
    public List<dataFormat> loadInBackground() {
        if (mUrl == null) {
            return null;}
        List<dataFormat> parsedWorld =worldQuery.fetchIndiaData(mUrl);
        return parsedWorld;
    }
}
