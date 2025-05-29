/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.btl_cnpm.dao;

import com.mycompany.btl_cnpm.model.ExportedProduct;
import com.mycompany.btl_cnpm.model.ExportReceipt;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

/**
 *
 * @author OS
 */
public class ExportReceiptDAO extends DAO {

    public ExportReceiptDAO() {
        super();
    }

    public boolean addProductOrder(ExportReceipt ExportReceipt) {
        if (ExportReceipt.getExportedProducts() == null || ExportReceipt.getExportedProducts().isEmpty()) {
            return false;
        }
        String sqlAddProductOrder = "INSERT INTO tblExportReceipt(idUser, idAgent, date, note ) VALUES(?,?,?,?)";
        String sqlAddExportedProduct = "INSERT INTO tblExportedProduct(idExportReceipt, idProduct, quantity, unitPrice) VALUES(?,?,?,?)";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sqlAddProductOrder, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, ExportReceipt.getUser().getId());
            preparedStatement.setInt(2, ExportReceipt.getAgent().getId());
            preparedStatement.setTimestamp(3, new Timestamp(ExportReceipt.getDate().getTime()));
            preparedStatement.setString(4, ExportReceipt.getNote());
            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                ExportReceipt.setId(generatedKeys.getInt(1));

                ArrayList<ExportedProduct> exportedProducts = ExportReceipt.getExportedProducts();
                for (ExportedProduct exportedProduct : exportedProducts) {
                    preparedStatement = conn.prepareStatement(sqlAddExportedProduct);
                    preparedStatement.setInt(1, ExportReceipt.getId());
                    preparedStatement.setInt(2, exportedProduct.getProduct().getId());
                    preparedStatement.setInt(3, exportedProduct.getQuantity());
                    preparedStatement.setInt(4, exportedProduct.getUnitPrice());
                    preparedStatement.executeUpdate();
                }
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
