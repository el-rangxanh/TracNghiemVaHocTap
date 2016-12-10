package com.el_rangxanh.test_11.slide;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.el_rangxanh.test_11.MainActivity;
import com.el_rangxanh.test_11.R;
import com.el_rangxanh.test_11.cauhoi.CauHoi;

import java.util.ArrayList;

public class XemDiemActivity extends AppCompatActivity {
    ArrayList<CauHoi> arr_Cauhoi = new ArrayList<CauHoi>();
    int socaudung = 0, socausai = 0, socaukolam = 0, tongdiem = 0;
    TextView tvcaudung, tvcausai, tvchualam, tvtongdiem;
    Button btnthoat, btnxemlai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xem_diem);
        Intent intent_diem = getIntent();
        arr_Cauhoi = (ArrayList<CauHoi>) intent_diem.getExtras().getSerializable("arr_cauhoi");
        AnhXa();
        KiemTraCacCauTraLoi();
        tongdiem = socaudung * 1;
        tvcaudung.setText("" + socaudung);
        tvcausai.setText("" + socausai);
        tvchualam.setText("" + socaukolam);
        tvtongdiem.setText("" + tongdiem);
        btnthoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(XemDiemActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        btnxemlai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              finish();
            }
        });
    }

    // anh xa
    public void AnhXa() {
        tvcaudung = (TextView) findViewById(R.id.tvsoCaudung);
        tvcausai = (TextView) findViewById(R.id.tvsoCausai);
        tvchualam = (TextView) findViewById(R.id.tvsocauChualam);
        tvtongdiem = (TextView) findViewById(R.id.tvsoTongdiem);
        btnthoat = (Button) findViewById(R.id.btnThoat);
        btnxemlai = (Button) findViewById(R.id.btnXemlai);
    }


    // Kiem tra cac cau tra loi
    public void KiemTraCacCauTraLoi() {
        for (int i = 0; i < arr_Cauhoi.size(); i++) {
            if (arr_Cauhoi.get(i).getTraloi().equals(arr_Cauhoi.get(i).getKetqua()) == true) {
                socaudung++;
            } else if (arr_Cauhoi.get(i).getTraloi().equals("") == true) {
                socaukolam++;
            } else socausai++;
        }
    }
}

