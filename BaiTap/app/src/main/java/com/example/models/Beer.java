package com.example.models;

public class Beer {
    private int beerCode;
    private String beerName;
    private double beerPrice;
    private int beerThumb;


    public Beer(int beerCode, String beerName, double beerPrice, int beerThumb) {
        this.beerCode = beerCode;
        this.beerName = beerName;
        this.beerPrice = beerPrice;
        this.beerThumb = beerThumb;
    }

    public int getBeerCode() {
        return beerCode;
    }

    public void setBeerCode(int beerCode) {
        this.beerCode = beerCode;
    }

    public String getBeerName() {
        return beerName;
    }

    public void setBeerName(String beerName) {
        this.beerName = beerName;
    }

    public double getBeerPrice() {
        return beerPrice;
    }

    public void setBeerPrice(double beerPrice) {
        this.beerPrice = beerPrice;
    }

    public int getBeerThumb() {
        return beerThumb;
    }

    public void setBeerThumb(int beerThumb) {
        this.beerThumb = beerThumb;
    }
}
