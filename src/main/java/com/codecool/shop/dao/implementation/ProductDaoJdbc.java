package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.DaoManager;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoJdbc implements ProductDao {
    DataSource dataSource;

    private final List<Product> data = new ArrayList<>();

    public ProductDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;

    }

    @Override
    public void add(Product product) {

    }

    @Override
    public Product find(int id) {
        return null;
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<Product> getAll() {
        ProductCategoryDao productCategoryDao = new ProductCategoryDaoJdbc(dataSource);
        SupplierDao supplierDao = new SupplierDaoJdbc(dataSource);
        List<Product> products = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM product");
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                Product product = new Product(result.getString(2),
                        result.getFloat(3),
                        result.getString(4),
                        result.getString(5),
                        productCategoryDao.find(result.getInt(6)),
                        supplierDao.find(result.getInt(7)));
                product.setId(result.getInt(1));
                products.add(product);
                result.next();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return products;
    }

    @Override
    public List<Product> getBy(Supplier supplier) {
        ProductCategoryDao productCategoryDao = new ProductCategoryDaoJdbc(dataSource);
        SupplierDao supplierDao = new SupplierDaoJdbc(dataSource);
        List<Product> products = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()){
            String query = "SELECT * FROM product " +
                    "INNER JOIN supplier s on product.supplier_id = s.id " +
                    "WHERE s.id = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, supplier.getId());
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                Product product = new Product(
                        result.getString(2),
                        result.getFloat(3),
                        result.getString(4),
                        result.getString(5),
                        productCategoryDao.find(result.getInt(6)),
                        supplierDao.find(result.getInt(7)));
                product.setId(result.getInt(1));
                products.add(product);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return products;
    }

    @Override
    public List<Product> getBy(ProductCategory productCategory) {
        System.out.println("-- id: " + productCategory.getId());
        ProductCategoryDao productCategoryDao = DaoManager.getInstance().getProductCategoryDao();
        SupplierDao supplierDao = DaoManager.getInstance().getSupplierDao();
        List<Product> products = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()){
            String query = "SELECT * FROM product " +
                    "INNER JOIN category c on product.category_id = c.id " +
                    "WHERE c.id = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, productCategory.getId());
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                Product product = new Product(
                        result.getString(2),
                        result.getFloat(3),
                        result.getString(4),
                        result.getString(5),
                        productCategoryDao.find(result.getInt(1)),
                        supplierDao.find(result.getInt(1)));
                product.setId(result.getInt(1));
                products.add(product);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return products;
    }
}
