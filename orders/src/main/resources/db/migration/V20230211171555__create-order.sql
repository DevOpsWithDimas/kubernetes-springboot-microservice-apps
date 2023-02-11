create table orders
(
    id           character varying(64) not null primary key,
    created_date timestamp             not null default now(),
    customer_id  varchar(64)           not null,
    item         varchar(100)          not null,
    qty          int2                  not null default 1
);
