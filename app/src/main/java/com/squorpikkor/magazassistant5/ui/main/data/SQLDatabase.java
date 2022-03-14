package com.squorpikkor.magazassistant5.ui.main.data;

import static com.squorpikkor.magazassistant5.ui.main.App.TAG;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.squorpikkor.magazassistant5.ui.main.App;
import com.squorpikkor.magazassistant5.ui.main.entities.Employee;
import com.squorpikkor.magazassistant5.ui.main.entities.Location;
import com.squorpikkor.magazassistant5.ui.main.entities.Order;

import java.util.ArrayList;

class SQLDatabase extends SQLiteOpenHelper implements Data{

   private static final int DATABASE_VERSION = 1;
   private static final String DATABASE_NAME = "magaz_db";
//--------------------------------------------------------------------------------------------------
   private static final String TABLE_EMPLOYEES = "customers_table";
   private static final String COLUMN_EMPLOYEE_ID = "id";
   private static final String COLUMN_EMPLOYEE_NAME = "name";
   private static final String COLUMN_EMPLOYEE_DAYS = "surname";
   private static final String COLUMN_EMPLOYEE_ISPRESENT = "onduty";
   private static final String COLUMN_EMPLOYEE_LOCATION = "department";
//--------------------------------------------------------------------------------------------------
   private static final String TABLE_LOCATIONS = "department_table";
   private static final String COLUMN_LOCATION_ID = "id";
   private static final String COLUMN_LOCATION_NAME = "name";
   private static final String COLUMN_LOCATION_ISUNITED = "koef";//juice per week for each customer in current dep
//--------------------------------------------------------------------------------------------------
   private static final String TABLE_ORDERS = "products_table";
   private static final String COLUMN_ORDER_ID = "id";
   private static final String COLUMN_ORDER_NAME = "title";
   private static final String COLUMN_ORDER_PRICE = "price";
   private static final String COLUMN_ORDER_COUNT = "quantity";
   private static final String COLUMN_ORDER_ISCHECKED = "purchased";
   private static final String COLUMN_ORDER_EMPLOYEE = "customer";

   public SQLDatabase() {
      super(App.getContext(), DATABASE_NAME, null, DATABASE_VERSION);
   }

   //boolean нет в SQLite, вместо него использую int 0 или 1

   @Override
   public void onCreate(SQLiteDatabase db) {

      //String id, String name, boolean isUnited
      db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_LOCATIONS + "("
              + COLUMN_LOCATION_ID + " INTEGER PRIMARY KEY,"
              + COLUMN_LOCATION_NAME + " TEXT, "
              + COLUMN_LOCATION_ISUNITED + " INTEGER"
              + ")"
      );
      Log.e(TAG, "onCreate: " + "table locations created");

      //String id, String name, String locationId, String days, boolean isPresent
      db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_EMPLOYEES + "("
              + COLUMN_EMPLOYEE_ID + " INTEGER PRIMARY KEY,"
              + COLUMN_EMPLOYEE_NAME + " TEXT, "
              + COLUMN_EMPLOYEE_DAYS + " TEXT,"
              + COLUMN_EMPLOYEE_ISPRESENT + " INTEGER," //нет boolean в sqlite, использую 0 и 1
              + COLUMN_EMPLOYEE_LOCATION + " INTEGER"
              + ")"
      );
      Log.e(TAG, "onCreate: " + "table employees created");

      //String id, String name, int price, int count, String employeeId, boolean isChecked
      db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_ORDERS + "("
              + COLUMN_ORDER_ID + " INTEGER PRIMARY KEY,"
              + COLUMN_ORDER_NAME + " TEXT,"
              + COLUMN_ORDER_PRICE + " INTEGER,"
              + COLUMN_ORDER_COUNT + " INTEGER,"
              + COLUMN_ORDER_ISCHECKED + " INTEGER,"
              + COLUMN_ORDER_EMPLOYEE + " INTEGER"
              + ")"
      );
      Log.e(TAG, "onCreate: " + "table orders created");
   }

   @Override
   public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
      db.execSQL("DROP TABLE IF EXISTS " + TABLE_LOCATIONS);
      onCreate(db);

      db.execSQL("DROP TABLE IF EXISTS " + TABLE_EMPLOYEES);
      onCreate(db);

      db.execSQL("DROP TABLE IF EXISTS " + TABLE_ORDERS);
      onCreate(db);
   }

