package hu.nye.progtech.sudoku.persist.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import hu.nye.progtech.sudoku.exceptions.GridReadException;
import hu.nye.progtech.sudoku.exceptions.GridWriteException;
import hu.nye.progtech.sudoku.model.Constants;
import hu.nye.progtech.sudoku.persist.Repository;

public class DbRepository implements Repository {

    private final String url;

    public DbRepository() {
        try {
            Class.forName("org.h2.Driver");
            url = String.format("jdbc:h2:mem:test;INIT=runscript from '%s'\\;runscript from '%s'",
                "c:/tmp/prog/create.sql", "c:/tmp/prog/init.sql");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override public String[] readLines() throws GridReadException {
        try (Connection connection = DriverManager.getConnection(url);
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM GRID_LINES WHERE grid_name = ?")) {
            statement.setString(1, "beginner");
            try (ResultSet resultSet = statement.executeQuery()) {
                String[] lines = new String[Constants.ROW_COUNT];
                resultSet.first();
                do {
                    lines[resultSet.getInt("row_nr") - 1] = resultSet.getString("line");
                } while (resultSet.next());
                return lines;
            }
        } catch (Exception e) {
            throw new GridReadException(e.getMessage());
        }
    }

    @Override public void writeLines(String[] lines) throws GridWriteException {
        try (Connection connection = DriverManager.getConnection(url)) {
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            connection.setAutoCommit(false);
            String name = String.format("beginner_%s", new Date().getTime());
            insertGrid(connection, name);
            insertGridLines(connection, name, lines);
            verify(connection);
            connection.commit();
        } catch (SQLException e) {
            throw new GridWriteException(e.getMessage());
        }
    }

    private void insertGridLines(Connection connection, String name, String[] lines) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(
            "INSERT INTO GRID_LINES (grid_name, line, row_nr) VALUES(?, ?, ?)")) {
            statement.setString(1, name);
            int recordCount = 0;
            for (int i = 0; i < lines.length; i++) {
                statement.setString(2, lines[i]);
                statement.setInt(3, i + 1);
                recordCount += statement.executeUpdate();
            }
            System.out.println("Recordcount: " + recordCount);
        }
    }

    private void insertGrid(Connection connection, String name) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(
            "INSERT INTO GRID (grid_name, rowcount, columncount) VALUES(?, ?, ?)")) {
            statement.setString(1, name);
            statement.setInt(2, Constants.ROW_COUNT);
            statement.setInt(3, Constants.COLUMN_COUNT);
            int recordCount = statement.executeUpdate();
            System.out.println("Recordcount: " + recordCount);
        }
    }

    private void verify(Connection connection) {
        printGrids(connection);
        printGridLines(connection);
    }

    private void printGridLines(Connection connection) {
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM GRID_LINES")) {
            try (ResultSet resultSet = statement.executeQuery()) {
                resultSet.first();
                do {
                    System.out.printf("name: %s, line: %s, row: %d%n",
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getInt(3));
                } while (resultSet.next());
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private void printGrids(Connection connection) {
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM GRID")) {
            try (ResultSet resultSet = statement.executeQuery()) {
                resultSet.first();
                do {
                    System.out.printf("name: %s, rows: %d, columns: %d%n",
                        resultSet.getString(1), resultSet.getInt(2), resultSet.getInt(3));
                } while (resultSet.next());
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
