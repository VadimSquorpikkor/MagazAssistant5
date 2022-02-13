package com.squorpikkor.magazassistant5.ui.main.adapter;

import com.squorpikkor.magazassistant5.ui.main.entities.Employee;

import java.util.ArrayList;

class EmployeeUnion {

   static Employee uniteEmployeesInOne(ArrayList<Employee> employees, String name) {
      ArrayList<String> list = new ArrayList<>();
      for (Employee e:employees) {
         list.add(e.getId());
      }
      Employee employee = new Employee(list, name, employees.get(0).getLocationId());
      return employee;
   }


}
