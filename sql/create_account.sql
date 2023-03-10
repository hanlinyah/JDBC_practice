use jdbctest;
show tables;
drop table if exists account;
create table if not exists account(
    id int  primary key auto_increment,
    name VARCHAR(10),
    money double(10,2)
);
desc account;
insert into account(id, name, money) values (1,'小明',1000);
update account set money = 20000 where id =1;
select * from account;

--
--
-- begin ;
-- # select @@autocommit=0;
-- update account set money=money+500 where name='AAA';
--
-- update account set money=money/0 where name='CCC';
--
-- update account set money=money-500 where name='BBB';
-- commit ;
-- rollback ;
