package com.squorpikkor.magazassistant5.ui.main.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.squorpikkor.magazassistant5.R;
import com.squorpikkor.magazassistant5.ui.main.MainViewModel;

public class LocationFragment extends Fragment {

   public static Fragment newInstance() {
      return new LocationFragment();
   }

   private MainViewModel mViewModel;

   @Override
   public View onCreateView(LayoutInflater inflater, ViewGroup container,
                            Bundle savedInstanceState) {
      View view = inflater.inflate(R.layout.fragment_location, container, false);
      mViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);

      return view;
   }
}
