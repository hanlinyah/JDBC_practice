Drop table if exists  tb_users;
create table tb_users(
    id int primary key auto_increment,
    username varchar(20),
    password varchar(32)
);

insert into  tb_users(id,username,password) values (1,'AAA','111'), (2,'BBB','222');
select *
from tb_users;

