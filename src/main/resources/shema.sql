CREATE TABLE clients (
     id int primary key,
     type varchar(100) not null ,
     model varchar(100) not null ,
     ip varchar(25) not null
);

CREATE TABLE addresses (
     id int primary key,
     city varchar(100) not null ,
     street varchar(100) not null ,
     num int not null,
     subnum int not null,
     flat int not null,
     extra varchar(200) not null,
     client_id int
);

alter table addresses add foreign key (client_id) references clients (id);

INSERT INTO clients values
                        (1, 'type1', 'model1', 'ip1'),
                        (2, 'type2', 'model2', 'ip2'),
                        (3, 'type3', 'model3', 'ip3'),
                        (4, 'type4', 'model4', 'ip4');

INSERT INTO addresses values
 (1, 'city1', 'street1', 1, 1, 1, 'extra1', 1),
 (2, 'city2', 'street2', 2, 2, 2, 'extra2', 2),
 (3, 'city3', 'street3', 3, 3, 3, 'extra3', 3);



