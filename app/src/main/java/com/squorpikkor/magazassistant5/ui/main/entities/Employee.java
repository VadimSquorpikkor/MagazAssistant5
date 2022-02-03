package com.squorpikkor.magazassistant5.ui.main.entities;

public class Employee extends Entity{

    private final String locationId;
    private int days;//на сколько дней брать сок
    private String daysString;// 11000
    private boolean isPresent;//присутствует на работе

    public Employee(String id, String name, String locationId) {
        super(id, name);
        this.locationId = locationId;
        this.days = 5;
        this.isPresent = true;
    }

    public String getLocationId() {
        return locationId;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public int getDays() {
        return days;
    }

    public boolean isPresent() {
        return isPresent;
    }

    public void setPresent(boolean present) {
        isPresent = present;
    }

}
