create table "station" (
  id serial primary key,
  name varchar(255) not null,
  longitude varchar(255) not null,
  latitude varchar(255) not null,
  employees_number int not  null
);