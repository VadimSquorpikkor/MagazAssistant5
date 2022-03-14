package com.squorpikkor.magazassistant5.ui.main.entities;

import java.util.ArrayList;

public class EmployeeUnion extends Employee{

   //TODO вместо листа можно просто передавать id локации (она одинакова у всех employees этой локации)

   private int days;
   private ArrayList<Integer> ids;

   //TODO deprecated? а зачем это нужно? зачем хранить ids? для ордеров нужно только id самого employeeUnion, а его id
   public ArrayList<Integer> getIds() {
      return ids;
   }

   //TODO ВАЖНО id employeeUnion равен id его локации, все его ордеры будут с этим employeeId
   public EmployeeUnion(ArrayList<Integer> ids, String name, int locationId, int days) {
      super(locationId, name, locationId, days);
      this.ids = ids;
      this.days = days;
   }

   @Override
   public int getDays(int daysInWeek) {
      return days;
   }
}
