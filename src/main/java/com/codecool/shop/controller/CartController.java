package com.codecool.shop.controller;

import com.codecool.shop.service.Cart;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "cartController", urlPatterns = {"/cart/add/", "/cart/remove/", "/cart/get", "/cart/expand/"})
public class CartController extends HttpServlet {
    private Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        Cart cart = Cart.getInstance();
        int productId = 0;
        String route = req.getRequestURI().split("/")[2];
        String content = "";

        switch (route) {
            case "remove":
                productId = Integer.parseInt(req.getParameter("id"));
                cart.removeOneElement(productId);
                content = String.format("{ \"size\": \"%s\"}", cart.getSize());
                resp.sendRedirect("/cart");
                break;
            case "get":
                var jsonToList = cart.getCartJson();
                content = gson.toJson(jsonToList);
                break;
            case "add":
                productId = Integer.parseInt(req.getParameter("id"));
                cart.addToCart(productId);
                content = gson.toJson(String.format("{ \"size\": \"%s\"}", cart.getSize()));
                break;
            case "expand":
                productId = Integer.parseInt(req.getParameter("id"));
                cart.addToCart(productId);
                content = gson.toJson(String.format("{ \"size\": \"%s\"}", cart.getSize()));
                resp.sendRedirect("/cart");
                break;
        }
        out.print(content);
        out.flush();
        out.close();
    }
}
