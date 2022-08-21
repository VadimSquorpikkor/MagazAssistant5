package com.squorpikkor.magazassistant5.ui.main;

import static com.squorpikkor.magazassistant5.ui.main.App.TAG;

import android.util.Log;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.squorpikkor.magazassistant5.ui.main.adapter.CheckBoxSwitcher;
import com.squorpikkor.magazassistant5.ui.main.adapter.UniteEmployees;
import com.squorpikkor.magazassistant5.ui.main.data.DataHelper;
import com.squorpikkor.magazassistant5.ui.main.entities.Employee;
import com.squorpikkor.magazassistant5.ui.main.entities.Location;
import com.squorpikkor.magazassistant5.ui.main.entities.Order;
import com.squorpikkor.magazassistant5.ui.main.utils.Utils;

import java.util.ArrayList;

public class MainViewModel extends ViewModel {

    private final DataHelper data;

    private final MutableLiveData<ArrayList<Location>> locations;
    private final MutableLiveData<ArrayList<Employee>> employees;
    private final MutableLiveData<ArrayList<Order>>    orders;
    private final MutableLiveData<Integer> juicePrice;
    private final MutableLiveData<Integer> juiceSmallPrice;
    private final MutableLiveData<Integer> kefirPrice;
    private final MutableLiveData<Integer> kefirSmallPrice;
    private final MutableLiveData<Integer> workingDays;

    /**Количество человекодней*/
    private final MutableLiveData<Integer> employeeDayCount;
    private final MutableLiveData<Integer> bigJuiceCount;
    private final MutableLiveData<Integer> smallJuiceCount;
    private final MutableLiveData<Integer> bigKefirCount;
    private final MutableLiveData<Integer> smallKefirCount;
    private final MutableLiveData<Integer> invoiceTotal;
    private final MutableLiveData<Integer> invoiceLeft;
    private final MutableLiveData<Double>  moneyForEmployeePerDay;//сумма на человекодень

    //Диалоги
    private final MutableLiveData<Employee> openEmployeeDialog;

    private static MainViewModel instance;
    public static MainViewModel getInstance() {
        return instance;
    }

    public MainViewModel() {
        moneyForEmployeePerDay  = new MutableLiveData<>(0.0);
        locations               = new MutableLiveData<>();
        employees               = new MutableLiveData<>();
        orders                  = new MutableLiveData<>();
        juicePrice              = new MutableLiveData<>(0);
        juiceSmallPrice         = new MutableLiveData<>(0);
        kefirPrice              = new MutableLiveData<>(0);
        kefirSmallPrice         = new MutableLiveData<>(0);
        workingDays             = new MutableLiveData<>(5);
        employeeDayCount        = new MutableLiveData<>(0);
        bigJuiceCount           = new MutableLiveData<>(0);
        smallJuiceCount         = new MutableLiveData<>(0);
        bigKefirCount           = new MutableLiveData<>(0);
        smallKefirCount         = new MutableLiveData<>(0);
        invoiceTotal            = new MutableLiveData<>(0);
        invoiceLeft             = new MutableLiveData<>(0);
        openEmployeeDialog      = new MutableLiveData<>();
        data = new DataHelper(locations, employees, orders, juicePrice, juiceSmallPrice, kefirPrice, kefirSmallPrice, workingDays);
        data.getAllLocations();
        data.getAllEmployees();
        data.getAllOrders();
        data.loadPrices();
        data.getWorkingDaysCount();
        calculateInvoice();
        employees.observeForever(new Observer<ArrayList<Employee>>() {
            @Override
            public void onChanged(ArrayList<Employee> employees) {
                setPresentEmployeesByAllLocationsWithUnited(employees);
            }
        });
        instance = this;
    }

    private ArrayList<Employee> presentEmployees;

