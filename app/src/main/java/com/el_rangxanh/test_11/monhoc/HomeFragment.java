package com.el_rangxanh.test_11.monhoc;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.el_rangxanh.test_11.MainActivity;
import com.el_rangxanh.test_11.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    public HomeFragment() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((MainActivity)getActivity()).getSupportActionBar().setTitle("Home");
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

}
