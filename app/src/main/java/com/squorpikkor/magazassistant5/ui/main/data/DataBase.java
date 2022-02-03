package com.squorpikkor.magazassistant5.ui.main.data;

import static com.squorpikkor.magazassistant5.ui.main.App.TAG;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

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

      employees = new ArrayList<>();
      employees.add(new Employee("2_1", "Шустов", "2"));
      employees.add(new Employee("2_2", "Алисевич", "2"));


   }

   public void saveEmployee(Employee employee) {

   }

   public void saveLocation(Location location) {

   }

   public void saveOrder(Order order) {

   }

   public void getAllLocations(MutableLiveData<ArrayList<Location>> locations) {
      locations.setValue(this.locations);
   }

   public void getAllEmployees(MutableLiveData<ArrayList<Employee>> employees) {
      employees.setValue(this.employees);
   }

   public ArrayList<Employee> getEmployeesByLocation(Location location) {
      String id = location.getId();
      return null;
   }

   public ArrayList<Order> getOrderByEmployee(Employee employee) {
      String name = employee.getName();
      return null;
   }

   public void loadPrices(MutableLiveData<Integer> juicePrice, MutableLiveData<Integer> juiceSmallPrice, MutableLiveData<Integer> kefirPrice, MutableLiveData<Integer> kefirSmallPrice) {
      juicePrice.setValue(350);
      juiceSmallPrice.setValue(150);
      kefirPrice.setValue(250);
      kefirSmallPrice.setValue(100);
   }
}
