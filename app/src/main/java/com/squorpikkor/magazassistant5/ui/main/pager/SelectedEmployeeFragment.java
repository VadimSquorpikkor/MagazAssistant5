package com.squorpikkor.magazassistant5.ui.main.pager;

import static com.squorpikkor.magazassistant5.ui.main.App.getContext;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.squorpikkor.magazassistant5.R;
import com.squorpikkor.magazassistant5.ui.main.MainViewModel;
import com.squorpikkor.magazassistant5.ui.main.adapter.CheckBoxSwitcher;
import com.squorpikkor.magazassistant5.ui.main.adapter.OrderAdapter;
import com.squorpikkor.magazassistant5.ui.main.dialog.OrderControlDialog;
import com.squorpikkor.magazassistant5.ui.main.dialog.OrderDialog;
import com.squorpikkor.magazassistant5.ui.main.entities.Employee;
import com.squorpikkor.magazassistant5.ui.main.entities.Order;
import com.squorpikkor.magazassistant5.ui.main.utils.Utils;

import java.util.ArrayList;

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
        TextView pageNumText = view.findViewById(R.id.page_num);
        TextView moneyLeftText = view.findViewById(R.id.money_left);

        nameText.setText(employee.getName());
        pageNumText.setText((position+1)+"/"+data.getPresentEmployeesByAllLocationsWithUnited().size());

        ArrayList<Order> orders = data.getOrdersByEmployee(employee);
        int moneyLimit = data.getMoneyLimit(employee);
        moneyLeftText.setText(Utils.calculateMoneyLeftByOrders(orders, moneyLimit));

//        data.getOrders().observe(getViewLifecycleOwner(), new Observer<ArrayList<Order>>() {
//            @Override
//            public void onChanged(ArrayList<Order> orders) {
//                int moneyLimit = data.getMoneyLimit(employee);
//                moneyLeftText.setText(Utils.calculateMoneyLeftByOrders(orders, moneyLimit));
//            }
//        });



        CheckBox[] checks;
//        CheckBox mainCheck;

//        mainCheck = view.findViewById(R.id.main_check);
        checks = new CheckBox[]{
                view.findViewById(R.id.check1),
                view.findViewById(R.id.check2),
                view.findViewById(R.id.check3),
                view.findViewById(R.id.check4),
                view.findViewById(R.id.check5),
                view.findViewById(R.id.check6),
                view.findViewById(R.id.check7),
                view.findViewById(R.id.check8),
                view.findViewById(R.id.check9)};

        CheckBoxSwitcher.setCheckboxesByEmployee(checks, employee, data.getWorkingDays().getValue());
//        mainCheck.setChecked(employee.isPresent());


        for (CheckBox check : checks) {
            check.setOnClickListener(v -> {
                CheckBoxSwitcher.setDaysByCheckboxes(checks, employee);
                data.updateEmployee(employee);
                data.update();
                data.getEmployees().setValue(data.getEmployees().getValue());//чтобы заапдейтился список работников в "Накладной" фрагменте
            });
        }

        RecyclerView recyclerView = view.findViewById(R.id.recycler);
        OrderAdapter adapter = new OrderAdapter(data);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
        recyclerView.setAdapter(adapter);
        adapter.setList(orders);
//        adapter.setOnItemClickListener(order -> new OrderDialog(order, employee).show(getParentFragmentManager(), null));

        return view;
    }

}