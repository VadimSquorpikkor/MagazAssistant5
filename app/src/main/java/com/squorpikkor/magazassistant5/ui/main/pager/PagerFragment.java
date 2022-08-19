package com.squorpikkor.magazassistant5.ui.main.pager;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squorpikkor.magazassistant5.R;
import com.squorpikkor.magazassistant5.ui.main.MainViewModel;

public class PagerFragment extends Fragment {

    private static final String POSITION = "position";

    // Required empty public constructor
    public PagerFragment() {
    }

    public static PagerFragment newInstance(int position) {
        PagerFragment pageFragment = new PagerFragment();
        Bundle arguments = new Bundle();
        arguments.putInt(POSITION, position);
        pageFragment.setArguments(arguments);
        return pageFragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        int position;
        if (getArguments() != null) position = getArguments().getInt(POSITION);
        else position = 0;

        View view = inflater.inflate(R.layout.fragment_pager, container, false);
        MainViewModel data = MainViewModel.getInstance();
        ViewPager pager = view.findViewById(R.id.pager);
        pager.setAdapter(new Pager(getChildFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, data.getPresentEmployeesByAllLocationsWithUnited().size()));
        pager.setCurrentItem(position);

        return view;
    }
}