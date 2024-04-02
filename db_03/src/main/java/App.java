import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class App {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/db_course2_task1";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "root";

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            createTables(connection);
            insertRecords(connection);
            insertRelatedRecords(connection);
            getByCustomerId(connection);
            deleteRecordWithCascade(connection);
            getByCustomerId(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void createTables(Connection connection) throws SQLException {
        String createSequenceSQL = "CREATE SEQUENCE IF NOT EXISTS customer_sequence START 100 INCREMENT 50 NO MAXVALUE NO CYCLE;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(createSequenceSQL)) {
            preparedStatement.executeUpdate();
        }
        createSequenceSQL = "CREATE SEQUENCE IF NOT EXISTS project_sequence START 100 INCREMENT 50 NO MAXVALUE NO CYCLE;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(createSequenceSQL)) {
            preparedStatement.executeUpdate();
        }

        String createCustomerTableSQL = "CREATE TABLE Customer (" +
                "customerId INT DEFAULT nextval('customer_sequence') PRIMARY KEY," +
                "nameOfCustomer VARCHAR(255)," +
                "surnameOfCustomer VARCHAR(255)," +
                "phoneNumber VARCHAR(20)" +
                ");";

        String createProjectTableSQL = "CREATE TABLE Project (" +
                "projectId  INT DEFAULT nextval('project_sequence') PRIMARY KEY," +
                "customerId INT REFERENCES Customer(customerId) ON DELETE CASCADE ON UPDATE CASCADE" +
                ");";

        try (PreparedStatement preparedStatement = connection.prepareStatement(createCustomerTableSQL)) {
            preparedStatement.executeUpdate();
        }

        try (PreparedStatement preparedStatement = connection.prepareStatement(createProjectTableSQL)) {
            preparedStatement.executeUpdate();
        }
    }

    private static void insertRecords(Connection connection) throws SQLException {
        String insertCustomerSQL = "INSERT INTO Customer (nameOfCustomer, surnameOfCustomer, phoneNumber) " +
                "VALUES (?, ?, ?);";

        for (int i = 0; i < 1000; i++) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertCustomerSQL)) {
                preparedStatement.setString(1, "CustomerName" + i);
                preparedStatement.setString(2, "CustomerSurname" + i);
                preparedStatement.setString(3, "CustomerPhone" + i);
                preparedStatement.executeUpdate();
            }
        }
    }

    private static void insertRelatedRecords(Connection connection) throws SQLException {
        String selectCustomerIdsSQL = "SELECT customerId FROM Customer;";
        List<Integer> customerIds = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(selectCustomerIdsSQL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                customerIds.add(resultSet.getInt("customerId"));
            }
        }

        if (customerIds.isEmpty()) {
            System.out.println("Нет существующих записей в таблице Customer.");
            return;
        }

        String insertProjectSQL = "INSERT INTO Project (customerId) VALUES (?);";

        for (int i = 0; i < 1000; i++) {
            int randomCustomerId = customerIds.get(new Random().nextInt(customerIds.size()));
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertProjectSQL)) {
                preparedStatement.setInt(1, randomCustomerId);
                preparedStatement.executeUpdate();
            }
        }
    }


    private static void deleteRecordWithCascade(Connection connection) throws SQLException {
        String deleteCustomerSQL = "DELETE FROM Customer WHERE customerId = ?;";

        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteCustomerSQL)) {
            preparedStatement.setInt(1, 100); // Удаляем первого клиента
            preparedStatement.executeUpdate();
        }
    }

    private static void getByCustomerId(Connection connection) throws SQLException {
        String selectProjectsSQL = "SELECT * FROM Project WHERE customerId = ?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(selectProjectsSQL)) {
            preparedStatement.setInt(1, 100);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (!resultSet.isBeforeFirst()) {
                    System.out.println("Нет проектов для указанного customerId.");
                } else {
                    while (resultSet.next()) {
                        int projectId = resultSet.getInt("projectId");
                        int projectCustomerId = resultSet.getInt("customerId");
                        System.out.println("Project ID: " + projectId + ", Customer ID: " + projectCustomerId);
                    }
                }
            }

        }
    }
}
