package com.squorpikkor.magazassistant5.ui.main;

import static com.squorpikkor.magazassistant5.ui.main.App.TAG;

import android.util.Log;

import androidx.lifecycle.LiveData;
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

    /**Количество человекодней*/
    private MutableLiveData<Integer> employeeDayCount;
    private MutableLiveData<Integer> bigJuiceCount;
    private MutableLiveData<Integer> smallJuiceCount;
    private MutableLiveData<Integer> bigKefirCount;
    private MutableLiveData<Integer> smallKefirCount;
    private MutableLiveData<Integer> invoiceTotal;
    private MutableLiveData<Integer> invoiceLeft;


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
        employeeDayCount = new MutableLiveData<>(0);
        bigJuiceCount = new MutableLiveData<>(0);
        smallJuiceCount = new MutableLiveData<>(0);
        bigKefirCount = new MutableLiveData<>(0);
        smallKefirCount = new MutableLiveData<>(0);
        invoiceTotal = new MutableLiveData<>(0);
        invoiceLeft = new MutableLiveData<>(0);
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
    public MutableLiveData<Integer> getBigJuiceCount() {
        return bigJuiceCount;
    }
    public MutableLiveData<Integer> getSmallJuiceCount() {
        return smallJuiceCount;
    }
    public MutableLiveData<Integer> getBigKefirCount() {
        return bigKefirCount;
    }
    public MutableLiveData<Integer> getSmallKefirCount() {
        return smallKefirCount;
    }
    public MutableLiveData<Integer> getInvoiceTotal() {
        return invoiceTotal;
    }
    public MutableLiveData<Integer> getInvoiceLeft() {
        return invoiceLeft;
    }

    public void update() {
        calculateInvoice();
    }

    private void проверка() {
        calculateInvoice();
    }

    private static final int JUICE_PER_DAY = 250;//норма сока в день
    private static final int KEFIR_PER_DAY = 500;//норма кефира в день
    private static final int JUICE_BIG_VOLUME = 1000;//объем большого сока
    private static final int JUICE_SMALL_VOLUME = 250;//объем маленького сока
    private static final int KEFIR_BIG_VOLUME = 1000;//объем большого кефира
    private static final int KEFIR_SMALL_VOLUME = 250;//объем маленького кефира


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
            invoiceTotal.setValue(0);
            return;
        }
        int totalDaysForAllEmployees = 0;
        for (Employee employee:employees.getValue()) totalDaysForAllEmployees+=employee.isPresent()?employee.getDays():0;
        Log.e(TAG, "calculateInvoice: totalDaysForAllEmployees = "+totalDaysForAllEmployees);
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
        if (totalDaysForAllEmployees!=0) moneyForEmployeePerDay = totalPrice/totalDaysForAllEmployees;

        //moneyForEmployeePerDay = JUICE_PER_DAY*juicePrice.getValue()+KEFIR_PER_DAY*kefirPrice.getValue();
        //это не совсем так: маленький сок не стоит четверть цены от большого, а значит при объеме
        // соков на всех = 2.25 литра и объеме 2 литра цена на один человекодень будет различаться:
        // 2*ценаБС/8 дней НЕ РАВНО (2*ценаБС+1*ценаМС)/9 дней

        this.bigJuiceCount.setValue(bigJuiceCount);
        this.smallJuiceCount.setValue(smallJuiceCount);
        this.bigKefirCount.setValue(bigKefirCount);
        this.smallKefirCount.setValue(smallKefirCount);
        this.invoiceTotal.setValue(totalPrice);
    }

    /**На какую сумму купить товара пользоватедю. Считается, как сумма указанная в накладной
     * деленная на количество всех дней у всех пользователей умноженная на количество дней,
     * которые выбранный пользователь отработал*/
    public int moneyForEmployee(Employee employee) {
        return employee.getDays()*moneyForEmployeePerDay;
    }

    /**Возвращает всех работников выбранной локации*/
    public ArrayList<Employee> getEmployeesByLocation(Location location) {
        ArrayList<Employee> list = new ArrayList<>();
        if (employees.getValue()==null) return list;
        for (Employee employee:employees.getValue()) {
            if (employee.getLocationId().equals(location.getId())) list.add(employee);
        }
        return list;
    }

    public ArrayList<Order> getOrdersByEmployee(Employee employee) {
        ArrayList<Order> list = new ArrayList<>();
        if (orders.getValue()==null) return list;
        for (Order order:orders.getValue()) {
            if (order.getEmployeeId().equals(employee.getId())) list.add(order);
        }
        return list;
    }

    //todo идея: реешние проблемы отображения обычного работника и объединенного:
    // можно сделать UnitedEmployee extends Employee (который будет собирать в себе несколько
    // работников) и из viewModel передавать каждой локации (в ресайклер) и вариант объединенного и
    // вариант раздельного работника (список), а уже в ресайклере в итеме отображать вариант который
    // выбирается нажатием кнопки выбора режима отображения локации



}
