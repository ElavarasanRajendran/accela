CREATE TABLE IF NOT EXISTS Person (
                              id VARCHAR (10) PRIMARY KEY NOT NULL,
                              firstName VARCHAR(100) NOT NULL,
                              lastName VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS Address (
                         street VARCHAR(10) NOT NULL,
                         city VARCHAR(100) NOT NULL,
                         state VARCHAR(100) NOT NULL,
                         postalCod VARCHAR(100) NOT NULL,
                         id Varchar(10),
                        CONSTRAINT FK_Person FOREIGN KEY (id) REFERENCES Person(id)
);