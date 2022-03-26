package com.squorpikkor.magazassistant5.ui.main.entities;

public class Location extends Entity{

   /**Для БД*/
   public Location(int id, String name, boolean isUnited) {
      super(id, name);
      this.isUnitedEmployees = isUnited;
   }

   public Location(String name, boolean isUnited) {
      super(-1, name);
      this.isUnitedEmployees = isUnited;
   }

   public Location(String name) {
      super(-1, name);
      this.isUnitedEmployees = false;
   }

   private boolean isUnitedEmployees;//Всех работников локации отображать как одного работника

   public boolean isUnitedEmployees() {
      return isUnitedEmployees;
   }
}
