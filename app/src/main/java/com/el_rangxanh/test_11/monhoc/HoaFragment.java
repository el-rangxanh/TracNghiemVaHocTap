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
public class HoaFragment extends Fragment {

    ExamAdapter examAdapter;
    android.widget.ListView lv_dethi;
    java.util.ArrayList<com.el_rangxanh.test_11.monhoc.Exam> arr_exam = new java.util.ArrayList<com.el_rangxanh.test_11.monhoc.Exam>();

    public HoaFragment() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ((MainActivity) getActivity()).getSupportActionBar().setTitle("Hoá Học");
        return inflater.inflate(R.layout.fragment_toan, container, false);
    }
    @Override
    public void onActivityCreated(@android.support.annotation.Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        lv_dethi = (android.widget.ListView) getActivity().findViewById(R.id.lvdethi);
        arr_exam.add(new Exam("Đề số 1"));
        arr_exam.add(new Exam("Đề số 2"));
        arr_exam.add(new Exam("Đề số 3"));
        arr_exam.add(new Exam("Đề số 4"));
        arr_exam.add(new Exam("Đề số 5"));
        arr_exam.add(new Exam("Đề số 6"));
        arr_exam.add(new Exam("Đề số 7"));

        examAdapter=new ExamAdapter(getContext(),arr_exam);
        lv_dethi.setAdapter(examAdapter);

        lv_dethi.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(android.widget.AdapterView<?> parent, View view, int position, long id) {
                android.content.Intent i=new android.content.Intent(getActivity(), com.el_rangxanh.test_11.slide.ScreenSlideActivity.class);
                startActivity(i);
            }
        });
    }


}
