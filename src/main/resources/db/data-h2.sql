INSERT INTO menu (id, parent_id, title, name, path, redirect, component, icon, keep_alive, hidden, perms, type, sort_index, create_time, modify_time) VALUES (1, 0, '仪表盘', 'dashboard', '/dashboard', null, 'dashboard/Workplace', 'dashboard', 1, 0, null, '0', 2, '2020-05-28 20:44:36.000000', '2020-05-28 20:44:41.000000');
INSERT INTO menu (id, parent_id, title, name, path, redirect, component, icon, keep_alive, hidden, perms, type, sort_index, create_time, modify_time) VALUES (9, 0, '系统管理', 'RAM', '/ram', '/ram/user', 'RouteView', 'safety-certificate', 1, 0, '', '0', 9, '2020-05-31 12:00:02.000000', '2020-05-31 12:00:04.000000');
INSERT INTO menu (id, parent_id, title, name, path, redirect, component, icon, keep_alive, hidden, perms, type, sort_index, create_time, modify_time) VALUES (10, 9, '用户管理', 'user', '/ram/user', null, 'user/UserList', null, 1, 0, 'user:view', '0', 10, '2020-05-31 11:57:50.000000', '2020-05-31 11:57:52.000000');
INSERT INTO menu (id, parent_id, title, name, path, redirect, component, icon, keep_alive, hidden, perms, type, sort_index, create_time, modify_time) VALUES (11, 9, '角色管理', 'role', '/ram/role', null, 'role/RoleList', null, 1, 0, 'role:view', '0', 11, '2020-06-04 14:47:58.000000', '2020-06-04 14:48:01.000000');
INSERT INTO menu (id, parent_id, title, name, path, redirect, component, icon, keep_alive, hidden, perms, type, sort_index, create_time, modify_time) VALUES (12, 9, '菜单管理', 'menu', '/ram/menu', null, 'menu/MenuList', null, 1, 0, 'menu:view', '0', 12, '2020-06-05 16:26:55.000000', '2020-06-05 16:26:58.000000');
INSERT INTO menu (id, parent_id, title, name, path, redirect, component, icon, keep_alive, hidden, perms, type, sort_index, create_time, modify_time) VALUES (13, 9, '用户组管理', 'UserGroup', '/ram/user/group', null, 'group/UserGroupList', null, 1, 0, 'group:view', '0', 13, '2020-06-05 18:35:27.000000', '2020-06-05 18:35:31.000000');
INSERT INTO menu (id, parent_id, title, name, path, redirect, component, icon, keep_alive, hidden, perms, type, sort_index, create_time, modify_time) VALUES (14, 13, '保存', 'UserGroupSave', '', null, null, null, 0, 0, 'group:save', '1', 14, '2020-06-06 13:09:17.000000', '2020-06-06 13:09:21.000000');
INSERT INTO menu (id, parent_id, title, name, path, redirect, component, icon, keep_alive, hidden, perms, type, sort_index, create_time, modify_time) VALUES (15, 12, '保存', 'MenuSave', '', null, '', null, 0, 0, 'menu:save', '1', 15, '2020-06-06 14:30:12.000000', '2020-06-06 14:30:14.000000');
INSERT INTO menu (id, parent_id, title, name, path, redirect, component, icon, keep_alive, hidden, perms, type, sort_index, create_time, modify_time) VALUES (16, 11, '保存', 'RoleSave', '', null, null, null, 0, 0, 'role:save', '1', 16, '2020-06-06 17:41:13.000000', '2020-06-06 17:41:13.000000');
INSERT INTO menu (id, parent_id, title, name, path, redirect, component, icon, keep_alive, hidden, perms, type, sort_index, create_time, modify_time) VALUES (18, 12, '删除', 'MenuDelete', null, null, null, null, 0, 0, 'menu:delete', '1', 17, '2020-06-16 11:46:12.000000', '2020-06-16 11:46:15.000000');
INSERT INTO menu (id, parent_id, title, name, path, redirect, component, icon, keep_alive, hidden, perms, type, sort_index, create_time, modify_time) VALUES (19, 11, '删除', 'RoleDelete', null, null, null, null, 0, 0, 'role:delete', '1', 18, '2020-06-16 11:47:26.000000', '2020-06-16 11:47:29.000000');
INSERT INTO menu (id, parent_id, title, name, path, redirect, component, icon, keep_alive, hidden, perms, type, sort_index, create_time, modify_time) VALUES (20, 13, '删除', 'UserGroupDelete', null, null, null, null, 0, 0, 'group:delete', '1', 19, '2020-06-16 11:48:37.000000', '2020-06-16 11:48:40.000000');
INSERT INTO menu (id, parent_id, title, name, path, redirect, component, icon, keep_alive, hidden, perms, type, sort_index, create_time, modify_time) VALUES (21, 10, '新增', 'UserAdd', null, null, null, null, 0, 0, 'user:add', '1', 20, '2020-06-29 11:53:35.000000', '2020-06-29 11:53:41.000000');
INSERT INTO menu (id, parent_id, title, name, path, redirect, component, icon, keep_alive, hidden, perms, type, sort_index, create_time, modify_time) VALUES (22, 10, '重置密码', 'UserResetPassword', null, null, null, null, 0, 0, 'user:reset', '1', 21, '2020-06-29 20:20:01.000000', '2020-06-29 20:20:03.000000');
INSERT INTO menu (id, parent_id, title, name, path, redirect, component, icon, keep_alive, hidden, perms, type, sort_index, create_time, modify_time) VALUES (23, 10, '更新', 'UserUpdate', null, null, null, null, 0, 0, 'user:update', '1', 22, '2020-06-30 17:53:02.000000', '2020-06-30 17:53:07.000000');
INSERT INTO menu (id, parent_id, title, name, path, redirect, component, icon, keep_alive, hidden, perms, type, sort_index, create_time, modify_time) VALUES (24, 10, '删除', 'UserDelete', null, null, null, null, 0, 0, 'user:delete', '1', 23, '2020-06-30 17:53:45.000000', '2020-06-30 17:53:48.000000');
INSERT INTO menu (id, parent_id, title, name, path, redirect, component, icon, keep_alive, hidden, perms, type, sort_index, create_time, modify_time) VALUES (25, 0, '监控日志', 'monitor', '/monitor', '/monitor/log/action', 'RouteView', 'fund', 1, 0, '', '0', 24, '2020-07-16 09:54:09.000000', '2020-07-16 09:54:12.000000');
INSERT INTO menu (id, parent_id, title, name, path, redirect, component, icon, keep_alive, hidden, perms, type, sort_index, create_time, modify_time) VALUES (26, 25, '系统日志', 'actionLog', '/monitor/log/action', null, 'monitor/ActionLog', null, 1, 0, null, '0', 25, '2020-07-16 09:54:27.000000', '2020-07-16 09:54:25.000000');
INSERT INTO menu (id, parent_id, title, name, path, redirect, component, icon, keep_alive, hidden, perms, type, sort_index, create_time, modify_time) VALUES (27, 25, '登录日志', 'loginLog', '/monitor/log/login', null, 'monitor/LoginLog', null, 1, 0, null, '0', 26, '2020-07-16 21:14:02.000000', '2020-07-16 21:14:04.000000');
INSERT INTO menu (id, parent_id, title, name, path, redirect, component, icon, keep_alive, hidden, perms, type, sort_index, create_time, modify_time) VALUES (30, 0, '预设卡口', 'presetPlan', '/preset', null, 'preset/List', 'rocket', 1, 0, null, '0', 30, '2020-10-19 11:16:52.000000', '2020-10-19 11:16:54.000000');
INSERT INTO menu (id, parent_id, title, name, path, redirect, component, icon, keep_alive, hidden, perms, type, sort_index, create_time, modify_time) VALUES (31, 0, '布设卡口', 'designPlan', '/design', null, 'design/List', 'build', 1, 0, null, '0', 31, '2020-10-19 11:16:52.000000', '2020-10-19 11:16:54.000000');
INSERT INTO menu (id, parent_id, title, name, path, redirect, component, icon, keep_alive, hidden, perms, type, sort_index, create_time, modify_time) VALUES (32, 0, '还原轨迹', 'route', '/route', null, 'route/List', 'environment', 1, 0, null, '0', 32, '2020-10-19 11:16:52.000000', '2020-10-19 11:16:54.000000');
INSERT INTO menu (id, parent_id, title, name, path, redirect, component, icon, keep_alive, hidden, perms, type, sort_index, create_time, modify_time) VALUES (33, 0, '轨迹管理', 'routePaths', '/route/paths', null, 'route/PathList', 'car', 1, 0, null, '0', 33, '2020-10-19 11:16:52.000000', '2020-10-19 11:16:54.000000');
INSERT INTO menu (id, parent_id, title, name, path, redirect, component, icon, keep_alive, hidden, perms, type, sort_index, create_time, modify_time) VALUES (34, 0, '个人页', 'account', '/account', '/account/center', 'RouteView', 'user', 1, 0, null, '0', 34, '2020-07-17 15:54:26.000000', '2020-07-17 15:54:29.000000');
INSERT INTO menu (id, parent_id, title, name, path, redirect, component, icon, keep_alive, hidden, perms, type, sort_index, create_time, modify_time) VALUES (35, 34, '个人中心', 'accountCenter', '/account/center', null, 'account/center/Index', null, 1, 0, '', '0', 35, '2020-07-17 11:37:09.000000', '2020-07-17 11:37:12.000000');

