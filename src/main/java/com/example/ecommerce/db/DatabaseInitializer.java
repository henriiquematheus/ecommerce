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
            if (e.getSQLState().equals("42P04")) {
                System.out.println("O banco de dados 'ecommerce' já existe. Nenhuma ação adicional necessária.");
            } else {
                System.out.println("Erro ao criar o banco de dados: " + e.getMessage());
            }
        } finally {
            System.out.println("Tentativa de conexão com o banco de dados concluída.");
        }
    }
    private static boolean databaseExists(Connection connection, String databaseName) throws SQLException {
        try (ResultSet resultSet = connection.getMetaData().getCatalogs()) {
            while (resultSet.next()) {
                String existingDatabaseName = resultSet.getString(1);
                System.out.println("Verificando o banco de dados existente: " + existingDatabaseName);
                if (databaseName.equals(existingDatabaseName)) {
                    System.out.println("Banco de dados '" + databaseName + "' encontrado.");
                    return true;
                }
            }
            System.out.println("Banco de dados '" + databaseName + "' não encontrado.");
            return false;
        } catch (SQLException e) {
            System.out.println("Erro ao verificar se o banco de dados existe: " + e.getMessage());
            throw e;
        }
    }
}