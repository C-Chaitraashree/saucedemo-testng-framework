CREATE DATABASE IF NOT EXISTS saucedemo_db;
USE saucedemo_db;

CREATE TABLE customers (
    customer_id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    postal_code VARCHAR(20)
);

CREATE TABLE products (
    product_id INT AUTO_INCREMENT PRIMARY KEY,
    product_name VARCHAR(100),
    price DECIMAL(10,2)
);

CREATE TABLE orders (
    order_id INT AUTO_INCREMENT PRIMARY KEY,
    customer_id INT,
    order_date DATE,
    FOREIGN KEY (customer_id) REFERENCES customers(customer_id)
);

CREATE TABLE order_items (
    order_item_id INT AUTO_INCREMENT PRIMARY KEY,
    order_id INT,
    product_id INT,
    quantity INT,
    FOREIGN KEY (order_id) REFERENCES orders(order_id),
    FOREIGN KEY (product_id) REFERENCES products(product_id)
);

INSERT INTO products (product_name, price) VALUES
('Sauce Labs Backpack', 29.99),
('Sauce Labs Bike Light', 9.99),
('Sauce Labs Bolt T-Shirt', 15.99),
('Sauce Labs Fleece Jacket', 49.99),
('Sauce Labs Onesie', 7.99),
('Test.allTheThings() T-Shirt (Red)', 15.99);

INSERT INTO customers (first_name, last_name, postal_code) VALUES
('John', 'Doe', '560001'),
('Jane', 'Smith', '560002'),
('Amit', 'Kumar', '560003');

INSERT INTO orders (customer_id, order_date) VALUES
(1, '2026-08-01'),
(2, '2026-08-05'),
(1, '2026-08-10');

INSERT INTO order_items (order_id, product_id, quantity) VALUES
(1, 1, 1),
(1, 2, 2),
(2, 3, 1),
(3, 4, 1),
(3, 1, 1);
