package com.example.models;

import java.io.Serializable;

public class Product implements Serializable {
    int ProductCode;
    String ProductName;
    double ProductPrice;

    public Product(int productCode, String productName, double productPrice) {
        ProductCode = productCode;
        ProductName = productName;
        ProductPrice = productPrice;
    }

    public int getProductCode() {
        return ProductCode;
    }

    public void setProductCode(int productCode) {
        ProductCode = productCode;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public double getProductPrice() {
        return ProductPrice;
    }

    public void setProductPrice(double productPrice) {
        ProductPrice = productPrice;
    }

    public String toString(){
        return this.getProductCode() + " : " + this.ProductName + " : " + this.ProductPrice;
    }
}
