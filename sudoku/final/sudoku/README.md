# Sudoku final

This version supports save to database as well. It is logging to `target/sudoku.log` file.

To enable database:
- In `...configuration.RepositoryConfiguration` set the `jdbcGameSavesRepository` as `@Primary`
- Run the database with `mvn exec:java`

By default you can run the app and it will will save to XML.