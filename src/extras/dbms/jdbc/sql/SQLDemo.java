package extras.dbms.jdbc.sql;

import extras.dbms.prettytable.PrettyTable;

import kotlin.random.Random;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.*;

/*
* Problem Statement:
* Implement MYSQL/Oracle database connectivity with PHP/Python/Java
* Implement Database navigation operations (add, delete, edit,) using ODBC/JDBC.
*/

public class SQLDemo {
    private static Connection connection;
    private static Statement stmt;

    static {
        final String dbURL = "jdbc:mysql://localhost:3306/";
        final String dbName = "demo1";
        final String uNAME = "c2p";
        final String pass = "mysql";
        try {
            connection = DriverManager.getConnection(dbURL+dbName, uNAME, pass);
            stmt = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void createTable() {
        final String q =
                "CREATE TABLE Games(" +
                "GameID INT NOT NULL AUTO_INCREMENT, " +
                "Name VARCHAR(20), " +
                "SIZE INTEGER, " +
                "GENRE VARCHAR(20), " +
                "PRIMARY KEY (GameID) " + ");";
        try {
            stmt.executeUpdate(q);
            System.out.println("Database has been created.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertGame(
        String gameName, Long gameSize, String genre
    ) {
        final String q = "INSERT INTO Games(Name, Size, Genre) VALUES(?,?,?);";
        try {
            PreparedStatement ps = connection.prepareStatement(q);
            ps.setString(1, gameName);
            ps.setLong(2, gameSize);
            ps.setString(3, genre.toUpperCase());
            int rows = ps.executeUpdate();
            System.out.println("Successfully entered " + rows + " rows.");
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteGame(String gameName) {
        final String q = "DELETE FROM Games WHERE Name=?;";
        try {
            PreparedStatement ps = connection.prepareStatement(q);
            ps.setString(1, gameName);
            boolean deleted = ps.executeUpdate() > 0;
            if (deleted)
                System.out.println("Successfully removed row.");
            else
                System.out.println("Did not remove row.");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void showAllData() {
        final String q = "SELECT * FROM Games;";
        PrettyTable table = new PrettyTable("GameID", "Game Name", "Game Size" , "Game Genre");
        try {
            ResultSet rs = stmt.executeQuery(q);
            while (rs.next()) {
                table.addRow(
                        "" + rs.getInt("GameID"),
                        rs.getString("Name"),
                        "" + rs.getLong("SIZE") + " GB",
                        rs.getString("GENRE")
                );
            }
            rs.close();
            table.print();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main3(String[] args) {
        for (int i = 1; i <= 4; i++) {
            insertGame(
                    "Resident Evil " + i,
                    Random.Default.nextLong(18, 30),
                    "horror"
            );
        }
    }

    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            while (true) {
                System.out.println("--------------------------------");
                System.out.println("1. Insert Data");
                System.out.println("2. Show Data");
                System.out.println("3. Delete Data");
                System.out.println("4. Exit");
                System.out.print("Choose an option: ");
                int choice = Integer.parseInt(reader.readLine());
                System.out.println("--------------------------------");

                switch (choice) {
                    case 1:
                        System.out.println("Enter game name: ");
                        String name = reader.readLine();

                        System.out.println("Enter game size: ");
                        long size = Long.parseLong(reader.readLine());

                        System.out.println("Enter game genre: ");
                        String genre = reader.readLine();

                        insertGame(name, size, genre);
                        break;
                    case 2:
                        showAllData();
                        break;
                    case 3:
                        System.out.println("Enter game name to delete: ");
                        deleteGame(
                                reader.readLine()
                        );
                        break;
                    case 4:
                        reader.close();
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Wrong Input Try Again later.");
                }
                System.out.println();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
