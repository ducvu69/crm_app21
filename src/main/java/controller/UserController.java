package controller;

import model.RoleModel;
import model.UserModel;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "userController", urlPatterns = {"/user","/user/add","/user/delete","/user/update"})
public class UserController extends HttpServlet {
    private UserService userService = new UserService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Lấy đường dẫn servlet người dùng gọi trên browser
        String path = req.getServletPath();
        switch (path){
            case "/user":
                getAllUser(req,resp);
                break;
            case "/user/add":
                addUser(req,resp);
                break;
            case "/user/delete":
                deleteUser(req,resp);
                break;
            case "/user/update":
                updateUser(req,resp);
            default:
                break;
        }

    }
    private void getAllUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<UserModel> list = userService.getAllUser();
        req.setAttribute("listUsers",list);
        req.getRequestDispatcher("user-table.jsp").forward(req,resp);
    }

    private void addUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<RoleModel> listRole = userService.getAllRole();
            String method = req.getMethod();
            if(method.equalsIgnoreCase("post")) {
                String email = req.getParameter("email");
                String password = req.getParameter("password");
                String fullname = req.getParameter("fullname");
                int role_id = Integer.parseInt(req.getParameter("role"));
                userService.insertUser(email, password, fullname, role_id);
            }
        req.setAttribute("listRole",listRole);
        // Nếu có thêm cấp trong đường dẫn thì thêm dấu / phía trước file .jsp
        req.getRequestDispatcher("/user-add.jsp").forward(req,resp);
    }

    private void deleteUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        int id = Integer.parseInt(req.getParameter("id"));
        boolean isSuccess = userService.deleteUser(id);
    }

    private void updateUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<RoleModel> listRole = userService.getAllRole();
        String method = req.getMethod();
        if(method.equalsIgnoreCase("post")) {
            String email = req.getParameter("email");
            String password = req.getParameter("password");
            String fullname = req.getParameter("fullname");
            int role_id = Integer.parseInt(req.getParameter("role"));
            int id = Integer.parseInt(req.getParameter("id"));
            userService.updateUser(email, password, fullname, role_id, id);
        }
        req.setAttribute("listRole",listRole);
        // Nếu có thêm cấp trong đường dẫn thì thêm dấu / phía trước file .jsp
        req.getRequestDispatcher("/user-update.jsp").forward(req,resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();
        switch (path){
            case "/user":
                getAllUser(req,resp);
                break;
            case "/user/add":
                addUser(req,resp);
                break;
            case "/user/update":
                updateUser(req,resp);
            default:
                break;
        }

    }
}

