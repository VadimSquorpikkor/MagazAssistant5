package com.squorpikkor.magazassistant5.ui.main.data;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.squorpikkor.magazassistant5.ui.main.App;

import java.util.ArrayList;

public class SaveLoad {

   static SharedPreferences mPrefManager = PreferenceManager.getDefaultSharedPreferences(App.getContext());
   /**Сохранение по ключу*/
   public static void save(String key, int param) {
      mPrefManager.edit().putInt(key, param).apply();
   }
   public static void save(String key, float param) {
      mPrefManager.edit().putFloat(key, param).apply();
   }
   public static void save(String key, boolean param) {
      mPrefManager.edit().putBoolean(key, param).apply();
   }
   public static void save(String key, String param) {
      mPrefManager.edit().putString(key, param).apply();
   }
   /**Загрузка String по ключу*/
   public static String loadString(String key) {
      if (mPrefManager.contains(key)) return mPrefManager.getString(key, "");
      return "";
   }
   /**Загрузка int по ключу*/
   public static int loadInt(String key) {
      if (mPrefManager.contains(key)) return mPrefManager.getInt(key, 0);
      return 0;
   }
   /**Загрузка float по ключу*/
   public static float loadFloat(String key) {
      if (mPrefManager.contains(key)) return mPrefManager.getFloat(key, 0);
      return 0;
   }
   /**Загрузка boolean по ключу*/
   public static boolean loadBoolean(String key) {
      if (mPrefManager.contains(key)) return mPrefManager.getBoolean(key, false);
      return false;
   }

   /**Загрузка настройки boolean, заданной через preferenceActivity, по ключу из resId*/
   public static boolean getPrefBoolean(int resId) {
      String key = App.getContext().getString(resId);
      if (mPrefManager.contains(key)) return mPrefManager.getBoolean(key, false);
      return false;
   }

   public static boolean getPrefBoolean(String key) {
      if (mPrefManager.contains(key)) return mPrefManager.getBoolean(key, false);
      return false;
   }

   public static boolean getPrefBoolean(String key, boolean defValue) {
      if (mPrefManager.contains(key)) return mPrefManager.getBoolean(key, defValue);
      return defValue;
   }

   /**Загрузка настройки int, заданной через preferenceActivity, по ключу из resId*/
   public static int getPrefInt(int resId) {
      String key = App.getContext().getString(resId);
      if (mPrefManager.contains(key)) return Integer.parseInt(mPrefManager.getString(key, "0"));
      return 0;
   }
   public static int getPrefInt(String key) {
//не нужно, всё равно в prefFragment можно только через @string/key_name имя задать, т.е. через int
      if (mPrefManager.contains(key)) return Integer.parseInt(mPrefManager.getString(key, "0"));
      return 0;
   }
   /**Загрузка настройки int, заданной через preferenceActivity, по ключу из resId*/
   public static int getPrefInt(int resId, int defValue) {
      String key = App.getContext().getString(resId);
      if (mPrefManager.contains(key)) return Integer.parseInt(mPrefManager.getString(key, String.valueOf(defValue)));
      return defValue;
   }
   public static int getPrefInt(String key, int defValue) {
//не нужно, всё равно в prefFragment можно только через @string/key_name имя задать, т.е. через int
      if (mPrefManager.contains(key)) return Integer.parseInt(mPrefManager.getString(key, String.valueOf(defValue)));
      return defValue;
   }
   /**Загрузка настройки String, заданной через preferenceActivity, по ключу из resId*/
   public static String getPrefString(int resId) {
      String key = App.getContext().getString(resId);
      if (mPrefManager.contains(key)) return mPrefManager.getString(key, "");
      return "";
   }
   /**Загрузка float по ключу. Задать значение по умолчанию*/
   public static float loadFloat(String key, float defValue) {
      if (mPrefManager.contains(key)) return mPrefManager.getFloat(key, defValue);
      return defValue;
   }
   /**Сохранение массива по ключу*/
   public static void saveArray(String key, ArrayList<String> list) {
      SharedPreferences.Editor editor = mPrefManager.edit();
      //очистить. если не очищать, то в случае, когда размер массива меньше сохраненного ранее, будет оставаться "хвост" предыдущего массива
      int count = 0;
      while (mPrefManager.contains(key + count)) {
         editor.remove(key + count);
         count++;
      }
      for (int i = 0; i < list.size(); i++) {
         editor.putString(key + i, list.get(i));
      }
      editor.apply();
   }
   /**Загрузка ArrayList<String> по ключу*/
   public static ArrayList<String> loadStringArray(String key) {
      ArrayList<String> list = new ArrayList<>();
      int count = 0;
      while (mPrefManager.contains(key + count)) {
         String s = mPrefManager.getString(key + count, "");
         list.add(s);
         count++;
      }
      return list;
   }
}
