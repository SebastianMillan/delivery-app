
insert into category(id,name,description) values (nextval('category_seq'),'Bebida','Refrescante');
insert into type(id,name,description) values (nextval('type_seq'),'Mexicano','ay ay ay que chingada');
insert into type(id,name,description) values (nextval('type_seq'),'Italiano','Me gusta el espaguetti');
insert into restaurant(id,name,type_id,avatar,description,location,phone,rating) values (nextval('restaurant_seq'),'La bella Napoli',51,'Un avatar','El dueño es ludopata','Mairena del Aljarafe','954172748',0);