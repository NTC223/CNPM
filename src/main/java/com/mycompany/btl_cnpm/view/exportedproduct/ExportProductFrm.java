package com.mycompany.btl_cnpm.view.exportedproduct;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import com.mycompany.btl_cnpm.model.ExportedProduct;
import com.mycompany.btl_cnpm.model.ExportReceipt;
import com.mycompany.btl_cnpm.view.searchproduct.SearchProductFrm;

public class ExportProductFrm extends JFrame implements ActionListener {
    private JPanel mainPanel, headerPanel, formPanel, buttonPanel;
    private JLabel lblTitle, lblUserInfo;
    private JLabel lblProductID, lblProductName, lblProductDesc, lblProductQuantity;
    private JLabel txtProductID, txtProductName, txtProductDesc, txtProductQuantity;
    private JLabel lblPrice, lblQuantity;
    private JTextField txtPrice, txtQuantity;
    private JButton btnAddToReceipt, btnCancel;
    private ExportReceipt exportReceipt;
    private ExportedProduct currentExportedProduct;
    
    public ExportProductFrm(ExportReceipt exportReceipt) {
        this.exportReceipt = exportReceipt;
        this.currentExportedProduct = exportReceipt.getExportedProducts().get(exportReceipt.getExportedProducts().size()-1);
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
        
        lblTitle = new JLabel("Export Product");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
        headerPanel.add(lblTitle, BorderLayout.WEST);
        
        // User Info
        lblUserInfo = new JLabel("Logged in as: " + exportReceipt.getUser().getFullname());
        lblUserInfo.setFont(new Font("Arial", Font.PLAIN, 12));
        headerPanel.add(lblUserInfo, BorderLayout.EAST);
        
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        
        // Form Panel
        formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBackground(new Color(240, 240, 240));
        formPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Product Information"),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        
        // Product info panel
        JPanel productInfoPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        productInfoPanel.setBackground(new Color(240, 240, 240));
        
        lblProductID = new JLabel("Product ID:");
        lblProductID.setFont(new Font("Arial", Font.BOLD, 14));
        productInfoPanel.add(lblProductID);
        
        txtProductID = new JLabel(String.valueOf(currentExportedProduct.getProduct().getId()));
        txtProductID.setFont(new Font("Arial", Font.PLAIN, 14));
        productInfoPanel.add(txtProductID);
        
        lblProductName = new JLabel("Product Name:");
        lblProductName.setFont(new Font("Arial", Font.BOLD, 14));
        productInfoPanel.add(lblProductName);
        
        txtProductName = new JLabel(currentExportedProduct.getProduct().getName());
        txtProductName.setFont(new Font("Arial", Font.PLAIN, 14));
        productInfoPanel.add(txtProductName);
        
        lblProductDesc = new JLabel("Description:");
        lblProductDesc.setFont(new Font("Arial", Font.BOLD, 14));
        productInfoPanel.add(lblProductDesc);
        
        txtProductDesc = new JLabel(currentExportedProduct.getProduct().getDescription());
        txtProductDesc.setFont(new Font("Arial", Font.PLAIN, 14));
        productInfoPanel.add(txtProductDesc);
        
        lblProductQuantity = new JLabel("Product Quantity: ");
        lblProductQuantity.setFont(new Font("Arial", Font.BOLD, 14));
        productInfoPanel.add(lblProductQuantity);

        txtProductQuantity = new JLabel(String.valueOf(currentExportedProduct.getProduct().getQuantity()));
        txtProductQuantity.setFont(new Font("Arial", Font.PLAIN, 14));
        productInfoPanel.add(txtProductQuantity);
        
        formPanel.add(productInfoPanel);
        formPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        
        // Export details panel
        JPanel exportDetailsPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        exportDetailsPanel.setBackground(new Color(240, 240, 240));
        exportDetailsPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Export Details"));
        
        lblPrice = new JLabel("Unit Price:");
        lblPrice.setFont(new Font("Arial", Font.BOLD, 14));
        exportDetailsPanel.add(lblPrice);
        
        txtPrice = new JTextField();
        txtPrice.setFont(new Font("Arial", Font.PLAIN, 14));
        exportDetailsPanel.add(txtPrice);
        
        lblQuantity = new JLabel("Quantity:");
        lblQuantity.setFont(new Font("Arial", Font.BOLD, 14));
        exportDetailsPanel.add(lblQuantity);
        
        txtQuantity = new JTextField();
        txtQuantity.setFont(new Font("Arial", Font.PLAIN, 14));
        exportDetailsPanel.add(txtQuantity);
        
        formPanel.add(exportDetailsPanel);
        
        mainPanel.add(formPanel, BorderLayout.CENTER);
        
        // Button Panel
        buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setBackground(new Color(240, 240, 240));
        
        btnAddToReceipt = new JButton("Add To Receipt");
        btnAddToReceipt.setFont(new Font("Arial", Font.BOLD, 14));
        btnAddToReceipt.setPreferredSize(new Dimension(150, 40));
        btnAddToReceipt.setBackground(new Color(70, 130, 180));
        btnAddToReceipt.setForeground(Color.WHITE);
        btnAddToReceipt.addActionListener(this);
        buttonPanel.add(btnAddToReceipt);
        
        btnCancel = new JButton("Cancel");
        btnCancel.setFont(new Font("Arial", Font.BOLD, 14));
        btnCancel.setPreferredSize(new Dimension(120, 40));
        btnCancel.addActionListener(this);
        buttonPanel.add(btnCancel);
        
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        this.setContentPane(mainPanel);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(650, 450);
        this.setLocationRelativeTo(null);
        this.setTitle("Export Product");
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnAddToReceipt) {
            try {
                if (txtPrice.getText().trim().isEmpty() || txtQuantity.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please fill in all fields!");
                    return;
                }
                
                int price = Integer.parseInt(txtPrice.getText().trim());
                int quantity = Integer.parseInt(txtQuantity.getText().trim());
                
                if (price <= 0) {
                    JOptionPane.showMessageDialog(this, "Price must be greater than 0!");
                    return;
                }
                
                if (quantity <= 0) {
                    JOptionPane.showMessageDialog(this, "Quantity must be greater than 0!");
                    return;
                }
                
                if (quantity > currentExportedProduct.getProduct().getQuantity()){
                    JOptionPane.showMessageDialog(this, "Not enough stock for this product!");
                    return;
                }
                currentExportedProduct.setQuantity(quantity);
                currentExportedProduct.setUnitPrice(price);
                
                SearchProductFrm productFrm = new SearchProductFrm(exportReceipt);
                productFrm.setVisible(true);
                this.dispose();
                
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter unit price and quantity values!");
            }
        } else if (e.getSource() == btnCancel) {
            exportReceipt.getExportedProducts().remove(currentExportedProduct);
            SearchProductFrm searchProductFrm = new SearchProductFrm(exportReceipt);
            searchProductFrm.setVisible(true);
            this.dispose();
        }
    }
} 