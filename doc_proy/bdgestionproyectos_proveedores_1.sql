create table proveedores
(
    ID        int auto_increment
        primary key,
    CODIGO    varchar(6)  not null,
    NOMBRE    varchar(20) not null,
    APELLIDOS varchar(30) not null,
    DIRECCION varchar(40) not null,
    constraint PROVEEDORES_CODIGO_uindex
        unique (CODIGO)
);

