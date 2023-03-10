show tables;
desc stu;
drop table if exists stu;
drop table if exists class;
create table stu(
    id int primary key ,
    name varchar(20) not null unique ,
    classid int,
    age int,
    sex varchar(5),
    address varchar(100),
    math double(5,2),
    english double(5,2),
    hire_date date not null,

    constraint class_id foreign key (classid) references class(id)
);

alter  table stu drop  foreign key class_id;
-- 加入數據
insert into stu(id,name,classid,age,sex,address,math,english,hire_date)
    values
    (1,'test',1,33,'F','home',65,50,'1990-01-01');



create table class(
    id int primary key ,
    classname varchar(20)
);
select * from stu;

