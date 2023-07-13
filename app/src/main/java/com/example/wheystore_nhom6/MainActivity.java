package com.example.wheystore_nhom6;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.example.wheystore_nhom6.Ui.Tab.Frag_Profile;
import com.example.wheystore_nhom6.Ui.Tab.Frag_WheyMass;
import com.example.wheystore_nhom6.Ui.Tab.Frag_home;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

public class MainActivity extends AppCompatActivity {

    ChipNavigationBar chipNavigationBar;
    FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        chipNavigationBar = findViewById(R.id.main_ChipNavigationBar);
        chipNavigationBar.setItemSelected(R.id.menu_Home,true);
        frameLayout = findViewById(R.id.main_FrameLayout);
        getSupportFragmentManager().beginTransaction().replace(R.id.main_FrameLayout,new Frag_home()).commit();
        bottom_Menu();
    }
    private void bottom_Menu() {
        chipNavigationBar.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int i) {
                Fragment fragment = null;
                switch (i){
                    case R.id.menu_Home:
                        fragment = new Frag_home();
                        break;
                    case  R.id.menu_WheyMass:
                        fragment = new Frag_WheyMass();
                        break;
                    case  R.id.menu_profile:
                        fragment = new Frag_Profile();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.main_FrameLayout,fragment).commit();
            }
        });
    }
}