INSERT INTO client (company_name, website_uri, phone_number, street_address, city, state, zip_code)
VALUES ('Aquent', 'aquent', '800-123-4567', '123 Any St.', 'Asheville', 'NC', '28801'),
       ('Company A', 'company-a', '800-321-9756', '123 Any St.', 'Asheville', 'NC', '28801'),
       ('Company B', 'company-b', '800-456-977', '123 Any St.', 'Asheville', 'NC', '28801'),
       ('Company C', 'company-c', '123-432-9876', '456 Any St.', 'Asheville', 'NC', '28812');

INSERT INTO person (first_name, last_name, email_address, street_address, city, state, zip_code, client_id)
VALUES ('John', 'Smith', 'fake1@aquent.com', '123 Any St.', 'Asheville', 'NC', '28801', 1),
       ('Jane', 'Smith', 'fake2@aquent.com', '123 Any St.', 'Asheville', 'NC', '28801', 2),
       ('Anna', 'William', 'fake3@aquent.com', '217 Any St.', 'Asheville', 'NC', '28802', null);