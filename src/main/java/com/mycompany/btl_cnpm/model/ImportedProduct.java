/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.btl_cnpm.model;

import java.io.Serializable;

/**
 *
 * @author OS
 */
public class ImportedProduct implements Serializable {
    private int id;
    private int quantity;
    private int unitPrice;
    private Product product;
    
    public ImportedProduct() {
        super();
    }
    
    public ImportedProduct(int quantity, int unitPrice, Product product) {
        super();
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.product = product;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(int unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getSubTotal() {
        return quantity * unitPrice;
    }
}
