import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.TemplateLoader;

import java.io.IOException;
import java.io.Writer;
import java.sql.*;
@WebServlet("/aircraft_db")
public class FromAircraftsDataServlet extends HttpServlet {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/demo";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "root";
    private static final String TITLE = "AIRCRAFT";
    private Connection connection;

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        try  {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Writer writer = resp.getWriter();
        String template = TemplateLoader.loadTemplateFromResource("site.html");
        template = template.replace("${title}", TITLE);
        template = template.replace("&{content}",createContent());
        writer.write(template);
    }
    @Override
    public void destroy() {
        System.out.println("destroy");
        try{
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private String createContent() {
        StringBuilder content = new StringBuilder();

        String statement  = "select * from aircrafts_data";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(statement);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String code = "code: " + resultSet.getString("aircraft_code");
                String model = "model: " + resultSet.getString("model");
                String range = "range: " + resultSet.getString("range");


                content.append("<p><font face=\"Impact,Charcoal, sans-serif\">").append(code).append("</p>");
                content.append("<p><font face=\"Impact,Charcoal, sans-serif\">").append(model).append("</p>");
                content.append("<p><font face=\"Impact,Charcoal, sans-serif\">").append(range).append("</p>");
                content.append("<br/>");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return content.toString();
    }
}
