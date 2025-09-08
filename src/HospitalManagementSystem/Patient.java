package HospitalManagementSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Patient {

    private Connection connection;
    private Scanner scanner;

    public Patient(Connection connection, Scanner scanner) {
       this.connection = connection;
       this.scanner = scanner;
    }

    public void addPatients() {
        System.out.println("Enter Patient Name:");
        String name = scanner.next();
        System.out.println("Enter Patient Age:");
        int age = scanner.nextInt();
        System.out.println("Enter Patient Gender");
        String gender = scanner.next();

        try {
         String query = "insert into patient(name, age, gender) values (?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, name);
            ps.setInt(2, age);
            ps.setString(3, gender);
            int affectedRows = ps.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Patient added Successfully...!!");
            } else {
                System.out.println("Failed to add patient...");
            }
        } catch(SQLException e) {
           e.printStackTrace();
        }
    }

    public void viewPatients() {
     String query = "select * from patient";
     try {
         PreparedStatement preparedstatement = connection.prepareStatement(query);
         ResultSet result = preparedstatement.executeQuery();
         System.out.println("Patient:");
         System.out.println("======================================================");
         System.out.println("|   Patient Id  |  Name           |   Age     |Gender|");
         System.out.println("======================================================");
         while(result.next()) {
             int id = result.getInt("id");
             String name = result.getString("name");
             int age = result.getInt("age");
             String gender = result.getString("gender");
             System.out.printf("|%12s|%-18s|%-12s|%-6s|\n", id, name, age, gender);
             System.out.println("======================================================");
         }
     } catch(SQLException e) {
       e.printStackTrace();
     }
    }

    public boolean getPatientById(int id) {
       String query = "select * from patient where id = ?";
       try {
           PreparedStatement preparedStatement = connection.prepareStatement(query);
           ResultSet result = preparedStatement.executeQuery();
           if (result.next()) {
               return true;
           } else {
               return false;
           }
       }
       catch(SQLException e) {
           e.printStackTrace();
       }
       return false;
    }
}
