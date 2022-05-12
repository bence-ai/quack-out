package com.codecool.shop.dao;

import com.codecool.shop.config.ReadConfig;
import com.codecool.shop.dao.implementation.ProductCategoryDaoJdbc;
import com.codecool.shop.dao.implementation.ProductDaoJdbc;
import com.codecool.shop.dao.implementation.SupplierDaoJdbc;
import com.codecool.shop.dao.implementation.UserJdbcDao;
import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.sql.SQLException;

public class DBManager {
    private final ReadConfig properties;
    ProductDao productDao;
    ProductCategoryDao productCategoryDao;
    SupplierDao supplierDao;
    UserDao userDao;

    public DBManager(ReadConfig properties) {
        this.properties = properties;
    }

    public DataSource connect() {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();

        dataSource.setDatabaseName(properties.getProperty().getProperty("database"));
        dataSource.setUser(properties.getProperty().getProperty("user"));
        dataSource.setPassword(properties.getProperty().getProperty("password"));
        dataSource.setServerName(properties.getProperty().getProperty("url"));

        try {
            dataSource.getConnection().close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return dataSource;
    }

    public void run() {
        try {
            setup();
        } catch (SQLException throwables) {
            System.err.println("Could not connect to the database.");
            return;
        }

        mainMenu();
    }

    private void setup() throws SQLException {
        DataSource dataSource = connect();
        productCategoryDao = new ProductCategoryDaoJdbc(dataSource);
        supplierDao = new SupplierDaoJdbc(dataSource);
        productDao = new ProductDaoJdbc(dataSource);
        userDao = new UserJdbcDao(dataSource);
    }

    public ProductDao getProductDao() {
        return productDao;
    }

    public ProductCategoryDao getProductCategoryDao() {
        return productCategoryDao;
    }

    public SupplierDao getSupplierDao() {
        return supplierDao;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    private void mainMenu() {
        boolean running = true;
    }
}
