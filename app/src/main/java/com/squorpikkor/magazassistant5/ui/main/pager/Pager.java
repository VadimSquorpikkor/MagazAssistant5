package com.squorpikkor.magazassistant5.ui.main.pager;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class Pager extends FragmentStatePagerAdapter {

    private final int size;

    public Pager(@NonNull FragmentManager fm, int behavior, int size) {
        super(fm, behavior);
        this.size = size;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return SelectedEmployeeFragment.newInstance(position);
    }

    @Override
    public int getCount() {
        return size;
    }
}
