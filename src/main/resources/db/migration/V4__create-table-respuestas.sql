create table respuestas(
    id bigint not null auto_increment,
    mensaje varchar(250) not null,
    topico_id bigint not null,
    fecha_creacion DATE not null,
    autor_id bigint not null,
    solucion boolean not null,
    primary key(id),
    foreign key(topico_id) references topicos(id) on delete cascade on update cascade,
    foreign key(autor_id) references usuarios(id) on delete cascade on update cascade
)ENGINE=InnoDB;