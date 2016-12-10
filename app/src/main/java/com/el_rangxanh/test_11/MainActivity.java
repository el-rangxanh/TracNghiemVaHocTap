package com.el_rangxanh.test_11;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.el_rangxanh.test_11.cauhoi.DBHelper;
import com.el_rangxanh.test_11.monhoc.AnhFragment;
import com.el_rangxanh.test_11.monhoc.CongDanFragment;
import com.el_rangxanh.test_11.monhoc.DiaLyFragment;
import com.el_rangxanh.test_11.monhoc.HoaFragment;
import com.el_rangxanh.test_11.monhoc.HomeFragment;
import com.el_rangxanh.test_11.monhoc.LichSuFragment;
import com.el_rangxanh.test_11.monhoc.LyFragment;
import com.el_rangxanh.test_11.monhoc.SinhFragment;
import com.el_rangxanh.test_11.monhoc.ToanFragment;

import java.io.IOException;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    FloatingActionButton fab, fab_tumoi, fab_dongtubqt, fab_congthuc;
    boolean an_hien = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab_tumoi = (FloatingActionButton) findViewById(R.id.fab_newword);
        fab_dongtubqt = (FloatingActionButton) findViewById(R.id.fab_dongtu);
        fab_congthuc = (FloatingActionButton) findViewById(R.id.fab_congthuc);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (an_hien == false) {
                    hienthi_fab();
                    an_hien = true;
                } else {
                    an_fab();
                    an_hien = false;
                }
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        HomeFragment homeFragment = new HomeFragment();
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.content_main, homeFragment, homeFragment.getTag()).commit();

        DBHelper dbHelper = new DBHelper(this);
        try {
            dbHelper.createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // method hien thi fab
    private void hienthi_fab() {
        fab_tumoi.show();
        fab_dongtubqt.show();
        fab_congthuc.show();
    }

    // method an fab
    private void an_fab() {
        fab_tumoi.hide();
        fab_dongtubqt.hide();
        fab_congthuc.hide();
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.home) {
            HomeFragment homeFragment = new HomeFragment();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.content_main, homeFragment, homeFragment.getTag()).commit();
        } else if (id == R.id.toan) {
            ToanFragment toanFragment = new ToanFragment();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.content_main, toanFragment, toanFragment.getTag()).commit();
        } else if (id == R.id.ly) {
            LyFragment lyFragment = new LyFragment();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.content_main, lyFragment, lyFragment.getTag()).commit();
        } else if (id == R.id.hoa) {
            HoaFragment hoaFragment = new HoaFragment();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.content_main, hoaFragment, hoaFragment.getTag()).commit();
        } else if (id == R.id.sinh) {
            SinhFragment sinhFragment = new SinhFragment();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.content_main, sinhFragment, sinhFragment.getTag()).commit();
        } else if (id == R.id.anh) {
            AnhFragment anhFragment = new AnhFragment();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.content_main, anhFragment, anhFragment.getTag()).commit();
        } else if (id == R.id.su) {
            LichSuFragment suFragment = new LichSuFragment();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.content_main, suFragment, suFragment.getTag()).commit();
        } else if (id == R.id.dia) {
            DiaLyFragment diaFragment = new DiaLyFragment();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.content_main, diaFragment, diaFragment.getTag()).commit();
        } else if (id == R.id.congdan) {
            CongDanFragment congdanFragment = new CongDanFragment();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.content_main, congdanFragment, congdanFragment.getTag()).commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

