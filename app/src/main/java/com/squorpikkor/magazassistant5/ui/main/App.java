package com.squorpikkor.magazassistant5.ui.main;

import android.app.Application;
import android.content.Context;

public class App extends Application {
   private static Application mApplication;

   public static final String TAG = "tag";

   @Override
   public void onCreate() {
      super.onCreate();
      mApplication = this;
   }

   public static Context getContext(){
      return mApplication.getApplicationContext();
   }
}
