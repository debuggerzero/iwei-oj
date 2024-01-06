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

insert into prob_submit(language, code, judge_info, status, pro_Id, create_person)
values ('java', 'public class Main() {}', '{}', 0, 1, 1001);
insert into prob_submit(language, code, judge_info, status, pro_Id, create_person)
values ('c', 'public class Main() {}', '{}', 0, 1, 1001);
insert into prob_submit(language, code, judge_info, status, pro_Id, create_person)
values ('cpp', 'public class Main() {}', '{}', 0, 1, 1001);
insert into prob_submit(language, code, judge_info, status, pro_Id, create_person)
values ('python', 'public class Main() {}', '{}', 0, 1, 1001);
insert into prob_submit(language, code, judge_info, status, pro_Id, create_person)
values ('cpp', 'public class Main() {}', '{}', 0, 2, 1001);
insert into prob_submit(language, code, judge_info, status, pro_Id, create_person)
values ('python', 'public class Main() {}', '{}', 0, 2, 1001);

############
insert into system_resource(name, url, identity, request_method, create_date, update_date)
values ('上传图片', '/file/upload/image', 'perms[user,admin]', 'POST', current_time, current_time);
############
insert into system_resource(name, url, identity, request_method)
values ('账号密码登录', '/user/login/password', 'anon', 'POST');
insert into system_resource(name, url, identity, request_method)
values ('退出登录', '/user/logout', 'perms[user, admin]', 'GET');
insert into system_resource(name, url, identity, request_method)
values ('获取用户列表', '/user/queryUserInfoList', 'perms[admin]', 'POST');
insert into system_resource(name, url, identity, request_method)
values ('查询排行榜', '/user/queryRanking', 'anon', 'POST');
insert into system_resource(name, url, identity, request_method)
values ('获取用户角色列表', '/user/queryUserRoleList', 'perms[admin]', 'POST');
insert into system_resource(name, url, identity, request_method)
values ('修改用户信息', '/user/modifyInfoByUser', 'perms[user,admin]', 'PUT');
insert into system_resource(name, url, identity, request_method)
values ('修改用户信息(管理员)', '/user/modifyInfoByAdmin', 'perms[admin]', 'PUT');
insert into system_resource(name, url, identity, request_method)
values ('修改用户密码', '/user/modifyUserPassword', 'perms[user,admin]', 'PUT');
insert into system_resource(name, url, identity, request_method)
values ('重置用户密码', '/user/resetPassword/*', 'perms[admin]', 'PUT');
insert into system_resource(name, url, identity, request_method)
values ('添加用户', '/user/insertOneUserInfo', 'perms[admin]', 'POST');
insert into system_resource(name, url, identity, request_method)
values ('批量添加用户', '/user/insertUserInfoList', 'perms[admin]', 'POST');
insert into system_resource(name, url, identity, request_method)
values ('删除用户信息', '/user/deleteUserInfo/*', 'perms[admin]', 'DELETE');
############
insert into system_resource(name, url, identity, request_method)
values ('查询问题信息列表', '/problem/getProbInfoList', 'perms[admin]', 'POST');
insert into system_resource(name, url, identity, request_method)
values ('查询信息视图列表', '/problem/getProbInfoVOList', 'anon', 'POST');
insert into system_resource(name, url, identity, request_method)
values ('根据题目 id 获取用例', '/problem/getSampleList/*', 'perms[admin]', 'GET');
insert into system_resource(name, url, identity, request_method)
values ('获取题目详细信息', '/problem/one/*', 'perms[user,admin]', 'GET');
insert into system_resource(name, url, identity, request_method)
values ('添加题目', '/problem/save', 'perms[admin]', 'POST');
insert into system_resource(name, url, identity, request_method)
values ('通过 id 更新题目信息', '/problem/update', 'perms[admin]', 'PUT');
insert into system_resource(name, url, identity, request_method)
values ('通过 id 删除题目信息', '/problem/delete/*', 'perms[admin]', 'DELETE');
insert into system_resource(name, url, identity, request_method)
values ('提交题目', '/problem/doQuestionSubmit', 'perms[user,admin]', 'POST');
insert into system_resource(name, url, identity, request_method)
values ('查询历史记录', '/problem/getProblemSubmitVO', 'perms[user,admin]', 'POST');
####################
insert into system_resource(name, url, identity, request_method)
values ('添加标签信息', '/tag/insert', 'perms[admin]', 'POST');
insert into system_resource(name, url, identity, request_method)
values ('通过 id 删除标签', '/tag/deleteTagInfoById/*', 'perms[admin]', 'DELETE');
insert into system_resource(name, url, identity, request_method)
values ('更新标签信息通过 id', '/tag/updateTagInfoById', 'perms[admin]', 'PUT');
insert into system_resource(name, url, identity, request_method)
values ('通过 id 查询标签详细信息', '/tag/getTagInfoById/*', 'perms[admin]', 'GET');
insert into system_resource(name, url, identity, request_method)
values ('通过 id 查询标签详细信息', '/tag/getTagInfoById/*', 'perms[admin]', 'GET');
insert into system_resource(name, url, identity, request_method)
values ('查询标签结果集', '/tag/getTagInfoList', 'perms[admin]', 'POST');
insert into system_resource(name, url, identity, request_method)
values ('查询标签视图对象', '/tag/getTagInfoVOList', 'anon', 'POST');