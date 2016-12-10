package com.el_rangxanh.test_11.monhoc;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.el_rangxanh.test_11.R;
import java.util.ArrayList;

public class ExamAdapter extends ArrayAdapter<Exam> {
    public ExamAdapter(Context context, ArrayList<Exam> exam) {
        super(context,0,exam);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            LayoutInflater inflater= LayoutInflater.from(getContext());
            convertView=inflater.inflate(R.layout.item_listview,parent,false);
        }
        TextView tvName=(TextView) convertView.findViewById(R.id.txtvdethi);
        ImageView imgIcon=(ImageView) convertView.findViewById(R.id.imgdethi);
        Exam e=getItem(position);
        if(e!=null){
            tvName.setText(""+e.getName());
            imgIcon.setImageResource(R.drawable.exam2);
        }
        return convertView;
    }
}
