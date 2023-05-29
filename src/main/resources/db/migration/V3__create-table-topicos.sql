create table topicos(
    id bigint not null auto_increment,
    titulo varchar(100) not null unique,
    mensaje varchar(250) not null unique,
    fecha_creacion DATE not null,
    status varchar(100) not null,
    autor_id bigint not null,
    curso_id bigint not null,
    primary key(id),
    foreign key(autor_id) references usuarios(id) on delete cascade on update cascade,
    foreign key(curso_id) references cursos(id) on delete cascade on update cascade
)ENGINE=InnoDB;