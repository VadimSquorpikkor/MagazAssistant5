package com.squorpikkor.magazassistant5;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import com.squorpikkor.magazassistant5.ui.main.MainViewModel;
import com.squorpikkor.magazassistant5.ui.main.SectionsPagerAdapter;
import com.squorpikkor.magazassistant5.databinding.ActivityMainBinding;
import com.squorpikkor.magazassistant5.ui.main.dialog.EmployeeDialog;
import com.squorpikkor.magazassistant5.ui.main.entities.Employee;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        MainViewModel mViewModel = new ViewModelProvider(this).get(MainViewModel.class);

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

        //диалоги
        mViewModel.getOpenEmployeeDialog().observe(this, new Observer<Employee>() {
            @Override
            public void onChanged(Employee employee) {
                if (employee!=null)
                new EmployeeDialog(employee).show(getSupportFragmentManager(), null);
            }
        });


    }
}