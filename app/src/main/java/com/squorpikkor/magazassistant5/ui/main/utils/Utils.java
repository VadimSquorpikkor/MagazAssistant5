package com.squorpikkor.magazassistant5.ui.main.utils;

public class Utils {

   /**Переводит: 456 -> 4.56*/
   public static String integerToMoneyString(int money) {
      int rub = money/100;
      int kop = money%100;
      String kopS = kop<10?"0"+kop:""+kop;
//      return ""+rub+"р. "+kopS+"коп.";
      return ""+rub+". "+kopS;
   }

}
