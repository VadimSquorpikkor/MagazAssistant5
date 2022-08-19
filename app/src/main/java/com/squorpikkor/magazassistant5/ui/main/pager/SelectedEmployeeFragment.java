package com.squorpikkor.magazassistant5.ui.main.pager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.squorpikkor.magazassistant5.R;
import com.squorpikkor.magazassistant5.ui.main.MainViewModel;
import com.squorpikkor.magazassistant5.ui.main.entities.Employee;

public class SelectedEmployeeFragment extends Fragment {

    private static final String POSITION = "position";

    public static SelectedEmployeeFragment newInstance(int position) {
        SelectedEmployeeFragment fragment = new SelectedEmployeeFragment();
        Bundle args = new Bundle();
        args.putInt(POSITION, position);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        int position;
        if (getArguments() != null) {
            position = getArguments().getInt(POSITION);
        } else {
            position = 0;
        }

        View view = inflater.inflate(R.layout.fragment_dose_info, container, false);

        MainViewModel data = MainViewModel.getInstance();
        Employee employee = data.getPresentEmployeesByAllLocationsWithUnited().get(position);

        TextView nameText = view.findViewById(R.id.name);
        nameText.setText(employee.getName());

        return view;
    }

}