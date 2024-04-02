package embededserver;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.startup.Tomcat;
import servlet.DispatcherServlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Пример встраивания контейнера сервлетов Tomcat в приложение
 * Запускаем на порту 8090
 * http://localhost:8090/show/info
 */
public class Main {
    public static void main(String[] args) {
        Tomcat tomcat = new Tomcat();
        tomcat.setBaseDir("temp");
        Connector conn = new Connector();
        conn.setPort(8090);
        tomcat.setConnector(conn);
        String contextPath = "";
        String docBase = new File(".").getAbsolutePath();
        Context tomcatContext = tomcat.addContext(contextPath, docBase);


        String servletName = "dispatcherServlet"; // любое уникальное имя
        tomcat.addServlet(contextPath, servletName, new DispatcherServlet());
        // Указываем имя ресурса и сервлет, который этот ресурс будет обрабатывать
        // (по пути "/*" наш сервлет будет перехватывать все запросы)
        tomcatContext.addServletMappingDecoded("/*", servletName);

        try {
            tomcat.start();
            tomcat.getServer().await();

            /*
                tomcat.stop()
                tomcat.destroy()
             */
        } catch (LifecycleException e) {
            throw new RuntimeException(e);
        }
    }
}
