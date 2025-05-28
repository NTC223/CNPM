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
public class ExportReceipt implements Serializable {
    private int id;
    private Date date;
    private String note;
    private Agent agent;
    private User user;
    private ArrayList<ExportedProduct> exportedProducts;
    
    public ExportReceipt() {
        super();
        this.exportedProducts = new ArrayList<ExportedProduct>();
    }

    public ExportReceipt(Date date, String note, Agent agent, User user) {
        super();
        this.date = date;
        this.note = note;
        this.agent = agent;
        this.user = user;
        this.exportedProducts = new ArrayList<ExportedProduct>();
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

    public Agent getAgent() {
        return agent;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void addExportedProduct(ExportedProduct exportedProduct) {
        this.exportedProducts.add(exportedProduct);
    }

    public ArrayList<ExportedProduct> getExportedProducts() {
        return exportedProducts;
    }

    public int getTotalPrice() {
        int totalPrice = 0;
        for (ExportedProduct i : exportedProducts) {
            totalPrice += i.getSubTotal();
        }
        return totalPrice;
    }
    
    public int getTotalProductQuantity() {
        int totalProductQuantity = 0;
        for (ExportedProduct i : exportedProducts) {
            totalProductQuantity += i.getQuantity();
        }
        return totalProductQuantity;
    }
}
