DROP SCHEMA `spring_shop`;
CREATE SCHEMA `spring_shop`;
USE `spring_shop`;

INSERT INTO 
	products(name, description, price, quantity, image) 
VALUES 
	('Crayons', 'Pack of 12 crayons', 12.34, 200, 'https://images.pexels.com/photos/8014280/pexels-photo-8014280.jpeg'),
	('Pencils', 'Just 1 pencil', 1.20, 145, 'https://images.pexels.com/photos/159752/pencil-office-design-creative-159752.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1'),
    ('Notebook', 'Just 1 onebook', 15, 57, 'https://images.pexels.com/photos/6353833/pexels-photo-6353833.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1');
    
INSERT INTO 
	roles(name, description)
VALUES
	('USER', 'User permissions'),
    ('ADMIN', 'Admin permissions');
    
INSERT INTO 
user_details(id) 
VALUES (1), (2), (3);

INSERT INTO
	users(email, password, role_id, status_id, user_details_id)
VALUES
	('user@user.user', '$2a$10$2wt8XlfM5nR6dbpWz4y9VuWk7yNpgWAkx1Fi52rGL.MKKao7ATQU2', 1, 1, 1),
	('admin@admin.admin', '$2a$10$2wt8XlfM5nR6dbpWz4y9VuWk7yNpgWAkx1Fi52rGL.MKKao7ATQU2', 2, 1, 2),
    ('user2@user2.user2', '$2a$10$2wt8XlfM5nR6dbpWz4y9VuWk7yNpgWAkx1Fi52rGL.MKKao7ATQU2', 1, 1, 3);
    
    
SELECT p.name, p.description, p.image, p.price, p.quantity, op.amount, u.email, u.user_id, p.price*op.amount AS PRICE_ORDER
FROM Products p INNER JOIN order_product op ON p.id = op.product_id
INNER JOIN orders o ON o.order_id = op.order_id
INNER JOIN users_orders uo ON uo.order_id = op.order_id
INNER JOIN users u ON u.user_id = uo.user_id
WHERE uo.user_id = 1;