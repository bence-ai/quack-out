package com.codecool.shop.service;

import com.codecool.shop.dao.DaoManager;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.Product;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Cart  {

    private List<Product> cartElements = new ArrayList<>();
    private ProductDao productDataStore = DaoManager.getInstance().getProductDao();
    private static Cart instance = null;
    public static final int deliveryPrice = 5;

    public Cart () {
    }

    public String getTotalPrice() {
        float totalPrice = 0;
        int shippingStandard = 5;
        for (Product cartElement : cartElements) {
            totalPrice += cartElement.getFloatPrice();
        }
        DecimalFormat df = new DecimalFormat("#.##");
        totalPrice += deliveryPrice;
        return df.format(totalPrice);
    }

    public List<Product> getCartElements (){
        return cartElements;
    }

    public ArrayList<Object> getCartJson() {
        var list = new ArrayList<>();
        for (Product cartElement : cartElements) {
            list.add(cartElement.toString());
        }
        return list;
    }

    public static Cart getInstance() {
        if (instance == null) {
            instance = new Cart();
        }
        return instance;
    }

    public int getSize() {
        return cartElements.size();
    }


    public void addToCart(int productId) {
        Product addProduct = productDataStore.find(productId);
        cartElements.add(addProduct);
    }

    public void removeOneElement(int removeId) {
        for (Product cartElement : cartElements) {
            if (cartElement.getId()==removeId) {
                cartElements.remove(cartElement);
                break;
            }
        }
    }

    /* public void removeSameTypeOfElement(int productId) {
        cartElements = cartElements.stream().filter(p -> p.getId() == productId)
                .collect(Collectors.toList());
    } */

    public void emptyCart() {
        cartElements.clear();
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Product cartElement : cartElements) {
            sb.append(cartElement.toString());
        }
        return sb.toString();
    }

    public void setProductDataStore(ProductDao productDataStore) {
        this.productDataStore = productDataStore;
    }
}
