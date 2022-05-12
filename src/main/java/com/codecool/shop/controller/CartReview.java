package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.service.Cart;
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

@WebServlet(urlPatterns = {"/cart"})
public class CartReview extends HttpServlet {
    static Logger logger = LoggerFactory.getLogger(CartReview.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cart cart = Cart.getInstance();
        logger.info("--Cart opened: " + cart);

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        Float totalPriceWithShipping = Float.parseFloat(cart.getTotalPrice());
        context.setVariable("cartItems", cart.getCartElements());
        context.setVariable("totalItems", cart.getSize() + " items");
        context.setVariable("totalPrice", totalPriceWithShipping);
        engine.process("product/cart.html", context, resp.getWriter());
    }


}
