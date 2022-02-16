package com.squorpikkor.magazassistant5.ui.main.entities;

import java.util.ArrayList;

public class EmployeeUnion extends Employee{

   //TODO вместо листа можно просто передавать id локации (она одинакова у всех employees этой локации)

   private int days;
   private ArrayList<String> ids;

   public ArrayList<String> getIds() {
      return ids;
   }

   public EmployeeUnion(ArrayList<String> ids, String name, String locationId, int days) {
      super(locationId, name, locationId, days);
      this.ids = ids;
   }
}
