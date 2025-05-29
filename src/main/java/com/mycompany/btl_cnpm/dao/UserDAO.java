/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.btl_cnpm.dao;

import com.mycompany.btl_cnpm.model.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author OS
 */
public class UserDAO extends DAO {
    
    public UserDAO() {
        super();
    }
    
    public boolean checkLogin(User user) {
        boolean isValid = false;
        String sql = "SELECT * FROM tblUser WHERE username=? AND password=?";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                user.setId(resultSet.getInt("id"));
                String fullname = resultSet.getString("fullname");
                user.setFullname(fullname);
                user.setRole(resultSet.getString("role"));
                isValid = true;
            }
            
            preparedStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isValid;
    }
}
