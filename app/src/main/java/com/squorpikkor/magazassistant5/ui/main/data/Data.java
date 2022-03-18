package com.squorpikkor.magazassistant5.ui.main.data;

import androidx.lifecycle.MutableLiveData;

import com.squorpikkor.magazassistant5.ui.main.entities.Employee;
import com.squorpikkor.magazassistant5.ui.main.entities.Location;
import com.squorpikkor.magazassistant5.ui.main.entities.Order;

import java.util.ArrayList;

interface Data {

    void updateEmployee(Employee employee);

    void addEmployee(Employee employee);

    void getAllEmployees(MutableLiveData<ArrayList<Employee>> employees);

    void getEmployeesByLocation(Location location, MutableLiveData<ArrayList<Employee>> selectedEmployees);

    void updateLocation(Location location);

    void addLocation(Location location);

    void getAllLocations(MutableLiveData<ArrayList<Location>> locations);

    void updateOrder(Order order);

    void addOrder(Order order);

    void removeOrder(Order order);

    void getAllOrders(MutableLiveData<ArrayList<Order>> orders);

    void getOrderByEmployee(Employee employee, MutableLiveData<ArrayList<Order>> selectedOrders);




    void loadPrices(MutableLiveData<Integer> juicePrice, MutableLiveData<Integer> juiceSmallPrice, MutableLiveData<Integer> kefirPrice, MutableLiveData<Integer> kefirSmallPrice);

    void savePrices(int juicePrice, int juiceSmallPrice, int kefirPrice, int kefirSmallPrice);

    void addAllEmployeesDefault();

    void addAllLocationsDefault();

}
