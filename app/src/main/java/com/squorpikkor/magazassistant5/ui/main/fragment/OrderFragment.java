package com.squorpikkor.magazassistant5.ui.main.fragment;

import static com.squorpikkor.magazassistant5.ui.main.App.TAG;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.squorpikkor.magazassistant5.R;
import com.squorpikkor.magazassistant5.ui.main.MainViewModel;
import com.squorpikkor.magazassistant5.ui.main.adapter.OrderLocationAdapter;
import com.squorpikkor.magazassistant5.ui.main.entities.Location;

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
      OrderLocationAdapter locationAdapter = new OrderLocationAdapter(mViewModel);
      locationRecyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
      locationRecyclerView.setAdapter(locationAdapter);
      locationAdapter.setOnItemClickListener(this::startFragment);
      mViewModel.getLocations().observe(getViewLifecycleOwner(), locationAdapter::setList);

      return view;
   }

   private void startFragment(Location location) {
      Log.e(TAG, "startFragment: "+location.getName());
      requireActivity().getSupportFragmentManager().beginTransaction()
              .replace(R.id.fragment_container, LocationFragment.newInstance())
              .addToBackStack(null)
              .commit();
   }

}
