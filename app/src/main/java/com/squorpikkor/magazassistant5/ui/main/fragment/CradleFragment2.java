package com.squorpikkor.magazassistant5.ui.main.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.squorpikkor.magazassistant5.R;

public class CradleFragment2 extends Fragment {

    public static Fragment newInstance() {
        return new CradleFragment2();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cradle_2, container, false);
        getChildFragmentManager().beginTransaction().replace(R.id.fragment_order_head, OrdersHead.newInstance()).commit();
        getChildFragmentManager().beginTransaction().replace(R.id.fragment_order_body, OrderFragment.newInstance()).commit();
        return view;
    }

}
