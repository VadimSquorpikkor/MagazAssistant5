package com.squorpikkor.magazassistant5.ui.main.data;

import androidx.lifecycle.MutableLiveData;

import com.squorpikkor.magazassistant5.ui.main.entities.Employee;
import com.squorpikkor.magazassistant5.ui.main.entities.Location;
import com.squorpikkor.magazassistant5.ui.main.entities.Order;

import java.util.ArrayList;

interface Data {

    public void updateEmployee(Employee employee);

    public void addEmployee(Employee employee);

    public void updateLocation(Location location);

    public void addLocation(Location location);

    public void updateOrder(Order order);

    public void addOrder(Order order);

    public void getAllLocations(MutableLiveData<ArrayList<Location>> locations);

    public void getAllEmployees(MutableLiveData<ArrayList<Employee>> employees);

    public void getAllOrders(MutableLiveData<ArrayList<Order>> orders);

    public ArrayList<Employee> getEmployeesByLocation(Location location);

    public ArrayList<Order> getOrderByEmployee(Employee employee);

    public void loadPrices(MutableLiveData<Integer> juicePrice, MutableLiveData<Integer> juiceSmallPrice, MutableLiveData<Integer> kefirPrice, MutableLiveData<Integer> kefirSmallPrice);

    public void savePrices(int juicePrice, int juiceSmallPrice, int kefirPrice, int kefirSmallPrice);

}
