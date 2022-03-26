package com.squorpikkor.magazassistant5.ui.main.entities;

import java.util.Arrays;

public class Employee extends Entity{

    private final int locationId;
    private boolean isPresent;//присутствует на работе
    private final boolean[] workingDaysArray;
    private static final int MAX_DAYS_COUNT = 9;
    private boolean show;

    private void daysToDaysArray(String days) {
        for (int i = 0; i < workingDaysArray.length; i++) {
            if (days.length() > i) {
                if (days.charAt(i) == '1') workingDaysArray[i] = true;
            } else {
                workingDaysArray[i] = false;
            }
        }
    }

    //Если id не задан, то он автоматом приравнивается -1 и не записывается в БД при сохранении
    public Employee(String name, int locationId) {
        super(-1, name);
        this.locationId = locationId;
        this.isPresent = true;
        this.workingDaysArray = new boolean[MAX_DAYS_COUNT];
        daysToDaysArray("11111");
    }

    public Employee(int id, String name, int locationId) {
        super(id, name);
        this.locationId = locationId;
        this.isPresent = true;
        this.workingDaysArray = new boolean[MAX_DAYS_COUNT];
        daysToDaysArray("11111");
    }

    //не паблик, этот констоуктор использует только EmployeeUnion
    Employee(int id, String name, int locationId, int workingDaysAtWeek) {
        super(id, name);
        this.locationId = locationId;
        this.isPresent = true;
        this.workingDaysArray = new boolean[workingDaysAtWeek];
        Arrays.fill(workingDaysArray, true);
    }

    /**Для БД*/
    public Employee(int id, String name, String days, boolean isPresent, int location) {
        super(id, name);
        this.locationId = location;
        this.isPresent = isPresent;
        this.workingDaysArray = new boolean[MAX_DAYS_COUNT];
        daysToDaysArray(days);
    }

    public int getLocationId() {
        return locationId;
    }

    public int getDays(int daysInWeek) {
        if (!isPresent) return 0;//если отсутствует, то и считать не надо, будет 0
        int count = 0;
        for (int i = 0; i < daysInWeek; i++) if (workingDaysArray[i]) count++;
        return count;
    }

    //TODO DEPRECATED вместо isPresent использовать if(getDays==0)
    public boolean isPresent() {
        return isPresent;
    }

    public void setPresent(boolean present) {
        isPresent = present;
    }

    public boolean[] getWorkingDaysArray(int daysInWeek) {
        boolean[] res = new boolean[daysInWeek];
        for (int i = 0; i < res.length; i++) {
            res[i]=workingDaysArray[i];
        }
        return res;
    }

    public void setWorkingDaysArray(boolean[] workingDays) {
        for (int i = 0; i < workingDays.length; i++) {
            this.workingDaysArray[i] = workingDays[i];
        }
    }

    public void setAllDaysToValue(boolean state) {
        Arrays.fill(workingDaysArray, state);
    }

    public boolean isShow() {
        return show;
    }

    public void setShow(boolean show) {
        this.show = show;
    }

    public String getWorkingDaysArray() {
        StringBuilder sb = new StringBuilder();
        for (boolean b : workingDaysArray) {
            if (b) sb.append("1");
            else sb.append("0");
        }
        return String.valueOf(sb);
    }

}
