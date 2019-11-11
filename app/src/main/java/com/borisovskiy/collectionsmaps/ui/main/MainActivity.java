package com.borisovskiy.collectionsmaps.ui.main;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.borisovskiy.collectionsmaps.R;
import com.borisovskiy.collectionsmaps.adapters.PagerAdapter;
import com.borisovskiy.collectionsmaps.ui.main.collections.CollectionsFragmentContract;
import com.google.android.material.tabs.TabLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements CollectionsFragmentContract.IHost {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.pager)
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        initialiseTabLayout();
        initialisePaging();
    }

    private void initialiseTabLayout() {
        tabLayout.addTab(tabLayout.newTab().setText(R.string.tab_collections_label));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.tab_maps_label));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                // NOP
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                // NOP
            }
        });
    }

    private void initialisePaging() {
        PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
    }
}