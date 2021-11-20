create sequence hibernate_sequence start with 1 increment by 1;
create table t_user (id integer not null, birth_date timestamp, name varchar(255), primary key (id));
INSERT INTO t_user VALUES (1, sysdate(), 'DAYAMAY HALDER');
INSERT INTO t_user VALUES (2, sysdate(), 'DRISHAN HALDER');
INSERT INTO t_user VALUES (3, sysdate(), 'DIPENDU HALDER');