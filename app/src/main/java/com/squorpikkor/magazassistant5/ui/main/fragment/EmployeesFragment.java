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
import com.squorpikkor.magazassistant5.ui.main.entities.Employee;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class EmployeesFragment extends Fragment{

   public static Fragment newInstance() {
      return new EmployeesFragment();
   }

   private MainViewModel mViewModel;
   InvoiceEmployeeAdapter adapter;

   @Override
   public View onCreateView(LayoutInflater inflater, ViewGroup container,
                            Bundle savedInstanceState) {
      View view = inflater.inflate(R.layout.fragment_employees, container, false);
      mViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);

      RecyclerView recyclerView = view.findViewById(R.id.recycler);
      adapter = new InvoiceEmployeeAdapter(mViewModel);
      recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
      recyclerView.setAdapter(adapter);
      mViewModel.getEmployees().observe(getViewLifecycleOwner(), list1 -> setListAndSort(list1));
      mViewModel.getWorkingDays().observe(getViewLifecycleOwner(), list ->
              setListAndSort(mViewModel.getEmployees().getValue()));

      return view;
   }

   private void setListAndSort(ArrayList<Employee> list) {
//      list.sort(Comparator.comparing(Employee::getLocationId).thenComparing(Employee::getName));//нужен sdk24, но гораздо компактнее и понятнее

      Collections.sort(list, new Comparator() {
         public int compare(Object o1, Object o2) {
            Integer x1 = ((Employee) o1).getLocationId();
            Integer x2 = ((Employee) o2).getLocationId();
            int sComp = x1.compareTo(x2);

            if (sComp != 0) {
               return sComp;
            }

            String x3 = ((Employee) o1).getName();
            String x4 = ((Employee) o2).getName();
            return x3.compareTo(x4);
         }});
      adapter.setList(list);
   }
}
