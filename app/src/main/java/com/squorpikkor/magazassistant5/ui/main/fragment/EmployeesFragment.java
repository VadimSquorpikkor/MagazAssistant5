package com.squorpikkor.magazassistant5.ui.main.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.squorpikkor.magazassistant5.R;
import com.squorpikkor.magazassistant5.ui.main.MainViewModel;
import com.squorpikkor.magazassistant5.ui.main.adapter.InvoiceEmployeeAdapter;

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
      InvoiceEmployeeAdapter adapter = new InvoiceEmployeeAdapter();
      recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
      recyclerView.setAdapter(adapter);
      mViewModel.getEmployees().observe(getViewLifecycleOwner(), adapter::setList);

      return view;
   }

}
