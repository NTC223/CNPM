/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.btl_cnpm.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author OS
 */
public class ImportReceipt implements Serializable {
    private int id;
    private Date date;
    private String note;
    private Supplier supplier;
    private User user;
    private ArrayList<ImportedProduct> importedProducts;
    
    public ImportReceipt() {
        super();
        this.importedProducts = new ArrayList<ImportedProduct>();
    }

    public ImportReceipt(Date date, String note, Supplier supplier, User user) {
        super();
        this.date = date;
        this.note = note;
        this.supplier = supplier;
        this.user = user;
        this.importedProducts = new ArrayList<ImportedProduct>();
    }
   
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setAgent(Supplier supplier) {
        this.supplier = supplier;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void addImportedProduct(ImportedProduct importedProduct) {
        this.importedProducts.add(importedProduct);
    }

    public ArrayList<ImportedProduct> getImportedProducts() {
        return importedProducts;
    }

    public int getTotalPrice() {
        int totalPrice = 0;
        for (ImportedProduct i : importedProducts) {
            totalPrice += i.getSubTotal();
        }
        return totalPrice;
    }
    
    public int getTotalProductQuantity() {
        int totalProductQuantity = 0;
        for (ImportedProduct i : importedProducts) {
            totalProductQuantity += i.getQuantity();
        }
        return totalProductQuantity;
    }
}