    private void setPresentEmployeesByAllLocationsWithUnited(ArrayList<Employee> employees) {
        if (locations.getValue()==null) return;
        presentEmployees = new ArrayList<>();
        for (Location location: locations.getValue()) {
            Log.e(TAG, "♦ presentEmployees: "+location.getName());
            presentEmployees.addAll(getPresentEmployeesByLocationWithUnited(location));
        }
        //пронумеровываю работников в списке по порядку. нужно для вызова по номеру в списке для запуска в каруселе
        for (int i = 0; i < presentEmployees.size(); i++) {
            presentEmployees.get(i).setCountInList(i);
            Log.e(TAG, "♦♦ : "+presentEmployees.get(i).getName()+" "+presentEmployees.get(i).getCountInList());
        }
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
    /**На какую сумму купить товара пользоватедю. Считается, как сумма указанная в накладной
     * деленная на количество всех дней у всех пользователей умноженная на количество дней,
     * которые выбранный пользователь отработал*/
    public MutableLiveData<Double> getMoneyForEmployeePerDay() {
        return moneyForEmployeePerDay;
    }
    public MutableLiveData<Integer> getEmployeeDayCount() {
        return employeeDayCount;
    }

    public MutableLiveData<Employee> getOpenEmployeeDialog() {
        return openEmployeeDialog;
    }



    public void update() {
        calculateInvoice();
        locations.setValue(locations.getValue());//перезапускаю ресайклер для ордеров при изменениях в накладной (если поменялось кол-во дней, то и сумма денег у работника изменится)
    }

    public void updateOrders() {
        invoiceLeft.setValue(Utils.calculateMoneyLeftByOrdersInt(getOrders().getValue(), invoiceTotal.getValue()));
        locations.setValue(locations.getValue());
    }

    private static final int JUICE_PER_DAY = 250;//норма сока в день, мл
    private static final int KEFIR_PER_DAY = 500;//норма кефира в день, мл
    private static final int JUICE_BIG_VOLUME = 1000;//объем большого сока, мл
    private static final int JUICE_SMALL_VOLUME = 250;//объем маленького сока, мл
    private static final int KEFIR_BIG_VOLUME = 1000;//объем большого кефира, мл
    private static final int KEFIR_SMALL_VOLUME = 500;//объем маленького кефира, мл


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
        for (Employee employee:employees.getValue()) totalDaysForAllEmployees+=employee.isPresent()?employee.getDays(workingDays.getValue()):0;
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
        if (totalDaysForAllEmployees!=0) moneyForEmployeePerDay.setValue((double)totalPrice/(double)totalDaysForAllEmployees);

        //moneyForEmployeePerDay = JUICE_PER_DAY*juicePrice.getValue()+KEFIR_PER_DAY*kefirPrice.getValue();
        //это не совсем так: маленький сок не стоит четверть цены от большого, а значит при объеме
        // соков на всех = 2.25 литра и объеме 2 литра цена на один человекодень будет различаться:
        // 2*ценаБС/8 дней НЕ РАВНО (2*ценаБС+1*ценаМС)/9 дней

        this.bigJuiceCount.setValue(bigJuiceCount);
        this.smallJuiceCount.setValue(smallJuiceCount);
        this.bigKefirCount.setValue(bigKefirCount);
        this.smallKefirCount.setValue(smallKefirCount);
        this.invoiceTotal.setValue(totalPrice);
        this.employeeDayCount.setValue(totalDaysForAllEmployees);

        //presentOrders -- это только те заказы, у которых работник проработал хотябы один день
        // (если использовать calculateMoneyLeftByOrdersInt просто для всех ордеров, то может так
        // получится, что ордер отмечен как куплен, а работник вообще не работает, при этом сумма
        // ордера будет учитываться при расчете оставшийся суммы денег)
        ArrayList<Order> presentOrders = Utils.presentOrders(orders.getValue(), employees.getValue(), workingDays.getValue());
        invoiceLeft.setValue(Utils.calculateMoneyLeftByOrdersInt(presentOrders, invoiceTotal.getValue()));
//        invoiceLeft.setValue(Utils.calculateMoneyLeftByOrdersInt(getOrders().getValue(), invoiceTotal.getValue()));
    }




    /**Возвращает всех работников выбранной локации*/
    public ArrayList<Employee> getEmployeesByLocation(Location location) {
        ArrayList<Employee> list = new ArrayList<>();
        if (employees.getValue()==null) return list;
        for (Employee employee:employees.getValue()) {
            if (employee.getLocationId()==location.getId()) {
                list.add(employee);
            }
        }
        return list;
    }

    /**Возвращает всех работников выбранной локации у которых есть хоть один рабочий день*/
    public ArrayList<Employee> getPresentEmployeesByLocation(Location location) {
        ArrayList<Employee> list = new ArrayList<>();
        if (employees.getValue()==null) return list;
        for (Employee employee:employees.getValue()) {
            if (employee.getLocationId()==location.getId()&&employee.getDays(getWorkingDays().getValue())!=0) {
                list.add(employee);
            }
        }
        return list;
    }

