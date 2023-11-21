package hu.nye.progtech;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws SQLException {

        try (Connection connection = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/progtech","admin","admin")){
            String deleteAllHack = "'BUDAPEST'; DELETE FROM player; --'";
            /*String update = "UPDATE player SET password = 'BUDAPEST'; DELETE FROM player; --' WHERE id = 2";
            Statement updateStatement = connection.createStatement();
            int result = updateStatement.executeUpdate(update);
            System.out.println(result);*/
            String preparedUpdate = "UPDATE player SET password = ? WHERE id = 4";
            PreparedStatement preparedStatement = connection.prepareStatement(preparedUpdate);
            preparedStatement.setString(1, deleteAllHack);

            preparedStatement.executeUpdate();

            String listAllPlayerQuery = "SELECT * FROM PLAYER";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(listAllPlayerQuery);

            List<Player> players = new ArrayList<>();

            while(resultSet.next()){
                String name = resultSet.getString("name");
                String password = resultSet.getString("password");
                int gameState = resultSet.getInt("game_state");
                int id = resultSet.getInt("id");
                Player player = new Player(id, name, password, gameState);
                players.add(player);
            }

            System.out.println(players);

            statement.close();
            resultSet.close();
            preparedStatement.close();
            //updateStatement.close();
        }
    }
}
