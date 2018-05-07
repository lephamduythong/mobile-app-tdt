package com.lanbao.christ.shoponline.model;

public class Bill {

    private int idpro;
    private int idcustomer;
    private String date;
    private int status;
    private String productData;

    public Bill(int idpro, int idcustomer, String date, int status, String productData) {
        this.idpro = idpro;
        this.idcustomer = idcustomer;
        this.date = date;
        this.status = status;
        this.productData = productData;
    }

    public String getProductData() {
        return productData;
    }

    public int getIdpro() {
        return idpro;
    }

    public void setIdpro(int idpro) {
        this.idpro = idpro;
    }

    public int getIdcustomer() {
        return idcustomer;
    }

    public void setIdcustomer(int idcustomer) {
        this.idcustomer = idcustomer;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
