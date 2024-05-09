create table "price" (
    id serial primary key,
    value double precision not null,
    price_datetime timestamp without time zone not null default (now() at time zone 'utc'),
    id_product int not null,
    foreign key (id_product) references product (id)
)