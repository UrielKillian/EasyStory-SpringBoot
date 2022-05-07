<<<<<<< HEAD
CREATE TABLE clients (
	id BINARY(16) NOT NULL,
	username VARCHAR(150) NOT NULL,
	password VARCHAR(150) NOT NULL,
	first_name VARCHAR(150) NOT NULL,
	last_name VARCHAR(150) NOT NULL,
	email VARCHAR(200) NOT NULL,
	telephone INT(150) NOT NULL,
	created_at DATETIME NOT NULL,
	created_by BINARY(16) NULL,
	updated_at DATETIME NULL,
	updated_by BINARY(16) NULL,
	PRIMARY KEY (id)
);
=======
CREATE TABLE clients(
    id BINARY(16) NOT NULL,
    username VARCHAR(150) NOT NULL,
    password VARCHAR(150) NOT NULL,
    first_name VARCHAR(150) NOT NULL,
    last_name VARCHAR(150) NOT NULL,
    email VARCHAR(200) NOT NULL,
    telephone INT(150) NOT NULL,
    created_at DATETIME NOT NULL,
    created_by BINARY(16) NULL,
    updated_at DATETIME  NULL,
    updated_by BINARY(16)  NULL,
    PRIMARY KEY (id)
);


>>>>>>> 85c13f50b24735d9dbbe28d22c062ccb6b6d6186
