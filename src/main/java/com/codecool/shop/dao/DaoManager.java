package com.codecool.shop.dao;

public class DaoManager {
    private ProductDao productDao;
    private ProductCategoryDao productCategoryDao;
    private SupplierDao supplierDao;
    private UserDao userDao;
    private static DaoManager instance = null;

    private DaoManager() {
    }

    public static DaoManager getInstance() {
        if (instance == null) {
            instance = new DaoManager();
        }
        return instance;
    }

    public void addDao(ProductDao productDao, ProductCategoryDao productCategoryDao, SupplierDao supplierDao, UserDao userDao) {
        this.productCategoryDao = productCategoryDao;
        this.supplierDao = supplierDao;
        this.productDao = productDao;
        this.userDao = userDao;
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
}
