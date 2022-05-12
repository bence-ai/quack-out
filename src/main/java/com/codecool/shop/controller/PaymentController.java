package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.service.Cart;
import com.codecool.shop.service.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/payment"})
public class PaymentController extends HttpServlet {
    Logger logger = LoggerFactory.getLogger(PaymentController.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{

        Cart cart = Cart.getInstance();
        Order order = Order.getInstance();
        order.setFirstName(req.getParameter("first-name"));
        order.setLastName(req.getParameter("last-name"));
        order.setEmail(req.getParameter("e-mail"));
        order.setPhoneNumber(req.getParameter("phone"));
        order.setAddress(req.getParameter("street"));
        order.setCity(req.getParameter("city"));
        order.setCountry(req.getParameter("state"));
        order.setZipCode(Integer.parseInt(req.getParameter("zip")));
        order.setCart(cart);
      
        logger.info("-- User sent to payment page. Order info: " + order);

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("totalPrice", cart.getTotalPrice());


        engine.process("product/payment.html", context, resp.getWriter());
    }



}
