package com.squorpikkor.magazassistant5;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import com.squorpikkor.magazassistant5.ui.main.SectionsPagerAdapter;
import com.squorpikkor.magazassistant5.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = binding.viewPager;
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = binding.tabs;
        tabs.setupWithViewPager(viewPager);

        //CustomView
        //noinspection ConstantConditions
        tabs.getTabAt(0).setCustomView(R.layout.tab_view_0);
        //noinspection ConstantConditions
        tabs.getTabAt(1).setCustomView(R.layout.tab_view_1);


    }
}