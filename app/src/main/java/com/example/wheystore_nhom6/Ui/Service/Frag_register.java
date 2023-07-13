package com.example.wheystore_nhom6.Ui.Service;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wheystore_nhom6.DAO.User_Dao;
import com.example.wheystore_nhom6.DAO.Validate;
import com.example.wheystore_nhom6.Model.Model;
import com.example.wheystore_nhom6.R;
import com.google.android.material.textfield.TextInputLayout;

public class Frag_register extends Fragment {

    TextInputLayout userName,pass,pass2,email,phone,adress;
    String _userName,_pass,_pass2,_email,_phone,_adress;
    int check =0;
    TextView create;
    Model _model;
    User_Dao user_dao;
    Validate validate;


    public Frag_register() {

    }



    public static Frag_register newInstance(String param1, String param2) {
        Frag_register fragment = new Frag_register();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_frag_register, container, false);
        call(view);

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _userName = userName.getEditText().getText().toString().trim();
                _email = email.getEditText().getText().toString().trim();
                _pass = pass.getEditText().getText().toString().trim();
                _pass2 = pass2.getEditText().getText().toString().trim();
                _phone = phone.getEditText().getText().toString().trim();
                _adress = adress.getEditText().getText().toString().trim();

                if (_userName.isEmpty()){
                    userName.setError("Mời bạn nhập họ và tên");
                    userName.setErrorEnabled(true);
                } else if (!validate.checkInputName(_userName)) {
                    userName.setError("Có ký tự đặc biệt");
                }else{
                    userName.setErrorEnabled(false);
                    check++;
                }

                if (_pass.isEmpty()){
                    pass.setError("Mời bạn nhập mật khẩu");
                    pass.setErrorEnabled(true);
                }else{
                    pass.setErrorEnabled(false);
                    check++;
                }

                if (_pass2.isEmpty()){
                    pass2.setError("Mời bạn nhập mật khẩu");
                    pass2.setErrorEnabled(true);
                }else if(!_pass.equals(_pass2)){
                    pass2.setError("Mật khẩu không khớp");
                    pass2.setErrorEnabled(true);
                }else{
                    pass2.setErrorEnabled(false);
                    check++;
                }

                if (_email.isEmpty()){
                    email.setError("Mời bạn nhập email");
                    email.setErrorEnabled(true);
                }else if (!validate.checkEmail(_email)){
                    email.setError("Sai định dạng email");
                }else{
                    email.setErrorEnabled(false);
                    check++;
                }


                if (_adress.isEmpty()){
                    adress.setError("Mời bạn nhập địa chỉ");
                    adress.setErrorEnabled(true);
                }else{
                    adress.setErrorEnabled(false);
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

                if (check==6){
                    _model = new Model(_email,_userName,_adress,_phone);
                    user_dao.singUp_User(_model,_pass,getContext());
                    check =0;
                }else{
                    Toast.makeText(getContext(), "Khong Thanh Cong", Toast.LENGTH_SHORT).show();
                    check =0;
                }
            }
        });


        return view;
    }
    private void call(View view){
        userName = view.findViewById(R.id.edt_userName_Create);
        pass = view.findViewById(R.id.edt_pass_Create);
        pass2 = view.findViewById(R.id.edt_pass2_Create);
        email = view.findViewById(R.id.edt_email_Create);
        adress = view.findViewById(R.id.edt_adress_Create);
        phone = view.findViewById(R.id.edt_phone_Create);
        create = view.findViewById(R.id.tv_Frag_login_login);
        user_dao = new User_Dao();
        _model = new Model();
        validate = new Validate();
    }
}