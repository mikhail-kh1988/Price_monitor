package com.pricemonitor.entity;


import com.fasterxml.jackson.annotation.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "product")
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @JsonMerge
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(
            name = "PRODUCT_PRICE",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "price_id")
    )
    private java.util.List<Price> priceList;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;

    @Column(name = "boxing")
    private String boxing;

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

    public List<Price> getPriceList() {
        return priceList;
    }

    public void setPriceList(List<Price> priceList) {
        this.priceList = priceList;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getBoxing() {
        return boxing;
    }

    public void setBoxing(String boxing) {
        this.boxing = boxing;
    }
}
