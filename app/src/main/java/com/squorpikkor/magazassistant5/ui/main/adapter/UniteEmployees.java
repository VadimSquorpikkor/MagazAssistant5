package com.squorpikkor.magazassistant5.ui.main.adapter;

import com.squorpikkor.magazassistant5.ui.main.entities.Employee;
import com.squorpikkor.magazassistant5.ui.main.entities.EmployeeUnion;

import java.util.ArrayList;

class UniteEmployees {

   //TODO вместо листа можно просто передавать id локации (она одинакова у всех employees этой локации)

   static Employee unitedEmployeesInOne(ArrayList<Employee> employees, String name, int workingDays) {
      ArrayList<Integer> list = new ArrayList<>();
      int days = 0;
      for (Employee employee:employees) {
         list.add(employee.getId());
         days+=employee.getDays(workingDays);
      }
      if (employees.size()==0)return null;
      return new EmployeeUnion(list, name, employees.get(0).getLocationId(), days);
   }
}
