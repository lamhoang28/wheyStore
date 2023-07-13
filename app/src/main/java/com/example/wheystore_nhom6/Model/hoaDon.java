package com.example.wheystore_nhom6.Model;

import java.util.Date;

public class hoaDon extends Model{
    private String idPost,idSanPham,imageSanPham,nameSanPham;
    private int priceSanPham;
    private Date date;
    private int count;

    public hoaDon() {
    }

    public hoaDon(String id, String name, String adress, String phone, String idPost, String idSanPham,
                  String imageSanPham, String nameSanPham, int priceSanPham, Date date, int count) {
        super(id, name, adress, phone);
        this.idPost = idPost;
        this.idSanPham = idSanPham;
        this.imageSanPham = imageSanPham;
        this.nameSanPham = nameSanPham;
        this.priceSanPham = priceSanPham;
        this.date = date;
        this.count = count;
    }

    public String getIdPost() {
        return idPost;
    }

    public void setIdPost(String idPost) {
        this.idPost = idPost;
    }

    public String getIdSanPham() {
        return idSanPham;
    }

    public void setIdSanPham(String idSanPham) {
        this.idSanPham = idSanPham;
    }

    public String getImageSanPham() {
        return imageSanPham;
    }

    public void setImageSanPham(String imageSanPham) {
        this.imageSanPham = imageSanPham;
    }

    public String getNameSanPham() {
        return nameSanPham;
    }

    public void setNameSanPham(String nameSanPham) {
        this.nameSanPham = nameSanPham;
    }

    public int getPriceSanPham() {
        return priceSanPham;
    }

    public void setPriceSanPham(int priceSanPham) {
        this.priceSanPham = priceSanPham;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public double sum() {
        return getCount()*getPriceSanPham();
    }
}
