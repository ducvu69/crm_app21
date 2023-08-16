package controller;

import model.RoleModel;
import model.UserModel;
import service.RoleService;
import service.UserService;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "RoleController", urlPatterns = {"/role","/role/add","/role/delete"})
public class RoleController extends HttpServlet {
    private RoleService roleService = new RoleService();
    private UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();
        switch (path){
            case "/role":
                getAllRole(req,resp);
                break;
            case "/role/add":
                addRole(req,resp);
                break;
            case "/role/delete":
                deleteRole(req,resp);
                break;
            default:
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();
        switch (path){
            case "/role":
                getAllRole(req,resp);
                break;
            case "/role/add":
                addRole(req,resp);
                break;
            case "/role/delete":
                deleteRole(req,resp);
                break;
            default:
                break;
        }
    }

    private void getAllRole(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<RoleModel> list = userService.getAllRole();
        req.setAttribute("listRoles",list);
        req.getRequestDispatcher("role-table.jsp").forward(req,resp);
    }

    private void addRole(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        List<RoleModel> list = userService.getAllRole();
        String method = req.getMethod();
        if(method.equalsIgnoreCase("post")){
            String name = req.getParameter("name");
            String description = req.getParameter("description");
            roleService.insertRole(name, description);
        }
        req.setAttribute("listRoles",list);
        req.getRequestDispatcher("/role-add.jsp").forward(req,resp);
    }

    private void deleteRole(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        int id = Integer.parseInt(req.getParameter("id"));
        boolean isSuccess = roleService.deleteRole(id);
    }
}
