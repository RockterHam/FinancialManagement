package com.example.financialmanagement.activity;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class BillFragmentPagerAdapter extends FragmentPagerAdapter {

    public BillFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @NonNull


    @Override
    public Fragment getItem(int position) {
        BillFragment billFragment = new BillFragment();
        return billFragment.newInstance(position + 1);
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "支出";
            case 1:
                return "收入";
        }
        return null;
    }
}
