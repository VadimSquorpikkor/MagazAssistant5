package com.squorpikkor.magazassistant5.ui.main.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squorpikkor.magazassistant5.R;
import com.squorpikkor.magazassistant5.ui.main.MainViewModel;
import com.squorpikkor.magazassistant5.ui.main.entities.Employee;
import com.squorpikkor.magazassistant5.ui.main.entities.Location;
import com.squorpikkor.magazassistant5.ui.main.entities.Order;
import com.squorpikkor.magazassistant5.ui.main.utils.Utils;

import java.util.ArrayList;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.AdapterViewHolder>{

   private OnItemClickListener onItemClickListener;

   public interface OnItemClickListener {
      void onItemClick(Order order);
   }

   public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
      this.onItemClickListener = onItemClickListener;
   }

   private ArrayList<Order> list;
   private final MainViewModel mainViewModel;

   public OrderAdapter(MainViewModel mainViewModel) {
      this.mainViewModel = mainViewModel;
   }

   @SuppressLint("NotifyDataSetChanged")
   public void setList(ArrayList<Order> list) {
      if (list == null)
         list = new ArrayList<>();//Если list == null, то в ресайклер будет передан пустой лист
      this.list = list;
      notifyDataSetChanged();
   }

   @NonNull
   @Override
   public AdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_orders, parent, false);
      return new OrderAdapter.AdapterViewHolder(view);
   }

   @Override
   public void onBindViewHolder(@NonNull AdapterViewHolder holder, int position) {
      Order order = list.get(position);
      holder.checkBox.setChecked(order.isChecked());
      holder.textName.setText(order.getName());
      holder.textPrice.setText(Utils.integerToMoneyString(order.getPrice()));
      holder.textCount.setText(""+order.getCount());
   }

   @Override
   public int getItemCount() {
      return list.size();
   }

   public class AdapterViewHolder extends RecyclerView.ViewHolder {

      private final CheckBox checkBox;
      private final TextView textName;
      private final TextView textPrice;
      private final TextView textCount;

      public AdapterViewHolder(@NonNull View itemView) {
         super(itemView);

         checkBox = itemView.findViewById(R.id.check);
         textName = itemView.findViewById(R.id.text_name);
         textPrice = itemView.findViewById(R.id.text_price);
         textCount = itemView.findViewById(R.id.text_count);

         checkBox.setOnClickListener(view -> {
            Order order = list.get(getAdapterPosition());
            order.setChecked(checkBox.isChecked());
            mainViewModel.updateOrders();
         });

         itemView.setOnClickListener(view -> {
            if (onItemClickListener != null)
               onItemClickListener.onItemClick(list.get(getAdapterPosition()));
         });

      }
   }
}
