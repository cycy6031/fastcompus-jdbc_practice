DROP TABLE IF EXISTS USERS;

CREATE TABLE USERS(
    userId      varchar(12) NOT NULL,
    password    varchar(12) NOT NULL,
    name        varchar(12) NOT NULL,
    email       varchar(12),

    primary key (userId)
)