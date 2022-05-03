INSERT INTO users (first_name, last_name, email, password, created_at)
VALUES
    ('Quinn', 'Frost', 'quinn@email.com', 'password@123', CURRENT_TIMESTAMP),
    ('Jack', 'Renewed', 'jack@email.com', 'password@456', CURRENT_TIMESTAMP),
    ('Alyssa', 'Tessia', 'alyssa@email.com', 'password@789', CURRENT_TIMESTAMP);

INSERT INTO roles (role_name)
VALUES ('ADMIN'),
       ('USER');

INSERT INTO user_roles (user_id, role_id)
VALUES (1, 1),
       (1, 2),
       (2, 2),
       (3, 2);

INSERT INTO product_category(name, description, created_at)
VALUES ('Laptop', 'Portable Computers', CURRENT_TIMESTAMP);

INSERT INTO product_inventory(quantity, created_at)
VALUES
    (100, CURRENT_TIMESTAMP),
    (57, CURRENT_TIMESTAMP),
    (79, CURRENT_TIMESTAMP);

INSERT INTO products(category_id, inventory_id, name, description, price, created_at)
VALUES
    (1, 1, 'Lenovo Laptop 15C3', 'High performance laptop, Sleek Design', 8999.99, CURRENT_TIMESTAMP),
    (1, 2, 'Dell Laptop 19M3', 'Student laptop, Beautifully Designed', 6999.99, CURRENT_TIMESTAMP),
    (1, 3, 'HP Laptop 21A5', 'Gaming laptop, Powerful CPU', 13999.99, CURRENT_TIMESTAMP);
