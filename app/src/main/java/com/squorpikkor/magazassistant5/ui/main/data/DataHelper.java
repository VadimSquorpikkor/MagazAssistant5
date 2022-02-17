package com.squorpikkor.magazassistant5.ui.main.data;

import androidx.lifecycle.MutableLiveData;

import com.squorpikkor.magazassistant5.ui.main.entities.Location;
import com.squorpikkor.magazassistant5.ui.main.entities.Employee;
import com.squorpikkor.magazassistant5.ui.main.entities.Order;

import java.util.ArrayList;

public class DataHelper {

   private final DataBase db;
   private final MutableLiveData<ArrayList<Location>> locations;
   private final MutableLiveData<ArrayList<Employee>> employees;
   private final MutableLiveData<ArrayList<Order>> orders;

   public DataHelper(MutableLiveData<ArrayList<Location>> locations,
                     MutableLiveData<ArrayList<Employee>> employees,
                     MutableLiveData<ArrayList<Order>> orders) {
      db = new DataBase();
      this.locations = locations;
      this.employees = employees;
      this.orders = orders;
   }

   public void saveLocation(Location location) {
      db.saveLocation(location);
   }

   public void getAllLocations() {
      db.getAllLocations(locations);
   }

   public void saveEmployee(Employee employee) {
      db.saveEmployee(employee);
   }

   public void getAllEmployees() {
      db.getAllEmployees(employees);
   }

   public void getAllOrders() {
      db.getAllOrders(orders);
   }

   public void saveOrder(Order order) {
      db.saveOrder(order);
   }

   public void loadPrices(MutableLiveData<Integer> juicePrice, MutableLiveData<Integer> juiceSmallPrice, MutableLiveData<Integer> kefirPrice, MutableLiveData<Integer> kefirSmallPrice) {
      db.loadPrices(juicePrice, juiceSmallPrice, kefirPrice, kefirSmallPrice);
   }
}
