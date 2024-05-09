create table "storage" (
    id serial primary key,
    value double precision not null,
    storage_datetime timestamp without time zone not null default (now() at time zone 'utc'),
    id_product int not null,
    id_station int not null,
    foreign key (id_product) references product (id),
    foreign key (id_station) references station (id)
);