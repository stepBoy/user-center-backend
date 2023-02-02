
create table user
(
    id           bigint auto_increment
        primary key,
    userName     varchar(256)                        null comment '用户名',
    userAccount  varchar(256)                        null comment '用户账号',
    userPassword varchar(256)                        null comment '用户密码',
    gender       tinyint                             null comment '性别 0-男 1-女',
    phone        varchar(128)                        null comment '电话号码',
    email        varchar(512)                        null comment '邮箱',
    avatarUrl    varchar(512)                        null comment '头像',
    userStatus   tinyint   default 0                 not null comment '状态 0 正常',
    userRole     tinyint   default 0                 not null comment '0-普通用户 1-管理员',
    createTime   timestamp default CURRENT_TIMESTAMP not null,
    updateTime   timestamp default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP,
    isDeleted    tinyint   default 0                 not null comment '逻辑删除 0-未删除 1-删除'
)
    comment '用户表';