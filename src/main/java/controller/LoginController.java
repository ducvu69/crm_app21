package controller;

import com.mysql.cj.Session;
import config.MysqlConfig;
import model.UserModel;
import service.LoginService;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "loginController", urlPatterns = {"/login"})
public class LoginController extends HttpServlet{
    private LoginService loginService = new LoginService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher("login.jsp").forward(req,resp);

    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = null;
        // Bước 1: Lấy tham số username và password người dùng nhập vào
        String email = req.getParameter("username");
        String password = req.getParameter("password");
        String remember = req.getParameter("remember");

        boolean isSuccess = loginService.checkLogin(email,password);
        if(isSuccess){
            String contextPath = req.getContextPath();
            resp.sendRedirect(contextPath + "");
        }else {
            PrintWriter writer = resp.getWriter();
            writer.println("Login Failed !!!");
            writer.close();
        }

    }
}
