INSERT INTO MENU (ID, PARENT_ID, TITLE, NAME, PATH, REDIRECT, COMPONENT, ICON, KEEP_ALIVE, HIDDEN, PERMS, TYPE, SORT_INDEX, CREATE_TIME, MODIFY_TIME) VALUES (2, 0, '仪表盘', 'dashboard', '/dashboard', '/dashboard/workplace', 'RouteView', 'dashboard', 1, 0, null, '0', 2, '2020-05-28 20:44:36.000000', '2020-05-28 20:44:41.000000');
INSERT INTO MENU (ID, PARENT_ID, TITLE, NAME, PATH, REDIRECT, COMPONENT, ICON, KEEP_ALIVE, HIDDEN, PERMS, TYPE, SORT_INDEX, CREATE_TIME, MODIFY_TIME) VALUES (3, 2, '分析页', 'Analysis', '/dashboard/analysis', null, 'dashboard/Analysis', '', 1, 0, 'personnel:user', '0', 4, '2020-05-28 20:47:05.000000', '2020-07-21 22:31:38.000000');
INSERT INTO MENU (ID, PARENT_ID, TITLE, NAME, PATH, REDIRECT, COMPONENT, ICON, KEEP_ALIVE, HIDDEN, PERMS, TYPE, SORT_INDEX, CREATE_TIME, MODIFY_TIME) VALUES (4, 2, '工作台', 'Workplace', '/dashboard/workplace', null, 'dashboard/Workplace', '', 1, 0, 'personnel:groups', '0', 3, '2020-05-28 20:49:03.000000', '2020-07-21 22:31:46.000000');
INSERT INTO MENU (ID, PARENT_ID, TITLE, NAME, PATH, REDIRECT, COMPONENT, ICON, KEEP_ALIVE, HIDDEN, PERMS, TYPE, SORT_INDEX, CREATE_TIME, MODIFY_TIME) VALUES (5, 0, '表单页', 'form', '/form', '/form/base-form', 'RouteView', 'form', 0, 0, null, '0', 5, '2020-05-28 20:50:32.000000', '2020-05-28 20:50:30.000000');
INSERT INTO MENU (ID, PARENT_ID, TITLE, NAME, PATH, REDIRECT, COMPONENT, ICON, KEEP_ALIVE, HIDDEN, PERMS, TYPE, SORT_INDEX, CREATE_TIME, MODIFY_TIME) VALUES (6, 5, '基础表单', 'BaseForm', '/form/base-form', '', 'form/basicForm', '', 1, 0, '', '0', 6, '2020-05-28 20:52:22.000000', '2020-05-28 20:52:26.000000');
INSERT INTO MENU (ID, PARENT_ID, TITLE, NAME, PATH, REDIRECT, COMPONENT, ICON, KEEP_ALIVE, HIDDEN, PERMS, TYPE, SORT_INDEX, CREATE_TIME, MODIFY_TIME) VALUES (7, 5, '分步表单', 'StepForm', '/form/step-form', null, 'form/stepForm/StepForm', '', 1, 0, null, '0', 7, '2020-05-30 14:40:24.000000', '2020-05-30 14:40:27.000000');
INSERT INTO MENU (ID, PARENT_ID, TITLE, NAME, PATH, REDIRECT, COMPONENT, ICON, KEEP_ALIVE, HIDDEN, PERMS, TYPE, SORT_INDEX, CREATE_TIME, MODIFY_TIME) VALUES (8, 5, '高级表单', 'AdvanceForm', '/form/advanced-form', null, 'form/advancedForm/AdvancedForm', null, 1, 0, null, '0', 8, '2020-05-30 14:41:56.000000', '2020-05-30 14:41:58.000000');
INSERT INTO MENU (ID, PARENT_ID, TITLE, NAME, PATH, REDIRECT, COMPONENT, ICON, KEEP_ALIVE, HIDDEN, PERMS, TYPE, SORT_INDEX, CREATE_TIME, MODIFY_TIME) VALUES (9, 0, '系统管理', 'RAM', '/ram', '/ram/user', 'RouteView', 'safety-certificate', 1, 0, '', '0', 9, '2020-05-31 12:00:02.000000', '2020-05-31 12:00:04.000000');
INSERT INTO MENU (ID, PARENT_ID, TITLE, NAME, PATH, REDIRECT, COMPONENT, ICON, KEEP_ALIVE, HIDDEN, PERMS, TYPE, SORT_INDEX, CREATE_TIME, MODIFY_TIME) VALUES (10, 9, '用户管理', 'User', '/ram/user', null, 'user/UserList', null, 1, 0, 'user:view', '0', 10, '2020-05-31 11:57:50.000000', '2020-05-31 11:57:52.000000');
INSERT INTO MENU (ID, PARENT_ID, TITLE, NAME, PATH, REDIRECT, COMPONENT, ICON, KEEP_ALIVE, HIDDEN, PERMS, TYPE, SORT_INDEX, CREATE_TIME, MODIFY_TIME) VALUES (11, 9, '角色管理', 'Role', '/ram/role', null, 'role/RoleList', null, 1, 0, 'role:view', '0', 11, '2020-06-04 14:47:58.000000', '2020-06-04 14:48:01.000000');
INSERT INTO MENU (ID, PARENT_ID, TITLE, NAME, PATH, REDIRECT, COMPONENT, ICON, KEEP_ALIVE, HIDDEN, PERMS, TYPE, SORT_INDEX, CREATE_TIME, MODIFY_TIME) VALUES (12, 9, '菜单管理', 'Menu', '/ram/menu', null, 'menu/MenuList', null, 1, 0, 'menu:view', '0', 12, '2020-06-05 16:26:55.000000', '2020-06-05 16:26:58.000000');
INSERT INTO MENU (ID, PARENT_ID, TITLE, NAME, PATH, REDIRECT, COMPONENT, ICON, KEEP_ALIVE, HIDDEN, PERMS, TYPE, SORT_INDEX, CREATE_TIME, MODIFY_TIME) VALUES (13, 9, '用户组管理', 'UserGroup', '/ram/user/group', null, 'group/UserGroupList', null, 1, 0, 'group:view', '0', 13, '2020-06-05 18:35:27.000000', '2020-06-05 18:35:31.000000');
INSERT INTO MENU (ID, PARENT_ID, TITLE, NAME, PATH, REDIRECT, COMPONENT, ICON, KEEP_ALIVE, HIDDEN, PERMS, TYPE, SORT_INDEX, CREATE_TIME, MODIFY_TIME) VALUES (14, 13, '保存', 'UserGroupSave', '', null, null, null, 0, 0, 'group:save', '1', 14, '2020-06-06 13:09:17.000000', '2020-06-06 13:09:21.000000');
INSERT INTO MENU (ID, PARENT_ID, TITLE, NAME, PATH, REDIRECT, COMPONENT, ICON, KEEP_ALIVE, HIDDEN, PERMS, TYPE, SORT_INDEX, CREATE_TIME, MODIFY_TIME) VALUES (15, 12, '保存', 'MenuSave', '', null, '', null, 0, 0, 'menu:save', '1', 15, '2020-06-06 14:30:12.000000', '2020-06-06 14:30:14.000000');
INSERT INTO MENU (ID, PARENT_ID, TITLE, NAME, PATH, REDIRECT, COMPONENT, ICON, KEEP_ALIVE, HIDDEN, PERMS, TYPE, SORT_INDEX, CREATE_TIME, MODIFY_TIME) VALUES (16, 11, '保存', 'RoleSave', '', null, null, null, 0, 0, 'role:save', '1', 16, '2020-06-06 17:41:13.000000', '2020-06-06 17:41:13.000000');
INSERT INTO MENU (ID, PARENT_ID, TITLE, NAME, PATH, REDIRECT, COMPONENT, ICON, KEEP_ALIVE, HIDDEN, PERMS, TYPE, SORT_INDEX, CREATE_TIME, MODIFY_TIME) VALUES (18, 12, '删除', 'MenuDelete', null, null, null, null, 0, 0, 'menu:delete', '1', 17, '2020-06-16 11:46:12.000000', '2020-06-16 11:46:15.000000');
INSERT INTO MENU (ID, PARENT_ID, TITLE, NAME, PATH, REDIRECT, COMPONENT, ICON, KEEP_ALIVE, HIDDEN, PERMS, TYPE, SORT_INDEX, CREATE_TIME, MODIFY_TIME) VALUES (19, 11, '删除', 'RoleDelete', null, null, null, null, 0, 0, 'role:delete', '1', 18, '2020-06-16 11:47:26.000000', '2020-06-16 11:47:29.000000');
INSERT INTO MENU (ID, PARENT_ID, TITLE, NAME, PATH, REDIRECT, COMPONENT, ICON, KEEP_ALIVE, HIDDEN, PERMS, TYPE, SORT_INDEX, CREATE_TIME, MODIFY_TIME) VALUES (20, 13, '删除', 'UserGroupDelete', null, null, null, null, 0, 0, 'group:delete', '1', 19, '2020-06-16 11:48:37.000000', '2020-06-16 11:48:40.000000');
INSERT INTO MENU (ID, PARENT_ID, TITLE, NAME, PATH, REDIRECT, COMPONENT, ICON, KEEP_ALIVE, HIDDEN, PERMS, TYPE, SORT_INDEX, CREATE_TIME, MODIFY_TIME) VALUES (21, 10, '新增', 'UserAdd', null, null, null, null, 0, 0, 'user:add', '1', 20, '2020-06-29 11:53:35.000000', '2020-06-29 11:53:41.000000');
INSERT INTO MENU (ID, PARENT_ID, TITLE, NAME, PATH, REDIRECT, COMPONENT, ICON, KEEP_ALIVE, HIDDEN, PERMS, TYPE, SORT_INDEX, CREATE_TIME, MODIFY_TIME) VALUES (22, 10, '重置密码', 'UserResetPassword', null, null, null, null, 0, 0, 'user:reset', '1', 21, '2020-06-29 20:20:01.000000', '2020-06-29 20:20:03.000000');
INSERT INTO MENU (ID, PARENT_ID, TITLE, NAME, PATH, REDIRECT, COMPONENT, ICON, KEEP_ALIVE, HIDDEN, PERMS, TYPE, SORT_INDEX, CREATE_TIME, MODIFY_TIME) VALUES (23, 10, '更新', 'UserUpdate', null, null, null, null, 0, 0, 'user:update', '1', 22, '2020-06-30 17:53:02.000000', '2020-06-30 17:53:07.000000');
INSERT INTO MENU (ID, PARENT_ID, TITLE, NAME, PATH, REDIRECT, COMPONENT, ICON, KEEP_ALIVE, HIDDEN, PERMS, TYPE, SORT_INDEX, CREATE_TIME, MODIFY_TIME) VALUES (24, 10, '删除', 'UserDelete', null, null, null, null, 0, 0, 'user:delete', '1', 23, '2020-06-30 17:53:45.000000', '2020-06-30 17:53:48.000000');
INSERT INTO MENU (ID, PARENT_ID, TITLE, NAME, PATH, REDIRECT, COMPONENT, ICON, KEEP_ALIVE, HIDDEN, PERMS, TYPE, SORT_INDEX, CREATE_TIME, MODIFY_TIME) VALUES (25, 0, '监控日志', 'Monitor', '/monitor', '/monitor/log/action', 'RouteView', 'fund', 1, 0, '', '0', 24, '2020-07-16 09:54:09.000000', '2020-07-16 09:54:12.000000');
INSERT INTO MENU (ID, PARENT_ID, TITLE, NAME, PATH, REDIRECT, COMPONENT, ICON, KEEP_ALIVE, HIDDEN, PERMS, TYPE, SORT_INDEX, CREATE_TIME, MODIFY_TIME) VALUES (26, 25, '系统日志', 'ActionLog', '/monitor/log/action', null, 'monitor/ActionLog', null, 1, 0, null, '0', 25, '2020-07-16 09:54:27.000000', '2020-07-16 09:54:25.000000');
INSERT INTO MENU (ID, PARENT_ID, TITLE, NAME, PATH, REDIRECT, COMPONENT, ICON, KEEP_ALIVE, HIDDEN, PERMS, TYPE, SORT_INDEX, CREATE_TIME, MODIFY_TIME) VALUES (27, 25, '登录日志', 'LoginLog', '/monitor/log/login', null, 'monitor/LoginLog', null, 1, 0, null, '0', 26, '2020-07-16 21:14:02.000000', '2020-07-16 21:14:04.000000');
INSERT INTO MENU (ID, PARENT_ID, TITLE, NAME, PATH, REDIRECT, COMPONENT, ICON, KEEP_ALIVE, HIDDEN, PERMS, TYPE, SORT_INDEX, CREATE_TIME, MODIFY_TIME) VALUES (28, 0, '个人页', 'Account', '/account', '/account/center', 'RouteView', 'user', 1, 0, null, '0', 27, '2020-07-17 15:54:26.000000', '2020-07-17 15:54:29.000000');
INSERT INTO MENU (ID, PARENT_ID, TITLE, NAME, PATH, REDIRECT, COMPONENT, ICON, KEEP_ALIVE, HIDDEN, PERMS, TYPE, SORT_INDEX, CREATE_TIME, MODIFY_TIME) VALUES (29, 28, '个人中心', 'AccountCenter', '/account/center', null, 'account/center/Index', null, 1, 0, '', '0', 28, '2020-07-17 11:37:09.000000', '2020-07-17 11:37:12.000000');
INSERT INTO MENU (ID, PARENT_ID, TITLE, NAME, PATH, REDIRECT, COMPONENT, ICON, KEEP_ALIVE, HIDDEN, PERMS, TYPE, SORT_INDEX, CREATE_TIME, MODIFY_TIME) VALUES (30, 0, '预设卡口', 'PresetPlan', '/preset', null, 'preset/List', null, 1, 0, null, '0', 40, '2020-10-19 11:16:52.000000', '2020-10-19 11:16:54.000000');
INSERT INTO MENU (ID, PARENT_ID, TITLE, NAME, PATH, REDIRECT, COMPONENT, ICON, KEEP_ALIVE, HIDDEN, PERMS, TYPE, SORT_INDEX, CREATE_TIME, MODIFY_TIME) VALUES (31, 0, '布设卡口', 'DesignPlan', '/design', null, 'design/List', null, 1, 0, null, '0', 41, '2020-10-19 11:16:52.000000', '2020-10-19 11:16:54.000000');


