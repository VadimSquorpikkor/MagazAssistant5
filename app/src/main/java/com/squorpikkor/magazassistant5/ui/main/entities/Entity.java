package com.squorpikkor.magazassistant5.ui.main.entities;

class Entity {

   private final String id;
   private String name;

   public Entity(String id, String name) {
      this.id = id;
      this.name = name;
   }

   public String getId() {
      return id;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }
}
