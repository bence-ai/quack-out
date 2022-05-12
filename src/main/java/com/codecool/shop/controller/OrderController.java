package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.service.Cart;
import com.codecool.shop.service.Order;
import com.codecool.shop.service.SendEmail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(urlPatterns = {"/order"})
public class OrderController extends HttpServlet {
    Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Order order = Order.getInstance();
        Cart cart = Cart.getInstance();
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        String orderJson = order.toString();

        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(new Date());
        File f = new File("LOG/" + order.getOrderID() + "@" + date + ".json");
        f.createNewFile();


        FileWriter filewriter = new FileWriter(f, true);
        filewriter.write(orderJson); // Pass json object.
        filewriter.flush();
        filewriter.close();

        context.setVariable("order", order);
        context.setVariable("totalPrice", cart.getTotalPrice());

        engine.process("product/order.html", context, resp.getWriter());

        try {
            sendMail(order);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        logger.info("--Payment successfully received. cart: " + cart);
        logger.info("--Order: " + order);

        cart.emptyCart();
        order.clear();
    }

    protected void sendMail(Order order) throws MessagingException {
        SendEmail.sendMail(order);
    }
}
