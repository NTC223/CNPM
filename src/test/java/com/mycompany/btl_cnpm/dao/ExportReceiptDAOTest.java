/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.btl_cnpm.dao;

import com.mycompany.btl_cnpm.model.ExportedProduct;
import com.mycompany.btl_cnpm.model.Product;
import com.mycompany.btl_cnpm.model.ExportReceipt;
import com.mycompany.btl_cnpm.model.Agent;
import com.mycompany.btl_cnpm.model.User;
import java.util.Date;
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
public class ExportReceiptDAOTest {
    
    private ExportReceiptDAO exportReceiptDAO;
    private AgentDAO agentDAO;
    private ProductDAO productDAO;
    private UserDAO userDAO;
    
    public ExportReceiptDAOTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        exportReceiptDAO = new ExportReceiptDAO();
        agentDAO = new AgentDAO();
        productDAO = new ProductDAO();
        userDAO = new UserDAO();
    }
    
    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testAddProductOrderSuccess() {
        System.out.println("addProductOrder - Success");

        User user = new User();
        user.setUsername("a");
        user.setPassword("a@123");
        boolean loginSuccess = userDAO.checkLogin(user);
        assertTrue(loginSuccess);

        Agent agent = null;
        for (Agent a : agentDAO.searchAgentByName("b")) {
            agent = a;
            break;
        }
        assertNotNull(agent);

        Product product = null;
        for (Product p : productDAO.searchProductByName("Vở")) {
            product = p;
            break;
        }
        assertNotNull(product);

        ExportReceipt exportReceipt = new ExportReceipt();
        exportReceipt.setDate(new Date());
        exportReceipt.setNote("Test receipt");
        exportReceipt.setAgent(agent);
        exportReceipt.setUser(user);

        ExportedProduct exportedProduct = new ExportedProduct(10, 1000, product);
        exportReceipt.addExportedProduct(exportedProduct);
        
        boolean result = exportReceiptDAO.addProductOrder(exportReceipt);

        assertTrue(result);

        assertTrue(exportReceipt.getId() > 0);
        System.out.println("Receipt created with ID: " + exportReceipt.getId());
    }
    
    @Test
    public void testAddProductOrderFailWithNoProducts() {
        System.out.println("addProductOrder - No Products");

        User user = new User();
        user.setUsername("a"); 
        user.setPassword("a@123");
        boolean loginSuccess = userDAO.checkLogin(user);
        assertTrue(loginSuccess);
        
        Agent agent = null;
        for (Agent a : agentDAO.searchAgentByName("b")) {
            agent = a;
            break;
        }
        assertNotNull(agent);
        
        ExportReceipt exportReceipt = new ExportReceipt();
        exportReceipt.setDate(new Date());
        exportReceipt.setNote("Empty receipt");
        exportReceipt.setAgent(agent);
        exportReceipt.setUser(user);
        
        boolean result = exportReceiptDAO.addProductOrder(exportReceipt);
        
        assertFalse(result);
    }
}