    /**Возвращает всех работников выбранной локации у которых есть хоть один рабочий день.
     * Если локация united, добавляет всех работников этой локации в один объединенный*/
    public ArrayList<Employee> getPresentEmployeesByLocationWithUnited(Location location) {
        ArrayList<Employee> list = new ArrayList<>();

        if (location.isUnitedEmployees()) {
            Log.e(TAG, ""+location.getName()+" (united)");
            Employee united = UniteEmployees.unitedEmployeesInOne(getPresentEmployeesByLocation(location), location.getName(), workingDays.getValue());
            if (united!=null) {
                list.add(united);
                for (Employee employee:list) Log.e(TAG, ": "+employee.getName()+" всего дней: "+employee.getDays(workingDays.getValue()));
            }
        } else {
            Log.e(TAG, ""+location.getName());
            if (employees.getValue()==null) return list;
            for (Employee employee:employees.getValue()) {
                if (employee.getLocationId()==location.getId()&&employee.getDays(getWorkingDays().getValue())!=0) {
                    list.add(employee);
                }
            }
            for (Employee employee:list) Log.e(TAG, ": "+employee.getName()+" всего дней: "+employee.getDays(workingDays.getValue()));
        }

        return list;
    }

    /**Возвращает всех работников всех локаций. Если есть объединенные, то добавляет, как объединенные*/
    public ArrayList<Employee> getPresentEmployeesByAllLocationsWithUnited() {
        return presentEmployees;
    }

    public ArrayList<Order> getOrdersByEmployee(Employee employee) {
        ArrayList<Order> list = new ArrayList<>();
        if (orders.getValue()==null) return list;
        for (Order order:orders.getValue()) {
            if (order.getEmployeeId()==employee.getId()) list.add(order);
        }
        return list;
    }

    /**На какую сумму работник может взять продуктов*/
    public int getMoneyLimit(Employee employee) {
        return (int)(employee.getDays(workingDays.getValue())*moneyForEmployeePerDay.getValue());
    }

    /**Анчек всех ордеров (но не удаление), чек всех работников*/
    public void resetShopping() {
        for (Employee employee:employees.getValue()) {
            employee.setPresent(true);
            employee.setAllDaysToValue(true);
            data.updateEmployee(employee);
        }
        employees.setValue(employees.getValue());
        for (Order order:orders.getValue()) {
            order.setChecked(false);
            data.updateOrder(order);
        }
        updateOrders();
    }
//--------------------------------------------------------------------------------------------------
    public void addNewOrder(Order order) {
        data.createOrder(order);
        getOrders().getValue().add(order);
        updateOrders();
    }

    public void updateOrder(Order order) {
        data.updateOrder(order);
        updateOrders();
    }

    public void deleteOrder(Order order) {
        data.deleteOrder(order);
        updateOrders();
    }
//--------------------------------------------------------------------------------------------------
    public void saveNewPrices(int bigJ, int smlJ, int bigK, int smlK) {
        juicePrice.setValue(bigJ);
        juiceSmallPrice.setValue(smlJ);
        kefirPrice.setValue(bigK);
        kefirSmallPrice.setValue(smlK);
        data.savePrices();
        calculateInvoice();
        updateOrders();
    }
//--------------------------------------------------------------------------------------------------
    public void saveWorkingDays(int days) {
        workingDays.setValue(days);
        data.updateWorkingDaysCount();
        calculateInvoice();
        updateOrders();
    }
//--------------------------------------------------------------------------------------------------
    public void addNewEmployee(Employee employee) {
        data.createEmployee(employee);
        data.getAllEmployees();
        //employees.setValue(employees.getValue());
//    getOrders().getValue().add(order);
//    updateOrders();
}

    public void updateEmployee(Employee employee) {
        data.updateEmployee(employee);
//        data.getAllEmployees();
        employees.setValue(employees.getValue());
//        updateOrders();
    }

    public void deleteEmployee(Employee employee) {
        data.deleteEmployee(employee);
        data.getAllEmployees();
//        employees.setValue(employees.getValue());
//        updateOrders();
    }
//--------------------------------------------------------------------------------------------------
    //todo идея: реешние проблемы отображения обычного работника и объединенного:
    // можно сделать UnitedEmployee extends Employee (который будет собирать в себе несколько
    // работников) и из viewModel передавать каждой локации (в ресайклер) и вариант объединенного и
    // вариант раздельного работника (список), а уже в ресайклере в итеме отображать вариант который
    // выбирается нажатием кнопки выбора режима отображения локации



}
