CREATE TABLE client (
    id int NOT NULL AUTO_INCREMENT,
    company_name varchar(50) NOT NULL,
    website_uri varchar(50) NOT NULL,
    phone_number varchar(20) NOT NULL,
    street_address varchar(50) NOT NULL,
    city varchar(50) NOT NULL,
    state varchar(2) NOT NULL,
    zip_code varchar(5) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE person (
    id int NOT NULL AUTO_INCREMENT,
    first_name varchar(50) NOT NULL,
    last_name varchar(50) NOT NULL,
    email_address varchar(50) NOT NULL,
    street_address varchar(50) NOT NULL,
    city varchar(50) NOT NULL,
    state varchar(2) NOT NULL,
    zip_code varchar(5) NOT NULL,
    client_id int,
    PRIMARY KEY (id),
    CONSTRAINT fk_person_client_id_client_id
        FOREIGN KEY (client_id) REFERENCES client(id)
);
