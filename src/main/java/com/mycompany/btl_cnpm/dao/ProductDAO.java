/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.btl_cnpm.dao;

import com.mycompany.btl_cnpm.model.Product;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
/**
 *
 * @author OS
 */
public class ProductDAO extends DAO {

    public ProductDAO() {
        super();
    }
    
    private int getProductQuantity(Product product){
        String sql = "SELECT " +
                     "COALESCE((SELECT SUM(quantity) FROM tblImportedProduct WHERE idProduct = ?), 0) - " +
                     "COALESCE((SELECT SUM(quantity) FROM tblExportedProduct WHERE idProduct = ?), 0) AS quantity";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, product.getId());
            preparedStatement.setInt(2, product.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("quantity");
            }
            preparedStatement.close();
            resultSet.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0; // Mặc định nếu có lỗi
    }
    
    public ArrayList<Product> searchProductByName(String name) {
        ArrayList<Product> products = new ArrayList<Product>();
        String sql = "SELECT * FROM tblProduct WHERE name LIKE ?";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, "%" + name + "%");
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                Product product = new Product();
                product.setId(resultSet.getInt("id"));
                product.setName(resultSet.getString("name"));
                product.setDescription(resultSet.getString("description"));
                product.setQuantity(getProductQuantity(product));
                products.add(product);
            }
            preparedStatement.close();
            resultSet.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return products;
    }
}
