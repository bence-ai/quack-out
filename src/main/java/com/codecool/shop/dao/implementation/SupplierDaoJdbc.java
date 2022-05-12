package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.Supplier;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierDaoJdbc implements SupplierDao {
    private final DataSource dataSource;

    public SupplierDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void add(Supplier supplier) {

    }

    @Override
    public Supplier find(int id) {
        ProductDaoJdbc productDaoJdbc = new ProductDaoJdbc(dataSource);
        Supplier supplier = null;
        try (Connection connection = dataSource.getConnection()) {
            String query = "SELECT * FROM supplier " +
                    "INNER JOIN product p on supplier.id = p.supplier_id " +
                    "WHERE p.id = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                supplier = new Supplier(result.getString(2), result.getString(3));
                supplier.setId(result.getInt(1));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return supplier;
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<Supplier> getAll() {
        ProductDaoJdbc productDaoJdbc = new ProductDaoJdbc(dataSource);
        List<Supplier> suppliers = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM supplier");
            ResultSet result = preparedStatement.executeQuery();
            System.out.println("--Product added" + result);
            while (result.next()) {
                Supplier supplier = new Supplier(result.getString(2), result.getString(3));
                supplier.setId(result.getInt(1));
                for (Product product : productDaoJdbc.getBy(supplier)) {
                    supplier.addProduct(product);
                }
                suppliers.add(supplier);
                result.next();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return suppliers;
    }
}
