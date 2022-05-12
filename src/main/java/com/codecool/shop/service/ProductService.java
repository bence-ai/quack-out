package com.codecool.shop.service;

import com.codecool.shop.dao.DaoManager;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;

import java.util.List;

public class ProductService{
    private final ProductDao productDao;
    private final ProductCategoryDao productCategoryDao;

    public ProductService(ProductDao productDao, ProductCategoryDao productCategoryDao) {
        this.productDao = productDao;
        this.productCategoryDao = productCategoryDao;
    }

    public ProductCategory getProductCategory(int categoryId){
        return productCategoryDao.find(categoryId);
    }

    public List<Product> getProductsForCategory(int categoryId){
        ProductCategory category = productCategoryDao.find(categoryId);
        return productDao.getBy(category);
    }
    public List<Product> getProductsForSupplier(int supplierId){
        var supplier = DaoManager.getInstance().getSupplierDao().find(supplierId);
        return productDao.getBy(supplier);
    }


}
