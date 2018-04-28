package com.lanbao.christ.shoponline.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ThongLee on 4/28/2018.
 */

public class ListProduct implements Serializable {
    List<Product> products = new ArrayList<Product>();
    public  List<Product> getProducts() {
        return products;
    }
    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
