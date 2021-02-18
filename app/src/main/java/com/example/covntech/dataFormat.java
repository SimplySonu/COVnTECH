package com.example.covntech;
import androidx.annotation.NonNull;

public class dataFormat {

    private String mCountry;

    private long mConfirmed;

    private long mRecovered;

    private long mDeath;

    private long mTotalConfirmed;

    private long mTotalRecovered;

    private long mTotalDeath;

    dataFormat()
    {

    }

    dataFormat(String country, long confirmed, long recovered, long death)
    {
        mCountry=country;
        mConfirmed=confirmed;
        mRecovered=recovered;
        mDeath=death;
    }
    dataFormat(long totConfirm,long totRecover,long totDeath)
    {
        mTotalConfirmed = totConfirm;
        mTotalRecovered = totRecover;
        mTotalDeath = totDeath;
    }

    public String getmCountry()
    {
        return mCountry;
    }

    public long getmConfirmed()
    {
        return mConfirmed;
    }

    public long getmRecovered()
    {
        return mRecovered;
    }

    public long getmDeath()
    {
        return mDeath;
    }

    public long getmTotalConfirmed(){return mTotalConfirmed;}

    public long getmTotalRecovered(){return  mTotalRecovered;}

    public long getmTotalDeath(){return mDeath;}
}

