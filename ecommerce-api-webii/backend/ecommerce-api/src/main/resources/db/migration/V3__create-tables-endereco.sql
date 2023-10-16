CREATE TABLE endereco (
    id SERIAL primary key,
    uf varchar(100) not null,
    bairro varchar(100) not null unique,
    rua varchar(6) not null unique,
);