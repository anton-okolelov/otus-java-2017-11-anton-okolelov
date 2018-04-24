-- postgres

 create table users (
    id bigint,
    login text,
    primary key(id)
 );

 insert into users
 (id, login)
 values
 (1, 'anton');