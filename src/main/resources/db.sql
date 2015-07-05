---- start server
-- mysqld -u root

---- connect as root
-- mysql -u root

---- change root password
-- set password for 'root'@'localhost' = password('newpwd');
-- set password for 'root'@'127.0.0.1' = password('newpwd');
-- set password for 'root'@'::1' = password('newpwd');

---- create db
create database if not exists simpleatm character set utf8 collate utf8_general_ci;

---- create and grant user
create user 'simple'@'localhost' identified by 'simple27';
create user 'simple'@'%' identified by 'simple27';
grant all on simpleatm.* to 'simple'@'localhost';
grant all on simpleatm.* to 'simple'@'%';

---- select db
use simpleatm;

---- create tables
create table creditcard (
  number varchar(16) not null,
  pin varchar(4) not null,
  primary key (number)
);

---- create default data
insert into creditcard values ('1111222233334444', '1111');