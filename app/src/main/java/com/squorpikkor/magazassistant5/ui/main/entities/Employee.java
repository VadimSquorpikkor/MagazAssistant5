package com.squorpikkor.magazassistant5.ui.main.entities;

import java.util.Arrays;

public class Employee extends Entity{

    private final String locationId;
    private boolean isPresent;//присутствует на работе
    private boolean[] workingDaysArray;

    //для проверки
    /*public Employee(String id, String name, String locationId, int workingDaysAtWeek) {
        super(id, name);
        this.locationId = locationId;
        this.isPresent = true;
        this.workingDaysArray = new boolean[workingDaysAtWeek];
        for (boolean b:workingDaysArray) b=true;
    }*/

    public Employee(String id, String name, String locationId, String days) {
        super(id, name);
        this.locationId = locationId;
        this.isPresent = true;
        this.workingDaysArray = new boolean[days.length()];
        for (int i = 0; i < days.length(); i++) {
            if (days.charAt(i)=='1') workingDaysArray[i]=true;
        }
    }

    public Employee(String id, String name, String locationId, int workingDaysAtWeek) {
        super(id, name);
        this.locationId = locationId;
        this.isPresent = true;
        this.workingDaysArray = new boolean[workingDaysAtWeek];
        Arrays.fill(workingDaysArray, true);
    }

    public String getLocationId() {
        return locationId;
    }

    public int getDays() {
        int count = 0;
        for (boolean checked:workingDaysArray) if (checked) count++;
        return count;
    }

    public boolean isPresent() {
        return isPresent;
    }

    public void setPresent(boolean present) {
        isPresent = present;
    }

    public boolean[] getWorkingDaysArray() {
        return workingDaysArray;
    }

    public void setWorkingDaysArray(boolean[] workingDaysArray) {
        this.workingDaysArray = workingDaysArray;
    }

    public void setAllDaysToValue(boolean state) {
        Arrays.fill(workingDaysArray, state);
    }


}
