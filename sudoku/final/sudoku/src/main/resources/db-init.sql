CREATE TABLE IF NOT EXISTS game_saves (
    id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    map varchar(255) NOT NULL,
    fixed varchar(255) NOT NULL
);