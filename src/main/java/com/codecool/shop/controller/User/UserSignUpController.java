package com.codecool.shop.controller.User;

import com.codecool.shop.Util.Verification;
import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.DaoManager;
import com.codecool.shop.dao.UserDao;
import com.codecool.shop.service.User;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = {"/signup"})
public class UserSignUpController extends HttpServlet {
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
        String userName = req.getParameter("username");
        List<String> allUserNames = new ArrayList<>();
        for(User user: userJdbcDao.getAll()) {
            allUserNames.add(user.getUserNameEmail());
            System.out.println(user.getUserNameEmail());
        }
        if(allUserNames.contains(userName)){
            String userTaken = "User Name already taken!";
            System.out.println(userTaken);
            HttpSession session = req.getSession();
            session.setAttribute("userTaken", userTaken);
        } else {
            User newUser = new User(userName, Verification.passwordHash(req.getParameter("password")));
            userJdbcDao.add(newUser);
        }
        resp.sendRedirect("/");

    }
}
