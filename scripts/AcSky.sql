drop database if exists AcSky;
create database AcSky;

use AcSky;

drop table if exists user_info;
create table user_info
(
    `id`          int auto_increment comment '用户 Id',
    `name`        varchar(32) character set utf8mb4 collate utf8mb4_0900_ai_ci comment '用户名',
    `account`     varchar(32) character set utf8mb4 collate utf8mb4_0900_ai_ci unique not null comment '账号',
    `password`    varchar(32) character set utf8mb4 collate utf8mb4_0900_ai_ci        not null comment '密码',
    `email`       varchar(64) character set utf8mb4 collate utf8mb4_0900_ai_ci comment '邮箱',
    `phone`       varchar(32) character set utf8mb4 collate utf8mb4_0900_ai_ci comment '电话',
    `profile`     text character set utf8mb4 collate utf8mb4_0900_ai_ci comment '个人简介',
    `avatar`      varchar(1024) character set utf8mb4 collate utf8mb4_0900_ai_ci      not null default '/assets/default.png' comment '头像',
    `create_date` datetime                                                            null comment '创建时间',
    `update_date` datetime                                                            null comment '修改时间',
    `is_delete`   tinyint                                                             not null default 0 comment '是否删除',
    primary key (`id`)
)
    ENGINE = InnoDB
    AUTO_INCREMENT = 1001
    CHARACTER SET = utf8mb4
    COLLATE utf8mb4_0900_ai_ci comment '用户信息表';

insert into user_info (name, account, password, email, phone, profile, create_date, update_date)
values ('root', '123456', md5(md5('123456')), 'debuggerzero@gmail.com', '123456', 'hhhh', current_time, current_time);

drop table if exists storage_image;
create table storage_image
(
    `id`          int auto_increment comment 'id'
        primary key,
    `image_name`  varchar(255) character set utf8mb4 collate utf8mb4_0900_ai_ci not null comment '图片名称',
    `image_path`  varchar(255) character set utf8mb4 collate utf8mb4_0900_ai_ci not null comment '图片路径',
    `image_hash`  varchar(255) character set utf8mb4 collate utf8mb4_0900_ai_ci not null comment '图片哈希值',
    `create_user` int                                                           not null comment '创建用户',
    `create_date` datetime                                                      not null comment '创建时间'
)
    ENGINE = InnoDB
    AUTO_INCREMENT = 1001
    CHARACTER SET = utf8mb4
    COLLATE utf8mb4_0900_ai_ci comment '图片信息表';

drop table if exists system_resource;
create table system_resource
(
    `id`             int                                                           not null auto_increment comment '唯一序号',
    `name`           varchar(50) character set utf8mb4 collate utf8mb4_0900_ai_ci  not null comment '资源名',
    `url`            varchar(255) character set utf8mb4 collate utf8mb4_0900_ai_ci not null comment 'url',
    `identity`       varchar(255) character set utf8mb4 collate utf8mb4_0900_ai_ci not null comment '资源标识',
    `request_method` varchar(10) character set utf8mb4 collate utf8mb4_0900_ai_ci  NOT NULL COMMENT '请求方法',
    `create_date`    datetime                                                      not null comment '创建日期',
    `update_date`    datetime                                                      not null comment '修改日期',
    primary key (`id`)
)
    ENGINE = InnoDB
    AUTO_INCREMENT = 1001
    CHARACTER SET = utf8mb4
    COLLATE utf8mb4_0900_ai_ci
    COMMENT '系统资源表';

insert into system_resource(name, url, identity, request_method, create_date, update_date)
values ('账号密码登录', '/login/password', 'anon', 'POST', current_time, current_time);
insert into system_resource(name, url, identity, request_method, create_date, update_date)
values ('上传图片', '/file/upload/image/*', 'perms[common,admin]', 'POST', current_time, current_time);
insert into system_resource(name, url, identity, request_method, create_date, update_date)
values ('获取用户列表', '/user/query/list/*/*', 'perms[admin]', 'GET', current_time, current_time);
insert into system_resource(name, url, identity, request_method, create_date, update_date)
values ('查询用户总数', '/user/query/total', 'perms[admin]', 'GET', current_time, current_time);
insert into system_resource(name, url, identity, request_method, create_date, update_date)
values ('修改用户信息', '/user/modify/info', 'perms[common,admin]', 'PUT', current_time, current_time);
insert into system_resource(name, url, identity, request_method, create_date, update_date)
values ('修改用户密码', '/user/modify/password', 'perms[common,admin]', 'PUT', current_time, current_time);
insert into system_resource(name, url, identity, request_method, create_date, update_date)
values ('添加用户信息', '/user/insert/userinfo', 'perms[admin]', 'POST', current_time, current_time);
insert into system_resource(name, url, identity, request_method, create_date, update_date)
values ('批量添加用户', '/user/insert/list/userinfo', 'perms[admin]', 'POST', current_time, current_time);
insert into system_resource(name, url, identity, request_method, create_date, update_date)
values ('删除用户信息', '/user/delete/userinfo/*', 'perms[admin]', 'DELETE', current_time, current_time);

drop table if exists system_role;
create table system_role
(
    `id`            int                                                           not null auto_increment comment '唯一序号',
    `name`          varchar(50) character set utf8mb4 collate utf8mb4_0900_ai_ci  not null comment '角色名',
    `describe`      varchar(255) character set utf8mb4 collate utf8mb4_0900_ai_ci not null comment '描述',
    `create_date`   datetime                                                      not null comment '创建日期',
    `create_person` int                                                           not null comment '创建用户',
    `update_date`   datetime                                                      not null comment '修改日期',
    `update_person` int                                                           not null comment '修改人',
    `remark`        varchar(255) character set utf8mb4 collate utf8mb4_0900_ai_ci not null COMMENT '备注',
    primary key (`id`)
)
    ENGINE = InnoDB
    AUTO_INCREMENT = 1001
    CHARACTER SET = utf8mb4
    COLLATE utf8mb4_0900_ai_ci
    COMMENT '系统角色表';

insert into system_role (`name`, `describe`, create_date, create_person, update_date, update_person, remark)
values ('none', '匿名用户', 20230406, 1001, 20230406, 1001, '无'),
       ('common', '普通角色', 20230406, 1001, 20230406, 1001, '无'),
       ('admin', '管理员', 20230406, 1001, 20230406, 1001, '无');

drop table if exists user_role;
create table user_role
(
    `user_id` int not null comment '用户编号',
    `role_id` int not null comment '角色编号',
    primary key (`user_id`, `role_id`),
    foreign key (`user_id`) references user_info (`id`) on delete cascade,
    foreign key (`role_id`) references system_role (`id`) on delete cascade
)
    ENGINE = InnoDB
    CHARACTER SET = utf8mb4
    COLLATE utf8mb4_0900_ai_ci
    COMMENT '用户角色关系表';

insert into user_role(user_id, role_id)
values (1001, 1003);