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

            // Task 3: Retrieve Data
            retrieveData(connection);

            // Close resources
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void insertSampleData(Connection connection) {
        try {
            PreparedStatement insertSingle = connection.prepareStatement("INSERT IGNORE INTO students (id, firstname, lastname, grade) VALUES (?, ?, ?, ?)");
            insertSingle.setInt(1, 1);
            insertSingle.setString(2, "John");
            insertSingle.setString(3, "Doe");
            insertSingle.setInt(4, 90);
            insertSingle.executeUpdate();

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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void retrieveData(Connection connection) {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM students");

            System.out.println("ID | First Name | Last Name | Grade");
            System.out.println("------------------------------------");

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String firstname = resultSet.getString("firstname");
                String lastname = resultSet.getString("lastname");
                int grade = resultSet.getInt("grade");

                System.out.println(id + " " + " | " + firstname + " " +" | " + lastname + " " + " | " + grade);
            }

            resultSet.close();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}