package com.squorpikkor.magazassistant5.ui.main;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.squorpikkor.magazassistant5.R;
import com.squorpikkor.magazassistant5.ui.main.fragment.CradleFragment;
import com.squorpikkor.magazassistant5.ui.main.fragment.CradleFragment2;
import com.squorpikkor.magazassistant5.ui.main.fragment.OrderFragment;
import com.squorpikkor.magazassistant5.ui.main.fragment.PriceFragment;

import java.util.HashMap;
import java.util.Map;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.invoice, R.string.orders};
    private final Context mContext;
    private final Map<Integer, Fragment> fragmentMap;


    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mContext = context;
        fragmentMap = new HashMap<Integer, Fragment>() {
            {
//                put(0, PriceFragment.newInstance());
                put(0, CradleFragment.newInstance());
                put(1, CradleFragment2.newInstance());
//                put(1, OrderFragment.newInstance());
            }
        };
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentMap.get(position);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        return fragmentMap.size();
    }
}