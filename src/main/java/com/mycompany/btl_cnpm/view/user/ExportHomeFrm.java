package com.mycompany.btl_cnpm.view.user;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
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
    private JButton btnExporting;
    private JButton btnCancel;
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
        JLabel lblWelcome = new JLabel("Export Home");
        lblWelcome.setFont(new Font("Arial", Font.BOLD, 24));
        pnWelcome.add(lblWelcome);
        
        pnCenter.add(pnWelcome);
        pnCenter.add(Box.createVerticalStrut(60));

        JPanel pnButton = new JPanel();
        pnButton.setLayout(new BoxLayout(pnButton, BoxLayout.Y_AXIS));
        pnButton.setBackground(new Color(240, 240, 240));
        
        btnExporting = new JButton("Export Products");
        btnExporting.setPreferredSize(new Dimension(180, 50));
        btnExporting.setMaximumSize(new Dimension(180, 50));
        btnExporting.setFont(new Font("Arial", Font.BOLD, 14));
        btnExporting.setFocusPainted(false);
        btnExporting.setBorder(BorderFactory.createRaisedBevelBorder());
        btnExporting.addActionListener(this);
        btnExporting.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        btnCancel = new JButton("Cancel");
        btnCancel.setPreferredSize(new Dimension(180, 50));
        btnCancel.setMaximumSize(new Dimension(180, 50));
        btnCancel.setFont(new Font("Arial", Font.BOLD, 14));
        btnCancel.setFocusPainted(false);
        btnCancel.setBorder(BorderFactory.createRaisedBevelBorder());
        btnCancel.addActionListener(this);
        btnCancel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        pnButton.add(btnExporting);
        pnButton.add(Box.createVerticalStrut(20));
        pnButton.add(btnCancel);
        
        pnCenter.add(pnButton);
   
        pnCenter.add(Box.createVerticalStrut(100));
  
        pnMain.add(pnCenter, BorderLayout.CENTER);

        this.setContentPane(pnMain);
        this.setSize(650, 550);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Export Home");
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnExporting) {
            SearchAgentFrm searchAgentFrm = new SearchAgentFrm(user);
            searchAgentFrm.setVisible(true);
            this.dispose();
        } else if (e.getSource() == btnCancel) {
            LoginFrm loginFrm = new LoginFrm();
            loginFrm.setVisible(true);
            this.dispose();
        }
    }
}
