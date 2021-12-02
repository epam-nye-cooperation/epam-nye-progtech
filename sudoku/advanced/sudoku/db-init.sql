CREATE TABLE game_saves (
    id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    username varchar(255) NOT NULL UNIQUE,
    map varchar(255) NOT NULL,
    fixed varchar(255) NOT NULL
);