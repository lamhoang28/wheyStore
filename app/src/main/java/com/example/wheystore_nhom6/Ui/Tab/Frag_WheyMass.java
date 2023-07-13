package com.example.wheystore_nhom6.Ui.Tab;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wheystore_nhom6.R;
import com.example.wheystore_nhom6.ViewPager_Adapter.ViewPager_Adapter_WheyMass;
import com.example.wheystore_nhom6.ViewPager_Adapter.ZoomOutPageTransformer;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class Frag_WheyMass extends Fragment {

    ViewPager2 viewPager;
    TabLayout tabLayout;



    public static Frag_WheyMass newInstance(String param1, String param2) {
        Frag_WheyMass fragment = new Frag_WheyMass();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_frag_wheymass, container, false);
        tabLayout = view.findViewById(R.id.wheyMass_TabLayout);
        viewPager = view.findViewById(R.id.wheyMass_ViewPager2);
        ViewPager_Adapter_WheyMass adapter = new ViewPager_Adapter_WheyMass((FragmentActivity) view.getContext());
        viewPager.setAdapter(adapter);
        viewPager.setPageTransformer(new ZoomOutPageTransformer());
        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            switch (position){
                case 0 : tab.setText("Whey");
                    break;
                case 1 :tab.setText("Mass");
                    break;
                case 2 :tab.setText("Bcaa");
                    break;
            }
        }).attach();
        return view;
    }
}