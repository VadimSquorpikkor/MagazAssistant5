package com.squorpikkor.magazassistant5.ui.main.dialog;

import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.FragmentManager;

import com.squorpikkor.magazassistant5.R;
import com.squorpikkor.magazassistant5.ui.main.entities.Employee;
import com.squorpikkor.magazassistant5.ui.main.entities.Order;

public class OrderControlDialog extends BaseDialog{

   Order order;
   Employee employee;
   FragmentManager manager;

   public OrderControlDialog(Order order, Employee employee, FragmentManager manager) {
      this.order = order;
      this.employee = employee;
      this.manager = manager;
   }

   @Override
   public Dialog onCreateDialog(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      initializeWithVM(R.layout.dialog_order_bla);

      view.findViewById(R.id.add_order).setOnClickListener(v->addOrder());
      view.findViewById(R.id.delete_order).setOnClickListener(v->deleteOrder());
      view.findViewById(R.id.open_employee).setOnClickListener(v->openInfo());

      return dialog;
   }

   private void openInfo() {
      new EmployeeDialog(employee).show(manager, null);
      dismiss();
   }

   private void deleteOrder() {
      mViewModel.deleteOrder(order);
      dismiss();
   }

   private void addOrder() {
      new OrderDialog(employee).show(manager, null);
      dismiss();
   }

}
