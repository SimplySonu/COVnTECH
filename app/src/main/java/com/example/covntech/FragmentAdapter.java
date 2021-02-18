package com.example.covntech;

import android.content.Context;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class FragmentAdapter extends FragmentPagerAdapter {

    private Context mContext;

    FragmentAdapter(Context context, FragmentManager fm)
    {
        super(fm);
        mContext = context;
    }
    public Fragment getItem(int position) {
        if (position == 0){
            return new HomeFragment();}
        else if(position == 1){
            return new InfoFragment();}
        else
            return new HistoryFragment();
    }

    @Override
    public int getCount() {
        return 3;
    }

    public CharSequence getPageTitle(int position){

        if (position==0){
            return "Home";}
        else if(position ==1){
            return "Info";}
        else
            return "History";}
    }

