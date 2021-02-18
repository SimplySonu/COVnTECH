package com.example.covntech;


import android.content.Context;

import androidx.annotation.NonNull;
import androidx.loader.content.AsyncTaskLoader;

import java.util.List;
public class worldLoader extends AsyncTaskLoader<List<dataFormat>> {

    private static final String LOG_TAG = worldLoader.class.getName();
    private String mUrl;

    public worldLoader(Context context, String url) {
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
        List<dataFormat> parsedWorld =worldQuery.fetchWorldData(mUrl);
        return parsedWorld;
    }
}

