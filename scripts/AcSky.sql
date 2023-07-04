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

############
insert into system_resource(name, url, identity, request_method, create_date, update_date)
values ('上传图片', '/file/upload/image/*', 'perms[common,admin]', 'POST', current_time, current_time);
############
insert into system_resource(name, url, identity, request_method, create_date, update_date)
values ('账号密码登录', '/login/password', 'anon', 'POST', current_time, current_time);
############
insert into system_resource(name, url, identity, request_method, create_date, update_date)
values ('获取用户列表', '/user/query/list/*/*', 'perms[admin]', 'GET', current_time, current_time);
insert into system_resource(name, url, identity, request_method, create_date, update_date)
values ('查询用户总数', '/user/query/total', 'perms[admin]', 'GET', current_time, current_time);
insert into system_resource(name, url, identity, request_method, create_date, update_date)
values ('查询排行榜', '/user/query/ranking/*/*', 'anon', 'GET', current_time, current_time);
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
############
insert into system_resource(name, url, identity, request_method, create_date, update_date)
values ('获取题目列表', '/problem/list/*/*', 'anon', 'GET', current_time, current_time);
insert into system_resource(name, url, identity, request_method, create_date, update_date)
values ('获取题目详细信息', '/problem/one/*', 'perms[admin]', 'GET', current_time, current_time);
insert into system_resource(name, url, identity, request_method, create_date, update_date)
values ('获取题目详细信息(Markdown 生成)', '/problem/oneByMd/*', 'anon', 'GET', current_time, current_time);
insert into system_resource(name, url, identity, request_method, create_date, update_date)
values ('获取题目总数', '/problem/total', 'anon', 'GET', current_time, current_time);
insert into system_resource(name, url, identity, request_method, create_date, update_date)
values ('调试题目', '/problem/debug', 'perms[common,admin]', 'POST', current_time, current_time);
insert into system_resource(name, url, identity, request_method, create_date, update_date)
values ('提交题目', '/problem/commit', 'perms[common,admin]', 'POST', current_time, current_time);
insert into system_resource(name, url, identity, request_method, create_date, update_date)
values ('获取用户历史记录', '/problem/history/*', 'perms[common,admin]', 'GET', current_time, current_time);

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

