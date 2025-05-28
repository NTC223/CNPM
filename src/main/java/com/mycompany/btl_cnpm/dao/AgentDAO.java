/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.btl_cnpm.dao;

import com.mycompany.btl_cnpm.model.Agent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author MSI-PC
 */
public class AgentDAO extends DAO {

    public AgentDAO() {
        super();
    }
    
    public ArrayList<Agent> searchAgentByName(String name) {
        ArrayList<Agent> agents = new ArrayList<Agent>();
        String sql = "SELECT * FROM tblAgent WHERE name LIKE ?";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, "%" + name + "%");
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()) {
                Agent agent = new Agent();
                agent.setId(rs.getInt("id"));
                agent.setName(rs.getString("name"));
                agent.setAddress(rs.getString("address"));
                agent.setTel(rs.getString("tel"));
                agents.add(agent);
            }
            preparedStatement.close();
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return agents;
    }
    
    public boolean addAgent(Agent agent) {
        String sql = "INSERT INTO tblAgent(name, address, tel) VALUES(?,?,?)";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, agent.getName());
            preparedStatement.setString(2, agent.getAddress());
            preparedStatement.setString(3, agent.getTel());
            preparedStatement.executeUpdate();
            
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                agent.setId(generatedKeys.getInt(1));
            }
            preparedStatement.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
