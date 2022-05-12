package com.codecool.shop.model;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.Currency;

public class Product extends BaseModel implements Serializable {

    private float defaultPrice;
    private Currency defaultCurrency;
    private ProductCategory productCategory;
    private Supplier supplier;
    private String imageName;

    public Product(String name, float defaultPrice, String currencyString, String description, ProductCategory productCategory, Supplier supplier) {
        super(name, description);
        this.setPrice(defaultPrice, currencyString);
        this.setSupplier(supplier);
        this.setProductCategory(productCategory);
        this.imageName = "duck-" + name + ".png";
    }

    public float getFloatPrice() {
        return defaultPrice;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public float getDefaultPrice() {
        return defaultPrice;
    }

    public void setDefaultPrice(float defaultPrice) {
        this.defaultPrice = defaultPrice;
    }

    public Currency getDefaultCurrency() {
        return defaultCurrency;
    }

    public void setDefaultCurrency(Currency defaultCurrency) {
        this.defaultCurrency = defaultCurrency;
    }

    public String getPrice() {
        return String.valueOf(this.defaultPrice) + " " + this.defaultCurrency.toString();
    }

    public void setPrice(float price, String currency) {
        this.defaultPrice = price;
        this.defaultCurrency = Currency.getInstance(currency);
    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
        this.productCategory.addProduct(this);
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
        this.supplier.addProduct(this);
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("#.##");
        //totalPrice += 5;
        return String.format("{ \"id\":\"%1$d\", " +
                        "\"name\":\"%2$s\", " +
                        "\"imageName\":\"%7$s\", " +
                        "\"defaultPrice\":\"%3$s\", " +
                        "\"defaultCurrency\":\"%4$s\", " +
                        "\"productCategory\":\"%5$s\", " +
                        "\"supplier\":\"%6$s\"}",
                this.id,
                this.name,
                df.format(defaultPrice),
                this.defaultCurrency.toString(),
                this.productCategory.getName(),
                this.supplier.getName(),
                this.imageName);
    }
}
