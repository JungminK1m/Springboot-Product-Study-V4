INSERT INTO user_tb(user_name, user_password, user_email, role, created_at) VALUES ('ssar', '1234', 'ssar@nate.com','USER', NOW());
INSERT INTO user_tb(user_name, user_password, user_email, role, created_at) VALUES ('cos', '1234', 'cos@nate.com', 'USER', NOW());
INSERT INTO user_tb(user_name, user_password, user_email, role, created_at) VALUES ('admin', '1234', 'admin@nate.com', 'ADMIN', NOW());

INSERT INTO product_tb(product_name, product_price, product_qty, created_at) VALUES('바나나', 3000, 98, NOW());
INSERT INTO product_tb(product_name, product_price, product_qty, created_at) VALUES('딸기', 2000, 100, NOW());

INSERT INTO orders_tb(orders_name, orders_price, orders_qty, product_id, user_id, created_at) VALUES ('바나나', 3000, 2, 1, 1, NOW());
INSERT INTO orders_tb(orders_name, orders_price, orders_qty, product_id, user_id, created_at)  VALUES ('딸기', 2000, 5, 2, 2, NOW());