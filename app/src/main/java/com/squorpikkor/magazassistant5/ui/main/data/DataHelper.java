package com.squorpikkor.magazassistant5.ui.main.data;

import androidx.lifecycle.MutableLiveData;

import com.squorpikkor.magazassistant5.ui.main.entities.Location;
import com.squorpikkor.magazassistant5.ui.main.entities.Employee;
import com.squorpikkor.magazassistant5.ui.main.entities.Order;

import java.util.ArrayList;

public class DataHelper {

   private final Data db;
   private final MutableLiveData<ArrayList<Location>> locations;
   private final MutableLiveData<ArrayList<Employee>> employees;
   private final MutableLiveData<ArrayList<Order>> orders;
   private final MutableLiveData<Integer> juicePrice;
   private final MutableLiveData<Integer> juiceSmallPrice;
   private final MutableLiveData<Integer> kefirPrice;
   private final MutableLiveData<Integer> kefirSmallPrice;
   private final MutableLiveData<Integer> workDaysCount;

   public DataHelper(MutableLiveData<ArrayList<Location>> locations,
                     MutableLiveData<ArrayList<Employee>> employees,
                     MutableLiveData<ArrayList<Order>> orders,
                     MutableLiveData<Integer> juicePrice,
                     MutableLiveData<Integer> juiceSmallPrice,
                     MutableLiveData<Integer> kefirPrice,
                     MutableLiveData<Integer> kefirSmallPrice,
                     MutableLiveData<Integer> workDaysCount
   ) {
//      db = new DataBase();
      db = new SQLDatabase();
      this.locations = locations;
      this.employees = employees;
      this.orders = orders;
      this.juicePrice = juicePrice;
      this.juiceSmallPrice = juiceSmallPrice;
      this.kefirPrice = kefirPrice;
      this.kefirSmallPrice = kefirSmallPrice;
      this.workDaysCount = workDaysCount;
   }

   public void updateLocation(Location location) {
      db.updateLocation(location);
   }

   public void getAllLocations() {
      db.getAllLocations(locations);
   }

   public void createEmployee(Employee employee) {
      db.addEmployee(employee);
   }

   public void updateEmployee(Employee employee) {
      db.updateEmployee(employee);
   }

   public void deleteEmployee(Employee employee) {
      db.removeEmployee(employee);
   }

   public void getAllEmployees() {
      db.getAllEmployees(employees);
   }

   public void getAllOrders() {
      db.getAllOrders(orders);
   }

   public void updateOrder(Order order) {
      db.updateOrder(order);
   }

   public void createOrder(Order order) {
      db.addOrder(order);
   }

   public void deleteOrder(Order order) {
      db.removeOrder(order);
   }

   public void loadPrices() {
      db.loadPrices(juicePrice, juiceSmallPrice, kefirPrice, kefirSmallPrice);
   }

   public void savePrices() {
      db.savePrices(juicePrice.getValue(), juiceSmallPrice.getValue(), kefirPrice.getValue(), kefirSmallPrice.getValue());
   }

   public void updateWorkingDaysCount() {
      db.saveWorkingDays(workDaysCount.getValue());
   }

   public void getWorkingDaysCount() {
      db.getWorkingDays(workDaysCount);
   }

   public void uncheckAllOrders() {

   }
}
