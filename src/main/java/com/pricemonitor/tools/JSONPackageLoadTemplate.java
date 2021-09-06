package com.pricemonitor.tools;

import com.pricemonitor.entity.Product;

import java.util.ArrayList;
import java.util.List;

public class JSONPackageLoadTemplate {

    private java.util.List<Product> list = new ArrayList<>();

    public List<Product> getList() {
        return list;
    }

    public void setList(List<Product> list) {
        this.list = list;
    }
}
