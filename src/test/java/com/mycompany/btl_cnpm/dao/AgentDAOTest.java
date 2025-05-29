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
 * @author OS
 */
public class AgentDAOTest {
    
    private AgentDAO agentDAO;
    
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
        agentDAO = new AgentDAO();
    }
    
    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testSearchAgentByNameException1() {
        System.out.println("searchAgentByName - Exception Case 1");
        String key = "xxxxxxxxxxxx";
        ArrayList<Agent> agents = agentDAO.searchAgentByName(key);
        assertNotNull(agents);
        assertEquals(0, agents.size());
    }
    
    @Test
    public void testSearchAgentByNameException2() {
        System.out.println("searchAgentByName - Exception Case 2");
        String key = "sd";
        ArrayList<Agent> agents = agentDAO.searchAgentByName(key);
        assertNotNull(agents);
        assertEquals(0, agents.size());
    }

    @Test
    public void testSearchAgentByNameStandard1() {
        System.out.println("searchAgentByName - Standard Case 1");
        String key = "An";
        ArrayList<Agent> agents = agentDAO.searchAgentByName(key);
        assertNotNull(agents);
        assertEquals(1, agents.size());
        for(int i = 0; i < agents.size(); i++){
            assertTrue(agents.get(i).getName().toLowerCase().contains(key.toLowerCase()));
        }
        return;
    }

    @Test
    public void testSearchAgentByNameStandard2() {
        System.out.println("searchAgentByName - Standard Case 2");
        String key = "B";
        ArrayList<Agent> agents = agentDAO.searchAgentByName(key);
        assertNotNull(agents);
        assertEquals(3, agents.size());
        for(int i = 0; i < agents.size(); i++){
            assertTrue(agents.get(i).getName().toLowerCase().contains(key.toLowerCase()));
        }
        return;
    }
    
    @Test
    public void testSearchAgentByNameEmptyKeyword() {
        System.out.println("searchAgentByName - Empty Keyword");
        String key = "";
        ArrayList<Agent> agents = agentDAO.searchAgentByName(key);
        assertNotNull(agents);
        assertTrue(agents.size() > 0);
    }

    @Test
    public void testAddAgent() {
        System.out.println("addAgent");
        Agent agent = new Agent();
        String uniqueName = "Test Agent " + System.currentTimeMillis();
        agent.setName(uniqueName);
        agent.setAddress("Test Address");   
        agent.setTel("0123456789");
        
        boolean result = agentDAO.addAgent(agent);
        
        assertTrue(result);
        assertTrue(agent.getId() > 0);
        
        ArrayList<Agent> foundAgents = agentDAO.searchAgentByName(uniqueName);
        assertEquals(1, foundAgents.size());
        
        Agent foundAgent = foundAgents.get(0);
        assertEquals(uniqueName, foundAgent.getName());
        assertEquals("Test Address", foundAgent.getAddress());
        assertEquals("0123456789", foundAgent.getTel());
    }
}
