package com.codecool.shop.controller.User;

import com.codecool.shop.Util.Verification;
import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.DBManager;
import com.codecool.shop.dao.DaoManager;
import com.codecool.shop.dao.UserDao;
import com.codecool.shop.dao.implementation.UserJdbcDao;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = {"/login"})
public class UserLoginController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (req.getSession().getAttribute("wrongUser") != null) {
            req.getSession().removeAttribute("wrongUser");
        }
        if (req.getSession().getAttribute("wrongPass") != null) {
            req.getSession().removeAttribute("wrongPass");
        }
        if (req.getSession().getAttribute("userTaken") != null) {
            req.getSession().removeAttribute("userTaken");
        }

        DaoManager daoManager = DaoManager.getInstance();
        UserDao userJdbcDao = daoManager.getUserDao();
//        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
//        WebContext context = new WebContext(req, resp, req.getServletContext());
        HttpSession session = req.getSession();
        String userName = req.getParameter("username");
        String password = req.getParameter("password");
        String hashedPass = userJdbcDao.getHashedPasswordByName(userName);

        if(hashedPass == null) {
            String wrongUser = "Wrong username";
            System.out.println(wrongUser);
            session.setAttribute("wrongUser", wrongUser);
            resp.sendRedirect("/");
        }
        boolean userVerification = Verification.verification(password, hashedPass);
        if(userVerification) {
            session.setAttribute("userName", userName);
            resp.sendRedirect("/");
            System.out.println("verification okay");
        } else {
            String wrongPass = "Wrong password";
            session.setAttribute("wrongPass", wrongPass);
            resp.sendRedirect("/");
        }

    }
}
