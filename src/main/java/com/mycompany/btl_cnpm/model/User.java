/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.btl_cnpm.model;
import java.io.Serializable;
/**
 *
 * @author MSI-PC
 */
public class User implements Serializable {
    private int id;
    private String username;
    private String password;
    private String fullname;
    private String role;
    
    public User() {
        super();
    }
    
    public User(String username, String password, String fullname, String role) {
        super();
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.role = role;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }   
}
