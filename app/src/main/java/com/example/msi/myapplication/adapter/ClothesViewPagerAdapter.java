package com.example.msi.myapplication.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.msi.myapplication.view.fragment.ClothesFragment;

/**
 * Created by msi on 8/16/2017.
 */
public class ClothesViewPagerAdapter extends FragmentPagerAdapter {
    public ClothesViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return ClothesFragment.newInstance();
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch(position){
            case 0:
                return "پربازدیدترین ها";
            case 1:
                return "جدیدترین ها";
            case 2:
                return "مشاهده شده ها";
            default:
                return "";
        }

    }
}
