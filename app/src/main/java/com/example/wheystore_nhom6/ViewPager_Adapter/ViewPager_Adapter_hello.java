package com.example.wheystore_nhom6.ViewPager_Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.wheystore_nhom6.Ui.Service.Frag_login;
import com.example.wheystore_nhom6.Ui.Service.Frag_register;

public class ViewPager_Adapter_hello extends FragmentStateAdapter {


    public ViewPager_Adapter_hello(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0 : return new Frag_login();

            case 1 : return new Frag_register();

            default: return new Frag_login();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
