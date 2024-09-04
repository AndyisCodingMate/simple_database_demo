/**
 * Run the following lines in CLI to compile and run the program:
 * javac -cp "lib/mysql-connector-java-9.0.0.jar" queryDatabase.java
 * java -cp ".:lib/mysql-connector-java-9.0.0.jar" queryDatabase
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class queryDatabase {
    public static void main(String[] args) {
        String username = "app_user";   // insert username
        String password = "1234";   // insert password
        String jdbcURL = "jdbc:mysql://localhost:3306/simpleDatabase"; // insert database URL
        // SQL query to insert data into the table, change table name
        String sql = 
        "SELECT * FROM coffeeSales where payment='cash' ORDER BY coffeetype ASC";

        Connection connection = null;
        Statement statement = null;
        ResultSet result = null;
        
        try  {
            // Establish a connection to the database
            connection = DriverManager.getConnection(jdbcURL,username,password);
            // Create a statement object
            statement = connection.createStatement();
            // Execute the query
            result = statement.executeQuery(sql);

            while (result.next()){
                String date = result.getString("date");
                String datetime = result.getString("datetime");
                String payment = result.getString("payment");
                String cardno = result.getString("cardno");
                float amount = result.getFloat("amount");
                String coffeetype = result.getString("coffeetype");

                // Print the results
                System.out.println(date + " " + datetime + " " + payment + " " + cardno + " " + amount + " " + coffeetype);
            }
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        } finally {
            try {
                if (result != null) {
                    result.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }

            }catch(SQLException e) {
                System.err.println("Database error: " + e.getMessage());
            }
        }
    }
}
