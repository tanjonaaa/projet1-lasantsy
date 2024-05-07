create table "movement" (
    id serial primary key,
    value double precision not null,
    movement_type movement_type not null default 'RESTOCKING',
    movement_datetime timestamp without time zone not null default (now() at time zone 'utc'),
    id_station serial not null,
    id_product serial not null,
    foreign key (id_station) references station (id),
    foreign key (id_product) references product (id)
);