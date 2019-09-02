package com.mall.demo.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * @author LowAndroider
 * @date 2019/9/2
 */
public class CustomFragmentPageAdapter extends FragmentPagerAdapter {

    private ArrayList<Fragment> pageList;

    public CustomFragmentPageAdapter(ArrayList<Fragment> pageList, FragmentManager fm) {
        super(fm);
        this.pageList = pageList;
    }

    @Override
    public Fragment getItem(int i) {
        return pageList.get(i);
    }

    @Override
    public int getCount() {
        return pageList.size();
    }
}
