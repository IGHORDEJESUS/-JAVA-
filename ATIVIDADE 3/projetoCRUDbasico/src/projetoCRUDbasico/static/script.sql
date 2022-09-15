-- https://dev.mysql.com/doc/refman/8.0/en/create-table.html
-- https://dev.mysql.com/doc/refman/8.0/en/integer-types.html

create database if not exists db01;
use db01;
create table aluno (
   id int primary key auto_increment,
   matricula int not null unique,
   nome varchar(150) not null,
   email varchar(150) not null unique,
   sexo varchar(1) not null
);

select * from aluno;
