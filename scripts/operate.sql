## system_role
insert into system_role (`name`, `describe`, create_person, update_person, remark)
values ('none', '黑名单', 1001, 1001, '无'),
       ('user', '用户', 1001, 1001, '无'),
       ('admin', '管理员', 1001, 1001, '无');

## user_info
insert into user_info (name, account, password, email, phone, profile, create_person, update_person, role_id)
values ('root', '123456', md5(md5('123456')), 'debuggerzero@gmail.com', '123456', 'hhhh', -1, -1, 1003);
insert into user_info (name, account, password, email, phone, profile, create_person, update_person, role_id)
values ('user1', '1234561', md5(md5('123456')), 'debuggerzero@163.com', '1234561', 'hhhh', 1001, 1001, 1002);

## prob_info
INSERT INTO prob_info (title, difficulty, submit_cnt, accept_cnt, time_limit, space_limit, description, create_person,
                       update_person)
VALUES ('两数之和', 2, 100, 80, 1, 64,
        '给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那两个整数，并返回它们的数组下标。',
        1001,
        1001);

INSERT INTO prob_info (title, difficulty, submit_cnt, accept_cnt, time_limit, space_limit, description, create_person,
                       update_person)
VALUES ('反转字符串', 1, 150, 120, 2, 128, '编写一个函数，其作用是将输入的字符串反转过来。', 1001, 1001);

INSERT INTO prob_info (title, difficulty, submit_cnt, accept_cnt, time_limit, space_limit, description, create_person,
                       update_person)
VALUES ('验证回文字符串', 3, 80, 60, 1, 64,
        '给定一个字符串，验证它是否是回文串。只考虑字母和数字字符，可以忽略字母的大小写。', 1001, 1001);

INSERT INTO prob_info (title, difficulty, submit_cnt, accept_cnt, time_limit, space_limit, description, create_person,
                       update_person)
VALUES ('合并两个有序数组', 2, 200, 180, 3, 256,
        '给你两个有序整数数组 nums1 和 nums2，请你将 nums2 合并到 nums1 中，使 nums1 成为一个有序数组。', 1001, 1001);

INSERT INTO prob_info (title, difficulty, submit_cnt, accept_cnt, time_limit, space_limit, description, create_person,
                       update_person)
VALUES ('删除链表中的节点', 1, 120, 90, 2, 128,
        '编写一个函数，输入为链表的第一个节点和一个整数 val ，从链表中删除与 val 相等的节点，返回删除后的链表。', 1001,
        1001);

## tag_info
INSERT INTO tag_info (name, create_person, update_person)
VALUES ('动态规划', 1001, 1001);
INSERT INTO tag_info (name, create_person, update_person)
VALUES ('贪心算法', 1001, 1001);
INSERT INTO tag_info (name, create_person, update_person)
VALUES ('回溯算法', 1001, 1001);
INSERT INTO tag_info (name, create_person, update_person)
VALUES ('图论', 1001, 1001);
INSERT INTO tag_info (name, create_person, update_person)
VALUES ('字符串', 1001, 1001);

insert into pro_tag
values (1, 1);
insert into pro_tag
values (1, 2);
insert into pro_tag
values (2, 3);
insert into pro_tag
values (2, 4);
insert into pro_tag
values (3, 1);
insert into pro_tag
values (3, 2);
insert into pro_tag
values (4, 1);
insert into pro_tag
values (4, 5);
insert into pro_tag
values (4, 3);

## system_resource
insert into prob_submit(language, code, judge_info, status, pro_Id, create_person) values
('java', 'public class Main() {}', '{}', 0, 1, 1001);
insert into prob_submit(language, code, judge_info, status, pro_Id, create_person) values
('c', 'public class Main() {}', '{}', 0, 1, 1001);
insert into prob_submit(language, code, judge_info, status, pro_Id, create_person) values
('cpp', 'public class Main() {}', '{}', 0, 1, 1001);
insert into prob_submit(language, code, judge_info, status, pro_Id, create_person) values
('python', 'public class Main() {}', '{}', 0, 1, 1001);
insert into prob_submit(language, code, judge_info, status, pro_Id, create_person) values
('cpp', 'public class Main() {}', '{}', 0, 2, 1001);
insert into prob_submit(language, code, judge_info, status, pro_Id, create_person) values
('python', 'public class Main() {}', '{}', 0, 2, 1001);

select
    prob_submit.id,
    prob_submit.language,
    prob_submit.code,
    prob_submit.judge_info,
    prob_submit.status,
    p.id as pid,
    p.title as p_title,
    u.name as create_person,
    prob_submit.create_date,
    prob_submit.update_date
from prob_submit
left join user_info u on prob_submit.create_person = u.id
left join prob_info p on p.id = prob_submit.pro_Id