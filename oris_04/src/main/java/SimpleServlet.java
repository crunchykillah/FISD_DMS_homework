import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.io.Writer;

@WebServlet("/simple")
public class SimpleServlet implements Servlet {
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println("init");
    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("service");
        Writer writer = servletResponse.getWriter();
        writer.write("Hello!");
        writer.write(servletRequest.getContentType());
        writer.write(servletRequest.getRemoteAddr());
        writer.write(servletRequest.getParameter("id"));
        writer.write(((HttpServletRequest)servletRequest).getMethod());

    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {
        System.out.println("destroy");
    }
}
