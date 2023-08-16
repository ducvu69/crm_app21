package controller;

import model.JobModel;
import model.RoleModel;
import service.JobService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet(name = "JobController", urlPatterns = {"/job","/job/add","/job/delete"})
public class JobController extends HttpServlet {
    private JobService jobService = new JobService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();
        switch (path){
            case "/job":
                getAllJob(req,resp);
                break;
            case "/job/add":
                addJob(req,resp);
                break;
            case "/job/delete":
                deleteJob(req,resp);
                break;
            default:
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();
        switch (path) {
            case "/job":
                getAllJob(req,resp);
                break;
            case "/job/add":
                addJob(req,resp);
                break;
            case "/job/delete":
                deleteJob(req,resp);
                break;
            default:
                break;
        }
    }

    public void getAllJob(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        List<JobModel> list = jobService.findAllJob();
        req.setAttribute("listJob", list);
        req.getRequestDispatcher("groupwork.jsp").forward(req,resp);
    }

    public void addJob(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getMethod();
        if (method.equalsIgnoreCase("post")) {
            String name = req.getParameter("name");
            String start_date = req.getParameter("start_date");
            String end_date = req.getParameter("end_date");
            jobService.addJob(name, start_date, end_date);
        }
        req.getRequestDispatcher("/groupwork-add.jsp").forward(req, resp);
    }

    public void deleteJob(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        int id = Integer.parseInt(req.getParameter("id"));
        boolean isSuccess= jobService.deleteJob(id);
    }
}

