-- user
create table if not exists worker
(
    id     int auto_increment primary key,
    `name` varchar(64),
    age    int
);


insert into worker (`name`, age)
values ('robin', 22);
insert into worker (`name`, age)
values ('tom', 23);
insert into worker (`name`, age)
values ('Jim', 24);