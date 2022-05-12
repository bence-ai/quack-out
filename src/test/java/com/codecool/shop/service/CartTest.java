package com.codecool.shop.service;

import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CartTest {
    private Cart cart;
    private ProductDao productDataStore;
    private final int prodictId = 2;
    private final ProductCategory productCategory = new ProductCategory("Star Wars ducks", "Star Wars department", "test description");
    Supplier quackQuack = new Supplier("QuackQuack", "Need a duck? Order from us!");
    Product product = new Product("DuckMaul", 5.90f, "USD", "Darth Maul rubber duck", productCategory, quackQuack);

    @BeforeEach
    public void setup() {
        cart = new Cart();
        productDataStore = mock(ProductDao.class);
        cart.setProductDataStore(productDataStore);
    }

    @Test
    public void getTotalPrice_emptyCart(){
        Assertions.assertEquals(Integer.toString(Cart.deliveryPrice), cart.getTotalPrice());
    }

    @Test
    public void getTotalPrice_ItemAdded(){
        when(productDataStore.find(prodictId)).thenReturn(product);
        cart.addToCart(prodictId);
        Assertions.assertEquals(Float.toString(Cart.deliveryPrice + product.getFloatPrice()), cart.getTotalPrice());
    }

    @Test
    public void getCartJson_emptyCart(){
        List<Object> emptyList = new ArrayList<>();
        Assertions.assertEquals(emptyList, cart.getCartJson());
    }

    @Test
    public void getCartJson_someItemsAdded(){
        when(productDataStore.find(prodictId)).thenReturn(product);
        cart.addToCart(prodictId);
        List<Object> list = new ArrayList<>();
        list.add(product.toString());
        Assertions.assertEquals(list, cart.getCartJson());
    }

    @Test
    public void emptyCart_emptiesCart(){
        when(productDataStore.find(prodictId)).thenReturn(product);
        cart.addToCart(prodictId);
        cart.emptyCart();
        List<Object> emptyList = new ArrayList<>();
        Assertions.assertEquals(emptyList, cart.getCartJson());
    }

    @Test
    public void getInstance_returnsTheSameObjectEveryTime() {
        assertEquals(Cart.getInstance(), Cart.getInstance());
    }

    @Test
    public void removeOneElement_removesElement() {
        when(productDataStore.find(prodictId)).thenReturn(product);
        cart.addToCart(prodictId);
        List<Object> list = new ArrayList<>();
        list.add(product);
        assertEquals(list, cart.getCartElements());
        cart.removeOneElement(0);
        List<Object> emptyList = new ArrayList<>();
        assertEquals(emptyList, cart.getCartElements());
    }

    @Test
    public void ToString_returnsEmptyString(){
        assertEquals("", cart.toString());
    }

    @Test
    public void ToString_returnsStringWithObjects(){
        when(productDataStore.find(prodictId)).thenReturn(product);
        cart.addToCart(prodictId);
        assertEquals(product.toString(), cart.toString());
    }

}
