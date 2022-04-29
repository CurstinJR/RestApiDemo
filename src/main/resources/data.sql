INSERT INTO users (first_name, last_name, email, password, created_at)
VALUES
    ('Quinn', 'Frost', 'quinn@email.com', 'password@123', CURRENT_TIME),
    ('Jack', 'Renewed', 'jack@email.com', 'password@456', CURRENT_TIME),
    ('Alyssa', 'Tessia', 'alyssa@email.com', 'password@789', CURRENT_TIME);

INSERT INTO roles (role_name)
VALUES ('ADMIN'),
       ('USER');

INSERT INTO user_roles (user_id, role_id)
VALUES (1, 1),
       (1, 2),
       (2, 2),
       (3, 2);
