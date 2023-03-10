drop table  if exists tb_brand;
create table  tb_brand(
    id int primary key auto_increment,
    brand_name varchar(20),
    company_name varchar(20),
    ordered int,
    description varchar(100),
    status int
);
insert into tb_brand(brand_name,company_name,ordered,description,status)
values ('brandName1','companyName1',111,'description1',0),
       ('brandName2','companyName2',222,'description2',1),
       ('brandName3','companyName3',333,'description3',0),
       ('brandName4','companyName4',444,'description4',1);
select *
from tb_brand;