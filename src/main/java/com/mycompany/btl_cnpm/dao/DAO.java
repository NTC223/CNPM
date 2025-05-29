/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.btl_cnpm.dao;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author OS
 */
public class DAO {
    protected static Connection conn;
    
    public DAO() {
        if(conn == null) {
            String dbUrl = 
                "jdbc:mysql://localhost:3307/ExportProduct?autoReconnect=true&useSSL=false";
            String jdbcDriver = "com.mysql.cj.jdbc.Driver";
            try {
                Class.forName(jdbcDriver);
                conn = DriverManager.getConnection(dbUrl, "root", "admin123");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
