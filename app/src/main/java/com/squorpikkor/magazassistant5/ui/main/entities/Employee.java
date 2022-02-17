package com.squorpikkor.magazassistant5.ui.main.entities;

import java.util.Arrays;

public class Employee extends Entity{

    private final String locationId;
    private boolean isPresent;//присутствует на работе
    private final boolean[] workingDaysArray;
    private static final int MAX_DAYS_COUNT = 9;
    private boolean show;

    public Employee(String id, String name, String locationId, String days) {
        super(id, name);
        this.locationId = locationId;
        this.isPresent = true;
        this.workingDaysArray = new boolean[MAX_DAYS_COUNT];
        for (int i = 0; i < workingDaysArray.length; i++) {
            if (days.length() > i) {
                if (days.charAt(i) == '1') workingDaysArray[i] = true;
            } else {
                workingDaysArray[i] = false;
            }
        }
    }

    //не паблик, этот констоуктор использует только EmployeeUnion
    Employee(String id, String name, String locationId, int workingDaysAtWeek) {
        super(id, name);
        this.locationId = locationId;
        this.isPresent = true;
        this.workingDaysArray = new boolean[workingDaysAtWeek];
        Arrays.fill(workingDaysArray, true);
    }

    public String getLocationId() {
        return locationId;
    }

    public int getDays(int daysInWeek) {
        if (!isPresent) return 0;//если отсутствует, то и считать не надо, будет 0
        int count = 0;
        for (int i = 0; i < daysInWeek; i++) if (workingDaysArray[i]) count++;
        return count;
    }

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

}
