package com.example.wheystore_nhom6.ViewPager_Adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.wheystore_nhom6.Ui.Tab.WheyMass.Frag_Bcaa;
import com.example.wheystore_nhom6.Ui.Tab.WheyMass.Frag_mass;
import com.example.wheystore_nhom6.Ui.Tab.WheyMass.Frag_whey;

public class ViewPager_Adapter_WheyMass extends FragmentStateAdapter {

    public ViewPager_Adapter_WheyMass(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0 : return new Frag_whey();
            case 1 : return new Frag_mass();
            case 2 : return new Frag_Bcaa();
            default: return  new Frag_whey();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
