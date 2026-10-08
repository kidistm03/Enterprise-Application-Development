import java.sql.*;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/StudentsDB";
        String username = "root";
        String password = "KiDiSt-3..,";

        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();

            // Task 1: Create Table
            statement.execute("CREATE TABLE IF NOT EXISTS students (id INT PRIMARY KEY, firstname VARCHAR(255), lastname VARCHAR(255), grade INT)");

            // Task 2: Insert Data
            insertSampleData(connection);

            // Close the resources
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void insertSampleData(Connection connection) {
        try {
            // Insert a single row (using IGNORE to avoid duplicate key errors)
            PreparedStatement insertSingle = connection.prepareStatement("INSERT IGNORE INTO students (id, firstname, lastname, grade) VALUES (?, ?, ?, ?)");
            insertSingle.setInt(1, 1);
            insertSingle.setString(2, "John");
            insertSingle.setString(3, "Doe");
            insertSingle.setInt(4, 90);
            insertSingle.executeUpdate();

            // Insert ten more rows
            PreparedStatement insertStmt = connection.prepareStatement("INSERT IGNORE INTO students (id, firstname, lastname, grade) VALUES (?, ?, ?, ?)");

            Object[][] studentsData = {
                    {2, "Jane", "Smith", 85},
                    {3, "Alex", "Jones", 92},
                    {4, "Emily", "Brown", 78},
                    {5, "Michael", "Davis", 88},
                    {6, "Sarah", "Wilson", 95},
                    {7, "David", "Taylor", 82},
                    {8, "Laura", "Anderson", 89},
                    {9, "James", "Thomas", 76},
                    {10, "Emma", "Jackson", 91},
                    {11, "Daniel", "White", 84}
            };

            for (Object[] student : studentsData) {
                insertStmt.setInt(1, (Integer) student[0]);
                insertStmt.setString(2, (String) student[1]);
                insertStmt.setString(3, (String) student[2]);
                insertStmt.setInt(4, (Integer) student[3]);
                insertStmt.executeUpdate();
            }

            System.out.println("All sample data inserted successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}