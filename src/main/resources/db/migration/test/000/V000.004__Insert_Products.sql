
USE warehouseSchema;

SET autocommit = 0;

START TRANSACTION;

INSERT INTO `PRODUCT` 
(`NAME`, `DESCRIPTION`, `PRICE`) 
VALUES 
('Fanta Lemon', '2l Bottle', '1.29'),
('Fanta Orange', '2l Bottle', '1.29'),
('Sprite', '2l Bottle', '1.29'),
('Cola-Cola', '2l Bottle', '1.29'),
('Pepsi', '2l Bottle', '1.09'),
('Fanta Lemon', '0.33l can', '0.33'),
('Fanta Orange', '0.33l can', '0.33'),
('Sprite', '0.33l can', '0.33'),
('Cola-Cola', '0.33l can', '0.33'),
('Pepsi', '0.33l can', '0.27'),
('Budweiser', 'Pack 6x0.25l', '4.99'),
('Sol', 'Pack 6x0.25l', '5.29'),
('Corona', 'Pack 6x0.25l', '5.31'),
('Heineken', 'Pack 6x0.25l', '4.63'),
('Heineken', '0.5l can', '2.33');

COMMIT;

SET autocommit = 1;