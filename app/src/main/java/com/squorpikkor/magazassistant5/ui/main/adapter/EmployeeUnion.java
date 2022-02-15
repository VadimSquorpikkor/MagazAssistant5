package com.squorpikkor.magazassistant5.ui.main.adapter;

import com.squorpikkor.magazassistant5.ui.main.entities.Employee;

import java.util.ArrayList;

class EmployeeUnion {

   static Employee unitedEmployeesInOne(ArrayList<Employee> employees, String name, int workingDays) {
      String multiId = "";
      int days = 0;
      for (Employee e:employees) {
         multiId += e.getId()+"&";
         days+=e.getDays(workingDays);
      }
      Employee employee = new Employee(multiId, name, employees.get(0).getLocationId());
      employee.setDays(days);
      return employee;
   }
}
