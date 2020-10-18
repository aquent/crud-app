CREATE TABLE client (
    client_id integer IDENTITY PRIMARY KEY,
    client_name varchar(50) UNIQUE NOT NULL,
    website_uri varchar(100) NOT NULL,
    phone_number varchar(15) NOT NULL,
    street_address varchar(50),
    city varchar(50) NOT NULL,
    state varchar(2) NOT NULL,
    zip_code varchar(5) NOT NULL
);
CREATE TABLE person (
    person_id integer IDENTITY PRIMARY KEY,
    first_name varchar(50) NOT NULL,
    last_name varchar(50) NOT NULL,
    email_address varchar(50) UNIQUE NOT NULL,
    street_address varchar(50) NOT NULL,
    city varchar(50) NOT NULL,
    state varchar(2) NOT NULL,
    zip_code varchar(5) NOT NULL,
    client_id integer,
    FOREIGN KEY (client_id) REFERENCES client(client_id)
);
