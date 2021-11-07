create table gestion
(
    CODPROVEEDOR int default -1 not null,
    CODPIEZA     int default -1 not null,
    CODPROYECTO  int default -1 not null,
    CANTIDAD     float          null,
    primary key (CODPROYECTO, CODPROVEEDOR, CODPIEZA),
    constraint gestion_piezas_ID_fk
        foreign key (CODPIEZA) references piezas (ID)
            on update cascade,
    constraint gestion_proveedores_ID_fk
        foreign key (CODPROVEEDOR) references proveedores (ID)
            on update cascade,
    constraint gestion_proyectos_ID_fk
        foreign key (CODPROYECTO) references proyectos (ID)
            on update cascade
);

