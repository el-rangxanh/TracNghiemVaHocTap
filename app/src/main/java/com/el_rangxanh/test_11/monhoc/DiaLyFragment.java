package com.el_rangxanh.test_11.monhoc;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.el_rangxanh.test_11.MainActivity;
import com.el_rangxanh.test_11.R;
import com.el_rangxanh.test_11.slide.ScreenSlideActivity;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class DiaLyFragment extends Fragment {

    ExamAdapter examAdapter;
    ListView lv_dethi;
    ArrayList<Exam> arr_exam = new ArrayList<Exam>();

    public DiaLyFragment() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ((MainActivity) getActivity()).getSupportActionBar().setTitle("Địa Lý");
        return inflater.inflate(R.layout.fragment_toan, container, false);
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        lv_dethi = (ListView) getActivity().findViewById(R.id.lvdethi);
        arr_exam.add(new Exam("Đề số 1"));
        arr_exam.add(new Exam("Đề số 2"));
        arr_exam.add(new Exam("Đề số 3"));
        arr_exam.add(new Exam("Đề số 4"));
        arr_exam.add(new Exam("Đề số 5"));
        arr_exam.add(new Exam("Đề số 6"));
        arr_exam.add(new Exam("Đề số 7"));

        examAdapter=new ExamAdapter(getContext(),arr_exam);
        lv_dethi.setAdapter(examAdapter);

        lv_dethi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i=new Intent(getActivity(), ScreenSlideActivity.class);
                startActivity(i);
            }
        });
    }


}
