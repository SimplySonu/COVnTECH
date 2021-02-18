package com.example.covntech;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import java.util.ArrayList;
import java.util.List;

public class World_Activity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<dataFormat>> {

    public static final String LOG_TAG = MainActivity.class.getName();
    private static final int LOADER_ID = 1;
    private static final String USGS_REQUEST_URL =
            "https://api.covid19api.com/summary";
    private static SearchView searchView = null;
    private dataAdapter mAdapter;
    private TextView mEmptyStateTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ListView listView = (ListView) findViewById(R.id.main_list);

        mEmptyStateTextView = (TextView) findViewById(R.id.empty_view);
        listView.setEmptyView(mEmptyStateTextView);
        ArrayList<dataFormat> list = new ArrayList<>();

        mAdapter = new dataAdapter(this, new ArrayList<dataFormat>());
        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        listView.setAdapter(mAdapter);

        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);

        // Get details on the currently active default data network
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        // If there is a network connection, fetch data
        if (networkInfo != null && networkInfo.isConnected()) {
            LoaderManager.getInstance(this).initLoader(LOADER_ID, null, this);
        } else {
            // Otherwise, display error
            // First, hide loading indicator so error message will be visible
            View loadingIndicator = findViewById(R.id.loading_spinner);
            loadingIndicator.setVisibility(View.GONE);
            mEmptyStateTextView.setText("No Internet Connection");

        }
    }

    @Override
    public Loader<List<dataFormat>> onCreateLoader(int id, Bundle args) {
        return new worldLoader(this, USGS_REQUEST_URL);
    }

    @Override
    public void onLoadFinished(Loader<List<dataFormat>> loader, List<dataFormat> worldInfo) {
        mAdapter.clear();

        ProgressBar hide = (ProgressBar) findViewById(R.id.loading_spinner);
        hide.incrementProgressBy(5);
        hide.setVisibility(View.GONE);
        // data set. This will trigger the ListView to update.
        if (worldInfo != null && !worldInfo.isEmpty()) {
            mAdapter.addAll(worldInfo);
        } else {
            mEmptyStateTextView.setText("No Information found\n Please Reload Again");
        }
    }

    @Override
    public void onLoaderReset(Loader<List<dataFormat>> loader) {
        // Loader reset, so we can clear out our existing data.
        mAdapter.clear();
    }
}
