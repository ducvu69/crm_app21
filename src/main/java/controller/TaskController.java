package controller;

import config.MysqlConfig;
import model.JobModel;
import model.RoleModel;
import model.TaskModel;
import model.UserModel;
import service.JobService;
import service.TaskService;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name="TaskController", urlPatterns ={"/task","/task/add","/task/delete"})
public class TaskController extends HttpServlet {

    TaskService taskService = new TaskService();
    JobService jobService = new JobService();
    UserService userService = new UserService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       String path = req.getServletPath();
       switch (path){
           case "/task":
               getAllTask(req, resp);
               break;
           case "/task/add":
               addTask(req,resp);
               break;
           case "/task/delete":
               deleteTask(req,resp);
               break;
           default:
               break;
       }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();
        switch (path){
            case "/task":
                getAllTask(req,resp);
                break;
            case "/task/add":
                addTask(req,resp);
                break;
            case "/task/delete":
                deleteTask(req, resp);
                break;
            default:
                break;
        }
    }

    public void getAllTask(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        List<TaskModel> list = taskService.getAllTask();
        req.setAttribute("listTask", list);
        req.getRequestDispatcher("task.jsp").forward(req,resp);
    }

    private void addTask(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<JobModel> jobModelList = jobService.findAllJob();
        List<UserModel> userModelList = userService.getAllUser();
        List<TaskModel> taskModelList = taskService.getAllTask();
        String method = req.getMethod();
        if(method.equalsIgnoreCase("post")) {
            int job_id = Integer.parseInt(req.getParameter("job"));
            String name = req.getParameter("name");
            int user_id = Integer.parseInt(req.getParameter("user_id"));
            String start_date = req.getParameter("start_date");
            String end_date = req.getParameter("end_date");
            int status_id = Integer.parseInt(req.getParameter("status_id"));
            taskService.addTask(name,start_date,end_date,user_id,job_id,status_id);
        }
        req.setAttribute("listJob",jobModelList);
        req.setAttribute("listUser", userModelList);

        req.getRequestDispatcher("/task-add.jsp").forward(req,resp);
    }

    private void deleteTask(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        int id = Integer.parseInt(req.getParameter("id"));
        boolean isSuccess = taskService.deleteTask(id);
    }
}
