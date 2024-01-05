drop database if exists iweioj;
create database if not exists iweioj;

use iweioj;

create table if not exists system_resource
(
    `id`             int                                                            not null auto_increment comment '唯一序号',
    `name`           varchar(50) character set utf8mb4 collate utf8mb4_0900_ai_ci   not null comment '资源名',
    `url`            varchar(255) character set utf8mb4 collate utf8mb4_0900_ai_ci  not null comment 'url',
    `identity`       varchar(255) character set utf8mb4 collate utf8mb4_0900_ai_ci  not null comment '资源标识',
    `request_method` varchar(10) character set utf8mb4 collate utf8mb4_0900_ai_ci   NOT NULL COMMENT '请求方法',
    `create_date`    datetime default current_timestamp                             not null comment '创建日期',
    `update_date`    datetime default current_timestamp on update current_timestamp not null comment '修改日期',
    primary key (`id`)
)
    ENGINE = InnoDB
    AUTO_INCREMENT = 1001
    CHARACTER SET = utf8mb4
    COLLATE utf8mb4_0900_ai_ci
    COMMENT '系统资源表';

create table if not exists system_role
(
    `id`            int                                                            not null auto_increment comment '唯一序号',
    `name`          varchar(50) character set utf8mb4 collate utf8mb4_0900_ai_ci   not null comment '角色名',
    `describe`      varchar(255) character set utf8mb4 collate utf8mb4_0900_ai_ci  not null comment '描述',
    `create_date`   datetime default current_timestamp                             not null comment '创建日期',
    `create_person` int                                                            not null comment '创建用户',
    `update_date`   datetime default current_timestamp on update current_timestamp not null comment '修改日期',
    `update_person` int                                                            not null comment '修改人',
    `remark`        varchar(255) character set utf8mb4 collate utf8mb4_0900_ai_ci  not null COMMENT '备注',
    primary key (`id`)
)
    ENGINE = InnoDB
    AUTO_INCREMENT = 1001
    CHARACTER SET = utf8mb4
    COLLATE utf8mb4_0900_ai_ci
    COMMENT '系统角色表';

create table if not exists user_info
(
    `id`            int auto_increment comment '用户 Id',
    `name`          varchar(32) character set utf8mb4 collate utf8mb4_0900_ai_ci comment '用户名',
    `account`       varchar(32) character set utf8mb4 collate utf8mb4_0900_ai_ci unique not null comment '账号',
    `password`      varchar(32) character set utf8mb4 collate utf8mb4_0900_ai_ci        not null comment '密码',
    `email`         varchar(64) character set utf8mb4 collate utf8mb4_0900_ai_ci unique comment '邮箱',
    `phone`         varchar(32) character set utf8mb4 collate utf8mb4_0900_ai_ci unique comment '电话',
    `profile`       text character set utf8mb4 collate utf8mb4_0900_ai_ci comment '个人简介',
    `avatar`        varchar(1024) character set utf8mb4 collate utf8mb4_0900_ai_ci      not null default '/assets/default.png' comment '头像',
    `submit_cnt`    int                                                                 not null default 0 comment '题目提交数',
    `accept_cnt`    int                                                                 not null default 0 comment '题目通过数',
    `create_date`   datetime                                                            not null default current_timestamp comment '创建时间',
    `create_person` int                                                                 not null comment '创建人',
    `update_date`   datetime                                                            not null default current_timestamp on update current_timestamp comment '修改时间',
    `update_person` int                                                                 not null comment '修改人',
    `role_id`       int                                                                 not null comment '权限',
    `status`        tinyint check ( status in (0, 1) )                                  not null default 0 comment '是否删除',
    primary key (`id`),
    foreign key user_info (role_id) references system_role (id)
)
    ENGINE = InnoDB
    AUTO_INCREMENT = 1001
    CHARACTER SET = utf8mb4
    COLLATE utf8mb4_0900_ai_ci comment '用户信息表';

