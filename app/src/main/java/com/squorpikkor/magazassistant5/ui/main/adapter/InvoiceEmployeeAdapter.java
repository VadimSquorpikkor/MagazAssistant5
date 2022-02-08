package com.squorpikkor.magazassistant5.ui.main.adapter;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squorpikkor.magazassistant5.R;
import com.squorpikkor.magazassistant5.ui.main.MainViewModel;
import com.squorpikkor.magazassistant5.ui.main.entities.Employee;

import java.util.ArrayList;

public class InvoiceEmployeeAdapter extends RecyclerView.Adapter<InvoiceEmployeeAdapter.AdapterViewHolder> {

   private ArrayList<Employee> list;
   private int workingDaysCount = 5;
   private MainViewModel mViewModel;

   @SuppressLint("NotifyDataSetChanged")
   public void setWorkingDaysCount(int workingDaysCount) {
      this.workingDaysCount = workingDaysCount;
      notifyDataSetChanged();
   }

   //   public InvoiceEmployeeAdapter(MainViewModel mViewModel) {
//      this.mViewModel = mViewModel;
//      mViewModel.getWorkingDays().observe();
//   }
//
//
//   public InvoiceEmployeeAdapter(int workingDaysCount) {
//      this.workingDaysCount = workingDaysCount;
//   }

   @SuppressLint("NotifyDataSetChanged")
   public void setList(ArrayList<Employee> list) {
      if (list==null) list = new ArrayList<>();
      this.list = list;
      notifyDataSetChanged();
   }

   @NonNull
   @Override
   public AdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_invoice_employee, parent, false);
      return new AdapterViewHolder(view);
   }

   @Override
   public void onBindViewHolder(@NonNull AdapterViewHolder holder, int position) {
      Employee employee = list.get(position);
      holder.name.setText(employee.getName());
      Log.e("TAG", "holder.checks.length: "+holder.checks.length);
      int workingDayForEmployee = 0;
      for (int i = 0; i < holder.checks.length; i++) {
         //перебираем все 9 чекбоксов
         holder.checks[i].setVisibility(workingDaysCount>i?View.VISIBLE:View.GONE);//оставляем количество чекбоксов равное количеству рабочих дней
         if (employee.getDaysString().length()<i) holder.checks[i].setChecked(true);//если чекбоксов больше, чем сохраненных то все чекбоксы сверх сохраненных чекаем

         Log.e("TAG", "*** employee.getDaysString().length(): "+employee.getDaysString().length());
         if (employee.getDaysString().length()>i
         && employee.getDaysString().charAt(i)=='1') {
            holder.checks[i].setChecked(true);//1 - это чекед
            workingDayForEmployee++;
         } else holder.checks[i].setChecked(false);//иначе - анчекед
      }
      employee.setDays(workingDayForEmployee);//ХЗ

   }

   @Override
   public int getItemCount() {
      return list.size();
   }

   public class AdapterViewHolder extends RecyclerView.ViewHolder {

      TextView name;
      CheckBox[] checks;

      public AdapterViewHolder(@NonNull View itemView) {
         super(itemView);
         name = itemView.findViewById(R.id.text_name);
         checks = new CheckBox[]{itemView.findViewById(R.id.check1),
                 itemView.findViewById(R.id.check2),
                 itemView.findViewById(R.id.check3),
                 itemView.findViewById(R.id.check4),
                 itemView.findViewById(R.id.check5),
                 itemView.findViewById(R.id.check6),
                 itemView.findViewById(R.id.check7),
                 itemView.findViewById(R.id.check8),
                 itemView.findViewById(R.id.check9)};
      }
   }
}