INSERT INTO ROLE (ID, ROLE_NAME, REMARK, IS_DEFAULT, DELETED, CREATE_TIME, MODIFY_TIME) VALUES (1, '普通用户', '用户注册默认角色', 1, 0, '2020-05-28 16:25:39.000000', '2020-07-23 18:27:45.000000');
INSERT INTO ROLE (ID, ROLE_NAME, REMARK, IS_DEFAULT, DELETED, CREATE_TIME, MODIFY_TIME) VALUES (2, '管理员', '管理员', 0, 0, '2020-06-04 10:23:29.000000', '2020-07-23 18:28:00.000000');

INSERT INTO ROLE_MENU (ROLE_ID, MENU_ID) VALUES (1, 2);
INSERT INTO ROLE_MENU (ROLE_ID, MENU_ID) VALUES (1, 3);
INSERT INTO ROLE_MENU (ROLE_ID, MENU_ID) VALUES (1, 4);
INSERT INTO ROLE_MENU (ROLE_ID, MENU_ID) VALUES (1, 5);
INSERT INTO ROLE_MENU (ROLE_ID, MENU_ID) VALUES (1, 6);
INSERT INTO ROLE_MENU (ROLE_ID, MENU_ID) VALUES (1, 7);
INSERT INTO ROLE_MENU (ROLE_ID, MENU_ID) VALUES (1, 8);
INSERT INTO ROLE_MENU (ROLE_ID, MENU_ID) VALUES (1, 9);
INSERT INTO ROLE_MENU (ROLE_ID, MENU_ID) VALUES (1, 10);
INSERT INTO ROLE_MENU (ROLE_ID, MENU_ID) VALUES (1, 11);
INSERT INTO ROLE_MENU (ROLE_ID, MENU_ID) VALUES (1, 12);
INSERT INTO ROLE_MENU (ROLE_ID, MENU_ID) VALUES (1, 13);
INSERT INTO ROLE_MENU (ROLE_ID, MENU_ID) VALUES (1, 25);
INSERT INTO ROLE_MENU (ROLE_ID, MENU_ID) VALUES (1, 26);
INSERT INTO ROLE_MENU (ROLE_ID, MENU_ID) VALUES (1, 27);
INSERT INTO ROLE_MENU (ROLE_ID, MENU_ID) VALUES (1, 28);
INSERT INTO ROLE_MENU (ROLE_ID, MENU_ID) VALUES (1, 29);
INSERT INTO ROLE_MENU (ROLE_ID, MENU_ID) VALUES (1, 30);
INSERT INTO ROLE_MENU (ROLE_ID, MENU_ID) VALUES (1, 31);
INSERT INTO ROLE_MENU (ROLE_ID, MENU_ID) VALUES (2, 2);
INSERT INTO ROLE_MENU (ROLE_ID, MENU_ID) VALUES (2, 3);
INSERT INTO ROLE_MENU (ROLE_ID, MENU_ID) VALUES (2, 4);
INSERT INTO ROLE_MENU (ROLE_ID, MENU_ID) VALUES (2, 5);
INSERT INTO ROLE_MENU (ROLE_ID, MENU_ID) VALUES (2, 6);
INSERT INTO ROLE_MENU (ROLE_ID, MENU_ID) VALUES (2, 7);
INSERT INTO ROLE_MENU (ROLE_ID, MENU_ID) VALUES (2, 8);
INSERT INTO ROLE_MENU (ROLE_ID, MENU_ID) VALUES (2, 9);
INSERT INTO ROLE_MENU (ROLE_ID, MENU_ID) VALUES (2, 10);
INSERT INTO ROLE_MENU (ROLE_ID, MENU_ID) VALUES (2, 11);
INSERT INTO ROLE_MENU (ROLE_ID, MENU_ID) VALUES (2, 12);
INSERT INTO ROLE_MENU (ROLE_ID, MENU_ID) VALUES (2, 13);
INSERT INTO ROLE_MENU (ROLE_ID, MENU_ID) VALUES (2, 14);
INSERT INTO ROLE_MENU (ROLE_ID, MENU_ID) VALUES (2, 15);
INSERT INTO ROLE_MENU (ROLE_ID, MENU_ID) VALUES (2, 16);
INSERT INTO ROLE_MENU (ROLE_ID, MENU_ID) VALUES (2, 18);
INSERT INTO ROLE_MENU (ROLE_ID, MENU_ID) VALUES (2, 19);
INSERT INTO ROLE_MENU (ROLE_ID, MENU_ID) VALUES (2, 20);
INSERT INTO ROLE_MENU (ROLE_ID, MENU_ID) VALUES (2, 21);
INSERT INTO ROLE_MENU (ROLE_ID, MENU_ID) VALUES (2, 22);
INSERT INTO ROLE_MENU (ROLE_ID, MENU_ID) VALUES (2, 23);
INSERT INTO ROLE_MENU (ROLE_ID, MENU_ID) VALUES (2, 24);
INSERT INTO ROLE_MENU (ROLE_ID, MENU_ID) VALUES (2, 25);
INSERT INTO ROLE_MENU (ROLE_ID, MENU_ID) VALUES (2, 26);
INSERT INTO ROLE_MENU (ROLE_ID, MENU_ID) VALUES (2, 27);
INSERT INTO ROLE_MENU (ROLE_ID, MENU_ID) VALUES (2, 28);
INSERT INTO ROLE_MENU (ROLE_ID, MENU_ID) VALUES (2, 29);


