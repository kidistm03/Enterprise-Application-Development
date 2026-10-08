import java.sql.*;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/StudentsDB";
        String username = "root";
        String password = "KiDiSt-3..,";

        try {
            // Task 1: Connect to MySQL Database, Create Database, and Table
            Connection connection = DriverManager.getConnection(url, username, password);
            System.out.println("Established Connection");

            Statement statement = connection.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS students (id INT PRIMARY KEY, firstname VARCHAR(255), lastname VARCHAR(255), grade INT)");
            System.out.println("Table 'students' created successfully.");

            // Close the resources
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}