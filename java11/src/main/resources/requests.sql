INSERT INTO buyers (name)
VALUES
    ('Елена'),
    ('Иван'),
    ('Ольга'),
    ('Александр'),
    ('Мария');

INSERT INTO product (title, price)
VALUES
    ('PS', 40000),
    ('Ноутбук', 80000),
    ('Велосипед', 15000),
    ('Книга', 20),
    ('Футболка', 30),
    ('Кофе', 5),
    ('Чай', 200);

INSERT INTO purchase (buyer_id, product_id, price)
VALUES
    (1, 1, 40000),
    (2, 2, 80000),
    (3, 3, 15000),
    (3, 6, 5),
    (3, 2, 80000),
    (4, 4, 20),
    (5, 5, 30),
    (1, 6, 5);

INSERT INTO purchase (buyer_id, product_id, price)
VALUES
    (1, 3, 15000);

