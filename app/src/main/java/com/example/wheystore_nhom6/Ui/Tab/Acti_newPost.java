package com.example.wheystore_nhom6.Ui.Tab;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wheystore_nhom6.DAO.File_DAO;
import com.example.wheystore_nhom6.DAO.Validate;
import com.example.wheystore_nhom6.DAO.sanPham_Dao;
import com.example.wheystore_nhom6.MainActivity;
import com.example.wheystore_nhom6.Model.sanPham;
import com.example.wheystore_nhom6.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class Acti_newPost extends AppCompatActivity {
    ImageView image;
    TextInputLayout name,adress,phone,brand, price,weight,element,count,messeger,note;
    String _name,_adress,_phone,_image,_brand,_price,_weight,_element,_count,_messeger,_note;
    int _type = 0;
    RadioButton radioButton_type;
    sanPham_Dao sp_Dao;
    sanPham sp;
    Uri imageUri;
    StorageReference storageReference;

    int check = 0;

    RadioGroup type;
    TextView create,cance;
    Validate validate;
    File_DAO file_dao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acti_new_post);

//        dNow = new Date( );
//        SimpleDateFormat ft =new SimpleDateFormat("dd.MM.yyyy");
//        Log.e("TAG", "date: "+ft.format(dNow) );

        call();

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (_image!=null){
                    file_dao.Delete_image(_image,Acti_newPost.this);
                    _image = null;
                    return;
                }
                    selectImage();

            }
        });

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


                if (_adress.isEmpty()){
                    adress.setError("Mời bạn nhập địa chỉ");
                    adress.setErrorEnabled(true);
                }else{
                    adress.setErrorEnabled(false);
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
                    Toast.makeText(Acti_newPost.this, "Loại sản phẩm chưa được chọn", Toast.LENGTH_SHORT).show();
                    return;
                }else{
                    radioButton_type = findViewById(_type);
                    Log.e("TAG", "radioButton_type: "+radioButton_type.getText());
                    Toast.makeText(Acti_newPost.this, "Loại sản phẩm "+radioButton_type.getText(), Toast.LENGTH_SHORT).show();
                    check++;
                }

                if (check==11||_image.isEmpty()){
                    sp = new sanPham("id",_name,_adress,_phone,_image,_brand,Integer.parseInt(_price),Integer.parseInt(_weight),_element,Integer.parseInt(_count),0,_messeger,_note
                    ,radioButton_type.getText().toString(),10,37,3.7);
                    sp_Dao.create_SanPham(sp,Acti_newPost.this);
                    check =0;
                    startActivity(new Intent(Acti_newPost.this, MainActivity.class));
                    finish();
                }else{
                    Toast.makeText(Acti_newPost.this, "Thêm sản phẩm thất bại. xin thử lại sau.", Toast.LENGTH_SHORT).show();
                    check =0;
                }

            }
        });

        cance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (_image!=null){
                    file_dao.Delete_image(_image,Acti_newPost.this);
                    startActivity(new Intent(Acti_newPost.this,MainActivity.class));
                    finish();
                }
            }
        });
    }


    private void call(){
        image = findViewById(R.id.Acti_newPost_ImageView);
        name = findViewById(R.id.Acti_newPost_name);
        brand = findViewById(R.id.Acti_newPost_brand);
        adress = findViewById(R.id.Acti_newPost_adress);
        //CheckBox
        type = findViewById(R.id.radioGroup_type);

        price = findViewById(R.id.Acti_newPost_price);
        phone = findViewById(R.id.Acti_newPost_phone);
        weight = findViewById(R.id.Acti_newPost_weight);
        count = findViewById(R.id.Acti_newPost_count);
        element = findViewById(R.id.Acti_newPost_element);
        messeger = findViewById(R.id.Acti_newPost_message);
        note = findViewById(R.id.Acti_newPost_note);
        create = findViewById(R.id.Acti_newPost_create);
        cance = findViewById(R.id.Acti_newPost_cance);
        validate = new Validate();
        sp_Dao = new sanPham_Dao();
        file_dao = new File_DAO();

        storageReference = FirebaseStorage.getInstance().getReference();
    }

    private void selectImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,100);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==100&&data!=null&&data.getData()!=null){
            imageUri = data.getData();
            image.setImageURI(imageUri);
            uploadImage();
        }
    }
    public void uploadImage(){
        StorageReference image = storageReference.child(imageUri.getLastPathSegment());
        image.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(Acti_newPost.this, "Cập nhật ảnh thành công", Toast.LENGTH_SHORT).show();
                image.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        _image = uri.toString();
                        Log.e("TAG", "url: "+uri.toString() );
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Acti_newPost.this, "Cập nhật ảnh thất bại", Toast.LENGTH_SHORT).show();
            }
        });
    }
}