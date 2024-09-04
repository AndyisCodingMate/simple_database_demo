/**
 * Run the following lines in CLI to compile and run the program:
 * javac -cp "lib/mysql-connector-java-9.0.0.jar" fillDatabase.java
 * java -cp ".:lib/mysql-connector-java-9.0.0.jar" fillDatabase
 */


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class fillDatabase {
    public static void main(String[] args) {
        String csvFilePath = "index.csv"; // insert path to the CSV file
        String username = "app_user";   // insert username
        String password = "1234";   // insert password
        String jdbcURL = "jdbc:mysql://localhost:3306/simpleDatabase"; // insert database URL
        // SQL query to insert data into the table, change table name
        String sql = 
        "INSERT INTO coffeeSales (date, datetime, payment, cardno, amount, coffeetype) VALUES (?, ?, ?, ?, ?, ?)";

        Connection connection = null;
        BufferedReader lineReader = null;

        // Open database connection and read index.csv
        try  {
            // Establish a connection to the database
            connection = DriverManager.getConnection(jdbcURL,username,password);
            
            // Create a buffered reader to read the CSV file
            lineReader = new BufferedReader(new FileReader(csvFilePath));

            String line;
            // Skip the header line in the CSV 
            lineReader.readLine(); 

            // Prepare the statement once outside the loop
            PreparedStatement statement = connection.prepareStatement(sql);

            while ((line = lineReader.readLine()) != null) {
                // Tokenize the CSV line by ','
                String[] record = line.split(",");

                // Ensure correct number of columns per row in CSV
                if (record.length != 6) {
                    System.out.println("Invalid row found: " + line);
                    continue; 
                }
                
                // Set the values in the prepared statement
                statement.setString(1, record[0]); // date
                statement.setString(2, record[1]); // datetime
                statement.setString(3, record[2]); // For column3
                statement.setString(4, record[3]); // For column4
                statement.setFloat(5, Float.parseFloat(record[4])); // For column5
                statement.setString(6, record[5]); // For column6
                // Execute the insertion
                statement.executeUpdate();
            }

            // Print success message
            System.out.println("Data inserted successfully.");

        } catch (IOException e) {
            System.err.println("Error reading the CSV file: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        }
    }
}
