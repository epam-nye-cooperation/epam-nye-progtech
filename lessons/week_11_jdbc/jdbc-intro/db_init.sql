/* Create a table to store user data */
CREATE TABLE USERS (
    ID int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    NAME varchar(255) NOT NULL,
    AGE int NOT NULL
);

/* Create a new user */
INSERT INTO USERS (NAME, AGE)
VALUES ('John', 26);

/* Update a user */
UPDATE USERS
SET AGE = 27
WHERE NAME = 'John';

/* Delete a user */
DELETE FROM USERS
WHERE NAME = 'John';

/* Query every user */
SELECT *
FROM USERS;
