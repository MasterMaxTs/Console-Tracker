create table if not exists items
(
    id      serial primary key,
    name    varchar(200),
    created timestamp,
    description text
);


comment on table items is '������';
comment on column items.id is '������������� ������ - ��������� ����';
comment on column items.name is '�������� ������';
comment on column items.created is '��������� ����� �������� ������';
comment on column items.description is '�������� ������';