INSERT INTO role (id, role_name, remark, is_default, deleted, create_time, modify_time) VALUES (1, '普通用户', '用户注册默认角色', 1, 0, '2020-05-28 16:25:39.000000', '2020-07-23 18:27:45.000000');
INSERT INTO role (id, role_name, remark, is_default, deleted, create_time, modify_time) VALUES (2, '管理员', '管理员', 0, 0, '2020-06-04 10:23:29.000000', '2020-07-23 18:28:00.000000');

INSERT INTO role_menu (role_id, menu_id) VALUES (1, 1);
INSERT INTO role_menu (role_id, menu_id) VALUES (1, 30);
INSERT INTO role_menu (role_id, menu_id) VALUES (1, 31);
INSERT INTO role_menu (role_id, menu_id) VALUES (1, 32);
INSERT INTO role_menu (role_id, menu_id) VALUES (1, 33);
INSERT INTO role_menu (role_id, menu_id) VALUES (1, 34);
INSERT INTO role_menu (role_id, menu_id) VALUES (1, 35);
INSERT INTO role_menu (role_id, menu_id) VALUES (2, 2);
INSERT INTO role_menu (role_id, menu_id) VALUES (2, 1);
INSERT INTO role_menu (role_id, menu_id) VALUES (2, 9);
INSERT INTO role_menu (role_id, menu_id) VALUES (2, 10);
INSERT INTO role_menu (role_id, menu_id) VALUES (2, 11);
INSERT INTO role_menu (role_id, menu_id) VALUES (2, 12);
INSERT INTO role_menu (role_id, menu_id) VALUES (2, 13);
INSERT INTO role_menu (role_id, menu_id) VALUES (2, 14);
INSERT INTO role_menu (role_id, menu_id) VALUES (2, 15);
INSERT INTO role_menu (role_id, menu_id) VALUES (2, 16);
INSERT INTO role_menu (role_id, menu_id) VALUES (2, 18);
INSERT INTO role_menu (role_id, menu_id) VALUES (2, 19);
INSERT INTO role_menu (role_id, menu_id) VALUES (2, 20);
INSERT INTO role_menu (role_id, menu_id) VALUES (2, 21);
INSERT INTO role_menu (role_id, menu_id) VALUES (2, 22);
INSERT INTO role_menu (role_id, menu_id) VALUES (2, 23);
INSERT INTO role_menu (role_id, menu_id) VALUES (2, 24);
INSERT INTO role_menu (role_id, menu_id) VALUES (2, 25);
INSERT INTO role_menu (role_id, menu_id) VALUES (2, 26);
INSERT INTO role_menu (role_id, menu_id) VALUES (2, 27);
INSERT INTO role_menu (role_id, menu_id) VALUES (2, 30);
INSERT INTO role_menu (role_id, menu_id) VALUES (2, 31);
INSERT INTO role_menu (role_id, menu_id) VALUES (2, 32);
INSERT INTO role_menu (role_id, menu_id) VALUES (2, 33);
INSERT INTO role_menu (role_id, menu_id) VALUES (2, 34);
INSERT INTO role_menu (role_id, menu_id) VALUES (2, 35);


