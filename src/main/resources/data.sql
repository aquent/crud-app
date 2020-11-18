INSERT INTO person (
    first_name,
    last_name,
    email_address,
    street_address,
    city,
    state,
    zip_code,
    client_id
) VALUES (
    'John',
    'Smith',
    'fake1@aquent.com',
    '123 Any St.',
    'Asheville',
    'NC',
    '28801',
    2
), (
    'Jane',
    'Smith',
    'fake2@aquent.com',
    '123 Any St.',
    'Asheville',
    'NC',
    '28801',
    1
);

INSERT INTO client (
    name,
    url,
    phone,
    street_address,
    city,
    state,
    zip_code,
    contact_ids
) VALUES (
    'Walmart',
    'www.walmart.com',
    '5555555555',
    '123 Any St.',
    'Asheville',
    'NC',
    '28801',
    '1,2'
), (
    'CVS',
    'www.cvs.com',
    '123-456-7890',
    '123 Any St.',
    'Asheville',
    'NC',
    '28801',
    '1'
);