package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
// urlPatterns : Khi người dùng gọi link được quy định trong urlPatterns thì
//               filter sẽ được kích hoạt
@WebFilter(urlPatterns = {"/login"})
public class CustomFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
//        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //Nơi quy định rule
        System.out.println("Filter đã được kích hoạt");
        //Cho phép đi vào link mà người dùng request
        filterChain.doFilter(servletRequest,servletResponse);

    }

    @Override
    public void destroy() {
//        Filter.super.destroy();
    }
}