INSERT INTO USER (ID, USERNAME, PASSWORD, NICKNAME, GROUP_ID, EMAIL, MOBILE, GENDER, IS_TAB, THEME, AVATAR, DESCRIPTION, LAST_LOGIN_TIME, STATUS, DELETED, CREATE_TIME, MODIFY_TIME) VALUES (1, 'guqing', '$2a$10$CIJ7cICy2uT3GdVxKegohezZwoiAvpaB1dv82K4aKob9P2tWis.1u', '聽見下雨的聲音', 1, '14845636141@qq.com', '18869817190', 0, 0, null, 'https://violet-cloud.oss-cn-hangzhou.aliyuncs.com/8e80f601-4001-4d6f-846f-ca0938c76bf8', '毕生所求无它，爱与自由而已', '2020-10-15 16:17:16.000000', 0, 0, '2020-05-25 18:06:52.000000', '2020-05-25 18:06:52.000000');


INSERT INTO USER_GROUP (ID, PARENT_ID, GROUP_NAME, SORT_INDEX, CREATE_TIME, MODIFY_TIME) VALUES (1, 0, '默认分组', 1, '2020-05-28 16:22:01.000000', '2020-05-28 16:21:58.000000');
INSERT INTO USER_GROUP (ID, PARENT_ID, GROUP_NAME, SORT_INDEX, CREATE_TIME, MODIFY_TIME) VALUES (2, 0, '测试用户组-1', 0, null, null);
INSERT INTO USER_GROUP (ID, PARENT_ID, GROUP_NAME, SORT_INDEX, CREATE_TIME, MODIFY_TIME) VALUES (3, 1, '默认分组的2级分组', 0, null, null);


