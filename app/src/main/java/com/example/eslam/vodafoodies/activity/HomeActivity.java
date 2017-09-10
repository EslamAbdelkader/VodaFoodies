package com.example.eslam.vodafoodies.activity;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.eslam.vodafoodies.R;
import com.example.eslam.vodafoodies.fragment.MyOrdersFragment;
import com.example.eslam.vodafoodies.fragment.OpenOrdersFragment;
import com.example.eslam.vodafoodies.fragment.ProfileFragment;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

public class HomeActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private BottomBar bottomBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        bottomBar = (BottomBar) findViewById(R.id.bottomBar);

        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                switch (tabId){
                    case R.id.tab_open_orders:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.tab_my_orders:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.tab_profile:
                        viewPager.setCurrentItem(2);
                        break;
                }
            }
        });



        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                //Todo - put all cases, remove default
                switch (position){
                    case 0:
                        return new OpenOrdersFragment();
                    case 1:
                        return new MyOrdersFragment();
                    case 2:
                        return new ProfileFragment();
                }
                return null;
            }

            @Override
            public int getCount() {
                return 3;
            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
            @Override
            public void onPageSelected(int position) {
                bottomBar.selectTabAtPosition(position);
            }
        });
    }
}
