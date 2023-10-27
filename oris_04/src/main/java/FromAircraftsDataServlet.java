import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.Writer;
import java.sql.*;
@WebServlet("/aircraft_db")
public class FromAircraftsDataServlet extends HttpServlet {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/demo";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "xxxx";
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
        resp.setContentType("text/html; charset=UTF-8");
        writer.write("<!doctype html>");
        writer.write("<html lang=\"en\">");
        writer.write("<head>");
        writer.write("<meta charset-\"UTF-8\">");
        writer.write("<title>TICKETS</title>");
        writer.write("</head>");
        writer.write("<body>");
        String statement = "select * from aircrafts_data";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(statement);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                writer.write(resultSet.getString("aircraft_code"));
                writer.write(": " + resultSet.getString("model"));
                writer.write(": " + resultSet.getString("range"));
                writer.write("<br/>");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        writer.write("</body>");
        writer.write("</html>");
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
}
