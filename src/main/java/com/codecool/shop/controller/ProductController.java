package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.DaoManager;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.service.Cart;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/products"})
public class ProductController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductCategoryDao productCategoryDataStore = DaoManager.getInstance().getProductCategoryDao();
        SupplierDao supplierDao = DaoManager.getInstance().getSupplierDao();

        Cart cart = Cart.getInstance();

        String category = req.getParameter("category");
        String supplier = req.getParameter("supplier");

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());

        context.setVariable("categories", productCategoryDataStore.getAll());
        context.setVariable("items", cart.getSize());
        if (category != null) {
            context.setVariable("categories", productCategoryDataStore.find(Integer.parseInt(category)));
        }
        if (supplier != null) {
            context.setVariable("categories", supplierDao.find(Integer.parseInt(supplier)));
        }
        System.out.println("context: " + productCategoryDataStore.getAll());

        engine.process("product/products.html", context, resp.getWriter());
    }
}
