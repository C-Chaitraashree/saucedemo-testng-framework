USE saucedemo_db;

SELECT * FROM products;

SELECT product_name, price FROM products WHERE price > 15;

SELECT o.order_id, c.first_name, c.last_name, o.order_date
FROM orders o
JOIN customers c ON o.customer_id = c.customer_id;

SELECT oi.order_id, p.product_name, oi.quantity
FROM order_items oi
JOIN products p ON oi.product_id = p.product_id;

SELECT o.order_id, c.first_name, p.product_name, oi.quantity
FROM orders o
JOIN customers c ON o.customer_id = c.customer_id
JOIN order_items oi ON o.order_id = oi.order_id
JOIN products p ON oi.product_id = p.product_id;

SELECT p.product_name, SUM(oi.quantity) AS total_quantity
FROM order_items oi
JOIN products p ON oi.product_id = p.product_id
GROUP BY p.product_name;

SELECT p.product_name, SUM(oi.quantity) AS total_quantity
FROM order_items oi
JOIN products p ON oi.product_id = p.product_id
GROUP BY p.product_name
HAVING SUM(oi.quantity) > 1;

SELECT c.first_name, c.last_name, COUNT(o.order_id) AS total_orders
FROM customers c
JOIN orders o ON c.customer_id = o.customer_id
GROUP BY c.first_name, c.last_name;

SELECT AVG(price) AS average_price FROM products;

SELECT c.first_name, c.last_name
FROM customers c
WHERE c.customer_id IN (
    SELECT o.customer_id FROM orders o
    GROUP BY o.customer_id
    HAVING COUNT(o.order_id) > (
        SELECT AVG(order_count) FROM (
            SELECT COUNT(order_id) AS order_count FROM orders GROUP BY customer_id
        ) AS sub
    )
);

SELECT product_name, price FROM products
WHERE price = (SELECT MAX(price) FROM products);

SELECT product_name, price FROM products
ORDER BY price DESC
LIMIT 3;

SELECT order_id, order_date FROM orders
ORDER BY order_date DESC
LIMIT 2;

SELECT product_name FROM products
WHERE product_id NOT IN (SELECT DISTINCT product_id FROM order_items);
