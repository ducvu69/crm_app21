import model.Users;
// /login
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//        int a = 5;
//        int b = 10;
//        int result = a + b;
//        req.setAttribute("kq",result); --> Servlet truyền biến ra cho file jsp

        // Lấy thread từ resp của server để trả ra page cho client sử dụng PrintWriter
        // PrintWriter writer = resp.getWriter();
        // writer.println("Hello Login Page");

        // Yêu cầu sử dụng giao diện và trả giao diện ra browser cho client
        req.getRequestDispatcher("login.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
//       nếu username là nguyenvana@gmail.com
//        Xuất ra hello với giá trị của tham số nhận được
        System.out.println("Kiemtra " + username);
        String message = "";
        if(username.toLowerCase().equals("nguyenvana@gmail.com")){
            message = "Hello " + username;
        }
//        PrintWriter writer = resp.getWriter();
//        writer.println(message);
        Users users = new Users();
        users.setUsername(username);
        users.setPassword(password);

        List<String> list = Arrays.asList("Cybersoft","Java 21", "JSP");

        req.setAttribute("msg",message);
        req.setAttribute("user",users);
        req.setAttribute("list",list);
        //Tìm hiểu JSTL hiển thị giá trị của list ra jsp
        req.getRequestDispatcher("login.jsp").forward(req,resp);
    }
}
