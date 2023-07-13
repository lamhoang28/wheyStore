package com.example.wheystore_nhom6.Model;

import java.io.Serializable;

public class sanPham extends Model implements Serializable {
    private String image,brand;
    private int price,weight;
    private String element;
    private int count,unCount;
    private String message,note,index;
    private int view,maxType;
    private double type;


    public sanPham() {
    }

    public sanPham(String id, String name, String adress, String phone, String image, String brand, int price, int weight, String element, int count, int unCount, String message, String note, String index, int view, int maxType, double type) {
        super(id, name, adress, phone);
        this.image = image;
        this.brand = brand;
        this.price = price;
        this.weight = weight;
        this.element = element;
        this.count = count;
        this.unCount = unCount;
        this.message = message;
        this.note = note;
        this.index = index;
        this.view = view;
        this.maxType = maxType;
        this.type = type;
    }

    public String getImage() {
        if (image.isEmpty()){
            return "https://bookvexe.vn/wp-content/uploads/2023/04/chia-se-25-meme-khoc-khien-ban-phai-roi-nuoc-mat_2.jpg";
        }
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getElement() {
        return element;
    }

    public void setElement(String element) {
        this.element = element;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getUnCount() {
        return unCount;
    }

    public void setUnCount(int unCount) {
        this.unCount = unCount;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public int getView() {
        return view;
    }

    public void setView(int view) {
        this.view = view;
    }

    public int getMaxType() {
        return maxType;
    }

    public void setMaxType(int maxType) {
        this.maxType = maxType;
    }

    public double getType() {
        return type;
    }

    public void setType(double type) {
        this.type = type;
    }

    @Override
    public double sum() {
        return 100/(getMaxType()/getType());
    }
}
