package com.example.wheystore_nhom6.Ui.View;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wheystore_nhom6.Bottom_Sheet_fragment.Bottom_sheet_fragment;
import com.example.wheystore_nhom6.DAO.User_Dao;
import com.example.wheystore_nhom6.DAO.sanPham_Dao;
import com.example.wheystore_nhom6.Model.sanPham;
import com.example.wheystore_nhom6.R;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;

public class Acti_ViewPost extends AppCompatActivity {

    sanPham_Dao sanPham_dao;


    sanPham sp;
    TextView title,name,brand,coin,phone,weight,timeTest,from,element,messeger, note,view;
    ImageView buy;
    ImageView img1;
    ImageView imgView;
    ImageView value1,value2,value3,value4,value5;

    LinearLayout linearLayout_bottom;
    BottomSheetBehavior bottomSheetBehavior;

    Bottom_sheet_fragment bottom_sheet_fragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acti_view_post);

        call();


        Bundle bundle = getIntent().getExtras();
        if (bundle==null){
            return;
        }
        sp = new sanPham();
        sp = (sanPham) bundle.get("data");

        sanPham_dao.UpdateView(sp.getId(),"view");

        name.setText(sp.getName());
        brand.setText(sp.getBrand());
        title.setText(sp.getName());
        weight.setText("Trọng Lượng : "+sp.getWeight()+" kg");
        from.setText("Địa chỉ : "+sp.getAdress());
        element.setText("Thành Phần Chính : "+sp.getElement());
        messeger.setText(sp.getMessage());
        note.setText("Chú Ý : "+sp.getNote());
        phone.setText(sp.getPhone());
        Picasso.get().load(sp.getImage()).resize(220,220).centerCrop().into(img1);
        view.setText(String.valueOf(sp.getView()));

        img1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Dialog dialog = new Dialog(Acti_ViewPost.this);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.setContentView(R.layout.item_show);
                imgView = dialog.findViewById(R.id.img_view_item);
                Picasso.get().load(sp.getImage()).resize(350,500).centerCrop().into(imgView);
                dialog.show();
                return false;
            }
        });

//        if (sp.getValue().equals("1")){
//            value2.setVisibility(View.GONE);
//            value3.setVisibility(View.GONE);
//            value4.setVisibility(View.GONE);
//            value5.setVisibility(View.GONE);
//        }
//        if (sp.getValue().equals("2")){
//            value3.setVisibility(View.GONE);
//            value4.setVisibility(View.GONE);
//            value5.setVisibility(View.GONE);
//        }
//        if (sp.getValue().equals("3")){
//            value4.setVisibility(View.GONE);
//            value5.setVisibility(View.GONE);
//        }
//        if (sp.getValue().equals("4")){
//            value5.setVisibility(View.GONE);
//        }
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        coin.setText(formatter.format(sp.getPrice())+" vnd");

        linearLayout_bottom = findViewById(R.id.LinearLayout_bottom_sheet);
        bottomSheetBehavior =BottomSheetBehavior.from(linearLayout_bottom);

        buy = findViewById(R.id.btn_viewPost_buy);

        if (sp.getCount()==0){
            buy.setImageDrawable(getDrawable(R.drawable.hethang_view));
            buy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(Acti_ViewPost.this, "Shop tạm thời hết hàng", Toast.LENGTH_SHORT).show();
                }
            });
        }else{
            buy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bottom_sheet_fragment bottom_sheet_fragment = Bottom_sheet_fragment.newInstance(sp);
                    bottom_sheet_fragment.show(getSupportFragmentManager(),bottom_sheet_fragment.getTag());

                }
            });
        }

    }
    private void call(){
        sanPham_dao = new sanPham_Dao();
        name = findViewById(R.id.tv_viewPost_title);
        brand = findViewById(R.id.tv_viewPost_brand);

        img1 = findViewById(R.id.img_viewPost_img1);

        title = findViewById(R.id.tv_titleSp);
        weight = findViewById(R.id.tv_trongluong);
        timeTest = findViewById(R.id.tv_timeTest);
        from = findViewById(R.id.tv_from);
        element = findViewById(R.id.tv_element);
        messeger = findViewById(R.id.tv_messeger);
        note = findViewById(R.id.tv_note);
        coin=findViewById(R.id.tv_viewPost_coin);
        phone=findViewById(R.id.tv_newPost_phone);
        view=findViewById(R.id.tv_viewPost_view);

        value1 =findViewById(R.id.img_view_type_1);
        value2 =findViewById(R.id.img_view_type_2);
        value3 =findViewById(R.id.img_view_type_3);
        value4 =findViewById(R.id.img_view_type_4);
        value5 =findViewById(R.id.img_view_type_5);
    }
}