INSERT INTO user (id, username, password, nickname, group_id, email, mobile, gender, is_tab, theme, avatar, description, last_login_time, status, deleted, create_time, modify_time) VALUES (1, 'guqing', '$2a$10$CIJ7cICy2uT3GdVxKegohezZwoiAvpaB1dv82K4aKob9P2tWis.1u', '聽見下雨的聲音', 1, '14845636141@qq.com', '18869817190', 'MALE', 0, null, 'https://violet-cloud.oss-cn-hangzhou.aliyuncs.com/8e80f601-4001-4d6f-846f-ca0938c76bf8', '毕生所求无它，爱与自由而已', '2020-10-15 16:17:16.000000', 0, 0, '2020-05-25 18:06:52.000000', '2020-05-25 18:06:52.000000');


INSERT INTO user_group (id, parent_id, group_name, sort_index, create_time, modify_time) VALUES (1, 0, '默认分组', 1, '2020-05-28 16:22:01.000000', '2020-05-28 16:21:58.000000');
INSERT INTO user_group (id, parent_id, group_name, sort_index, create_time, modify_time) VALUES (2, 0, '测试用户组-1', 0, null, null);
INSERT INTO user_group (id, parent_id, group_name, sort_index, create_time, modify_time) VALUES (3, 1, '默认分组的2级分组', 0, null, null);


INSERT INTO user_role (user_id, role_id) VALUES (1, 1);
INSERT INTO user_role (user_id, role_id) VALUES (1, 2);
INSERT INTO user_role (user_id, role_id) VALUES (4, 1);
INSERT INTO user_role (user_id, role_id) VALUES (14, 1);
INSERT INTO user_role (user_id, role_id) VALUES (15, 1);

