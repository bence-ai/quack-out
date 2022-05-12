package com.codecool.shop.service;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.UUID;

public class Order implements Serializable {

    public String getOrderID() {
        return orderID.toString();
    }

    private UUID orderID;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String country;
    private String city;
    private String address;
    private int zipCode;
    private static Order instance = null;
    private Cart cart;
    private boolean confirmed = false;

    public Order () {
        orderID = UUID.randomUUID();

    }

    public static Order getInstance() {
        if (instance == null) {
            instance = new Order();

        }
        return instance;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public Cart getCart() {
        return cart;
    }


    public void setCart(Cart cart) {
        this.cart = cart;
    }

    @Override
    public String toString() {


    return String.format("{ \"id\":\"%1$s\", " +
            "\"firstname\":\"%2$s\", " +
            "\"lastName\":\"%3s\", " +
            "\"phone\":\"%4$s\", " +
            "\"city\":\"%5$s\", " +
            "\"email\":\"%6$s\", " +
            "\"zip\":\"%7$s\"}",
            this.orderID,
            this.firstName,
            this.lastName,
            this.phoneNumber,
            this.city,
            this.email,
            this.zipCode);
}


    private void writeObject(ObjectOutputStream oos)
            throws IOException {
        oos.defaultWriteObject();
        oos.writeObject(this.getFirstName());
        oos.writeObject(this.getLastName());
        oos.writeObject(this.city);
    }

    private void readObject(ObjectInputStream ois)
            throws ClassNotFoundException, IOException {
        ois.defaultReadObject();
        String firstName = (String) ois.readObject();
        String lastName = (String) ois.readObject();
        String city = (String) ois.readObject();

        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setCity(city);
    }

    public void clear() {
        instance = new Order();
    }
}
