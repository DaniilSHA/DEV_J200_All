CREATE TABLE clients (
     id bigint primary key,
     type varchar(100) not null ,
     model varchar(100) not null ,
     ip varchar(25) not null,
     addresses bigint
);

CREATE TABLE addresses (
     id bigint primary key,
     city varchar(100) not null ,
     street varchar(100) not null ,
     num int not null,
     subnum int not null,
     flat int not null,
     extra varchar(200) not null
);

alter table clients add foreign key (addresses) references addresses (id);

INSERT INTO addresses values
 (1, 'city1', 'street1', 1, 1, 1, 'extra1'),
 (2, 'city2', 'street2', 2, 2, 2, 'extra2'),
 (3, 'city3', 'street3', 3, 3, 3, 'extra3');

INSERT INTO clients values
(1, 'type1', 'model1', 'ip1', 1),
(2, 'type2', 'model2', 'ip2', 1),
(3, 'type3', 'model3', 'ip3', 2),
(4, 'type4', 'model4', 'ip4', 3);


