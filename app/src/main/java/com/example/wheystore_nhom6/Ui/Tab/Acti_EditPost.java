package com.example.wheystore_nhom6.Ui.Tab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wheystore_nhom6.DAO.Validate;
import com.example.wheystore_nhom6.DAO.sanPham_Dao;
import com.example.wheystore_nhom6.DAO.sanPham_thongKe_DAO;
import com.example.wheystore_nhom6.MainActivity;
import com.example.wheystore_nhom6.Model.sanPham;
import com.example.wheystore_nhom6.Model.sanPham_thongKe;
import com.example.wheystore_nhom6.R;
import com.google.android.material.textfield.TextInputLayout;

public class Acti_EditPost extends AppCompatActivity {

    TextInputLayout name,adress,phone,brand, price,weight,element,count,messeger,note;
    String _name,_adress,_phone,_image,_brand,_price,_weight,_element,_count,_messeger,_note;
    int _type = 0;
    RadioButton radioButton_type;
    sanPham_Dao sp_Dao;
    sanPham_thongKe_DAO thongKe_Dao;
    Validate validate;
    sanPham sp;

    int check = 0;

    RadioGroup type;
    TextView create;
    RadioButton _whey,_mass,_bcaa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acti_edit_post);

        call();

        Bundle bundle = getIntent().getExtras();
        if (bundle==null){
            return;
        }
        sp = new sanPham();
        sp = (sanPham) bundle.get("data");
        name.getEditText().setText(sp.getName());
        adress.getEditText().setText(sp.getAdress());
        brand.getEditText().setText(sp.getBrand());
        phone.getEditText().setText(sp.getPhone());
        weight.getEditText().setText(String.valueOf(sp.getWeight()));
        element.getEditText().setText(sp.getElement());
        count.getEditText().setText(String.valueOf(sp.getCount()));
        messeger.getEditText().setText(sp.getMessage());
        note.getEditText().setText(sp.getNote());
        price.getEditText().setText(String.valueOf(sp.getPrice()));
        _image = sp.getImage();

        if (sp.getIndex().equals("whey")){
            _whey.setChecked(true);
        }else if (sp.getIndex().equals("mass")){
            _mass.setChecked(true);
        }else {
            _bcaa.setChecked(true);
        }

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _name = name.getEditText().getText().toString().trim();
                _adress = adress.getEditText().getText().toString().trim();
                _brand = brand.getEditText().getText().toString().trim();
                _price = price.getEditText().getText().toString().trim();
                _phone = phone.getEditText().getText().toString().trim();
                _weight = weight.getEditText().getText().toString().trim();
                _element = element.getEditText().getText().toString().trim();
                _count = count.getEditText().getText().toString().trim();
                _messeger = messeger.getEditText().getText().toString().trim();
                _note = note.getEditText().getText().toString().trim();




                if (_name.isEmpty()){
                    name.setError("Mời bạn nhập tên sản phẩm");
                    name.setErrorEnabled(true);
                } else if (!validate.checkInputName(_name)) {
                    name.setError("Có ký tự đặc biệt");
                }else{
                    name.setErrorEnabled(false);
                    check++;
                }

                if (_adress.isEmpty()){
                    adress.setError("Mời bạn nhập địa chỉ");
                    adress.setErrorEnabled(true);
                }else{
                    adress.setErrorEnabled(false);
                    check++;
                }


                if (_brand.isEmpty()){
                    brand.setError("Mời bạn nhập tên thương hiệu");
                    brand.setErrorEnabled(true);
                }else{
                    brand.setErrorEnabled(false);
                    check++;
                }

                if (_price.isEmpty()){
                    price.setError("Mời bạn nhập giá sản phẩm");
                    price.setErrorEnabled(true);
                }else if (!validate.checkNumber(_price)){
                    price.setError("Giá sản phẩm không hợp lệ");
                    price.setErrorEnabled(true);
                }else{
                    price.setErrorEnabled(false);
                    check++;
                }


                if (_phone.isEmpty()){
                    phone.setError("Mời bạn nhập số điện thoại");
                    phone.setErrorEnabled(true);
                }else if (!validate.checkNumber(_phone)){
                    phone.setError("Số điện thoại không hợp lệ");
                    phone.setErrorEnabled(true);
                }else{
                    phone.setErrorEnabled(false);
                    check++;
                }


                if (_weight.isEmpty()){
                    weight.setError("Mời bạn nhập trọng lương");
                    weight.setErrorEnabled(true);
                }else{
                    weight.setErrorEnabled(false);
                    check++;
                }


                if (_count.isEmpty()){
                    count.setError("Mời bạn nhập số lượng nhập");
                    count.setErrorEnabled(true);
                }else if (!validate.checkNumber(_count)){
                    count.setError("Số lượng nhập không hợp lệ");
                    count.setErrorEnabled(true);
                }else{
                    count.setErrorEnabled(false);
                    check++;
                }

                if (_element.isEmpty()){
                    element.setError("Mời bạn nhập thành phần chính");
                    element.setErrorEnabled(true);
                }else{
                    element.setErrorEnabled(false);
                    check++;
                }



                if (_messeger.isEmpty()){
                    messeger.setError("Mời bạn nhập nội dung");
                    messeger.setErrorEnabled(true);
                }else{
                    messeger.setErrorEnabled(false);
                    check++;
                }


                if (_note.isEmpty()){
                    note.setError("Mời bạn nhập ghi chú");
                    note.setErrorEnabled(true);
                }else{
                    note.setErrorEnabled(false);
                    check++;
                }

                _type =type.getCheckedRadioButtonId();

                if (_type<0){
                    Toast.makeText(Acti_EditPost.this, "loai chua duoc chon", Toast.LENGTH_SHORT).show();
                    return;
                }else{
                    radioButton_type = findViewById(_type);
                    Log.e("TAG", "radioButton_type: "+radioButton_type.getText());
                    Toast.makeText(Acti_EditPost.this, "loai : "+radioButton_type.getText(), Toast.LENGTH_SHORT).show();
                    check++;
                }

                if (check==11){//radioButton.getText().toString()
                    sp = new sanPham(sp.getId(),_name,_adress,_phone,_image,_brand,Integer.parseInt(_price),Integer.parseInt(_weight),_element,Integer.parseInt(_count),sp.getUnCount(),_messeger,_note
                            ,radioButton_type.getText().toString(),sp.getView(),sp.getMaxType(),sp.getType());
                    sp_Dao.Edit_Post(sp,sp.getId(),Acti_EditPost.this);
                    startActivity(new Intent(Acti_EditPost.this, MainActivity.class));
                    check =0;
                    finish();
                    Toast.makeText(Acti_EditPost.this, "Okk", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(Acti_EditPost.this, "khong the them san pham", Toast.LENGTH_SHORT).show();
                    check =0;
                }
            }
        });
    }
    private void call(){
        sp_Dao = new sanPham_Dao();
        thongKe_Dao = new sanPham_thongKe_DAO();



        //CheckBox
        type = findViewById(R.id.radioGroup_type);
        //check value
        //check type
        _whey = findViewById(R.id.rdo_whey_edit);
        _mass = findViewById(R.id.rdo_mass_edit);
        _bcaa = findViewById(R.id.rdo_bcaa_edit);
        //text

        name = findViewById(R.id.Acti_EditPost_name);
        brand = findViewById(R.id.Acti_EditPost_brand);
        adress = findViewById(R.id.Acti_EditPost_adress);
        price = findViewById(R.id.Acti_EditPost_price);
        phone = findViewById(R.id.Acti_EditPost_phone);
        weight = findViewById(R.id.Acti_EditPost_weight);
        element = findViewById(R.id.Acti_EditPost_element);
        count = findViewById(R.id.Acti_EditPost_count);
        messeger = findViewById(R.id.Acti_EditPost_message);
        note = findViewById(R.id.Acti_EditPost_note);

        create = findViewById(R.id.Acti_EditPost_update);
        validate = new Validate();
    }
}