//--------------------------------------------------------------------------------------------------

   private Cursor cursor;
   private void setCursor(String query) {
      SQLiteDatabase db = this.getWritableDatabase();
      cursor = db.rawQuery(query, null);
   }

   private int cursorGetInt(int col) {
      return Integer.parseInt(cursor.getString(col));
   }

   private String cursorGetString(int col) {
      return cursor.getString(col);
   }

   private boolean cursorGetBoolean(int col){
      return cursor.getString(col).equals("1");
   }

   private void selectAllFrom(String table) {
      setCursor("SELECT * FROM " + table);
   }

//--------------------------------------------------------------------------------------------------

   @Override
   public void updateEmployee(Employee employee) {
      SQLiteDatabase db = this.getWritableDatabase();

      ContentValues values = new ContentValues();
      values.put(COLUMN_EMPLOYEE_ID       , employee.getId());
      values.put(COLUMN_EMPLOYEE_NAME     , employee.getName());
      values.put(COLUMN_EMPLOYEE_DAYS     , employee.getWorkingDaysArray());
      values.put(COLUMN_EMPLOYEE_ISPRESENT, employee.isPresent()?1:0);
      values.put(COLUMN_EMPLOYEE_LOCATION , employee.getLocationId());

      //update возвращает int, можно отслеживать удалось записать или нет. Пока использую void
      db.update(TABLE_EMPLOYEES, values, COLUMN_EMPLOYEE_ID + " = ?",
              new String[]{String.valueOf(employee.getId())});
   }

   @Override
   public void addEmployee(Employee employee) {

   }

   @Override
   public void getAllEmployees(MutableLiveData<ArrayList<Employee>> employees) {
      ArrayList<Employee> list = new ArrayList<>();
      selectAllFrom(TABLE_EMPLOYEES);
      if (cursor.moveToFirst()) {
         do {
            int id = cursorGetInt(0);
            String name = cursorGetString(1);
            String days = cursorGetString(2);
            boolean isPresent = cursorGetBoolean(3);
            int location = cursorGetInt(4);
            Employee employee = new Employee(id, name, days, isPresent, location);
            list.add(employee);
         } while (cursor.moveToNext());
      }
      cursor.close();
      employees.setValue(list);
   }

//--------------------------------------------------------------------------------------------------

   @Override
   public void updateLocation(Location location) {

   }

   @Override
   public void addLocation(Location location) {

   }

   @Override
   public void updateOrder(Order order) {

   }

//--------------------------------------------------------------------------------------------------

   @Override
   public void addOrder(Order order) {

   }

   @Override
   public void getAllLocations(MutableLiveData<ArrayList<Location>> locations) {

   }



   @Override
   public void getAllOrders(MutableLiveData<ArrayList<Order>> orders) {

   }

//--------------------------------------------------------------------------------------------------

   @Override
   public ArrayList<Employee> getEmployeesByLocation(Location location) {
      return null;
   }

   @Override
   public ArrayList<Order> getOrderByEmployee(Employee employee) {
      return null;
   }

   @Override
   public void loadPrices(MutableLiveData<Integer> juicePrice, MutableLiveData<Integer> juiceSmallPrice, MutableLiveData<Integer> kefirPrice, MutableLiveData<Integer> kefirSmallPrice) {

   }

   @Override
   public void savePrices(int juicePrice, int juiceSmallPrice, int kefirPrice, int kefirSmallPrice) {

   }

}
