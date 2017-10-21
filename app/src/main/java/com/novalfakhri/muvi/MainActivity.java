package com.novalfakhri.muvi;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.novalfakhri.muvi.Adapter.PagerAdapter;

public class MainActivity extends AppCompatActivity {
    PagerAdapter pagerAdapter;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        pagerAdapter = new PagerAdapter(getSupportFragmentManager());

        viewPager = (ViewPager) findViewById(R.id.viewpager);
//        viewPager.setBackgroundResource(R.drawable.bg_movies);
        viewPager.setAdapter(pagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tablayout);
        tabLayout.setupWithViewPager(viewPager);
    }

}