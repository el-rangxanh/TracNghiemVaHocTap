package com.el_rangxanh.test_11.slide;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.el_rangxanh.test_11.R;
import com.el_rangxanh.test_11.cauhoi.CauHoi;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ScreenSlidePageFragment extends Fragment {
    ArrayList<CauHoi> arr_cauhoi;
    public static final String DU_LIEU_TRANG = "page";  //key
    public static final String DU_LIEU_TRA_LOI = "traloi"; //key
    public static final String DU_LIEU_STAR_CHECK = "star check";
    private int sochitranghtai; // vi tri trang hien tai trong mang
    public int ktra, starcheck; // kiem tra tra loi cac cau hoi
    TextView tvsttcau, tvcauhoi;
    RadioGroup radioGroup;
    RadioButton radA, radB, radC, radD;
    ImageView imgstar;

    public ScreenSlidePageFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_screen_slide_page, container, false);

        tvsttcau = (TextView) rootView.findViewById(R.id.tvsttcau);
        tvcauhoi = (TextView) rootView.findViewById(R.id.tvcauhoi);
        radA = (RadioButton) rootView.findViewById(R.id.radA);
        radB = (RadioButton) rootView.findViewById(R.id.radB);
        radC = (RadioButton) rootView.findViewById(R.id.radC);
        radD = (RadioButton) rootView.findViewById(R.id.radD);
        radioGroup = (RadioGroup) rootView.findViewById(R.id.radGroup);
        imgstar = (ImageView) rootView.findViewById(R.id.star);

        return rootView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ScreenSlideActivity screenSlideActivity = (ScreenSlideActivity) getActivity();
        arr_cauhoi = screenSlideActivity.laydulieu();
        sochitranghtai = getArguments().getInt(DU_LIEU_TRANG);
        ktra = getArguments().getInt(DU_LIEU_TRA_LOI);

    }

    public static ScreenSlidePageFragment create(int sotrang, int ktraTraloi, int starcheck) {
        ScreenSlidePageFragment fragment = new ScreenSlidePageFragment();
        Bundle dulieutrang = new Bundle();
        dulieutrang.putInt(DU_LIEU_TRANG, sotrang);
        dulieutrang.putInt(DU_LIEU_TRA_LOI, ktraTraloi);
        dulieutrang.putInt(DU_LIEU_STAR_CHECK, starcheck);
        fragment.setArguments(dulieutrang);
        return fragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        imgstar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.graphics.drawable.Drawable myDrawable = getResources().getDrawable(R.drawable.checked);
                imgstar.setImageDrawable(myDrawable);
            }
        });
        super.onActivityCreated(savedInstanceState);
        tvsttcau.setText("Câu " + (sochitranghtai + 1));
        tvcauhoi.setText(laysochitranghtai(sochitranghtai).getCauhoi());
        radA.setText(laysochitranghtai(sochitranghtai).getA());
        radB.setText(laysochitranghtai(sochitranghtai).getB());
        radC.setText(laysochitranghtai(sochitranghtai).getC());
        radD.setText(laysochitranghtai(sochitranghtai).getD());
        if (ktra != 0) {
            radA.setClickable(false);
            radB.setClickable(false);
            radC.setClickable(false);
            radD.setClickable(false);
            getKtraTraloi(laysochitranghtai(sochitranghtai).getKetqua().toString());
        }
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                laysochitranghtai(sochitranghtai).choiceId = checkedId;
                laysochitranghtai(sochitranghtai).setTraloi(getChoiceId(checkedId));
            }
        });

    }

    public CauHoi laysochitranghtai(int sochitrang) {
        return arr_cauhoi.get(sochitrang);
    }

// Lay gia tri id cua radiobutton chuyen sang ABCD

    private String getChoiceId(int Id) {
        if (Id == R.id.radA) {
            return "A";
        } else if (Id == R.id.radB) {
            return "B";
        } else if (Id == R.id.radC) {
            return "C";
        } else if (Id == R.id.radD) {
            return "D";
        } else {
            return "";
        }
    }

    private void getKtraTraloi(String cauTraloi) {
        if (cauTraloi.equals("A") == true) {
            radA.setBackgroundColor(Color.RED);
        } else if (cauTraloi.equals("B") == true) {
            radB.setBackgroundColor(Color.RED);
        } else if (cauTraloi.equals("C") == true) {
            radC.setBackgroundColor(Color.RED);
        } else if (cauTraloi.equals("D") == true) {
            radD.setBackgroundColor(Color.RED);
        } else ;
    }
}