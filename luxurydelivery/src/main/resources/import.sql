INSERT INTO type(id,name,description) VALUES (1,'Mexicano','ay ay ay que chingada');
INSERT INTO type(id,name,description) VALUES (2,'Italiano','Me gusta el espaguetti');

INSERT INTO restaurant(id,name,type_id,avatar,description,location,phone,rating) VALUES (1,'La bella Napoli',2,'Un avatar','El dueño es ludopata','Mairena del Aljarafe','954172748',0);

INSERT INTO category(id,restaurant_id,name,description) VALUES (1,1,'Bebida','Refrescante');
INSERT INTO category(id,restaurant_id,name,description) VALUES (2,1,'Entrantes','Entremeses');
INSERT INTO category(id,restaurant_id,name,description) VALUES (3,1,'Carnes','Ibérica española');
INSERT INTO category(id,restaurant_id,name,description) VALUES (4,1,'Pescados','Fresco y de temporada');
INSERT INTO category(id,restaurant_id,name,description) VALUES (5,1,'Postre','Artesanos');

INSERT INTO allergen(id, name, description, image) VALUES (1, 'Lactosa', 'Leche, lacteos y derivados', 'imagen');
INSERT INTO allergen(id, name, description, image) VALUES (2, 'Gluten', 'Panes y harinas', 'imagen');
INSERT INTO allergen(id, name, description, image) VALUES (3, 'Marisco', 'Gambas, gambones, langostinos, etc', 'imagen');

INSERT INTO product (id, name, description, image, price, category_id) VALUES (1, 'Pizza Margherita', 'Pizza clásica con tomate, mozzarella y albahaca fresca', 'margherita.jpg', 8.50, 3);
INSERT INTO product (id, name, description, image, price, category_id) VALUES (2, 'Lasagna alla Bolognese', 'Lasaña tradicional con ragú de carne y bechamel', 'lasagna.jpg', 10.00, 2);
INSERT INTO product (id, name, description, image, price, category_id) VALUES (3, 'Risotto ai Funghi', 'Risotto cremoso con setas porcini y parmesano', 'risotto.jpg', 11.50, 3);
INSERT INTO product (id, name, description, image, price, category_id) VALUES (4, 'Chianti Classico', 'Vino tinto italiano con notas afrutadas y taninos suaves', 'chianti.jpg', 4.50, 1);
INSERT INTO product (id, name, description, image, price, category_id) VALUES (5, 'Limoncello', 'Licor de limón típico del sur de Italia, servido frío', 'limoncello.jpg', 3.00, 1);

INSERT INTO food (id, calories, food_portion) VALUES (1, 850, 'FULL');
INSERT INTO food (id, calories, food_portion) VALUES (2, 950, 'FULL');
INSERT INTO food (id, calories, food_portion) VALUES (3, 780, 'HALF');

INSERT INTO drink (id, alcoholic, drink_size) VALUES (4, TRUE, 'LARGE');
INSERT INTO drink (id, alcoholic, drink_size) VALUES (5, TRUE, 'SMALL');


ALTER TABLE category ALTER COLUMN id RESTART WITH 1000;
ALTER TABLE allergen ALTER COLUMN id RESTART WITH 1000;
ALTER TABLE type ALTER COLUMN id RESTART WITH 1000;
ALTER TABLE restaurant ALTER COLUMN id RESTART WITH 1000;
ALTER TABLE product ALTER COLUMN id RESTART WITH 1000;


