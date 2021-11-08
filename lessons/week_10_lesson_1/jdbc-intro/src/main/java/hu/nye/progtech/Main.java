package hu.nye.progtech;

import java.sql.*;

public class Main {

    public static void main(String[] args) throws SQLException {

        Connection connection = DriverManager.getConnection("jdbc:h2:tcp://localhost/./progtech", "sa", "password");

        String query = "SELECT * FROM USERS;";

        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery(query);

        while (resultSet.next()) {
            String name = resultSet.getString("NAME");

            System.out.println(name);
        }

        // --------------------

        query = "UPDATE USERS SET AGE = 32 WHERE ID = 4;";

        int i = statement.executeUpdate(query);

        System.out.println(i);

        // --------------------

        resultSet.close();
        statement.close();
        connection.close();

    }

}
