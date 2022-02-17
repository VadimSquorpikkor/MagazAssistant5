package com.squorpikkor.magazassistant5.ui.main.utils;

import com.squorpikkor.magazassistant5.ui.main.entities.Order;

import java.util.ArrayList;

public class Utils {

   /**Переводит: 456 -> 4.56*/
   public static String integerToMoneyString(int money) {
      if (money==0) return "0.00";
      if (money<0) return "-"+integerToMoneyString(-money);
      int rub = money/100;
      int kop = money%100;
      String kopS = kop<10?"0"+kop:""+kop;
      return ""+rub+". "+kopS;
   }

   public static String getOrdersStatusString(ArrayList<Order> orders) {
      int checked = 0;
      for (Order order:orders) {
         if (order.isChecked()) checked++;
      }
      int all = orders.size();
      return ""+checked+"/"+all;
   }

   public static String calculateMoneyLeftByOrders(ArrayList<Order> orders, int moneyLimit) {
      return integerToMoneyString(calculateMoneyLeftByOrdersInt(orders, moneyLimit));
   }

   public static int calculateMoneyLeftByOrdersInt(ArrayList<Order> orders, int moneyLimit) {
      int moneySpent = 0;
      for (Order order:orders) {
         if (order.isChecked()) moneySpent+= order.getPrice()* order.getCount();
      }
      return moneyLimit-moneySpent;
   }

   public static boolean allOrdersIsComplete(String s) {
      if (s.equals("0/0")) return false;
      String[] arr = s.split("/");
      return arr[0].equals(arr[1]);
   }

}
