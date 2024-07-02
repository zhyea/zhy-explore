--
-- create schema
--

create table if not exists staff
(

    id          int         not null default 0 auto_increment comment 'id',

    name        varchar(32) not null default '' comment '姓名',
    age         int         not null default 0 comment '年龄',
    gender      tinyint              default 0 comment '性别',
    identity_no varchar(32) not null default '' comment '身份证号',
    staff_code  varchar(32) not null default '' comment '工号',

    deleted     tinyint     not null default 0 comment '删除标记',
    create_time datetime    not null default now() comment '创建时间',
    update_time timestamp   not null default current_timestamp on update current_timestamp comment '更新时间',

    primary key (id)
);

