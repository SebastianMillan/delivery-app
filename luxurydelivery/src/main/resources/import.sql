INSERT INTO type(id,name,description,activate) VALUES (1,'Mexicano','ay ay ay que chingada',true);
INSERT INTO type(id,name,description,activate) VALUES (2,'Italiano','Me gusta el espaguetti',true);
INSERT INTO type(id,name,description,activate) VALUES (3,'Hindú','Esta fresco',false);
INSERT INTO type(id,name,description,activate) VALUES (4,'Sin tipo','Almacena restaurantes sin tipo asignado',true);

INSERT INTO restaurant(id,name,type_id,avatar,description,location,phone,rating,activate) VALUES (1,'La bella Napoli',2,'Un avatar','El dueño es ludopata','Mairena del Aljarafe','954172748',0,true);

INSERT INTO category(id,restaurant_id,name,description,activate) VALUES (1,1,'Bebida','Refrescante',true);
INSERT INTO category(id,restaurant_id,name,description,activate) VALUES (2,1,'Entrantes','Entremeses',true);
INSERT INTO category(id,restaurant_id,name,description,activate) VALUES (3,1,'Carnes','Ibérica española',true);
INSERT INTO category(id,restaurant_id,name,description,activate) VALUES (4,1,'Pescados','Fresco y de temporada',true);
INSERT INTO category(id,restaurant_id,name,description,activate) VALUES (5,1,'Postre','Artesanos',true);
INSERT INTO category(id,restaurant_id,name,description,activate) VALUES (6,1,'Sin categoría','Para productos sin categoría seleccionada',true);

INSERT INTO allergen(id, name, description, image,activate) VALUES (1, 'Lactosa', 'Leche, lacteos y derivados', 'imagen',true);
INSERT INTO allergen(id, name, description, image,activate) VALUES (2, 'Gluten', 'Panes y harinas', 'imagen',true);
INSERT INTO allergen(id, name, description, image,activate) VALUES (3, 'Marisco', 'Gambas, gambones, langostinos, etc', 'imagen',true);

INSERT INTO product (id, name, description, image, price, category_id,activate) VALUES (1, 'Pizza Margherita', 'Pizza clásica con tomate, mozzarella y albahaca fresca', 'margherita.jpg', 8.50, 3,true);
INSERT INTO product (id, name, description, image, price, category_id,activate) VALUES (2, 'Lasagna alla Bolognese', 'Lasaña tradicional con ragú de carne y bechamel', 'lasagna.jpg', 10.00, 2,true);
INSERT INTO product (id, name, description, image, price, category_id,activate) VALUES (3, 'Risotto ai Funghi', 'Risotto cremoso con setas porcini y parmesano', 'risotto.jpg', 11.50, 3,true);
INSERT INTO product (id, name, description, image, price, category_id,activate) VALUES (4, 'Chianti Classico', 'Vino tinto italiano con notas afrutadas y taninos suaves', 'chianti.jpg', 4.50, 1,true);
INSERT INTO product (id, name, description, image, price, category_id,activate) VALUES (5, 'Limoncello', 'Licor de limón típico del sur de Italia, servido frío', 'limoncello.jpg', 3.00,1,true);

INSERT INTO food (id, calories, food_portion) VALUES (1, 850, 'FULL');
INSERT INTO food (id, calories, food_portion) VALUES (2, 950, 'FULL');
INSERT INTO food (id, calories, food_portion) VALUES (3, 780, 'HALF');

INSERT INTO drink (id, alcoholic, drink_size) VALUES (4, TRUE, 'LARGE');
INSERT INTO drink (id, alcoholic, drink_size) VALUES (5, TRUE, 'SMALL');

INSERT INTO orders (id,state_order,create_date,total,activate) VALUES (1,'PENDING',CURRENT_TIMESTAMP,29,true);
INSERT INTO orders (id,state_order,create_date,total,activate) VALUES (2,'IN_KITCHEN',CURRENT_TIMESTAMP,18.50,true);

INSERT INTO orderline (id,quantity,subtotal,orders_id,product_id,activate) VALUES (1,2,20,1,2,true);
INSERT INTO orderline (id,quantity,subtotal,orders_id,product_id,activate) VALUES (2,3,9,1,5,true);
INSERT INTO orderline (id,quantity,subtotal,orders_id,product_id,activate) VALUES (3,1,8.50,2,1,true);
INSERT INTO orderline (id,quantity,subtotal,orders_id,product_id,activate) VALUES (4,1,10,2,2,true);

ALTER TABLE category ALTER COLUMN id RESTART WITH 1000;
ALTER TABLE allergen ALTER COLUMN id RESTART WITH 1000;
ALTER TABLE type ALTER COLUMN id RESTART WITH 1000;
ALTER TABLE restaurant ALTER COLUMN id RESTART WITH 1000;
ALTER TABLE product ALTER COLUMN id RESTART WITH 1000;
ALTER TABLE orders ALTER COLUMN id RESTART WITH 1000;
ALTER TABLE orderline ALTER COLUMN id RESTART WITH 1000;


