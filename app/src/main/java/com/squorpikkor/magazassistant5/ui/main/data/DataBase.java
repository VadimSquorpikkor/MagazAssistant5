package com.squorpikkor.magazassistant5.ui.main.data;

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

class DataBase implements Data{

   ArrayList<Location> locations;
   ArrayList<Employee> employees;
   ArrayList<Order> orders;


   public DataBase() {
      locations = new ArrayList<>();
      locations.add(new Location("1","Монтаж", false));
      locations.add(new Location("2","Сборка", false));
      locations.add(new Location("3","Мнипи", true));
      locations.add(new Location("4","1-й монтаж", false));

      employees = DefaultData.employeesDefault();



      orders = new ArrayList<>();
      orders.add(new Order("1", "Сок", 256, 2, "7", false));
      orders.add(new Order("2", "Сок", 256, 1, "7", true));
      orders.add(new Order("3", "Сок", 256, 3, "7", false));

   }

   //TODO ВАЖНО id у сущностей будет состоять из номера: "1", "2" ... "23", никаких "1_2_7" НЕ БУДЕТ.
   // При создании нового итема (например ордера) очень просто узнать, какой уникальный id дать,
   // так чтобы такого ещё не было, для этого достаточно знать, какое количество ордеров всего на
   // данный момент. Если считать номера с "0", то уникальный id нового ордера будет равен
   // количеству ордеров! Например: если всего ордеров 5 (id будут такими: "0", "1", "2", "3", "4"),
   // то новый уникальный id -- это "5" ! Для EmployeeUnion тоже всё просто, его id -- это id его
   // локации.
   // ЕСТЬ КОСЯК! Если удален элемент из середины списка, то это уже не будет работать:
   // (0, 1, 2)->(0, 2) size==2, но последний элемент уже с таким id


   @Override
   public void saveEmployee(Employee employee) {

   }

   @Override
   public void saveLocation(Location location) {

   }

   @Override
   public void saveOrder(Order order) {

   }

   @Override
   public void getAllLocations(MutableLiveData<ArrayList<Location>> locations) {
      locations.setValue(this.locations);
   }

   @Override
   public void getAllEmployees(MutableLiveData<ArrayList<Employee>> employees) {
      employees.setValue(this.employees);
   }

   @Override
   public void getAllOrders(MutableLiveData<ArrayList<Order>> orders) {
      orders.setValue(this.orders);
   }

   @Override
   public ArrayList<Employee> getEmployeesByLocation(Location location) {
      String id = location.getId();
      return null;
   }

   @Override
   public ArrayList<Order> getOrderByEmployee(Employee employee) {
      String name = employee.getName();
      return null;
   }

   @Override
   public void loadPrices(MutableLiveData<Integer> juicePrice, MutableLiveData<Integer> juiceSmallPrice, MutableLiveData<Integer> kefirPrice, MutableLiveData<Integer> kefirSmallPrice) {
      juicePrice.setValue(349);
      juiceSmallPrice.setValue(161);
      kefirPrice.setValue(106);
      kefirSmallPrice.setValue(89);
   }
}
