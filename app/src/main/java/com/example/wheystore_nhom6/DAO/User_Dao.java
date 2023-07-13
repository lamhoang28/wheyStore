package com.example.wheystore_nhom6.DAO;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.wheystore_nhom6.MainActivity;
import com.example.wheystore_nhom6.Model.hoaDon;
import com.example.wheystore_nhom6.Model.sanPham;
import com.example.wheystore_nhom6.Model.Model;
import com.example.wheystore_nhom6.Ui.Tab.Acti_updateProfile;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Source;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class User_Dao {

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseUser _UserAuth = FirebaseAuth.getInstance().getCurrentUser();
    FirebaseAuth mAuth;
    hoaDon_Dao hoaDon_dao;
    hoaDon _hoaDon;

    public void singUp_User(Model _model, String passWord,Context context){
        mAuth = FirebaseAuth.getInstance();
        mAuth.createUserWithEmailAndPassword(_model.getId(),passWord)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    FirebaseUser Fuser = mAuth.getCurrentUser();
                    Fuser.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {

                            UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                .setDisplayName(_model.getName())
                                .build();
                                Fuser.updateProfile(profileUpdates)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            create_User(_model);
                                        }
                                    }
                                });
                        }
                    });
                    Toast.makeText(context, "Authentication Success.",Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Authentication failed.",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void sign_User(String pass, String email, Context context, Dialog dialog){
        mAuth = FirebaseAuth.getInstance();
        mAuth.signInWithEmailAndPassword(pass,email)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            FirebaseUser firebaseUser = mAuth.getCurrentUser();
                            boolean emailVerified = firebaseUser.isEmailVerified();
                            Log.e("TAG", "onComplete: "+emailVerified );
                            if (emailVerified != true) {
                                dialog.dismiss();
                                Toast.makeText(context, "Bạn cần xác nhận email trước khi đăng nhập", Toast.LENGTH_SHORT).show();
                            return;
                            } else {
                                Log.e("TAG", "login onComplete: " );
                                dialog.dismiss();
                                Intent intent = new Intent(context, MainActivity.class);
                                context.startActivity(intent);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            }
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(context, "Tài Khoản hoặc Mật Khẩu Không Đúng", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });
    }

    public void create_User(Model _model){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Map<String, Object> member = new HashMap<>();
        member.put("email", _model.getId());
        member.put("name", _model.getName());
        member.put("phone", _model.getPhone());
        member.put("adress", _model.getAdress());

        db.collection("User").document(_model.getId())
            .set(_model)
            .addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    Log.e("TAG", "successfully!");
                }
            })
            .addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.e("TAG", "Error writing document", e);
                }
            });
    }


    public void Edit_User(Model _model, String index, Context context){
        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseFirestore.collection("User").document(index)
                .set(_model)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        updatePassW_Auth(_model.getName(),context);
                        update_UserName(_model.getName());
                        context.startActivity(new Intent(context,MainActivity.class));
                    }

                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(context, "Update User Thành Công Thất Bại  !!!", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void deleteUserAuth(String email,Context context){
        _UserAuth = FirebaseAuth.getInstance().getCurrentUser();
        _UserAuth.delete()
            .addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        deleteUser(email);
                        Toast.makeText(context, "Vô hiệu hóa tài khoản thành công", Toast.LENGTH_SHORT).show();
                    }
                }
            });
    }

    public void deleteUser(String ID) {
        CollectionReference collectionReference = db.collection("User");
        collectionReference.document(ID).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Log.e("TAG", "Xóa User Thành Công" );
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.e("TAG", "Xóa User Dell Thành Công" );
            }
        });

    }

    public void updatePassW_Auth(String passNew,Context context) {
        _UserAuth = FirebaseAuth.getInstance().getCurrentUser();
        _UserAuth.updatePassword(passNew)
            .addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(context, "Cập nhật mật khẩu Thành Công !!!", Toast.LENGTH_SHORT).show();
                    }
                }
            });

    }

    public void read_User(TextInputLayout userName,TextInputLayout email,TextInputLayout pass,TextInputLayout pass2,TextInputLayout adress,TextInputLayout phone ){
        _UserAuth = FirebaseAuth.getInstance().getCurrentUser();
        List<Model> _list = new ArrayList<>();
        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseFirestore.collection("User")
                .document(_UserAuth.getEmail()).addSnapshotListener(new EventListener<DocumentSnapshot>() {
                    @Override
                    public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                        Model us = value.toObject(Model.class);
                        _list.add(us);
                        for (int i = 0; i < _list.size(); i++) {
                            Log.e("TAG", "usuer: "+_list.get(i).getAdress());
                            userName.getEditText().setText(_list.get(i).getAdress());
                            email.getEditText().setText(_list.get(i).getAdress());
                            pass.getEditText().setText(_list.get(i).getAdress());
                            pass2.getEditText().setText(_list.get(i).getAdress());
                            adress.getEditText().setText(_list.get(i).getAdress());
                            phone.getEditText().setText(_list.get(i).getPhone());
                        }
                    }
                });
    }

    public boolean check_admin(){
        _UserAuth = FirebaseAuth.getInstance().getCurrentUser();
        if (_UserAuth.getEmail().equals("lamnhph18826@fpt.edu.vn")){
            Log.e("TAG", "day la admin: " );
            return true;
        }
        return false;
    }


    public void checkPassWord(String email,String passRep,Context context) {
        DocumentReference docRef = db.collection("User").document(email);
        Source source = Source.CACHE;
         docRef.get(source).addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {

                    DocumentSnapshot document = task.getResult();

                    String pass = (String) document.getData().get("pass");
                    if (passRep.equals(pass)){
                        context.startActivity(new Intent(context, Acti_updateProfile.class));
                        Toast.makeText(context, "Thành công", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Log.d("TAG", "Cached get failed: ", task.getException());
                }
            }
        });
    }

    public void SendPassReset(String mail,Context context) {
        mAuth.sendPasswordResetEmail(mail)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(context, "Mời bạn kiểm tra mail để lấy lại mật khẩu", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public void update_UserName(String username){
        _UserAuth = FirebaseAuth.getInstance().getCurrentUser();

        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                .setDisplayName(username)
                .build();

        _UserAuth.updateProfile(profileUpdates)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.e("TAG", "UserName updated.");
                        }
                    }
                });

    }

    public void checkInfoMember(TextView UserName,TextView UserPhone,TextView UserAdress) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        DocumentReference docRef = db.collection("User").document(user.getEmail());
        Source source = Source.CACHE;
        docRef.get(source).addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {

                    DocumentSnapshot document = task.getResult();

                    String name = (String) document.getData().get("userName");
                    String phone = (String) document.getData().get("phone");
                    String adress =(String) document.getData().get("adress");
                    UserName.setText(name);
                    UserPhone.setText(phone);
                    UserAdress.setText(adress);

                } else {
                    Log.d("TAG", "Cached get failed: ", task.getException());
                }
            }
        });
    }

    public void checkInfo( sanPham sp,int value, Date date, Context context) {
        hoaDon_Dao hoaDonDao = new hoaDon_Dao();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        DocumentReference docRef = db.collection("User").document(user.getEmail());
        Source source = Source.CACHE;
        docRef.get(source).addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {

                    DocumentSnapshot document = task.getResult();

                    String phone = (String) Objects.requireNonNull(document.getData()).get("phone");
                    String adress = (String) document.getData().get("adress");
                    String name = (String) document.getData().get("name");
                    String email = (String) document.getData().get("id");
                    //String id, String name, String adress, String phone, String idPost, String idSanPham,
                    //                  String imageSanPham, String nameSanPham, int priceSanPham, Date date, int count)
                    hoaDon _hoaDon = new hoaDon(email,name,adress,phone,"id",sp.getId(),sp.getImage(),sp.getName(),sp.getPrice(),date,sp.getCount());
                    hoaDonDao.create_HoaDon(_hoaDon,context);

                    Toast.makeText(context, "okkk", Toast.LENGTH_SHORT).show();
                } else {
                    Log.d("TAG", "Cached get failed: ", task.getException());
                }
            }
        });
    }

}
