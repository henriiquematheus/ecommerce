package com.example.ecommerce.db;

import java.sql.*;

public class DatabaseInitializer {

    private static final String URL = "jdbc:postgresql://localhost:5432/";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "admin";
    private static final String DATABASE_NAME = "ecommerce";

    public static void main(String[] args) {
        createDatabaseIfNotExists();
    }

    private static void createDatabaseIfNotExists() {
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            if (!databaseExists(connection, DATABASE_NAME)) {
                Statement statement = connection.createStatement();
                statement.executeUpdate("CREATE DATABASE " + DATABASE_NAME);
                System.out.println("Banco de dados 'ecommerce' criado com sucesso.");
            } else {
                System.out.println("O banco de dados 'ecommerce' já existe. Nenhuma ação adicional necessária.");
            }
        } catch (SQLException e) {
            if (!e.getSQLState().equals("42P04")) {
                System.out.println("Erro ao criar o banco de dados: " + e.getMessage());
            }
        }
    }
    private static boolean databaseExists(Connection connection, String databaseName) throws SQLException {
        ResultSet resultSet = connection.getMetaData().getCatalogs();
        while (resultSet.next()) {
            if (databaseName.equals(resultSet.getString(1))) {
                return true;
            }
        }
        return false;
    }
}