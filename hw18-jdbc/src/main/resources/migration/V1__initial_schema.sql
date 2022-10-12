create table test
(
    id   int,
    name varchar(50)
);
create table Client
(
    id   bigserial not null primary key,
    name varchar(50)
);
create table Manager
(
    no   bigserial not null primary key,
    label varchar(50),
    param1 varchar(50)
);
