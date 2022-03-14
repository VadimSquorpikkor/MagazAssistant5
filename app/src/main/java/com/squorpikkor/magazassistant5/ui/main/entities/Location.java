package com.squorpikkor.magazassistant5.ui.main.entities;

public class Location extends Entity{

   public Location(int id, String name, boolean isUnited) {
      super(id, name);
      this.isUnitedEmployees = isUnited;
   }

   private boolean isUnitedEmployees;//Всех работников локации отображать как одного работника

   public boolean isUnitedEmployees() {
      return isUnitedEmployees;
   }
}
