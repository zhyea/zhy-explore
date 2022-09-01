--
--


-- 车型表
create table vehicle_model
(
    id             bigint      not null primary key auto_increment,

    model_code     varchar(64) not null unique default '' comment '车型ID',

    model_name_bom varchar(64)                 default '' comment '车型-内部名称',
    model_name_cn  varchar(64)                 default '' comment '车型-中文名称',
    model_name_en  varchar(64)                 default '' comment '车型-英文名称',

    vehicle_type   tinyint                     default 0 comment '车型类别：1 SUV， 2 轿车，3 商务车',
    status         tinyint                     default 0 comment '有效状态:0无效 1有效',
    sort           int                         default 1 comment '优先级',
    remark         text                        default '' comment '车型描述',
    pictures       text                        default '' comment '车型图片',

    data_version   int                         default 1,

    staff_code     varchar(32) comment '操作人 iamCode',
    staff_name     varchar(32) comment '操作人姓名',
    create_time    timestamp   not null        default current_timestamp comment '创建时间',
    update_time    timestamp   not null        default current_timestamp on update current_timestamp comment '更新时间',
    data_del       tinyint     not null        default 0 comment '数据是否删除 0 否 1 是',
) engine=innodb default charset=utf8mb4;


-- 车型版本
create table vehicle_edition
(
    id               bigint not null primary key auto_increment,

    model_code       varchar(64) default '' comment '所属车型ID',
    edition_code     varchar(64) default '' comment '车型版本ID',

    edition_name_bom varchar(64) default '' comment '车型版本-内部名称',
    edition_name_cn  varchar(64) default '' comment '车型版本-中文名称',
    edition_name_en  varchar(64) default '' comment '车型版本-英文名称',

    sale_status      tinyint     default 0 comment '售卖状态：0 未启售、1 在售、2 停售',
    sort             int         default 1 comment '优先级',
    remark           text        default '' comment '车型版本描述',
    pictures         text        default '' comment '车型图片',

    data_version     int         default 1,

    staff_code       varchar(32) comment '操作人iamCode',
    staff_name       varchar(32) comment '操作人姓名',
    create_time      timestamp   default current_timestamp comment '创建时间',
    update_time      timestamp   default current_timestamp on update current_timestamp comment '更新时间',
    data_del         tinyint     default '0' comment '数据是否删除 0 否 1 是'
) engine=innodb default charset=utf8mb4;


--