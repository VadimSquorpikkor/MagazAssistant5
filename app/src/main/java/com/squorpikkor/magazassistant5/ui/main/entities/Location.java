package com.squorpikkor.magazassistant5.ui.main.entities;

public class Location extends Entity{

   public Location(String id, String name) {
      super(id, name);
   }

   private boolean isUnitedEmployees;//Всех работников локации отображать как одного работника

   public boolean isUnitedEmployees() {
      return isUnitedEmployees;
   }
}
