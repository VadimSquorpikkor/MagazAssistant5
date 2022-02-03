package com.squorpikkor.magazassistant5.ui.main.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squorpikkor.magazassistant5.R;
import com.squorpikkor.magazassistant5.ui.main.entities.Employee;

import java.util.ArrayList;

public class InvoiceEmployeeAdapter extends RecyclerView.Adapter<InvoiceEmployeeAdapter.AdapterViewHolder> {

   private ArrayList<Employee> list;

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

   }

   @Override
   public int getItemCount() {
      return list.size();
   }

   public class AdapterViewHolder extends RecyclerView.ViewHolder {

      TextView name;

      public AdapterViewHolder(@NonNull View itemView) {
         super(itemView);
         name = itemView.findViewById(R.id.text_name);
      }
   }
}
