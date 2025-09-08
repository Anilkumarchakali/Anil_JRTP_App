package HospitalManagementSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
public class Doctor {
    private Connection connection;

    public Doctor(Connection connection) {
        this.connection = connection;
    }

    public void viewDoctor() {
        String query = "select * from doctor";
        try {
            PreparedStatement preparedstatement = connection.prepareStatement(query);
            ResultSet result = preparedstatement.executeQuery();
            System.out.println("Doctor:");
            System.out.println("======================================================");
            System.out.println("|   Doctor Id  |  Name           |   Specialization|");
            System.out.println("======================================================");
            while(result.next()) {
                int id = result.getInt("id");
                String name = result.getString("name");
                String specialization = result.getString("specialization");
                System.out.printf("|%12s|%-18s|%-18s|\n", id, name, specialization);
                System.out.println("====================================================");
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean getDoctorById(int id) {
        String query = "select * from doctor where id = ?";
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
