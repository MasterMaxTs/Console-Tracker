create table if not exists items
(
    id      serial primary key,
    name    varchar(200),
    created timestamp,
    description text
);


comment on table items is 'Заявки';
comment on column items.id is 'Идентификатор заявки - первичный ключ';
comment on column items.name is 'Название заявки';
comment on column items.created is 'Локальное время создания заявки';
comment on column items.description is 'Описание заявки';