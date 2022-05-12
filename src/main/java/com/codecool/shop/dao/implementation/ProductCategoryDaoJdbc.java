package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.DaoManager;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductCategoryDaoJdbc implements ProductCategoryDao {

    private final DataSource dataSource;

    public ProductCategoryDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void add(ProductCategory category) {

    }

    @Override
    public ProductCategory find(int id) {
        ProductCategory category = null;
        try (Connection connection = dataSource.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM category " +
                    "INNER JOIN product ON product.category_id = category.id " +
                            "WHERE product.id = ?;");
            preparedStatement.setInt(1, id);
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                category = new ProductCategory(
                        result.getString(2),
                        result.getString(3),
                        result.getString(4));
                category.setId(result.getInt(1));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
//        ProductDao productDao = DaoManager.getInstance().getProductDao();
//        for (Product product : productDao.getBy(category)) {
//            category.addProduct(product);
//        }
        return category;
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<ProductCategory> getAll() {
        List<ProductCategory> categories = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM category");
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                ProductCategory productCategory = new ProductCategory(
                        result.getString(2),
                        result.getString(3),
                        result.getString(4));
                productCategory.setId(result.getInt(1));

                categories.add(productCategory);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        ProductDao productDao = DaoManager.getInstance().getProductDao();
        for (ProductCategory productCategory: categories) {
            for (Product product : productDao.getBy(productCategory)) {
                productCategory.addProduct(product);
            }
        }
        return categories;
    }
}
