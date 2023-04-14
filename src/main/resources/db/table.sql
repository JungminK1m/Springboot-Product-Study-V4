CREATE TABLE user_tb(
	user_id INT PRIMARY KEY auto_increment,
	user_name VARCHAR(20) NOT null,
	user_password VARCHAR(20) NOT null,
	user_email VARCHAR(20) NOT null,
	created_at TIMESTAMP NOT null
);

CREATE TABLE product_tb(
	product_id INT PRIMARY KEY auto_increment,
	product_name VARCHAR(20) NOT null,
	product_price INT NOT null,
	product_qty INT NOT null,
	created_at TIMESTAMP NOT null
);

create table orders_tb(
    orders_id int primary KEY auto_increment,
    orders_name varchar(20) NOT null,
    orders_price int NOT null,
    orders_qty int NOT null,
    product_id int NOT null,
    user_id int NOT null,
    created_at TIMESTAMP
);