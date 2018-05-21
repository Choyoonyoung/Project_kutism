package com.example.yy.loading_1;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class CustomAdapter extends FragmentStatePagerAdapter {

    public CustomAdapter(FragmentManager fm){
        super(fm);
    }



    @Override
    public Fragment getItem(int position) {
        switch(position){
            case 1:
                return new TwoFragment();

            default :
                return new OneFragment();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }


}

