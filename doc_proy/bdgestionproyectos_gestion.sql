create table gestion
(
    CODPROVEEDOR varchar(6) default '000000' not null,
    CODPIEZA     varchar(6) default '000000' not null,
    CODPROYECTO  varchar(6) default '000000' not null,
    CANTIDAD     float                       null,
    primary key (CODPROYECTO, CODPROVEEDOR, CODPIEZA),
    constraint gestion_piezas__fk
        foreign key (CODPIEZA) references piezas (CODIGO)
            on update cascade on delete set default,
    constraint gestion_proveedores_CODIGO_fk
        foreign key (CODPROVEEDOR) references proveedores (CODIGO)
            on update cascade on delete set default,
    constraint gestion_proyectos_CODIGO_fk
        foreign key (CODPROYECTO) references proyectos (CODIGO)
            on update cascade on delete set default
);

