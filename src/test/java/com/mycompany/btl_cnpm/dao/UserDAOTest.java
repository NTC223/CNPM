/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.btl_cnpm.dao;

import com.mycompany.btl_cnpm.model.User;
import static org.junit.Assert.assertTrue;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author OS
 */
public class UserDAOTest {
    
    private UserDAO userDAO;
    
    public UserDAOTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        userDAO = new UserDAO();
    }
    
    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testCheckLoginSuccess() {
        System.out.println("checkLogin - Success");
        User user = new User();
        user.setUsername("a");
        user.setPassword("a@123");
        
        boolean result = userDAO.checkLogin(user);
        
        // Kiểm tra đăng nhập thành công
        assertTrue(result);
        
        // Kiểm tra thông tin user được cập nhật sau khi đăng nhập
        assertNotNull(user.getFullname());
        assertNotNull(user.getRole());
        assertTrue(user.getId() > 0);
        
        // In thông tin người dùng
        System.out.println("User ID: " + user.getId());
        System.out.println("User Fullname: " + user.getFullname());
        System.out.println("User Role: " + user.getRole());
    }
    
    @Test
    public void testCheckLoginFailWithInvalidUsername() {
        System.out.println("checkLogin - Invalid Username");
        User user = new User();
        user.setUsername("nonexistentuser123");
        user.setPassword("password123");
        
        boolean result = userDAO.checkLogin(user);

        assertFalse(result);
    }
    
    @Test
    public void testCheckLoginFailWithWrongPassword() {
        System.out.println("checkLogin - Wrong Password");
        User user = new User();
        user.setUsername("admin");  // Username có trong DB
        user.setPassword("wrongpassword123"); // Password sai
        
        boolean result = userDAO.checkLogin(user);
        
        assertFalse(result);
    }
}
