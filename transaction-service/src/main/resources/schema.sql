
DROP TABLE TRANSACTION if exists;
CREATE TABLE TRANSACTION(
    ID int,
    FIRST_NAME  varchar(50),
    LAST_NAME  varchar(50),
    PHONE_NUMBER  varchar(50),
    EMAIL  varchar(50),
    USERNAME  varchar(50) NOT NULL UNIQUE,
    PASSWORD  varchar(50),
    CURRENT_AMOUNT int,
    AMOUNT int,
    BALANCE  int DEFAULT 0,
    STATUS varchar(50),
    DESCRIPTION varchar(100)
);
