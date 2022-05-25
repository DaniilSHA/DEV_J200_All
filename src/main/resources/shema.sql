CREATE TABLE clients (
     id serial primary key,
     type varchar(100) not null ,
     model varchar(100) not null ,
     ip varchar(25) not null
);

CREATE TABLE addresses (
     id serial primary key,
     city varchar(100) not null ,
     street varchar(100) not null ,
     num int not null,
     subnum int not null,
     flat int not null,
     extra varchar(200) not null,
     client_id int
);

alter table addresses add foreign key (client_id) references clients (id) on update cascade on delete cascade;

INSERT INTO clients (type, model, ip) values
                        ('мини', 'д110', '255.012.122.111'),
                        ('биг', 'д120', '255.255.65'),
                        ('мини', 'д110', '125.21.11.0'),
                        ('мини', 'д80', '1.1.1.1'),
                        ('мини', 'д80', '1.1.1.1'),
                        ('мини', 'д80', '1.1.1.1');

INSERT INTO addresses (city, street, num, subnum, flat, extra, client_id) values
 ('Хабаровск', 'Яна', 110, 42, 42, 'extra1', 1),
 ('Москва', 'Яна', 20, 4, 242, 'extra2', 2),
 ('Москва', 'Фрунзенская', 325, 24, 42, 'extra3', 3),
 ('Новгород', 'Яна', 110, 43, 13, 'extra3', 4),
 ('Новгород', 'Фрунзенская', 110, 43, 13, 'extra3', 1),
 ('Новгород', 'Всеволодская', 110, 43, 13, 'extra3', 6),
 ('Хабаровск', 'Всеволодская', 20, 43, 13, 'extra3', 5);


