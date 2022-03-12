package com.squorpikkor.magazassistant5.ui.main.data;

import androidx.lifecycle.MutableLiveData;

import com.squorpikkor.magazassistant5.ui.main.entities.Employee;
import com.squorpikkor.magazassistant5.ui.main.entities.Location;
import com.squorpikkor.magazassistant5.ui.main.entities.Order;

import java.util.ArrayList;

interface Data {

    public void saveEmployee(Employee employee);

    public void saveLocation(Location location);

    public void saveOrder(Order order);

    public void getAllLocations(MutableLiveData<ArrayList<Location>> locations);

    public void getAllEmployees(MutableLiveData<ArrayList<Employee>> employees);

    public void getAllOrders(MutableLiveData<ArrayList<Order>> orders);

    public ArrayList<Employee> getEmployeesByLocation(Location location);

    public ArrayList<Order> getOrderByEmployee(Employee employee);

    public void loadPrices(MutableLiveData<Integer> juicePrice, MutableLiveData<Integer> juiceSmallPrice, MutableLiveData<Integer> kefirPrice, MutableLiveData<Integer> kefirSmallPrice);

}
