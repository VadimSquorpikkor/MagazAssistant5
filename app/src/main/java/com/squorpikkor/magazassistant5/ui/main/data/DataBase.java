package com.squorpikkor.magazassistant5.ui.main.data;

import com.squorpikkor.magazassistant5.ui.main.entities.Employee;
import com.squorpikkor.magazassistant5.ui.main.entities.Location;
import com.squorpikkor.magazassistant5.ui.main.entities.Order;

import java.util.ArrayList;

/**
 *    ____      ____      ____
 *    |  |      |  |      |  |
 *    |  |-----<|  |-----<|  |
 *    |  |      |  |      |  |
 * Locations  Employees  Orders
 *
 * */

class DataBase {

   ArrayList<Location> locations;
   ArrayList<Employee> employees;
   ArrayList<Order> orders;


   public DataBase() {
      locations = new ArrayList<>();
      locations.add(new Location("1","Монтаж"));
      locations.add(new Location("2","Сборка"));
      locations.add(new Location("3","Праневич"));
      locations.add(new Location("4","Корелин"));



   }

   public void saveEmployee(Employee employee) {

   }

   public void saveLocation(Location location) {

   }

   public void saveOrder(Order order) {

   }

   public ArrayList<Location> getAllLocations() {
      return locations;
   }

   public ArrayList<Employee> getAllEmployees() {
      return null;
   }

   public ArrayList<Employee> getEmployeesByLocation(Location location) {
      String id = location.getId();
      return null;
   }

   public ArrayList<Order> getOrderByEmployee(Employee employee) {
      String name = employee.getName();
      return null;
   }
}
