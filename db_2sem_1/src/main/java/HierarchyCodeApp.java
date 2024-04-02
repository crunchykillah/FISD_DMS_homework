import java.sql.*;

public class HierarchyCodeApp {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/test_db";
        String user = "postgres";
        String password = "root";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            printHierarchy(conn, "01.01.01.11.02");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private static void printHierarchy(Connection conn, String code) throws SQLException {
        String query = "WITH RECURSIVE hierarchy AS (" +
                "SELECT code, title FROM hierarchy_code WHERE code = ? " +
                "UNION ALL " +
                "SELECT hc.code, hc.title FROM hierarchy_code hc, hierarchy h WHERE hc.code = replace(h.code, right(h.code, 2), '00')" +
                ") SELECT title FROM hierarchy ORDER BY code DESC";

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, code);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    System.out.println(rs.getString("title"));
                }
            }
        }
    }
}