INSERT INTO client (
    company_name,
    uri,
    phone,
    street_address,
    city,
    state,
    zip_code
) VALUES (
    'Fake Aquent',
    'http://www.fakeaquent.com',
    '(555) 222-1242',
    '123 Any St.',
    'Asheville',
    'NC',
    '28801'
);
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
    1
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
