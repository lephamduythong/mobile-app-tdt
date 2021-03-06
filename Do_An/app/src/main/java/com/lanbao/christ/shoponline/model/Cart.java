package com.lanbao.christ.shoponline.model;

public class Cart {
    private int idPro;
    private String namePro;
    private long pricePro;
    private String imagePro;
    private int numberPro;

    public Cart(int idPro, String namePro, long pricePro, String imagePro, int numberPro) {
        this.idPro = idPro;
        this.namePro = namePro;
        this.pricePro = pricePro;
        this.imagePro = imagePro;
        this.numberPro = numberPro;
    }

    public int getIdPro() {
        return idPro;
    }

    public void setIdPro(int idPro) {
        this.idPro = idPro;
    }

    public String getNamePro() {
        return namePro;
    }

    public void setNamePro(String namePro) {
        this.namePro = namePro;
    }

    public long getPricePro() {
        return pricePro;
    }

    public void setPricePro(long pricePro) {
        this.pricePro = pricePro;
    }

    public String getImagePro() {
        return imagePro;
    }

    public void setImagePro(String imagePro) {
        this.imagePro = imagePro;
    }

    public int getNumberPro() {
        return numberPro;
    }

    public void setNumberPro(int numberPro) {
        this.numberPro = numberPro;
    }
}
