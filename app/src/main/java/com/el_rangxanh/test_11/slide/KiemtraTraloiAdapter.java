package com.el_rangxanh.test_11.slide;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.el_rangxanh.test_11.R;
import com.el_rangxanh.test_11.cauhoi.CauHoi;
import java.util.ArrayList;


public class KiemtraTraloiAdapter extends BaseAdapter {

    ArrayList listData;
    LayoutInflater inflater;

    public KiemtraTraloiAdapter(ArrayList listData, Context context) {
        this.listData = listData;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CauHoi data = (CauHoi) getItem(position);
        ViewHolder holder;
        if (convertView == null) {
            holder = new KiemtraTraloiAdapter.ViewHolder();
            convertView = inflater.inflate(R.layout.item_gridview_traloi, null);
            holder.tvcauhoi = (TextView) convertView.findViewById(R.id.tvcauhoi);
            holder.tvtraloi = (TextView) convertView.findViewById(R.id.tvtraloi);
            convertView.setTag(holder);
        }else {
            holder=(ViewHolder)convertView.getTag();
        }
        int i=position+1;
        holder.tvcauhoi.setText( "Câu "+i+":");
        holder.tvtraloi.setText(data.getTraloi());
        return convertView;
    }

    private static class ViewHolder {
        TextView tvcauhoi, tvtraloi;
    }
}
