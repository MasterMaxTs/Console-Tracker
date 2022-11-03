create table if not exists items
(
    id      serial primary key,
    name    varchar(30),
    created timestamp,
    description text
);