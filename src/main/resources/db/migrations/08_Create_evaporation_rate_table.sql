create table "evaporation_rate" (
  id serial primary key,
  value double precision not null,
  id_station int not null,
  id_product int not null,
    foreign key (id_station) references station (id),
    foreign key (id_product) references product (id)
);