drop table if exists prob_info;
CREATE TABLE prob_info
(
    `id`          INT PRIMARY KEY AUTO_INCREMENT COMMENT '题目id',
    `title`       VARCHAR(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci COMMENT '题目标题',
    `difficulty`  TINYINT(4) COMMENT '题目难度',
    `time_limit`  INT default 1 COMMENT '时间限制(s)',
    `space_limit` INT default 64 COMMENT '空间限制(kb)',
    `description` TEXT CHARACTER SET utf8mb4 collate utf8mb4_0900_ai_ci COMMENT '题目描述',
    `input_desc`  TEXT CHARACTER SET utf8mb4 collate utf8mb4_0900_ai_ci COMMENT '输入格式描述',
    `output_desc` TEXT CHARACTER SET utf8mb4 collate utf8mb4_0900_ai_ci COMMENT '输出格式描述',
    `hint`        TEXT CHARACTER SET utf8mb4 collate utf8mb4_0900_ai_ci COMMENT '提示信息',
    `create_ts`   DATE COMMENT '添加时间',
    `created_by`  INT COMMENT '添加者 id',
    `is_delete`   INT DEFAULT 0 COMMENT '是否删除'
#     `path`          VARCHAR(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci COMMENT '本地路径(图片...)',
#     `tags`          VARCHAR(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci COMMENT '分类标签'
)
    ENGINE = InnoDB
    AUTO_INCREMENT = 1
    CHARACTER SET = utf8mb4
    COLLATE utf8mb4_0900_ai_ci
    COMMENT '问题信息表';

#1.
insert into prob_info(title, difficulty, description, input_desc, output_desc, create_ts, created_by)
values ('A+B', 1, '输入两个整数，求这两个整数的和是多少。', '输入两个整数A,B，用空格隔开', '输出一个整数，表示这两个数的和',
        current_time, 1001);
#2.
INSERT INTO prob_info (title, difficulty, time_limit, space_limit, description, input_desc, output_desc, hint,
                       create_ts, created_by, is_delete)
VALUES ('最大公约数', 2, 2, 128, '给定两个正整数，求它们的最大公约数。', '输入两个正整数 A,B，用空格隔开。',
        '输出一个整数，表示 A 和 B 的最大公约数。', '', CURRENT_DATE, 1001, 0);
#3.
INSERT INTO prob_info (title, difficulty, time_limit, space_limit, description, input_desc, output_desc, hint,
                       create_ts, created_by, is_delete)
VALUES ('字符串逆序', 1, 1, 64, '给定一个字符串，将其逆序输出。', '输入一个字符串 S。', '输出逆序后的字符串。',
        '可以使用额外的空间存储结果。', CURRENT_DATE, 1001, 0);
#4.
INSERT INTO prob_info (title, difficulty, time_limit, space_limit, description, input_desc, output_desc, hint,
                       create_ts, created_by, is_delete)
VALUES ('素数判定', 2, 2, 128, '给定一个正整数，判断它是否为素数。', '输入一个正整数N。',
        '输出字符串 "Yes" 或 "No"，表示N 是否为素数。', '可以使用试除法判断素数。', CURRENT_DATE, 1001, 0);
#5.
INSERT INTO prob_info (title, difficulty, time_limit, space_limit, description, input_desc, output_desc, hint,
                       create_ts, created_by, is_delete)
VALUES ('字符串匹配', 3, 3, 256, '给定一个字符串S和一个模式串P，判断S中是否包含P。', '输入两个字符串S和P。',
        '输出字符串"Yes"或"No"，表示S是否包含P。', '', CURRENT_DATE, 1001, 0);
#6.
INSERT INTO prob_info (title, difficulty, time_limit, space_limit, description, input_desc, output_desc, hint,
                       create_ts, created_by, is_delete)
VALUES ('数组求和', 1, 1, 64, '给定一个整数数组，求所有元素的和。',
        '输入一个整数N表示数组的长度，和N个整数作为数组的元素。', '输出一个整数，表示数组所有元素的和。',
        '可以使用循环遍历数组并累加求和。', CURRENT_DATE, 1001, 0);
#7.
INSERT INTO prob_info (title, difficulty, time_limit, space_limit, description, input_desc, output_desc, hint,
                       create_ts, created_by, is_delete)
VALUES ('字符串分割', 2, 2, 128, '给定一个字符串S和一个分隔符字符C，将S按照C进行分割并输出分割后的结果。',
        '输入一个字符串S和一个字符C。', '输出按照字符C分割后的字符串数组。', '可以使用字符串分割函数或手动实现分割算法。',
        CURRENT_DATE, 1001, 0);
#8.
INSERT INTO prob_info (title, difficulty, time_limit, space_limit, description, input_desc, output_desc, hint,
                       create_ts, created_by, is_delete)
VALUES ('二进制转十进制', 3, 3, 256, '给定一个二进制数，将其转换为十进制数。', '输入一个二进制数字符串。',
        '输出一个十进制数。', '可以使用二进制转十进制的算法。', CURRENT_DATE, 1001, 0);
#9.
INSERT INTO prob_info (title, difficulty, time_limit, space_limit, description, input_desc, output_desc, hint,
                       create_ts, created_by, is_delete)
VALUES ('矩阵转置', 1, 1, 64, '给定一个二维矩阵，将其转置并输出。', '输入一个二维矩阵，每行的元素用空格隔开。',
        '输出转置后的二维矩阵，每行的元素用空格隔开。', '可以使用额外的空间存储转置后的矩阵。', CURRENT_DATE, 1001, 0);
#10.
INSERT INTO prob_info (title, difficulty, time_limit, space_limit, description, input_desc, output_desc, hint,
                       create_ts, created_by, is_delete)
VALUES ('字符串排序', 2, 2, 128, '给定一个字符串，将其按照字典序排序并输出。', '输入一个字符串S。',
        '输出按照字典序排序后的字符串。', '可以使用字符串排序算法，如快速排序或归并排序。', CURRENT_DATE, 1001, 0);
#11.
INSERT INTO prob_info (title, difficulty, time_limit, space_limit, description, input_desc, output_desc, hint,
                       create_ts, created_by, is_delete)
VALUES ('字符串长度', 2, 2, 128, '给定一个字符串，计算其长度。', '输入一个字符串，长度不超过1000。',
        '输出一个整数，表示字符串的长度。', '可以使用内置函数或者遍历字符计数。', CURRENT_DATE, 1001, 0);
#12.
INSERT INTO prob_info (title, difficulty, time_limit, space_limit, description, input_desc, output_desc, create_ts,
                       created_by, is_delete)
VALUES ('替换空格', 1, 1, 64, '请实现一个函数，把字符串中的每个空格替换成"%20"。', '输入一个字符串，长度不超过1000。',
        '输出一个整数，表示字符串替换空格后的结果。', CURRENT_DATE, 1001, 0);
#13
INSERT INTO prob_info (title, difficulty, time_limit, space_limit, description, input_desc, output_desc, hint,
                       create_ts, created_by, is_delete)
VALUES ('阶乘计算', 1, 1, 64, '给定一个正整数N，计算N的阶乘。', '输入一个正整数N。', '输出一个整数，表示N的阶乘。',
        '可以使用循环递归或迭代的方式来计算阶乘。', CURRENT_DATE, 1001, 0);
#14
INSERT INTO prob_info (title, difficulty, time_limit, space_limit, description, input_desc, output_desc, hint,
                       create_ts, created_by, is_delete)
VALUES ('斐波那契数列', 2, 1, 64, '计算斐波那契数列的第N项。', '输入一个正整数N。',
        '输出一个整数，表示斐波那契数列的第N项。', '可以使用递归或循环迭代的方式来计算斐波那契数列。', CURRENT_DATE, 1001,
        0);
#15
INSERT INTO prob_info (title, difficulty, time_limit, space_limit, description, input_desc, output_desc, hint,
                       create_ts, created_by, is_delete)
VALUES ('找出数组中的重复元素', 2, 1, 64, '给定一个整数数组，其中包含重复的元素，请找出数组中所有重复的元素。',
        '输入一个整数N 表示数组的长度，和N个整数作为数组的元素。', '输出一个数组，包含所有重复的元素。',
        '可以使用哈希表或排序的方法来解决。', CURRENT_DATE, 1001, 0);
#16
INSERT INTO prob_info (title, difficulty, time_limit, space_limit, description, input_desc, output_desc, hint,
                       create_ts, created_by, is_delete)
VALUES ('链表反转', 2, 1, 64, '给定一个单链表，请将其反转并输出。', '输入一个链表的头节点。',
        '输出一个链表的头节点，表示反转后的结果。', '可以使用迭代或递归的方式进行反转。', CURRENT_DATE, 1001, 0);
#17
INSERT INTO prob_info (title, difficulty, time_limit, space_limit, description, input_desc, output_desc, hint,
                       create_ts, created_by, is_delete)
VALUES ('判断字符串是否为回文串', 2, 1, 64, '给定一个字符串，请判断它是否是一个回文串。',
        '输入一个字符串 S，长度不超过   1000。', '输出一个布尔值，表示是否是回文串。',
        '可以使用双指针进行判断，忽略大小写和非字母字符。', CURRENT_DATE, 1001, 0);
#18
INSERT INTO prob_info (title, difficulty, time_limit, space_limit, description, input_desc, output_desc, hint,
                       create_ts, created_by, is_delete)
VALUES ('最大子序和', 2, 1, 64, '给定一个整数数组，找到一个具有最大和的连续子数组。',
        '输入一个整数 N 表示数组的长度，和 N 个整数作为数组的元素。', '输出一个整数，表示最大子序和。',
        '可以使用动态规划求解。', CURRENT_DATE, 1001, 0);
#19
INSERT INTO prob_info (title, difficulty, time_limit, space_limit, description, input_desc, output_desc, hint,
                       create_ts, created_by, is_delete)
VALUES ('A - B', 1, 1, 64, '输入两个整数，求这两个整数的和是多少。', '输入两个整数 A,B，用空格隔开。',
        '输出一个整数，表示这两个数的差值。', '', CURRENT_DATE, 1001, 0);
#20
INSERT INTO prob_info (title, difficulty, time_limit, space_limit, description, input_desc, output_desc, hint,
                       create_ts, created_by, is_delete)
VALUES ('最长上升子序列', 3, 1, 64, '给定一个无序的整数数组，找到其中最长上升子序列的长度。',
        '输入一个整数 N 表示数组的长度，和 N 个整数作为数组的元素。', '输出一个整数，表示最长上升子序列的长度。',
        '可以使用动态规划求解。', CURRENT_DATE, 1001, 0);

drop table if exists test_data;
CREATE TABLE test_data
(
    `id`         INT PRIMARY KEY AUTO_INCREMENT COMMENT '数据id',
    `input`      TEXT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci COMMENT '输入数据',
    `output`     TEXT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci COMMENT '输出数据',
    `pid`        INT NOT NULL COMMENT '题目id',
    `is_example` INT NOT NULL DEFAULT 0 COMMENT '是否为样例'
)
    ENGINE = InnoDB
    AUTO_INCREMENT = 1
    CHARACTER SET = utf8mb4
    COLLATE utf8mb4_0900_ai_ci
    COMMENT '测试数据表';

insert into test_data(input, output, pid, is_example)
values ('1 2', '3', 1, 1);
insert into test_data(input, output, pid, is_example)
values ('1 4', '5', 1, 1);
INSERT INTO test_data (input, output, pid, is_example)
VALUES ('12 18', '6', 2, 1);
INSERT INTO test_data (input, output, pid, is_example)
VALUES ('24 60', '12', 2, 1);
INSERT INTO test_data (input, output, pid, is_example)
VALUES ('world', 'dlrow', 3, 1);
INSERT INTO test_data (input, output, pid, is_example)
VALUES ('Hello', 'olleH', 3, 1);
INSERT INTO test_data (input, output, pid, is_example)
VALUES ('3', 'Yes', 4, 1);
INSERT INTO test_data (input, output, pid, is_example)
VALUES ('9', 'No', 4, 1);
INSERT INTO test_data (input, output, pid, is_example)
VALUES ('abc b', 'Yes', 5, 1);
INSERT INTO test_data (input, output, pid, is_example)
VALUES ('abc e', 'No', 5, 1);
INSERT INTO test_data (input, output, pid, is_example)
VALUES ('1 2 3 4 5', '15', 6, 1);
INSERT INTO test_data (input, output, pid, is_example)
VALUES ('hello ,', 'h,e,l,l,o', 7, 1);
INSERT INTO test_data (input, output, pid, is_example)
VALUES ('1001', '9', 8, 1);
INSERT INTO test_data (input, output, pid, is_example)
VALUES ('1 2 3 4 5 6 7 8 9', '1 4 7 2 5 8 3 6 9', 9, 1);
INSERT INTO test_data (input, output, pid, is_example)
VALUES ('dcba', 'abcd', 10, 1);
INSERT INTO test_data (input, output, pid, is_example)
VALUES ('hello', '5', 11, 1);
INSERT INTO test_data (input, output, pid, is_example)
VALUES ('We are happy.', 'We%20are%20happy.', 12, 1);
INSERT INTO test_data (input, output, pid, is_example)
VALUES ('5', '120', 13, 1);
INSERT INTO test_data (input, output, pid, is_example)
VALUES ('4', '2', 14, 1);
INSERT INTO test_data (input, output, pid, is_example)
VALUES ('1 2 2 3 3 4 5 6', '2 3', 15, 1);
INSERT INTO test_data (input, output, pid, is_example)
VALUES ('1 2 3 4 5 6 7', '7 6 5 4 3 2 1', 16, 1);
INSERT INTO test_data (input, output, pid, is_example)
VALUES ('323', 'true', 17, 1);
INSERT INTO test_data (input, output, pid, is_example)
VALUES ('987', 'flase', 17, 1);
INSERT INTO test_data (input, output, pid, is_example)
VALUES ('-2,1,-3,4,-1,2,1,-5,4', '6', 18, 1);
INSERT INTO test_data (input, output, pid, is_example)
VALUES ('8 3', '5', 19, 1);
INSERT INTO test_data (input, output, pid, is_example)
VALUES ('1, 7, 3, 5, 9, 4, 8', '4', 20, 1);

drop table if exists history;
create table history
(
    `id`       int primary key auto_increment comment '记录 Id',
    `pid`      int         not null comment '题目 Id',
    `uid`      int         not null comment '做题人',
    `status`   varchar(64) not null comment '题目状态',
    `code`     text comment '代码',
    `type`     varchar(64) not null comment '语言类型',
    `pro_time` date        not null comment '做题时间'
)
    ENGINE = InnoDB
    AUTO_INCREMENT = 1
    CHARACTER SET = utf8mb4
    COLLATE utf8mb4_0900_ai_ci
    COMMENT '做题历史表';