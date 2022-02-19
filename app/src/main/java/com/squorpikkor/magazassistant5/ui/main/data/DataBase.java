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

class DataBase {

   ArrayList<Location> locations;
   ArrayList<Employee> employees;
   ArrayList<Order> orders;


   public DataBase() {
      locations = new ArrayList<>();
      locations.add(new Location("1","Монтаж", false));
      locations.add(new Location("2","Сборка", false));
      locations.add(new Location("3","Мнипи", true));
      locations.add(new Location("4","1-й монтаж", false));

      employees = new ArrayList<>();
      employees.add(new Employee("1_1", "Карпук И.Н.", "1", "11111"));
      employees.add(new Employee("1_2", "Король В.А.", "1", "11111"));
      employees.add(new Employee("1_3", "Курак Л.И.", "1", "11111"));
      employees.add(new Employee("1_4", "Лютаревич А.Л.", "1", "11111"));
      employees.add(new Employee("1_5", "Шпилевская Л.С.", "1", "11111"));
      employees.add(new Employee("1_6", "Яцкевич И.В.", "1", "11111"));

      employees.add(new Employee("2_7", "Алисевич Д.В.", "2", "10101"));
      employees.add(new Employee("2_8", "Алисевич О.В.", "2", "10101"));
      employees.add(new Employee("2_9", "Дементьев В.Ю.", "2", "11111"));
      employees.add(new Employee("2_10", "Долгий С.В.", "2", "11111"));
      employees.add(new Employee("2_11", "Махнюков И.С.", "2", "11111"));
      employees.add(new Employee("2_12", "Мороз Ю.В.", "2", "11111"));
      employees.add(new Employee("2_13", "Пекарский Ю.В.", "2", "11111"));
      employees.add(new Employee("2_14", "Праневич А.А.", "2", "11111"));
      employees.add(new Employee("2_15", "Шустов М.В.", "2", "00110"));
      employees.add(new Employee("2_16", "Яворский А.В.", "2", "11111"));
      employees.add(new Employee("2_17", "Ячминев М.В.", "2", "11111"));

      employees.add(new Employee("3_2", "Барсуков С.Н.", "3", "11111"));
      employees.add(new Employee("3_2", "Буров Л.В.", "3", "11111"));
      employees.add(new Employee("3_2", "Буров М.Л.", "3", "11111"));
      employees.add(new Employee("3_2", "Видасев Р.В.", "3", "11111"));
      employees.add(new Employee("3_2", "Денисенко С.В.", "3", "11111"));
      employees.add(new Employee("3_2", "Дыба Н.А.", "3", "11111"));
      employees.add(new Employee("3_2", "Зуевский В.И.", "3", "11111"));
      employees.add(new Employee("3_2", "Каменщиков Е.С.", "3", "11111"));
      employees.add(new Employee("3_2", "Кишкилевич В.П.", "3", "11111"));
      employees.add(new Employee("3_2", "Колосовский А.Г.", "3", "11111"));
      employees.add(new Employee("3_2", "Любач В.Г.", "3", "11111"));
      employees.add(new Employee("3_2", "Матвеенко А.М,", "3", "11111"));
      employees.add(new Employee("3_2", "Муравецкий М.В,", "3", "11111"));
      employees.add(new Employee("3_2", "Поляков В.В,", "3", "11111"));
      employees.add(new Employee("3_2", "Рычик А.А.", "3", "11111"));
      employees.add(new Employee("3_2", "Рябов А.К.", "3", "11111"));
      employees.add(new Employee("3_2", "Станкевич Ю.Б.", "3", "11111"));
      employees.add(new Employee("3_2", "Фролов И.А.", "3", "11111"));
      employees.add(new Employee("3_2", "Юревич С.А,", "3", "11111"));

      employees.add(new Employee("4_2", "Крот А.С.", "4", "11111"));
      employees.add(new Employee("4_2", "Штылев Г.Н.", "4", "11111"));
      employees.add(new Employee("4_2", "Антонова О.Н.", "4", "11111"));

      orders = new ArrayList<>();
      orders.add(new Order("1", "Сок", 256, 2, "2_7", false));
      orders.add(new Order("2", "Сок", 256, 1, "2_7", true));
      orders.add(new Order("3", "Сок", 256, 3, "2_7", false));

   }

   //TODO ВАЖНО id у сущностей будет состоять из номера: "1", "2" ... "23", никаких "1_2_7" НЕ БУДЕТ.
   // При создании нового итема (например ордера) очень просто узнать, какой уникальный id дать,
   // так чтобы такого ещё не было, для этого достаточно знать, какое количество ордеров всего на
   // данный момент. Если считать номера с "0", то уникальный id нового ордера будет равен
   // количеству ордеров! Например: если всего ордеров 5 (id будут такими: "0", "1", "2", "3", "4"),
   // то новый уникальный id -- это "5" ! Для EmployeeUnion тоже всё просто, его id -- это id его
   // локации

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

   public void getAllOrders(MutableLiveData<ArrayList<Order>> orders) {
      orders.setValue(this.orders);
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
      juicePrice.setValue(349);
      juiceSmallPrice.setValue(161);
      kefirPrice.setValue(106);
      kefirSmallPrice.setValue(89);
   }
}
