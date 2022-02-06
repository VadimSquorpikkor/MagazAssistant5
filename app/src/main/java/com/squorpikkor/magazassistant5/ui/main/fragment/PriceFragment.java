package com.squorpikkor.magazassistant5.ui.main.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.squorpikkor.magazassistant5.R;
import com.squorpikkor.magazassistant5.ui.main.MainViewModel;

/**Фрагмент для отображения цен на сок и кефир*/
public class PriceFragment extends Fragment{

   public static Fragment newInstance() {
      return new PriceFragment();
   }

   @Override
   public View onCreateView(LayoutInflater inflater, ViewGroup container,
                            Bundle savedInstanceState) {
      View view = inflater.inflate(R.layout.fragment_price, container, false);
      MainViewModel mViewModel = new ViewModelProvider(getActivity()).get(MainViewModel.class);

      TextView juiceBigPrice = view.findViewById(R.id.juice_big_price);
      TextView juiceSmallPrice = view.findViewById(R.id.juice_small_price);
      TextView kefirBigPrice = view.findViewById(R.id.kefir_big_price);
      TextView kefirSmallPrice = view.findViewById(R.id.kefir_small_price);

      mViewModel.getJuicePrice().observe(getViewLifecycleOwner(), jbp -> juiceBigPrice.setText(""+jbp));
      mViewModel.getJuiceSmallPrice().observe(getViewLifecycleOwner(), jsp -> juiceSmallPrice.setText(""+jsp));
      mViewModel.getKefirPrice().observe(getViewLifecycleOwner(), kbp -> kefirBigPrice.setText(""+kbp));
      mViewModel.getKefirSmallPrice().observe(getViewLifecycleOwner(), ksp -> kefirSmallPrice.setText(""+ksp));

      return view;
   }
}
