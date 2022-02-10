package com.squorpikkor.magazassistant5.ui.main.entities;

public class Employee extends Entity{

    private final String locationId;
//    private int days;//на сколько дней брать сок
    private String daysString;// 11000
    private boolean isPresent;//присутствует на работе

    public Employee(String id, String name, String locationId) {
        super(id, name);
        this.locationId = locationId;
//        this.days = 5;
        this.isPresent = true;
    }

    //для проверки
    public Employee(String id, String name, String locationId, String daysString) {
        super(id, name);
        this.locationId = locationId;
//        this.days = 5;
        this.isPresent = true;
        this.daysString = daysString;
    }

    public String getLocationId() {
        return locationId;
    }

    /*public void setDays(int days) {
        this.days = days;
    }*/

    public int getDays() {//11001000 -> 3 дня
        int days = 0;
        for (int i = 0; i < getDaysString().length(); i++) {
            if (getDaysString().charAt(i)=='1') days++;
        }
        return days;
    }

    public String getDaysString() {
        return daysString;
    }

    public void setDaysString(String daysString) {
        this.daysString = daysString;
    }

    public boolean isPresent() {
        return isPresent;
    }

    public void setPresent(boolean present) {
        isPresent = present;
    }

}
