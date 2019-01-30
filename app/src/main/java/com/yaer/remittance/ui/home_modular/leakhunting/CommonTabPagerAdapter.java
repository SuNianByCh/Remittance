package com.yaer.remittance.ui.home_modular.leakhunting;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

public class CommonTabPagerAdapter extends FragmentPagerAdapter {

    private List<String> list;
    private Context context;
    private List<LeakFragment> mLeakFragment;





    public CommonTabPagerAdapter(FragmentManager fm,  List<String> list,List<LeakFragment> leakFragmentList,Context context) {
        super(fm);
        if (list==null||list.isEmpty() || leakFragmentList ==null || leakFragmentList.isEmpty()){
            throw new ExceptionInInitializerError("list can't be null or empty");
        }
        this.mLeakFragment = leakFragmentList;

        this.list = list;
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
       return mLeakFragment.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return list.get(position);
    }



}
