package com.squorpikkor.magazassistant5.ui.main.data;

import com.squorpikkor.magazassistant5.ui.main.entities.Location;
import com.squorpikkor.magazassistant5.ui.main.entities.Employee;
import com.squorpikkor.magazassistant5.ui.main.entities.Order;

import java.util.ArrayList;

public class DataHelper {

   private final DataBase db;

   public DataHelper() {
      this.db = new DataBase();
   }


   public void saveLocation(Location location) {
      db.saveLocation(location);
   }

   public ArrayList<Location> getAllLocations() {
      return db.getAllLocations();
   }

   public void saveEmployee(Employee employee) {
      db.saveEmployee(employee);
   }

   public ArrayList<Employee> getAllEmployees() {
      return db.getAllEmployees();
   }

   public ArrayList<Employee> getEmployeesByLocation(Location location) {
      return db.getEmployeesByLocation(location);
   }

   public void saveOrder(Order order) {
      db.saveOrder(order);
   }

   public ArrayList<Order> getOrderByEmployee(Employee employee) {
      return db.getOrderByEmployee(employee);
   }

}