INSERT INTO USER_ROLE (USER_ID, ROLE_ID) VALUES (1, 1);
INSERT INTO USER_ROLE (USER_ID, ROLE_ID) VALUES (1, 2);
INSERT INTO USER_ROLE (USER_ID, ROLE_ID) VALUES (4, 1);
INSERT INTO USER_ROLE (USER_ID, ROLE_ID) VALUES (14, 1);
INSERT INTO USER_ROLE (USER_ID, ROLE_ID) VALUES (15, 1);

INSERT INTO SETTING_OPTION (ID, OPTION_KEY, OPTION_VALUE, CREATE_TIME, MODIFY_TIME) VALUES (1, 'email_host', 'smtp.qq.com', '2020-07-14 17:07:35.000000', '2020-07-14 17:07:37.000000');
INSERT INTO SETTING_OPTION (ID, OPTION_KEY, OPTION_VALUE, CREATE_TIME, MODIFY_TIME) VALUES (2, 'email_protocol', 'smtp', '2020-07-14 17:07:39.000000', '2020-07-14 17:07:46.000000');
INSERT INTO SETTING_OPTION (ID, OPTION_KEY, OPTION_VALUE, CREATE_TIME, MODIFY_TIME) VALUES (3, 'email_ssl_port', '587', '2020-07-14 17:07:42.000000', '2020-07-14 17:07:48.000000');
INSERT INTO SETTING_OPTION (ID, OPTION_KEY, OPTION_VALUE, CREATE_TIME, MODIFY_TIME) VALUES (4, 'email_username', 'guqing@qq.com', '2020-07-14 17:07:44.000000', '2020-07-14 17:07:51.000000');
INSERT INTO SETTING_OPTION (ID, OPTION_KEY, OPTION_VALUE, CREATE_TIME, MODIFY_TIME) VALUES (5, 'email_password', '123456', '2020-07-14 17:08:03.000000', '2020-07-14 17:08:05.000000');
INSERT INTO SETTING_OPTION (ID, OPTION_KEY, OPTION_VALUE, CREATE_TIME, MODIFY_TIME) VALUES (6, 'email_enabled', 'true', '2020-07-14 17:08:37.000000', '2020-07-14 17:08:39.000000');
INSERT INTO SETTING_OPTION (ID, OPTION_KEY, OPTION_VALUE, CREATE_TIME, MODIFY_TIME) VALUES (7, 'email_from_name', 'guqing', '2020-07-14 19:57:47.000000', '2020-07-14 19:57:50.000000');

