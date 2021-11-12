package hu.nye.progtech;

import java.sql.*;

public class Main {

    public static void main(String[] args) throws SQLException {

        Connection connection = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test", "sa", "");

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

        System.out.println(insertUser(connection, "Hacker','10000'); DROP TABLE USERS;--", 28));

        // --------------------

        resultSet.close();
        statement.close();
        connection.close();

    }

    public static int insertUser(Connection connection, String name, Integer age) throws SQLException {
        String query = "INSERT INTO USERS (NAME, AGE) VALUES (?, ?);";
        int ret;
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, age);
            ret = preparedStatement.executeUpdate();
        }
        return ret;
    }

}
