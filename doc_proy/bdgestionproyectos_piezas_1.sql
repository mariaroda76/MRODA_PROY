create table piezas
(
    ID          int auto_increment
        primary key,
    CODIGO      varchar(6)  not null,
    NOMBRE      varchar(20) not null,
    PRECIO      float       not null,
    DESCRIPCION text        null,
    constraint piezas_CODIGO_uindex
        unique (CODIGO)
);

