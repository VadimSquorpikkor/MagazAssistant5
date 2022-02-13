package com.squorpikkor.magazassistant5.ui.main.entities;

import java.util.ArrayList;

public class Employee extends Entity{

    private final String locationId;
//    private int days;//на сколько дней брать сок
    private String daysString;// 11000
    private boolean isPresent;//присутствует на работе

    ArrayList<String> ids;

    public Employee(String id, String name, String locationId) {
        super(id, name);
        this.locationId = locationId;
//        this.days = 5;
        this.isPresent = true;
    }

    //ArrayList<String> ids нужен для объединенных работников, ведь нужно такому объединенному
    // работнику как-то получить все ордеры входящих в него работников
    //todo возможно есть смысл вместо string id использовать ArrayList, тогда getId будет брать
    // первый id (для не объединенных работников он же единственный), с другой стороны с таким
    // усложнением не очень очевидно всё работать будет...
    public Employee(ArrayList<String> ids, String name, String locationId) {
        super(ids.get(0), name);
        this.ids = ids;
        this.locationId = locationId;
        this.isPresent = true;
    }

    public ArrayList<String> getIds() {
        return ids;
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
