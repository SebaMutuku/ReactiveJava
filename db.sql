CREATE TABLE users
(
    id    serial PRIMARY KEY,
    username   VARCHAR(50)  NOT NULL,
    password   VARCHAR(50)  NOT NULL,
    fullName      VARCHAR(255) NULL,
    lastName      VARCHAR(255) NULL,
    age     int default 0
);