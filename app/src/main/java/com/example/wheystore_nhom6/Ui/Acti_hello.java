package com.example.wheystore_nhom6.Ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import com.example.wheystore_nhom6.R;
import com.example.wheystore_nhom6.ViewPager_Adapter.ViewPager_Adapter_hello;
import com.example.wheystore_nhom6.ViewPager_Adapter.ZoomOutPageTransformer;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import android.annotation.SuppressLint;
import android.os.Bundle;

public class Acti_hello extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager2 viewPager;
    ViewPager_Adapter_hello adapter;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acti_hello);

        tabLayout = findViewById(R.id.Acti_hello_TabLayout);
        viewPager = findViewById(R.id.Acti_hello_ViewPager2);
        adapter = new ViewPager_Adapter_hello(Acti_hello.this);
        viewPager.setAdapter(adapter);
        viewPager.setPageTransformer(new ZoomOutPageTransformer());
        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            switch (position){
                case 0 : tab.setText("Login");
                    break;
                case 1 :tab.setText("Register");
                    break;
            }
        }).attach();
    }
}