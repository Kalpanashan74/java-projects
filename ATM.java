package ATMSimulator;

import java.sql.*;

public class ATM {
    private Connection conn;
    public ATM() {
        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish connection
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ATMSystem", "root", "0103");
            System.out.println("Database connected successfully!");
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver not found. Add it to your classpath!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Error connecting to the database! Check your credentials and DB status.");
            e.printStackTrace();
        }
    }

    // CREATE: Register a new user
    public boolean registerUser(String username, int pin) {
        try {
            String sql = "INSERT INTO users (username, pin, balance) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setInt(2, pin);
            stmt.setDouble(3, 0.0); // Initial balance set to 0
            stmt.executeUpdate();
            System.out.println("User registered successfully!");
            return true;
        } catch (SQLException e) {
            System.out.println("Error: Username already exists!");
            return false;
        }
    }

    // READ: Authenticate user
    public boolean authenticateUser(String username, int pin) {
        try {
            String sql = "SELECT * FROM users WHERE username = ? AND pin = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setInt(2, pin);
            ResultSet rs = stmt.executeQuery();
            return rs.next(); // If a record is found, authentication is successful
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // READ: Get balance
    public double getBalance(String username) {
        try {
            String sql = "SELECT balance FROM users WHERE username = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getDouble("balance");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    // UPDATE: Deposit money
    public void deposit(String username, double amount) {
        try {
            String sql = "UPDATE users SET balance = balance + ? WHERE username = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setDouble(1, amount);
            stmt.setString(2, username); // Fixed error: using username instead of sql
            stmt.executeUpdate();
            System.out.println("Deposit successful! New Balance: " + getBalance(username));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // UPDATE: Withdraw money
    public boolean withdrawal(String username, double amount) {
        double balance = getBalance(username);
        if (amount > balance) {
            System.out.println("Insufficient funds!");
            return false;
        }
        try {
            String sql = "UPDATE users SET balance = balance - ? WHERE username = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setDouble(1, amount);
            stmt.setString(2, username);
            stmt.executeUpdate();
            System.out.println("Withdrawal successful! New Balance: " + getBalance(username));
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // DELETE: Delete user account
    public void deleteUser(String username) {
        try {
            String sql = "DELETE FROM users WHERE username = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.executeUpdate();
            System.out.println("User deleted successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
