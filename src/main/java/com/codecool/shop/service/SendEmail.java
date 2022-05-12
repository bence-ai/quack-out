package com.codecool.shop.service;

import com.codecool.shop.model.Product;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;


public class SendEmail {
    public static void sendMail(Order order) throws MessagingException {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", true);
        properties.put("mail.smtp.starttls.enable", true);
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        String myAccountEmail = "QuackOutWebshop@gmail.com";
        String password = "!QuackQuack4";

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountEmail, password);
            }
        });

        String email = order.getEmail();
        String orderStatus = getOrderStatus(order);
        Message message = prepareMessage(session, myAccountEmail, email, orderStatus);

        Transport.send(message);
    }

    private static Message prepareMessage(Session session, String myAccountEmail, String recipient, String orderStatus) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            message.setSubject("Order confirmation by QuackOut");
            message.setText(orderStatus);
            return message;
        } catch (Exception exception) {
            Logger.getLogger(SendEmail.class.getName()).log(Level.SEVERE, null, exception);
        }
        return null;
    }

    private static String getOrderStatus(Order order) {
        StringBuilder str = new StringBuilder();
        str.append("Hey " + order.getFirstName() + "!\n\n");
        str.append("Your order ID: " + order.getOrderID() + "\n\n");
        str.append("Order details:\n");
        str.append("Name: " + order.getFirstName() + " "+ order.getLastName() + "\n");
        str.append("Address: " + order.getCity() + ", " +order.getAddress() + "\n");
        str.append("Phone number: " + order.getPhoneNumber() + "\n\n");
        str.append("You bought the following products:\n");
        for (Product product: order.getCart().getCartElements()) {
            str.append(product.getName() + " price: " + product.getPrice() + "\n");
        }
        str.append("\nThank you for your purchase!\n");
        str.append("QuackOut team\n\n");
        str.append("quack quack");
        return str.toString();
    }
}