create table if not exists storage_image
(
    `id`          int auto_increment comment 'id'
        primary key,
    `image_name`  varchar(255) character set utf8mb4 collate utf8mb4_0900_ai_ci not null comment '图片名称',
    `image_path`  varchar(255) character set utf8mb4 collate utf8mb4_0900_ai_ci not null comment '图片路径',
    `image_hash`  varchar(255) character set utf8mb4 collate utf8mb4_0900_ai_ci not null comment '图片哈希值',
    `create_user` int                                                           not null comment '创建用户',
    `create_date` datetime default current_timestamp                            not null comment '创建时间'
)
    ENGINE = InnoDB
    AUTO_INCREMENT = 1001
    CHARACTER SET = utf8mb4
    COLLATE utf8mb4_0900_ai_ci comment '图片信息表';

create table if not exists prob_info
(
    `id`            int primary key auto_increment comment '题目id',
    `title`         varchar(255) character set utf8mb4 collate utf8mb4_0900_ai_ci comment '题目标题',
    `difficulty`    tinyint(4) check ( difficulty in (1, 2, 3) ) comment '题目难度',
    `submit_cnt`    int                                default 0 not null comment '题目提交数',
    `accept_cnt`    int                                default 0 not null comment '题目通过数',
    `time_limit`    int                                default 1 comment '时间限制(s)',
    `space_limit`   int                                default 64 comment '空间限制(kb)',
    `description`   text character set utf8mb4 collate utf8mb4_0900_ai_ci comment '题目描述',
    `create_date`   datetime not null                  default current_timestamp comment '添加时间',
    `create_person` int      not null comment '添加人',
    `update_date`   datetime not null                  default current_timestamp on update current_timestamp comment '修改时间',
    `update_person` int      not null comment '修改人',
    `status`        tinyint check ( status in (0, 1) ) default 0 comment '是否删除'
)
    ENGINE = innodB
    AUTO_INCREMENT = 1
    CHARACTER SET = utf8mb4
    COLLATE utf8mb4_0900_ai_ci
    COMMENT '问题信息表';

create table if not exists tag_info
(
    `id`            int primary key auto_increment comment 'id',
    `name`          varchar(64) not null comment '标签名',
    `create_date`   datetime    not null default current_timestamp comment '添加时间',
    `create_person` int         not null comment '添加用户',
    `update_date`   datetime    not null default current_timestamp on update current_timestamp comment '更新时间',
    `update_person` int         not null comment '更新用户'
)
    engine = innodB
    AUTO_INCREMENT = 1
    CHARACTER SET = utf8mb4
    COLLATE utf8mb4_0900_ai_ci
    COMMENT '标签信息表';

create table if not exists pro_tag
(
    `pro_id` int not null comment '问题id',
    `tag_id` int not null comment '标签id',
    foreign key (pro_id) references prob_info (id),
    foreign key (tag_id) references tag_info (id),
    primary key (`pro_id`, `tag_id`)
)
    engine = innodB
    AUTO_INCREMENT = 1
    CHARACTER SET = utf8mb4
    COLLATE utf8mb4_0900_ai_ci
    COMMENT '题目标签关系表';

create table if not exists sample_info
(
    `id`     int primary key auto_increment comment '数据id',
    `input`  text character set utf8mb4 collate utf8mb4_0900_ai_ci comment '输入数据',
    `output` text character set utf8mb4 collate utf8mb4_0900_ai_ci comment '输出数据',
    `pid`    int not null comment '题目id'
)
    ENGINE = InnoDB
    AUTO_INCREMENT = 1
    CHARACTER SET = utf8mb4
    COLLATE utf8mb4_0900_ai_ci
    COMMENT '测试数据表';

-- 题目提交表
create table if not exists prob_submit
(
    id            bigint auto_increment comment 'id' primary key,
    language      varchar(128)                                                   not null comment '编程语言',
    code          text                                                           not null comment '用户代码',
    judge_info    text                                                           null comment '判题信息（json 对象）',
    status        int check ( status in (0, 1, 2, 3) ) default 0                 not null comment '判题状态（0 - 待判题、1 - 判题中、2 - 成功、3 - 失败）',
    pro_Id        int                                                            not null comment '题目 id',
    create_person int                                                            not null comment '创建用户 id',
    create_date   datetime                             default current_timestamp not null comment '创建时间',
    update_date   datetime                             default current_timestamp not null on update current_timestamp comment '更新时间',
    index idx_problem_id (pro_Id),
    index idx_userId (create_person)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8mb4
  COLLATE utf8mb4_0900_ai_ci
    COMMENT '题目提交记录表';