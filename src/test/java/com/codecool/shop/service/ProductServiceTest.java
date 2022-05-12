package com.codecool.shop.service;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ProductServiceTest {
    private ProductDao productDataStore;
    private ProductCategoryDao productCategoryDataStore;
    private ProductService productService;
    private final int categoryId = 2;
    private final ProductCategory productCategory = new ProductCategory("Star Wars ducks", "Star Wars department", "test description");

    @BeforeEach
    public void setup() {
        productDataStore = mock(ProductDao.class);
        productCategoryDataStore = mock(ProductCategoryDao.class);
        productService = new ProductService(productDataStore, productCategoryDataStore);
    }

    @Test
    public void getProductCategory_returnsCorrectCategory() {
        when(productCategoryDataStore.find(categoryId)).thenReturn(productCategory);
        assertEquals(productService.getProductCategory(categoryId), productCategory);
    }

    @Test
    public void getProductCategory_productCategoryIDDoesNotExist() {
        when(productCategoryDataStore.find(categoryId)).thenReturn(null);
        assertNull(productService.getProductCategory(categoryId));
    }

    @Test
    public void getProductsForCategory_returnsCorrectProducts() {
        Supplier quackQuack = new Supplier("QuackQuack", "Need a duck? Order from us!");
        ArrayList<Product> products = new ArrayList<>();
        products.add(new Product("DuckMaul", 5.90f, "USD", "Darth Maul rubber duck", productCategory, quackQuack));
        products.add(new Product("PrincessDuck", 5.90f, "USD", "Princess Lila rubber duck", productCategory, quackQuack));
        products.add(new Product("Chewquackka", 5.90f, "USD", "Chewbacca rubber duck", productCategory, quackQuack));

        when(productCategoryDataStore.find(categoryId)).thenReturn(productCategory);
        when(productDataStore.getBy(productCategory)).thenReturn(products);
        assertEquals(productService.getProductsForCategory(categoryId), products);
    }

    @Test
    public void getProductsForCategory_productCategoryDoesNotExist() {
        when(productCategoryDataStore.find(categoryId)).thenReturn(productCategory);
        when(productDataStore.getBy(productCategory)).thenReturn(null);
        assertNull(productService.getProductsForCategory(categoryId));
    }
}
