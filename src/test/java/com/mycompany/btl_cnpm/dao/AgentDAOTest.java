/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.btl_cnpm.dao;

import com.mycompany.btl_cnpm.model.Agent;
import java.util.ArrayList;
import static org.junit.Assert.assertTrue;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author MSI-PC
 */
public class AgentDAOTest {
    
    private AgentDAO supplierDAO;
    
    public AgentDAOTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        supplierDAO = new AgentDAO();
    }
    
    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testSearchSupplierByNameException1() {
        System.out.println("searchSupplierByName - Exception Case 1");
        String key = "xxxxxxxxxxxx";
        ArrayList<Agent> suppliers = supplierDAO.searchAgentByName(key);
        assertNotNull(suppliers);
        assertEquals(0, suppliers.size());
    }
    
    @Test
    public void testSearchSupplierByNameException2() {
        System.out.println("searchSupplierByName - Exception Case 2");
        String key = "sd";
        ArrayList<Agent> suppliers = supplierDAO.searchAgentByName(key);
        assertNotNull(suppliers);
        assertEquals(0, suppliers.size());
    }

    @Test
    public void testSearchSupplierByNameStandard1() {
        System.out.println("searchSupplierByName - Standard Case 1");
        String key = "An";
        ArrayList<Agent> suppliers = supplierDAO.searchAgentByName(key);
        assertNotNull(suppliers);
        assertEquals(2, suppliers.size());
        for(int i = 0; i < suppliers.size(); i++){
            assertTrue(suppliers.get(i).getName().toLowerCase().contains(key.toLowerCase()));
        }
        return;
    }

    @Test
    public void testSearchSupplierByNameStandard2() {
        System.out.println("searchSupplierByName - Standard Case 2");
        String key = "B";
        ArrayList<Agent> suppliers = supplierDAO.searchAgentByName(key);
        assertNotNull(suppliers);
        assertEquals(4, suppliers.size());
        for(int i = 0; i < suppliers.size(); i++){
            assertTrue(suppliers.get(i).getName().toLowerCase().contains(key.toLowerCase()));
        }
        return;
    }
    
    @Test
    public void testSearchSupplierByNameEmptyKeyword() {
        System.out.println("searchSupplierByName - Empty Keyword");
        String key = "";
        ArrayList<Agent> suppliers = supplierDAO.searchAgentByName(key);
        assertNotNull(suppliers);
        assertTrue(suppliers.size() > 0);
    }

    @Test
    public void testAddSupplier() {
        System.out.println("addSupplier");
        Agent supplier = new Agent();
        String uniqueName = "Test Supplier " + System.currentTimeMillis();
        supplier.setName(uniqueName);
        supplier.setAddress("Test Address");
        supplier.setTel("0123456789");
        
        boolean result = supplierDAO.addAgent(supplier);
        
        assertTrue(result);
        assertTrue(supplier.getId() > 0);
        
        ArrayList<Agent> foundSuppliers = supplierDAO.searchAgentByName(uniqueName);
        assertEquals(1, foundSuppliers.size());
        
        Agent foundSupplier = foundSuppliers.get(0);
        assertEquals(uniqueName, foundSupplier.getName());
        assertEquals("Test Address", foundSupplier.getAddress());
        assertEquals("0123456789", foundSupplier.getTel());
    }
}
