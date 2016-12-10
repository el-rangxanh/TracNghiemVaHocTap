package com.el_rangxanh.test_11.slide;

import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.el_rangxanh.test_11.R;
import com.el_rangxanh.test_11.cauhoi.CauHoi;
import com.el_rangxanh.test_11.cauhoi.QuanLiCauHoi;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import static android.view.View.GONE;
import static android.view.View.OnClickListener;
import static android.view.View.VISIBLE;
import static com.el_rangxanh.test_11.slide.ScreenSlideActivity.hieuUngChuyenTrang.TransformType.DEPTH;

public class ScreenSlideActivity extends FragmentActivity {

    private static final int NUM_PAGES = 10;

    private ViewPager mPager;

    private PagerAdapter mPagerAdapter;
    public int ktraTraloi = 0, starcheck = 0;
    TextView tvnopbai, tvthoigian, tvxemdiem;
    ImageView imgTraloi;


    // co so du lieu
    QuanLiCauHoi quanlicauhoi;
    ArrayList<CauHoi> arr_cauhoi;
    DemtgianClass demtgian;
    int tgianktra; // tinh theo phut

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_slide);
        mPager = (ViewPager) findViewById(R.id.pager);
        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);
        mPager.setPageTransformer(true, new hieuUngChuyenTrang(DEPTH));

        tgianktra = 15;
        demtgian = new DemtgianClass(tgianktra * 60 * 1000 + 1000, 1000);

        quanlicauhoi = new QuanLiCauHoi(this);
        arr_cauhoi = new ArrayList<CauHoi>();
        arr_cauhoi = quanlicauhoi.laycauhoiVatLy(1, "ly");

        tvnopbai = (TextView) findViewById(R.id.tvNopBai);
        tvthoigian = (TextView) findViewById(R.id.tvThoigian);
        tvxemdiem = (TextView) findViewById(R.id.tvDiem);
        imgTraloi = (ImageView) findViewById(R.id.as);

        imgTraloi.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                phieutraloi();
            }
        });
        tvnopbai.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                nopbai2();
            }
        });
        tvxemdiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ScreenSlideActivity.this, XemDiemActivity.class);
                intent.putExtra("arr_cauhoi", arr_cauhoi);
                startActivity(intent);
            }
        });
        tvthoigian.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        demtgian.start();
    }

    public ArrayList<CauHoi> laydulieu() {
        return arr_cauhoi;
    }

    @Override
    public void onBackPressed() {
        if (mPager.getCurrentItem() == 0) {
            alertThoat();
        } else {
            mPager.setCurrentItem(mPager.getCurrentItem() - 1);
        }
    }

    public void alertThoat() {
        final Builder builder = new Builder(ScreenSlideActivity.this);
        builder.setIcon(R.drawable.exit2);
        builder.setTitle("    Thông báo");
        builder.setMessage("Thoát khỏi bài thi ngay bây giờ ?");
        builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                demtgian.cancel();
                finish();
            }
        });
        builder.show();
    }

    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            return ScreenSlidePageFragment.create(position, ktraTraloi, starcheck);
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }

    // Hieu ung chuyen trang

    public static class hieuUngChuyenTrang implements ViewPager.PageTransformer {
        private static final float MIN_SCALE_DEPTH = 0.75f;
        private static final float MIN_SCALE_ZOOM = 0.85f;
        private static final float MIN_ALPHA_ZOOM = 0.5f;
        private static final float SCALE_FACTOR_SLIDE = 0.85f;
        private static final float MIN_ALPHA_SLIDE = 0.35f;
        private final TransformType mTransformType;

        public hieuUngChuyenTrang(TransformType transformType) {
            mTransformType = transformType;
        }

        public void transformPage(View page, float position) {
            final float alpha;
            final float scale;
            final float translationX;

            switch (mTransformType) {
                case FLOW:
                    page.setRotationY(position * -30f);
                    return;

                case SLIDE_OVER:
                    if (position < 0 && position > -1) {
                        // chuyen trai
                        scale = Math.abs(Math.abs(position) - 1) * (1.0f - SCALE_FACTOR_SLIDE) + SCALE_FACTOR_SLIDE;
                        alpha = Math.max(MIN_ALPHA_SLIDE, 1 - Math.abs(position));
                        int pageWidth = page.getWidth();
                        float translateValue = position * -pageWidth;
                        if (translateValue > -pageWidth) {
                            translationX = translateValue;
                        } else {
                            translationX = 0;
                        }
                    } else {
                        alpha = 1;
                        scale = 1;
                        translationX = 0;
                    }
                    break;

                case DEPTH:
                    if (position > 0 && position < 1) {
                        // chuyen phai
                        alpha = (1 - position);
                        scale = MIN_SCALE_DEPTH + (1 - MIN_SCALE_DEPTH) * (1 - Math.abs(position));
                        translationX = (page.getWidth() * -position);
                    } else {
                        // default
                        alpha = 1;
                        scale = 1;
                        translationX = 0;
                    }
                    break;

                case ZOOM:
                    if (position >= -1 && position <= 1) {
                        scale = Math.max(MIN_SCALE_ZOOM, 1 - Math.abs(position));
                        alpha = MIN_ALPHA_ZOOM +
                                (scale - MIN_SCALE_ZOOM) / (1 - MIN_SCALE_ZOOM) * (1 - MIN_ALPHA_ZOOM);
                        float vMargin = page.getHeight() * (1 - scale) / 2;
                        float hMargin = page.getWidth() * (1 - scale) / 2;
                        if (position < 0) {
                            translationX = (hMargin - vMargin / 2);
                        } else {
                            translationX = (-hMargin + vMargin / 2);
                        }
                    } else {
                        alpha = 1;
                        scale = 1;
                        translationX = 0;
                    }
                    break;

                default:
                    return;
            }

            page.setAlpha(alpha);
            page.setTranslationX(translationX);
            page.setScaleX(scale);
            page.setScaleY(scale);
        }

        public static enum TransformType {
            FLOW,
            DEPTH,
            ZOOM,
            SLIDE_OVER
        }
    }

    public void Diem() {
        ktraTraloi = 1;
        if (mPager.getCurrentItem() >= 4) mPager.setCurrentItem(mPager.getCurrentItem() - 3);
        else if (mPager.getCurrentItem() <= 4) mPager.setCurrentItem(mPager.getCurrentItem() + 3);
        tvxemdiem.setVisibility(VISIBLE);
        tvnopbai.setVisibility(GONE);
    }

    public void nopbai2() {
        final Builder builder = new Builder(ScreenSlideActivity.this);
        builder.setTitle("Nộp bài ngay bây giờ ?");
        builder.setMessage("Hãy chắc chắn rằng bạn đã hoàn thành xong bài thi");
        builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                demtgian.cancel();
                Diem();
            }
        });
        builder.show();
    }

    public void nopbai() {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.nop_bai_dialog);
        dialog.setTitle("Nộp bài ngay bây giờ ?");
        Button btnnopbai, btnthoat;
        btnnopbai = (Button) dialog.findViewById(R.id.btnnopbai);
        btnthoat = (Button) dialog.findViewById(R.id.btnthoat);
        btnnopbai.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                demtgian.cancel();
                Diem();
                dialog.dismiss();
            }
        });
        btnthoat.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public void phieutraloi() {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.phieu_tra_loi_dialog);
        dialog.setTitle("            Phiếu Trả Lời");
        KiemtraTraloiAdapter TraloiAdapter = new KiemtraTraloiAdapter(arr_cauhoi, this);
        GridView dsachtraloi = (GridView) dialog.findViewById(R.id.gvtraloi);
        dsachtraloi.setAdapter(TraloiAdapter);
        dsachtraloi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mPager.setCurrentItem(position);
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public void starCheck() {

    }
    // tgian tinh theo millisecond
    public class DemtgianClass extends CountDownTimer {
        /**
         * @param tongThoigian     The number of millis in the future from the call
         *                         to {@link #start()} until the countdown is done and {@link #onFinish()}
         *                         is called.
         * @param buocNhayThoigian The interval along the way to receive
         *                         {@link #onTick(long)} callbacks.
         */
        public DemtgianClass(long tongThoigian, long buocNhayThoigian) {
            super(tongThoigian, buocNhayThoigian);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            String countTime = String.format("%02d:%02d", TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished), TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(
                    TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)));
            tvthoigian.setText(countTime); //SetText cho textview hiện thị thời gian.
        }

        @Override
        public void onFinish() {
            tvthoigian.setText("00:00");  //SetText cho textview hiển thị thời gian.
        }
    }


}



