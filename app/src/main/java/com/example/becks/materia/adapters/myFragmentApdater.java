package com.example.becks.materia.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;

import java.util.ArrayList;

/**
 * Created by becks on 4/23/16.
 */
public class myFragmentApdater extends FragmentPagerAdapter {
    ArrayList<Fragment> pages=new ArrayList<>();
    public myFragmentApdater(FragmentManager fm) {

        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        return pages.get(position);
    }

    @Override
    public int getCount() {
        return pages.size();
    }
    // to add th fragment
    public void addFragment(Fragment f){
        pages.add(f);


    }
    // method to set the title or get the title

    @Override
    public CharSequence getPageTitle(int position) {
        return pages.get(position).toString();
    }
}