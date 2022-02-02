package com.squorpikkor.magazassistant5.ui.main;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.squorpikkor.magazassistant5.ui.main.data.DataHelper;
import com.squorpikkor.magazassistant5.ui.main.entities.Employee;
import com.squorpikkor.magazassistant5.ui.main.entities.Location;
import com.squorpikkor.magazassistant5.ui.main.entities.Order;

import java.util.ArrayList;

public class MainViewModel extends ViewModel {

    private final DataHelper data;

    private final MutableLiveData<ArrayList<Location>> locations;
    private final MutableLiveData<ArrayList<Employee>> employees;
    private final MutableLiveData<ArrayList<Order>>    orders;

    public MainViewModel() {
        locations = new MutableLiveData<>();
        employees = new MutableLiveData<>();
        orders = new MutableLiveData<>();
        data = new DataHelper(locations, employees, orders);
        getAllLocations();
    }

    public MutableLiveData<ArrayList<Location>> getLocations() {
        return locations;
    }
    public MutableLiveData<ArrayList<Employee>> getEmployees() {
        return employees;
    }
    public MutableLiveData<ArrayList<Order>>    getOrders() {
        return orders;
    }

    private void getAllLocations() {
        data.getAllLocations();
    }



}
