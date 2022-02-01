package com.squorpikkor.magazassistant5.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.squorpikkor.magazassistant5.R;
import com.squorpikkor.magazassistant5.ui.main.adapter.LocationAdapter;

public class OrderFragment  extends Fragment {


   public static Fragment newInstance() {
      return new OrderFragment();
   }

   @Override
   public View onCreateView(LayoutInflater inflater, ViewGroup container,
                            Bundle savedInstanceState) {
      View view = inflater.inflate(R.layout.fragment_order, container, false);
      MainViewModel mViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);

      RecyclerView locationRecyclerView = view.findViewById(R.id.recycler_locations);
      LocationAdapter locationAdapter = new LocationAdapter();
      locationRecyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
      locationRecyclerView.setAdapter(locationAdapter);
      //locationAdapter.setOnDeviceClickListener(mViewModel::connectToDevice);
      mViewModel.getLocations().observe(getViewLifecycleOwner(), locationAdapter::setList);

      return view;
   }

}
