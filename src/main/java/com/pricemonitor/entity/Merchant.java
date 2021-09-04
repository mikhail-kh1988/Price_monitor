package com.pricemonitor.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "merchant")
public class Merchant implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @JsonBackReference
    @ManyToMany(cascade = CascadeType.PERSIST)
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(
            name = "CATEGORY_MERCHANT",
            joinColumns = @JoinColumn(name = "merchant_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private java.util.List<Category> categoryList;

    @JsonBackReference
    @ManyToMany(cascade = CascadeType.PERSIST)
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(
            name = "PRODUCT_MERCHANT",
            joinColumns = @JoinColumn(name = "merchant_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private java.util.List<Product> productList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public void addCategory(Category category){
        categoryList.add(category);
    }
}
