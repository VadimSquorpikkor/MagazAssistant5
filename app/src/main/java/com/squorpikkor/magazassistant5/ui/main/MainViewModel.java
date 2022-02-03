package com.squorpikkor.magazassistant5.ui.main;

import static com.squorpikkor.magazassistant5.ui.main.App.TAG;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.squorpikkor.magazassistant5.ui.main.data.DataHelper;
import com.squorpikkor.magazassistant5.ui.main.entities.Employee;
import com.squorpikkor.magazassistant5.ui.main.entities.Location;
import com.squorpikkor.magazassistant5.ui.main.entities.Order;

import java.util.ArrayList;

public class MainViewModel extends ViewModel {

    private final DataHelper data;

    private final MutableLiveData<ArrayList<Location>> locations;
    private final MutableLiveData<ArrayList<Employee>> employees;
    private final MutableLiveData<ArrayList<Order>>    orders;
    private MutableLiveData<Integer> juicePrice;
    private MutableLiveData<Integer> juiceSmallPrice;
    private MutableLiveData<Integer> kefirPrice;
    private MutableLiveData<Integer> kefirSmallPrice;
    private MutableLiveData<Integer> workingDays;
    private MutableLiveData<Integer> invoiceMoney;

    private int moneyForEmployeePerDay;//сумма на человекодень

    public MainViewModel() {
        moneyForEmployeePerDay = 0;
        locations = new MutableLiveData<>();
        employees = new MutableLiveData<>();
        orders = new MutableLiveData<>();
        juicePrice = new MutableLiveData<>();
        juiceSmallPrice = new MutableLiveData<>();
        kefirPrice = new MutableLiveData<>();
        kefirSmallPrice = new MutableLiveData<>();
        workingDays = new MutableLiveData<>(5);
        invoiceMoney = new MutableLiveData<>(0);
        data = new DataHelper(locations, employees, orders);
        data.getAllLocations();
        data.getAllEmployees();
        data.loadPrices(juicePrice, juiceSmallPrice, kefirPrice, kefirSmallPrice);
        проверка();
    }

    public MutableLiveData<ArrayList<Location>> getLocations() {
        return locations;
    }
    public MutableLiveData<ArrayList<Employee>> getEmployees() {
        return employees;
    }
    public MutableLiveData<ArrayList<Order>>    getOrders() {
        return orders;
    }
    public MutableLiveData<Integer> getJuicePrice() {
        return juicePrice;
    }
    public MutableLiveData<Integer> getJuiceSmallPrice() {
        return juiceSmallPrice;
    }
    public MutableLiveData<Integer> getKefirPrice() {
        return kefirPrice;
    }
    public MutableLiveData<Integer> getKefirSmallPrice() {
        return kefirSmallPrice;
    }
    public MutableLiveData<Integer> getWorkingDays() {
        return workingDays;
    }

    private void проверка() {
        calculateInvoice();
        Log.e(TAG, "в накладной: "+invoiceMoney.getValue());
    }

    private static final int JUICE_PER_DAY = 250;
    private static final int JUICE_BIG_VOLUME = 1000;
    private static final int JUICE_SMALL_VOLUME = 250;
    private static final int KEFIR_PER_DAY = 500;
    private static final int KEFIR_BIG_VOLUME = 1000;
    private static final int KEFIR_SMALL_VOLUME = 250;


    /**Подсчет денег в накладной. Также считает количество больших и маленьких соков и кефиров и
     * сумму денег для пользователя в день*/
    private void calculateInvoice() {
        if (employees.getValue()==null
                ||employees.getValue().size()==0
                ||juicePrice.getValue()==null
                ||juiceSmallPrice.getValue()==null
                ||kefirPrice.getValue()==null
                ||kefirSmallPrice.getValue()==null
        ) {
            invoiceMoney.setValue(0);
            return;
        }
        int totalDaysForAllEmployees = 0;
        for (Employee employee:employees.getValue()) totalDaysForAllEmployees+=employee.isPresent()?employee.getDays():0;
        int juiceVolume = JUICE_PER_DAY*totalDaysForAllEmployees;
        int bigJuiceCount = juiceVolume/JUICE_BIG_VOLUME;//2500ml->2 больших сока
        int smallJuiceCount = (juiceVolume-bigJuiceCount*JUICE_BIG_VOLUME)/JUICE_SMALL_VOLUME;//(2500ml-2000ml)=>500ml/250=2 маленьких сока
        int kefirVolume = KEFIR_PER_DAY*totalDaysForAllEmployees;
        int bigKefirCount = kefirVolume/KEFIR_BIG_VOLUME;
        int smallKefirCount = (kefirVolume-bigKefirCount*KEFIR_BIG_VOLUME)/KEFIR_SMALL_VOLUME;
        int totalPrice =
                bigJuiceCount*juicePrice.getValue()+
                smallJuiceCount*juiceSmallPrice.getValue()+
                bigKefirCount*kefirPrice.getValue()+
                smallKefirCount*kefirSmallPrice.getValue();
        Log.e(TAG, "calculateInvoice: JB-"+bigJuiceCount+" JS-"+smallJuiceCount+" KB-"+bigKefirCount+" KS-"+smallKefirCount);
        invoiceMoney.setValue(totalPrice);
        if (totalDaysForAllEmployees!=0) moneyForEmployeePerDay = totalPrice/totalDaysForAllEmployees;
    }

    /**На какую сумму купить товара пользоватедю. Считается, как сумма указанная в накладной
     * деленная на количество всех дней у всех пользователей умноженная на количество дней,
     * которые выбранный пользователь отработал*/
    public int moneyForEmployee(Employee employee) {
        return employee.getDays()*moneyForEmployeePerDay;
    }

}
