package com.example.hnino.businessdirectory.entities;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by hnino on 13/11/2017.
 */
@Entity
public class Enterprise {

    @Id
    private Long id;
    private String name;
    private String url;
    private String phone;
    private String email;
    private String products;
    private String classification;
    @Generated(hash = 43413846)
    public Enterprise(Long id, String name, String url, String phone, String email,
            String products, String classification) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.phone = phone;
        this.email = email;
        this.products = products;
        this.classification = classification;
    }
    @Generated(hash = 790140480)
    public Enterprise() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getUrl() {
        return this.url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getPhone() {
        return this.phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getEmail() {
        return this.email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getProducts() {
        return this.products;
    }
    public void setProducts(String products) {
        this.products = products;
    }
    public String getClassification() {
        return this.classification;
    }
    public void setClassification(String classification) {
        this.classification = classification;
    }


}
