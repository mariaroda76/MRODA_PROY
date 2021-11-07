create table proyectos
(
    ID     int auto_increment
        primary key,
    CODIGO varchar(6)  not null,
    NOMBRE varchar(40) not null,
    CIUDAD varchar(40) null,
    constraint PROYECTOS_CODIGO_uindex
        unique (CODIGO)
);

