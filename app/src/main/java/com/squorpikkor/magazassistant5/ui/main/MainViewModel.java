package com.squorpikkor.magazassistant5.ui.main;

import androidx.lifecycle.ViewModel;

import com.squorpikkor.magazassistant5.ui.main.data.DataHelper;
import com.squorpikkor.magazassistant5.ui.main.entities.Location;

import java.util.ArrayList;

public class MainViewModel extends ViewModel {

    private final DataHelper data;

    private ArrayList<Location> locations;

    public MainViewModel() {
        data = new DataHelper();
        locations = data.getAllLocations();
    }



}
