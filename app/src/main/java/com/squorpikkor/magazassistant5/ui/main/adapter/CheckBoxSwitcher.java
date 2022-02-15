package com.squorpikkor.magazassistant5.ui.main.adapter;

import android.view.View;
import android.widget.CheckBox;

import com.squorpikkor.magazassistant5.ui.main.entities.Employee;

class CheckBoxSwitcher {

    static void setCheckboxesByEmployee(CheckBox[] checks, Employee employee, int workingDaysCount) {
//        int count = 0;
        for (int i = 0; i < checks.length; i++) {
            //перебираем все 9 чекбоксов
            checks[i].setVisibility(workingDaysCount>i? View.VISIBLE:View.GONE);//оставляем количество чекбоксов равное количеству рабочих дней
            if (employee.getDaysString().length()<i) checks[i].setChecked(true);//если чекбоксов больше, чем сохраненных то все чекбоксы сверх сохраненных чекаем

            //Log.e("TAG", "*** employee.getDaysString().length(): "+employee.getDaysString().length());
            if (employee.getDaysString().length()>i
                    && employee.getDaysString().charAt(i)=='1') {
                checks[i].setChecked(true);//1 - это чекед
//                count++;
            } else checks[i].setChecked(false);//иначе - анчекед
        }
//        employee.setDays(count);
    }

    static void setDaysByCheckboxes(CheckBox[] checks, Employee employee) {
        char[] str = new char[checks.length];
        for (int i = 0; i < checks.length; i++) str[i]=checks[i].isChecked()&&checks[i].getVisibility()==View.VISIBLE?'1':'0';
        String dayString = new String(str);
        employee.setDaysString(dayString);
//        Log.e("TAG", "setDaysByCheckboxes: "+dayString);
    }

    static void toggleCheckBoxes(CheckBox[] checks, boolean state, Employee employee) {
        for (CheckBox ch:checks) {
            ch.setChecked(state);
            ch.setEnabled(state);
        }
        employee.setDaysString(state?"111111111":"000000000");
        employee.setPresent(state);
    }

}
