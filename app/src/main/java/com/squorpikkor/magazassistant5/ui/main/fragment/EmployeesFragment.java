package com.squorpikkor.magazassistant5.ui.main.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.squorpikkor.magazassistant5.R;
import com.squorpikkor.magazassistant5.ui.main.MainViewModel;
import com.squorpikkor.magazassistant5.ui.main.adapter.InvoiceEmployeeAdapter;
import com.squorpikkor.magazassistant5.ui.main.dialog.AskResetShopping;
import com.squorpikkor.magazassistant5.ui.main.dialog.EmployeeDialog;
import com.squorpikkor.magazassistant5.ui.main.pager.InfoActivity;

public class EmployeesFragment extends Fragment{

   public static Fragment newInstance() {
      return new EmployeesFragment();
   }

   private MainViewModel mViewModel;

   @Override
   public View onCreateView(LayoutInflater inflater, ViewGroup container,
                            Bundle savedInstanceState) {
      View view = inflater.inflate(R.layout.fragment_employees, container, false);
      mViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);

      RecyclerView recyclerView = view.findViewById(R.id.recycler);
      InvoiceEmployeeAdapter adapter = new InvoiceEmployeeAdapter(mViewModel);
      recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
      recyclerView.setAdapter(adapter);
      mViewModel.getEmployees().observe(getViewLifecycleOwner(), adapter::setList);
      mViewModel.getWorkingDays().observe(getViewLifecycleOwner(), list ->
              adapter.setList(mViewModel.getEmployees().getValue()));

      view.findViewById(R.id.add_employee).setOnClickListener(v->
              new EmployeeDialog().show(getActivity().getSupportFragmentManager(), null));

      view.findViewById(R.id.reset_shopping).setOnClickListener(v->
              new AskResetShopping().show(getParentFragmentManager(), null));

      return view;
   }

   //todo !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
//   private void openInfo(int position) {
//      Intent intent = new Intent(context, InfoActivity.class);
//      intent.putExtra(EXTRA_POSITION, position);
//      context.startActivity(intent);
//   }
}
