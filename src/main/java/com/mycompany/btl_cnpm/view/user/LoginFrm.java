package com.mycompany.btl_cnpm.view.user;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.Box;
import javax.swing.BoxLayout;
import java.awt.Component;
import java.awt.Dimension;

import com.mycompany.btl_cnpm.dao.UserDAO;
import com.mycompany.btl_cnpm.model.User;


public class LoginFrm extends JFrame implements ActionListener{
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;
     
    public LoginFrm(){
        initComponents();
    }
    private void initComponents() {
        txtUsername = new JTextField(15);
        txtPassword = new JPasswordField(15);
        txtPassword.setEchoChar('*');
        btnLogin = new JButton("Login");
         
        JPanel pnMain = new JPanel();
        pnMain.setSize(this.getSize().width-5, this.getSize().height-20);       
        pnMain.setLayout(new BoxLayout(pnMain, BoxLayout.PAGE_AXIS));
        pnMain.add(Box.createRigidArea(new Dimension(0,10)));
         
        JLabel lblHome = new JLabel("Login");
        lblHome.setAlignmentX(Component.CENTER_ALIGNMENT);  
        lblHome.setFont (lblHome.getFont ().deriveFont (20.0f));
        pnMain.add(lblHome);
        pnMain.add(Box.createRigidArea(new Dimension(0,20)));
         
        JPanel pnUsername = new JPanel();
        pnUsername.setLayout(new FlowLayout());
        pnUsername.add(new JLabel("Username:"));
        pnUsername.add(txtUsername);
        pnMain.add(pnUsername);
         
        JPanel pnPass = new JPanel();
        pnPass.setLayout(new FlowLayout());
        pnPass.add(new JLabel("Password:"));
        pnPass.add(txtPassword);
        pnMain.add(pnPass);;
         
        pnMain.add(btnLogin);   
        pnMain.add(Box.createRigidArea(new Dimension(0,10)));
        btnLogin.addActionListener(this);  
        this.setSize(400,200);              
        this.setLocation(200,10);
        this.setContentPane(pnMain);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setTitle("Login");
    }
    public void actionPerformed(ActionEvent e) {
        if((e.getSource() instanceof JButton)
                &&(((JButton)e.getSource()).equals(btnLogin))) {
            User user = new User();
            user.setUsername(txtUsername.getText());
            user.setPassword(new String(txtPassword.getPassword()));
             
            UserDAO userDAO = new UserDAO();
            if(userDAO.checkLogin(user)) {
                if(user.getRole().equalsIgnoreCase("exporter")) {
                    (new ExportHomeFrm(user)).setVisible(true);
                    this.dispose();
                }else
                    JOptionPane.showMessageDialog(this, 
                                 "The function of the role " + user.getRole() 
                                 + " is under construction!");
            }else {
                JOptionPane.showMessageDialog(this, 
                          "Incorrect username and/or password!");
            }
        }
    }
}