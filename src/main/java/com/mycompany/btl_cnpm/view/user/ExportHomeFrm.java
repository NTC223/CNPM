package com.mycompany.btl_cnpm.view.user;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.mycompany.btl_cnpm.model.User;
import com.mycompany.btl_cnpm.view.searchagent.SearchAgentFrm;

public class ExportHomeFrm extends JFrame implements ActionListener {
    private JButton btnImporting;
    private User user;
    
    public ExportHomeFrm(User user) {
        this.user = user;
        initComponents();
    }
    
    private void initComponents() {
        // Main panel
        JPanel pnMain = new JPanel(new BorderLayout());
        pnMain.setBackground(new Color(240, 240, 240));
        pnMain.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        
        // User info panel
        JPanel userPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        userPanel.setBackground(new Color(240, 240, 240));
        JLabel lblLoggedIn = new JLabel("Logged in as: " + user.getFullname());
        lblLoggedIn.setFont(new Font("Arial", Font.ITALIC, 12));
        userPanel.add(lblLoggedIn);
        
        pnMain.add(userPanel, BorderLayout.NORTH);
        
        // Center panel 
        JPanel pnCenter = new JPanel();
        pnCenter.setLayout(new BoxLayout(pnCenter, BoxLayout.Y_AXIS));
        pnCenter.setBackground(new Color(240, 240, 240));

        pnCenter.add(Box.createVerticalStrut(100));
        
        // Welcome label panel
        JPanel pnWelcome = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pnWelcome.setBackground(new Color(240, 240, 240));
        JLabel lblWelcome = new JLabel("Welcome " + user.getFullname() + " (" + user.getRole() + ")");
        lblWelcome.setFont(new Font("Arial", Font.BOLD, 18));
        pnWelcome.add(lblWelcome);
        
        pnCenter.add(pnWelcome);
        pnCenter.add(Box.createVerticalStrut(60));

        JPanel pnButton = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pnButton.setBackground(new Color(240, 240, 240));
        
        btnImporting = new JButton("Exoport Products");
        btnImporting.setPreferredSize(new Dimension(180, 50));
        btnImporting.setFont(new Font("Arial", Font.BOLD, 14));
        btnImporting.setFocusPainted(false);
        btnImporting.setBorder(BorderFactory.createRaisedBevelBorder());
        btnImporting.addActionListener(this);
        
        pnButton.add(btnImporting);
        pnCenter.add(pnButton);
   
        pnCenter.add(Box.createVerticalStrut(100));
  
        pnMain.add(pnCenter, BorderLayout.CENTER);

        this.setContentPane(pnMain);
        this.setSize(650, 550);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Import Home");
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnImporting) {
            SearchAgentFrm supFrm = new SearchAgentFrm(user);
            supFrm.setVisible(true);
            this.dispose();
        }
    }
}
