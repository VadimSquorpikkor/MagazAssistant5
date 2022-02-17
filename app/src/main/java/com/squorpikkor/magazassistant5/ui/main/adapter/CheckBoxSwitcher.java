package com.squorpikkor.magazassistant5.ui.main.adapter;

import android.view.View;
import android.widget.CheckBox;
import com.squorpikkor.magazassistant5.ui.main.entities.Employee;

class CheckBoxSwitcher {

    static void setCheckboxesByEmployee(CheckBox[] checks, Employee employee, int workingDays) {
        boolean[] eDays = employee.getWorkingDaysArray(workingDays);
        for (int i = 0; i < checks.length; i++) {
            if (eDays.length > i) {
                checks[i].setChecked(eDays[i]);
                checks[i].setVisibility(View.VISIBLE);
            } else {
                checks[i].setVisibility(View.GONE);
            }
        }
    }

    static void setDaysByCheckboxes(CheckBox[] checks, Employee employee) {
        boolean[] arr = new boolean[checks.length];
        for (int i = 0; i < checks.length; i++) arr[i] = checks[i].isChecked();
        employee.setWorkingDaysArray(arr);
    }

    static void toggleCheckBoxes(CheckBox[] checks, boolean state, Employee employee) {
        for (CheckBox ch:checks) {
            ch.setChecked(state);
            ch.setEnabled(state);
        }
        employee.setAllDaysToValue(state);
        employee.setPresent(state);
    }

}
