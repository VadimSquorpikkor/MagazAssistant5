package com.squorpikkor.magazassistant5.ui.main.data;

import com.squorpikkor.magazassistant5.ui.main.entities.Employee;
import com.squorpikkor.magazassistant5.ui.main.entities.Location;
import com.squorpikkor.magazassistant5.ui.main.entities.Order;

import java.util.ArrayList;

class DataBase {

   public void saveEmployee(Employee employee) {

   }

   public void saveLocation(Location location) {

   }

   public void saveOrder(Order order) {

   }

   public ArrayList<Location> getAllLocations() {
      ArrayList<Location> list = new ArrayList<>();
      list.add(new Location("Монтаж"));
      list.add(new Location("Сборка"));
      list.add(new Location("Праневич"));
      list.add(new Location("Корелин"));
      return list;
   }

   public ArrayList<Employee> getAllEmployees() {
      return null;
   }

   public ArrayList<Employee> getEmployeesByLocation(Location location) {
      String name = location.getName();
      return null;
   }

   public ArrayList<Order> getOrderByEmployee(Employee employee) {
      String name = employee.getName();
      return null;
   }
}
