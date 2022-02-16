package com.squorpikkor.magazassistant5.ui.main.adapter;

import android.util.Log;
import android.view.View;
import android.widget.CheckBox;

import com.squorpikkor.magazassistant5.ui.main.entities.Employee;

import java.util.Arrays;

class CheckBoxSwitcher {

    static void setCheckboxesByEmployee(CheckBox[] checks, Employee employee) {
        boolean[] eDays = employee.getWorkingDaysArray();
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
        for (int i = 0; i < employee.getWorkingDaysArray().length; i++) {
            employee.getWorkingDaysArray()[i]=checks[i].isChecked();
            Log.e("TAG", "setDaysByCheckboxes: "+ Arrays.toString(employee.getWorkingDaysArray()));
        }
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
