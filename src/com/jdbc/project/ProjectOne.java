package com.jdbc.project;


import java.sql.*;
import java.util.Scanner;

public class ProjectOne {

    private static final String JDBC_URL = "jdbc:mysql://127.0.0.1:3306/anjer_jdbc_project";
    private static final String USER = "root";
    private static final String PASSWORD = "Jayjay123@";


    public static void main(String[] args) {
        connectToDatabase();
        createTable();
        populateTable();
    }
    private static void connectToDatabase(){
        try {
            Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
            System.out.println("Connected to database");
            connection.close();
        } catch (SQLException e) {
            System.err.println("Error connecting...: " + e.getMessage());
        }
    }
//createTable method to create my table 'candidates
    private static void createTable() {
        try {
            Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
            Statement statement = connection.createStatement();

            String createTableSQL = "CREATE TABLE IF NOT EXISTS project_one (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY," +
                    "name VARCHAR(50)," +
                    "age INTEGER(10)," +
                    "email VARCHAR(50)," +
                    "location VARCHAR(50)," +
                    "designation VARCHAR(50)" +
                    ")";

            statement.executeUpdate(createTableSQL);
            System.out.println("Table successfully created");
            statement.close();
            connection.close();
        } catch (SQLException e) {
            System.err.println("Error Creating Table: " + e.getMessage());
        }
    }

    private static int populateTable() {
        int count = 0;
        try {
            Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
            PreparedStatement insertStatement = connection.prepareStatement("INSERT INTO project_one(name, age, email, location, designation) VALUES(?,?,?,?,?)");

            Scanner scanner = new Scanner(System.in);

            for (int i = 0; i < 10; i++) {
                System.out.println("Enter the details of the Candidate " + (i + 1));

                System.out.print("Name: ");
                String name = scanner.nextLine();

                System.out.print("Age: ");
                int age = scanner.nextInt();
                scanner.nextLine();

                System.out.print("Email: ");
                String email = scanner.nextLine();

                System.out.print("Location: ");
                String location = scanner.nextLine();

                System.out.print("Designation: ");
                String designation = scanner.nextLine();

                insertStatement.setString(1, name);
                insertStatement.setInt(2, age);
                insertStatement.setString(3, email);
                insertStatement.setString(4, location);
                insertStatement.setString(5, designation);

//                String insertSQL = "INSERT INTO users (name, age, email, location, designation) VALUES " +
//                        "('" + name + "', " + age + ", '" + email + "', '" + location + "', '" + designation + "')";

                insertStatement.execute();
                count++;
            }

            System.out.println("Data inserted successfully!");

            insertStatement.close();
            connection.close();
        } catch (SQLException e) {
            System.err.println("Error populating table: " + e.getMessage());
        }
        return count;
    }
}

