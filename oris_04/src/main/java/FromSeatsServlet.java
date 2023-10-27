import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.Writer;
import java.sql.*;
@WebServlet("/seats_db")
public class FromSeatsServlet extends HttpServlet {
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
        String statement = "select\n" +
                "  a.model,\n" +
                "  SUM(CASE WHEN s.fare_conditions = 'Economy' THEN 1 ELSE 0 END) AS economy_seats,\n" +
                "  SUM(CASE WHEN s.fare_conditions = 'Business' THEN 1 ELSE 0 END) AS business_seats\n" +
                "from\n" +
                "  aircrafts_data a\n" +
                "  JOIN seats s ON a.aircraft_code = s.aircraft_code\n" +
                "group by\n" +
                "  a.model;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(statement);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                writer.write(resultSet.getString("model"));
                writer.write(": " + resultSet.getString("economy_seats"));
                writer.write(": " + resultSet.getString("business_seats"));
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
