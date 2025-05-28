package com.mycompany.btl_cnpm.view.receipt;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.mycompany.btl_cnpm.dao.ExportReceiptDAO;
import com.mycompany.btl_cnpm.model.ExportedProduct;
import com.mycompany.btl_cnpm.model.ExportReceipt;
import com.mycompany.btl_cnpm.model.User;
import com.mycompany.btl_cnpm.view.searchagent.SearchAgentFrm;

public class ConfirmFrm extends JFrame implements ActionListener {
    private JPanel mainPanel, headerPanel, infoPanel, tablePanel, buttonPanel;
    private JLabel lblTitle, lblUserInfo;
    private JTable tblProducts;
    private DefaultTableModel tableModel;
    private JButton btnConfirm, btnCancel;
    
    private ExportReceipt receipt;
    
    public ConfirmFrm(ExportReceipt receipt) {
        this.receipt = receipt;
        initComponents();
    }
    
    private void initComponents() {
        // Main Panel
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(10, 10));
        mainPanel.setBackground(new Color(240, 240, 240));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        
        // Header Panel
        headerPanel = new JPanel();
        headerPanel.setLayout(new BorderLayout());
        headerPanel.setBackground(new Color(240, 240, 240));
        
        lblTitle = new JLabel("Receipt Confirmation");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
        headerPanel.add(lblTitle, BorderLayout.WEST);
        
        // User Info
        lblUserInfo = new JLabel("Logged in as: " + receipt.getUser().getFullname());
        lblUserInfo.setFont(new Font("Arial", Font.PLAIN, 12));
        headerPanel.add(lblUserInfo, BorderLayout.EAST);
        
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        
        // Info Panel
        infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBackground(new Color(240, 240, 240));
        infoPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Receipt Information"));
        
        // Receipt info
        JPanel gridPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        gridPanel.setBackground(new Color(240, 240, 240));
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        
        gridPanel.add(new JLabel("Created by:"));
        gridPanel.add(new JLabel(receipt.getUser().getFullname()));
        
        gridPanel.add(new JLabel("Date:"));
        gridPanel.add(new JLabel(dateFormat.format(receipt.getDate())));
        
        gridPanel.add(new JLabel("Supplier:"));
        gridPanel.add(new JLabel(receipt.getAgent().getName() + " (Address: " + receipt.getAgent().getAddress() + ", Tel: " + receipt.getAgent().getTel() + ")"));
        
        gridPanel.add(new JLabel("Total Items:"));
        gridPanel.add(new JLabel(String.valueOf(receipt.getTotalProductQuantity())));
        
        infoPanel.add(gridPanel);
        infoPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        
        // Product Table 
        tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBackground(new Color(240, 240, 240));
        tablePanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Exported Products"));
        
        String[] columns = {"STT", "Product", "Description", "Quantity", "Unit Price", "Subtotal"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; 
            }
        };
        
        tblProducts = new JTable(tableModel);
        tblProducts.getTableHeader().setReorderingAllowed(false);
        tblProducts.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        tblProducts.setRowHeight(25);
    
        ArrayList<ExportedProduct> products = receipt.getExportedProducts();
        int stt = 1;
        for (ExportedProduct ip : products) {
            Object[] row = {
                stt++,
                ip.getProduct().getName(),
                ip.getProduct().getDescription(),
                ip.getQuantity(),
                ip.getUnitPrice(),
                ip.getSubTotal()
            };
            tableModel.addRow(row);
        }
        
        JScrollPane scrollPane = new JScrollPane(tblProducts);
        scrollPane.setPreferredSize(new Dimension(600, 200));
        tablePanel.add(scrollPane, BorderLayout.CENTER);

        JPanel totalPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        totalPanel.setBackground(new Color(240, 240, 240));
        
        JLabel lblTotal = new JLabel("Total Price: ");
        lblTotal.setFont(new Font("Arial", Font.BOLD, 14));
        totalPanel.add(lblTotal);
        
        JLabel txtTotal = new JLabel(String.valueOf(receipt.getTotalPrice()));
        txtTotal.setFont(new Font("Arial", Font.BOLD, 14));
        totalPanel.add(txtTotal);
        
        tablePanel.add(totalPanel, BorderLayout.SOUTH);
        
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBackground(new Color(240, 240, 240));
        centerPanel.add(infoPanel);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        centerPanel.add(tablePanel);
        
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        
        buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setBackground(new Color(240, 240, 240));
        
        btnConfirm = new JButton("Confirm");
        btnConfirm.setFont(new Font("Arial", Font.BOLD, 14));
        btnConfirm.setPreferredSize(new Dimension(120, 40));
        btnConfirm.setBackground(new Color(70, 130, 180));
        btnConfirm.setForeground(Color.WHITE);
        btnConfirm.addActionListener(this);
        buttonPanel.add(btnConfirm);
        
        btnCancel = new JButton("Cancel");
        btnCancel.setFont(new Font("Arial", Font.BOLD, 14));
        btnCancel.setPreferredSize(new Dimension(120, 40));
        btnCancel.addActionListener(this);
        buttonPanel.add(btnCancel);
        
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        this.setContentPane(mainPanel);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(650, 550);
        this.setLocationRelativeTo(null);
        this.setTitle("Confirm Receipt");
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnConfirm) {
            ExportReceiptDAO receiptDAO = new ExportReceiptDAO();
            
            if (receiptDAO.addProductOrder(receipt)) {
                JOptionPane.showMessageDialog(this, "Receipt confirmed successfully!");
                backToSupplierFrm(receipt.getUser());
            } else {
                JOptionPane.showMessageDialog(this, "Failed to confirm receipt!");
            }
        } else if (e.getSource() == btnCancel) {
            int choice = JOptionPane.showConfirmDialog(this, 
                    "Are you sure you want to cancel this receipt?", 
                    "Cancel Receipt", 
                    JOptionPane.YES_NO_OPTION);
            
            if (choice == JOptionPane.YES_OPTION) {
                backToSupplierFrm(receipt.getUser());
            }
        }
    }

    private void backToSupplierFrm(User u) {
        SearchAgentFrm supplierFrm = new SearchAgentFrm(u);
        supplierFrm.setVisible(true);
        this.dispose();
    }
} 