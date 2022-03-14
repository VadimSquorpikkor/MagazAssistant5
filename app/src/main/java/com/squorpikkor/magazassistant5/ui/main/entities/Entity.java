package com.squorpikkor.magazassistant5.ui.main.entities;

class Entity {

   private final int id;
   private String name;

   public Entity(int id, String name) {
      this.id = id;
      this.name = name;
   }

   public int getId() {
      return id;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }
}
