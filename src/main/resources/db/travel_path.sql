/*
 Navicat Premium Data Transfer

 Source Server         : 本地Postgresql
 Source Server Type    : PostgreSQL
 Source Server Version : 90614
 Source Host           : localhost:5432
 Source Catalog        : travel_path
 Source Schema         : public

 Target Server Type    : PostgreSQL
 Target Server Version : 90614
 File Encoding         : 65001

 Date: 25/09/2019 22:42:35
*/


-- ----------------------------
-- Table structure for actual_bayonet_point
-- ----------------------------
DROP TABLE IF EXISTS "public"."actual_bayonet_point";
CREATE TABLE "public"."actual_bayonet_point" (
  "id" int8 NOT NULL DEFAULT nextval('actual_bayonet_point_id_seq'::regclass),
  "lng" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
  "lat" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
  "edge_number" varchar(20) COLLATE "pg_catalog"."default",
  "actual_id" int8 NOT NULL,
  "create_time" timestamp(6) NOT NULL,
  "modify_time" timestamp(6) NOT NULL
)
;
COMMENT ON COLUMN "public"."actual_bayonet_point"."lng" IS '经度坐标';
COMMENT ON COLUMN "public"."actual_bayonet_point"."lat" IS '纬度坐标';
COMMENT ON COLUMN "public"."actual_bayonet_point"."edge_number" IS '所属拓扑边编号';
COMMENT ON COLUMN "public"."actual_bayonet_point"."actual_id" IS '所属卡口实际布设方案';

-- ----------------------------
-- Records of actual_bayonet_point
-- ----------------------------
INSERT INTO "public"."actual_bayonet_point" VALUES (4, '116.20444738320862', '40.0174791164707', NULL, 6, '2019-08-16 01:44:10', '2019-08-16 01:44:10');
INSERT INTO "public"."actual_bayonet_point" VALUES (5, '116.21185352453206', '40.017636876969455', NULL, 6, '2019-08-16 01:44:10', '2019-08-16 01:44:10');
INSERT INTO "public"."actual_bayonet_point" VALUES (6, '116.21456101238479', '40.01710114970745', NULL, 6, '2019-08-16 01:44:10', '2019-08-16 01:44:10');
INSERT INTO "public"."actual_bayonet_point" VALUES (7, '116.2124837757744', '40.03262649351757', NULL, 1, '2019-08-16 02:25:03', '2019-08-16 02:25:03');
INSERT INTO "public"."actual_bayonet_point" VALUES (8, '116.21479542215067', '40.03277846782997', NULL, 1, '2019-08-16 02:25:03', '2019-08-16 02:25:03');
INSERT INTO "public"."actual_bayonet_point" VALUES (9, '116.2044473832', '40.0174791165', NULL, 7, '2019-08-16 09:10:31', '2019-08-16 09:10:31');
INSERT INTO "public"."actual_bayonet_point" VALUES (10, '116.2118535245', '40.017636877', NULL, 7, '2019-08-16 09:10:31', '2019-08-16 09:10:31');
INSERT INTO "public"."actual_bayonet_point" VALUES (11, '116.2145610124', '40.0171011497', NULL, 7, '2019-08-16 09:10:31', '2019-08-16 09:10:31');
INSERT INTO "public"."actual_bayonet_point" VALUES (12, '108.91463863674623', '34.55714574244688', NULL, 8, '2019-09-08 13:37:57.087', '2019-09-08 13:37:57.087');
INSERT INTO "public"."actual_bayonet_point" VALUES (13, '108.91498838826598', '34.5573383637679', NULL, 8, '2019-09-08 13:37:57.089', '2019-09-08 13:37:57.089');
INSERT INTO "public"."actual_bayonet_point" VALUES (14, '108.91650032079184', '34.55733129509435', NULL, 8, '2019-09-08 13:37:57.09', '2019-09-08 13:37:57.09');
INSERT INTO "public"."actual_bayonet_point" VALUES (15, '108.91712823672763', '34.557303020444614', NULL, 8, '2019-09-08 13:37:57.091', '2019-09-08 13:37:57.091');
INSERT INTO "public"."actual_bayonet_point" VALUES (16, '108.91775428504741', '34.557279163719855', NULL, 8, '2019-09-08 13:37:57.093', '2019-09-08 13:37:57.093');
INSERT INTO "public"."actual_bayonet_point" VALUES (17, '108.91860830608826', '34.55728358163736', NULL, 8, '2019-09-08 13:37:57.094', '2019-09-08 13:37:57.094');

-- ----------------------------
-- Table structure for actual_layout_scheme
-- ----------------------------
DROP TABLE IF EXISTS "public"."actual_layout_scheme";
CREATE TABLE "public"."actual_layout_scheme" (
  "id" int8 NOT NULL DEFAULT nextval('actual_layout_scheme_id_seq'::regclass),
  "name" varchar(140) COLLATE "pg_catalog"."default" NOT NULL,
  "description" varchar(250) COLLATE "pg_catalog"."default",
  "bayonet_count" int8 DEFAULT 0,
  "userid" int4 NOT NULL,
  "preset_id" int8 NOT NULL,
  "create_time" timestamp(6) NOT NULL,
  "modify_time" timestamp(6) NOT NULL,
  "deleted" int2 DEFAULT 0
)
;
COMMENT ON COLUMN "public"."actual_layout_scheme"."name" IS '卡口实际布设方案名称';
COMMENT ON COLUMN "public"."actual_layout_scheme"."description" IS '描述';
COMMENT ON COLUMN "public"."actual_layout_scheme"."bayonet_count" IS '方案所包含的卡口数量';
COMMENT ON COLUMN "public"."actual_layout_scheme"."userid" IS '所属用户';
COMMENT ON COLUMN "public"."actual_layout_scheme"."preset_id" IS '预设卡口方案id';
COMMENT ON COLUMN "public"."actual_layout_scheme"."create_time" IS '创建时间';
COMMENT ON COLUMN "public"."actual_layout_scheme"."modify_time" IS '修改时间';
COMMENT ON COLUMN "public"."actual_layout_scheme"."deleted" IS '逻辑删除，0未删除，1回收站';

-- ----------------------------
-- Records of actual_layout_scheme
-- ----------------------------
INSERT INTO "public"."actual_layout_scheme" VALUES (1, '一条方案', '22222', 2, 1, 7, '2019-08-16 01:34:56', '2019-08-16 02:59:27', 1);
INSERT INTO "public"."actual_layout_scheme" VALUES (8, '6666', '66666', 6, 1, 39, '2019-09-08 13:37:57.081', '2019-09-08 13:37:57.081', 0);
INSERT INTO "public"."actual_layout_scheme" VALUES (6, '第二条方案333', NULL, 3, 1, 10, '2019-08-16 01:44:10', '2019-09-08 13:38:11.468', 1);
INSERT INTO "public"."actual_layout_scheme" VALUES (7, '第二条方案333', NULL, 3, 1, 10, '2019-08-16 09:10:31', '2019-09-08 13:42:30.636', 1);

-- ----------------------------
-- Table structure for log
-- ----------------------------
DROP TABLE IF EXISTS "public"."log";
CREATE TABLE "public"."log" (
  "id" int8 NOT NULL DEFAULT nextval('log_id_seq'::regclass),
  "user_id" int4 NOT NULL,
  "username" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "name" varchar(255) COLLATE "pg_catalog"."default",
  "content" varchar(255) COLLATE "pg_catalog"."default",
  "type" int4 NOT NULL,
  "method_name" varchar(200) COLLATE "pg_catalog"."default",
  "ip" varchar(63) COLLATE "pg_catalog"."default" NOT NULL,
  "create_time" timestamp(6),
  "modify_time" timestamp(6)
)
;
COMMENT ON COLUMN "public"."log"."username" IS '操作者用户名';
COMMENT ON COLUMN "public"."log"."type" IS '日志类型：1新增，2修改，3删除';
COMMENT ON COLUMN "public"."log"."method_name" IS '方法名称';
COMMENT ON COLUMN "public"."log"."ip" IS '操作ip';
COMMENT ON COLUMN "public"."log"."create_time" IS '操作时间';
COMMENT ON COLUMN "public"."log"."modify_time" IS '修改时间';

-- ----------------------------
-- Records of log
-- ----------------------------
INSERT INTO "public"."log" VALUES (1, 1, 'guqing', '删除', '删除预设卡口方案到回收站', 3, 'throwTrash', '0:0:0:0:0:0:0:1', '2019-08-30 06:40:35', '2019-08-30 06:40:35');
INSERT INTO "public"."log" VALUES (2, 1, 'guqing', '删除', '删除预设卡口方案到回收站', 3, 'throwTrash', '0:0:0:0:0:0:0:1', '2019-08-30 06:46:45', '2019-08-30 06:46:45');
INSERT INTO "public"."log" VALUES (3, 1, 'guqing', '删除', '删除预设卡口方案到回收站', 3, 'throwTrash', '0:0:0:0:0:0:0:1', '2019-08-30 06:47:32', '2019-08-30 06:47:32');
INSERT INTO "public"."log" VALUES (4, 1, 'guqing', '删除', '删除预设卡口方案到回收站', 3, 'throwTrash', '0:0:0:0:0:0:0:1', '2019-08-30 07:18:02', '2019-08-30 07:18:02');
INSERT INTO "public"."log" VALUES (5, 1, 'guqing', '删除', '删除预设卡口方案到回收站', 3, 'throwTrash', '0:0:0:0:0:0:0:1', '2019-08-30 07:20:21', '2019-08-30 07:20:21');
INSERT INTO "public"."log" VALUES (6, 1, 'guqing', '删除', '删除预设卡口方案到回收站', 3, 'throwTrash', '0:0:0:0:0:0:0:1', '2019-08-30 07:23:44', '2019-08-30 07:23:44');
INSERT INTO "public"."log" VALUES (7, 1, 'guqing', '删除', '删除预设卡口方案到回收站', 3, 'throwTrash', '0:0:0:0:0:0:0:1', '2019-09-08 13:03:36.113', '2019-09-08 13:03:36.113');
INSERT INTO "public"."log" VALUES (8, 1, 'guqing', '删除', '删除预设卡口方案到回收站', 3, 'throwTrash', '0:0:0:0:0:0:0:1', '2019-09-08 13:11:04.059', '2019-09-08 13:11:04.059');
INSERT INTO "public"."log" VALUES (9, 1, 'guqing', '删除', '删除预设卡口方案到回收站', 3, 'throwTrash', '0:0:0:0:0:0:0:1', '2019-09-08 13:11:07.773', '2019-09-08 13:11:07.773');
INSERT INTO "public"."log" VALUES (10, 1, 'guqing', '删除', '删除预设卡口方案到回收站', 3, 'throwTrash', '0:0:0:0:0:0:0:1', '2019-09-08 13:12:55.641', '2019-09-08 13:12:55.641');
INSERT INTO "public"."log" VALUES (11, 1, 'guqing', '新增', '新增预设卡口方案', 1, 'createScheme', '0:0:0:0:0:0:0:1', '2019-09-08 13:37:27.495', '2019-09-08 13:37:27.495');

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS "public"."permission";
CREATE TABLE "public"."permission" (
  "id" int4 NOT NULL DEFAULT nextval('permission_id_seq'::regclass),
  "permission_id" varchar(128) COLLATE "pg_catalog"."default" NOT NULL,
  "permission_name" varchar(128) COLLATE "pg_catalog"."default",
  "description" varchar(200) COLLATE "pg_catalog"."default",
  "url" varchar(128) COLLATE "pg_catalog"."default",
  "sort_index" varchar(128) COLLATE "pg_catalog"."default",
  "available" char(1) COLLATE "pg_catalog"."default",
  "create_time" timestamp(6),
  "modify_time" timestamp(6) NOT NULL
)
;
COMMENT ON COLUMN "public"."permission"."id" IS '主键';
COMMENT ON COLUMN "public"."permission"."permission_id" IS '资源识别号例如dashboard';
COMMENT ON COLUMN "public"."permission"."permission_name" IS '资源名称，例如仪表盘';
COMMENT ON COLUMN "public"."permission"."description" IS '资源备注';
COMMENT ON COLUMN "public"."permission"."url" IS '访问url地址';
COMMENT ON COLUMN "public"."permission"."sort_index" IS '排序号';
COMMENT ON COLUMN "public"."permission"."available" IS '是否可用,1：可用，0不可用';

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO "public"."permission" VALUES (1, 'dashboard', '仪表盘', '仪表盘', '', '1', '1', '2019-08-11 10:43:20', '2019-08-11 10:43:22');
INSERT INTO "public"."permission" VALUES (2, 'user', '用户', '用户相关url', NULL, '2', '1', '2019-08-11 15:17:59', '2019-08-11 15:18:01');
INSERT INTO "public"."permission" VALUES (5, 'actualBayonet', '实际布设卡口方案', '实际布设卡口方案相关权限', NULL, '5', '1', '2019-08-15 15:26:52', '2019-08-15 15:26:54');
INSERT INTO "public"."permission" VALUES (6, 'via', '途经卡口方案', '车辆途经卡口方案相关api', NULL, '6', '1', '2019-08-24 15:24:12', '2019-08-24 15:24:14');
INSERT INTO "public"."permission" VALUES (7, 'role', '用户角色', '用户角色管理相关api', NULL, '7', '1', '2019-08-29 23:19:07', '2019-08-29 23:19:09');
INSERT INTO "public"."permission" VALUES (9, 'log', '日志', '日志相关api', NULL, '9', '1', '2019-08-30 15:46:46', '2019-08-30 15:46:48');
INSERT INTO "public"."permission" VALUES (10, 'ram', '访问控制', '包括用户管理里,角色管理,权限管理', NULL, '10', '1', '2019-09-08 14:24:54', '2019-09-08 14:24:56');
INSERT INTO "public"."permission" VALUES (3, 'preset', '预设卡口方案', '预设卡口方案相关权限', NULL, '3', '1', '2019-08-11 15:49:11', '2019-08-11 15:49:13');
INSERT INTO "public"."permission" VALUES (11, 'permission', '权限', '权限相关api', NULL, '11', '1', '2019-09-10 11:45:42', '2019-09-10 11:45:44');

-- ----------------------------
-- Table structure for permission_action
-- ----------------------------
DROP TABLE IF EXISTS "public"."permission_action";
CREATE TABLE "public"."permission_action" (
  "id" int4 NOT NULL DEFAULT nextval('permission_action_id_seq'::regclass),
  "action" varchar(150) COLLATE "pg_catalog"."default" NOT NULL,
  "description" varchar(200) COLLATE "pg_catalog"."default",
  "defaultcheck" int2,
  "url" varchar(100) COLLATE "pg_catalog"."default",
  "p_id" int8 NOT NULL,
  "create_time" timestamp(6),
  "modify_time" timestamp(6)
)
;
COMMENT ON COLUMN "public"."permission_action"."action" IS '功能前缀名例如：add,get,delete';
COMMENT ON COLUMN "public"."permission_action"."description" IS '功能描述';
COMMENT ON COLUMN "public"."permission_action"."defaultcheck" IS '默认是否选中';
COMMENT ON COLUMN "public"."permission_action"."url" IS '权限url';
COMMENT ON COLUMN "public"."permission_action"."p_id" IS '权限id';

-- ----------------------------
-- Records of permission_action
-- ----------------------------
INSERT INTO "public"."permission_action" VALUES (6, 'info', '用户详情', 0, '/user/info', 2, '2019-08-11 15:19:57', '2019-08-11 15:19:59');
INSERT INTO "public"."permission_action" VALUES (35, 'ram_count', '统计RAM数据量', 1, '/dashboard/ram-overview', 1, '2019-08-30 12:27:20', '2019-08-30 12:27:22');
INSERT INTO "public"."permission_action" VALUES (37, 'list', '用户列表', 1, '/user/list', 10, '2019-09-08 14:57:21', '2019-09-08 14:57:23');
INSERT INTO "public"."permission_action" VALUES (38, 'list', '权限列表', 1, '/permission/list', 11, '2019-09-10 11:45:50', '2019-09-10 11:45:52');
INSERT INTO "public"."permission_action" VALUES (39, 'list', '权限列表不分页', 1, '/permission/list-all', 11, '2019-09-10 15:21:15', '2019-09-10 15:21:18');
INSERT INTO "public"."permission_action" VALUES (1, 'data', '数据概览', 0, '/dashbord/scheme-overview', 1, '2019-08-11 15:10:52', '2019-08-11 15:10:54');
INSERT INTO "public"."permission_action" VALUES (2, 'ram', '访问控制', 0, '/dashbord/ram-overview', 1, '2019-08-11 15:11:55', '2019-08-11 15:12:00');
INSERT INTO "public"."permission_action" VALUES (7, 'add', '添加', 1, '/preset/save', 3, '2019-08-11 15:50:36', '2019-08-11 15:50:38');
INSERT INTO "public"."permission_action" VALUES (8, 'query', '查询', 1, '/preset/list', 3, '2019-08-11 15:51:13', '2019-08-11 15:51:16');
INSERT INTO "public"."permission_action" VALUES (10, 'list_point', '查询坐标', 1, '/preset/list-point/**', 3, '2019-08-11 15:52:28', '2019-08-15 15:32:19');
INSERT INTO "public"."permission_action" VALUES (11, 'update', '更新', 1, '/preset/update', 3, '2019-08-11 15:53:06', '2019-08-11 15:53:08');
INSERT INTO "public"."permission_action" VALUES (12, 'trash', '回收站', 1, '/preset/trash/**', 3, '2019-08-11 15:53:41', '2019-08-13 13:29:49');
INSERT INTO "public"."permission_action" VALUES (14, 'batch_trash', '批量丢进回收站', 1, '/preset/batch-trash', 3, '2019-08-13 13:30:39', '2019-08-13 13:30:41');
INSERT INTO "public"."permission_action" VALUES (15, 'download', '批量下载', 1, '/preset/download', 3, '2019-08-13 20:43:55', '2019-08-13 20:43:57');
INSERT INTO "public"."permission_action" VALUES (16, 'upload', '上传', 1, '/preset/upload', 3, '2019-08-14 10:54:47', '2019-08-14 10:54:50');
INSERT INTO "public"."permission_action" VALUES (17, 'add', '添加', 1, '/actual/save', 5, '2019-08-15 15:27:54', '2019-08-15 15:27:58');
INSERT INTO "public"."permission_action" VALUES (18, 'query', '查询', 1, '/actual/list', 5, '2019-08-15 15:28:30', '2019-08-15 15:28:32');
INSERT INTO "public"."permission_action" VALUES (19, 'get', '查询', 1, '/actual/get/**', 5, '2019-08-15 15:29:06', '2019-08-15 15:29:08');
INSERT INTO "public"."permission_action" VALUES (20, 'trash', '回收站', 1, '/actual/trash/**', 5, '2019-08-15 15:31:10', '2019-08-15 15:31:13');
INSERT INTO "public"."permission_action" VALUES (21, 'batch_trash', '批量丢进回收站', 1, '/actual/batch-trash', 5, '2019-08-15 15:32:06', '2019-08-15 15:32:08');
INSERT INTO "public"."permission_action" VALUES (22, 'update', '更新', 1, '/actual/update', 5, '2019-08-15 15:32:51', '2019-08-15 15:32:53');
INSERT INTO "public"."permission_action" VALUES (23, 'download', '下载', 1, '/actual/download', 5, '2019-08-15 15:33:38', '2019-08-15 15:33:40');
INSERT INTO "public"."permission_action" VALUES (24, 'upload', '上传', 1, '/actual/upload', 5, '2019-08-15 15:34:03', '2019-08-15 15:34:05');
INSERT INTO "public"."permission_action" VALUES (25, 'add', '添加', 1, '/via/save', 6, '2019-08-24 15:23:24', '2019-08-24 15:23:26');
INSERT INTO "public"."permission_action" VALUES (26, 'query', '查询', 1, '/via/list', 6, '2019-08-24 15:25:04', '2019-08-24 15:25:08');
INSERT INTO "public"."permission_action" VALUES (27, 'get', '详情', 1, '/via/get/**', 6, '2019-08-24 15:25:44', '2019-08-24 17:21:31');
INSERT INTO "public"."permission_action" VALUES (29, 'batch_trash', '批量丢进回收站', 1, '/via/batch-trash', 6, '2019-08-24 15:26:37', '2019-08-24 15:26:40');
INSERT INTO "public"."permission_action" VALUES (30, 'update', '更新', 1, '/via/update', 6, '2019-08-24 15:27:17', '2019-08-24 15:27:20');
INSERT INTO "public"."permission_action" VALUES (31, 'download', '下载', 1, '/via/download', 6, '2019-08-24 15:28:10', '2019-08-24 15:28:13');
INSERT INTO "public"."permission_action" VALUES (32, 'upload', '上传', 1, '/via/upload', 6, '2019-08-24 15:28:56', '2019-08-24 15:28:58');
INSERT INTO "public"."permission_action" VALUES (33, 'list', '角色列表', 1, '/role/list', 7, '2019-08-29 23:19:47', '2019-08-29 23:19:49');
INSERT INTO "public"."permission_action" VALUES (36, 'list', '日志列表', 1, '/log/list', 9, '2019-08-30 15:47:14', '2019-08-30 15:47:16');
INSERT INTO "public"."permission_action" VALUES (28, 'trash', '回收站', 1, '/via/trash/**', 6, '2019-08-24 15:26:18', '2019-08-24 15:26:20');
INSERT INTO "public"."permission_action" VALUES (9, 'get', '详情', 1, '/preset/getScheme/**', 3, '2019-08-11 15:51:57', '2019-08-11 15:57:07');
INSERT INTO "public"."permission_action" VALUES (34, 'scheme_count', '统计方案数据量', 1, '/dashboard/scheme-overview', 1, '2019-08-30 12:07:56', '2019-08-30 12:07:58');

-- ----------------------------
-- Table structure for preset_scheme
-- ----------------------------
DROP TABLE IF EXISTS "public"."preset_scheme";
CREATE TABLE "public"."preset_scheme" (
  "id" int8 NOT NULL DEFAULT nextval('preset_scheme_id_seq'::regclass),
  "name" varchar(145) COLLATE "pg_catalog"."default" NOT NULL DEFAULT ''::character varying,
  "description" varchar(200) COLLATE "pg_catalog"."default" DEFAULT ''::character varying,
  "bayonet_count" int4 DEFAULT 0,
  "userid" int8 NOT NULL DEFAULT 0,
  "create_time" timestamp(6) NOT NULL,
  "modify_time" timestamp(6) NOT NULL,
  "deleted" int2 DEFAULT 0
)
;
COMMENT ON COLUMN "public"."preset_scheme"."name" IS '预设点方案描述';
COMMENT ON COLUMN "public"."preset_scheme"."description" IS '预设点方案说明';
COMMENT ON COLUMN "public"."preset_scheme"."bayonet_count" IS '方案所包含的卡口点的数量';
COMMENT ON COLUMN "public"."preset_scheme"."userid" IS '所属用户';
COMMENT ON COLUMN "public"."preset_scheme"."deleted" IS '逻辑删除，0未删除，1回收站';

-- ----------------------------
-- Records of preset_scheme
-- ----------------------------
INSERT INTO "public"."preset_scheme" VALUES (7, '23424', '', 2, 1, '2019-08-09 13:22:42', '2019-08-30 14:40:34', 1);
INSERT INTO "public"."preset_scheme" VALUES (9, '两个点的方案', '妈的整死我了', 2, 1, '2019-08-09 13:33:31', '2019-08-13 11:29:11', 1);
INSERT INTO "public"."preset_scheme" VALUES (10, '4变6', '6666', 4, 1, '2019-08-12 07:52:56', '2019-08-30 15:18:01', 1);
INSERT INTO "public"."preset_scheme" VALUES (11, '五个点的数据', '没啥好说的', 5, 1, '2019-08-12 07:54:01', '2019-08-30 15:20:20', 1);
INSERT INTO "public"."preset_scheme" VALUES (12, '55555', NULL, 5, 1, '2019-08-12 07:55:25', '2019-08-30 15:23:43', 1);
INSERT INTO "public"."preset_scheme" VALUES (15, '3333333', NULL, 3, 1, '2019-08-12 08:05:18', '2019-08-13 13:32:53', 1);
INSERT INTO "public"."preset_scheme" VALUES (16, '22222', NULL, 2, 1, '2019-08-12 08:06:12', '2019-08-13 11:29:07', 1);
INSERT INTO "public"."preset_scheme" VALUES (17, '111111', NULL, 1, 1, '2019-08-12 08:08:25', '2019-08-13 09:50:31', 1);
INSERT INTO "public"."preset_scheme" VALUES (18, '77777777', NULL, 7, 1, '2019-08-12 08:09:11', '2019-08-13 09:42:12', 1);
INSERT INTO "public"."preset_scheme" VALUES (19, '1111', '查看回显', 3, 1, '2019-08-12 08:15:18', '2019-08-13 09:43:05', 1);
INSERT INTO "public"."preset_scheme" VALUES (20, '33333', NULL, 3, 1, '2019-08-13 02:21:13', '2019-08-13 11:29:05', 1);
INSERT INTO "public"."preset_scheme" VALUES (21, '44444', NULL, 4, 1, '2019-08-13 02:22:15', '2019-08-13 10:46:32', 1);
INSERT INTO "public"."preset_scheme" VALUES (22, '5555', NULL, 5, 1, '2019-08-13 02:22:39', '2019-08-13 11:28:46', 1);
INSERT INTO "public"."preset_scheme" VALUES (23, '3333', NULL, 3, 1, '2019-08-13 03:06:57', '2019-08-13 11:29:00', 1);
INSERT INTO "public"."preset_scheme" VALUES (24, '测试写入第一条方案', '没啥好说的', 0, 1, '2019-08-14 07:36:58', '2019-08-15 09:55:32', 1);
INSERT INTO "public"."preset_scheme" VALUES (25, '测试写入第一条方案', '没啥好说的', 0, 1, '2019-08-14 07:42:30', '2019-08-14 15:45:54', 1);
INSERT INTO "public"."preset_scheme" VALUES (26, '测试写入第一条方案', '没啥好说的', 0, 1, '2019-08-14 07:46:19', '2019-08-14 16:24:20', 1);
INSERT INTO "public"."preset_scheme" VALUES (27, '测试写入第一条方案', '没啥好说的', 0, 1, '2019-08-14 08:24:27', '2019-08-15 09:55:31', 1);
INSERT INTO "public"."preset_scheme" VALUES (28, '测试写入第一条方案', '没啥好说的', 0, 1, '2019-08-14 08:26:31', '2019-08-15 09:55:33', 1);
INSERT INTO "public"."preset_scheme" VALUES (29, '测试写入第一条方案', '没啥好说的', 0, 1, '2019-08-14 08:29:13', '2019-08-15 09:55:34', 1);
INSERT INTO "public"."preset_scheme" VALUES (30, '测试写入第一条方案', '没啥好说的', NULL, 1, '2019-08-14 08:31:34', '2019-08-14 16:34:48', 1);
INSERT INTO "public"."preset_scheme" VALUES (32, '测试写入第一条方案', '没啥好说的', 1, 1, '2019-08-14 08:36:17', '2019-08-14 16:37:24', 1);
INSERT INTO "public"."preset_scheme" VALUES (33, '测试写入第一条方案', '没啥好说的', 1, 1, '2019-08-14 08:36:44', '2019-08-14 16:37:02', 1);
INSERT INTO "public"."preset_scheme" VALUES (34, '测试写入第一条方案', '没啥好说的', 1, 1, '2019-08-14 08:37:10', '2019-08-14 16:37:21', 1);
INSERT INTO "public"."preset_scheme" VALUES (35, '23424', NULL, NULL, 1, '2019-08-16 07:32:12', '2019-08-16 15:32:38', 1);
INSERT INTO "public"."preset_scheme" VALUES (37, '4变6', '6666', 6, 1, '2019-08-16 08:06:18', '2019-08-16 16:06:53', 1);
INSERT INTO "public"."preset_scheme" VALUES (39, '修改更换地图后首次添加数据', '11', 11, 1, '2019-08-26 09:12:14', '2019-08-26 09:31:23', 0);
INSERT INTO "public"."preset_scheme" VALUES (13, '6666', '6666', 6, 1, '2019-08-12 08:00:59', '2019-08-12 08:00:59', 1);
INSERT INTO "public"."preset_scheme" VALUES (14, '3333', NULL, 3, 1, '2019-08-12 08:04:04', '2019-08-12 08:04:04', 1);
INSERT INTO "public"."preset_scheme" VALUES (31, '测试写入第一条方案', '没啥好说的', 1, 1, '2019-08-14 08:34:37', '2019-08-14 08:34:37', 1);
INSERT INTO "public"."preset_scheme" VALUES (36, '23424', NULL, NULL, 1, '2019-08-16 07:37:04', '2019-08-16 07:37:04', 1);
INSERT INTO "public"."preset_scheme" VALUES (40, '55555', '5555', 5, 1, '2019-09-08 13:37:27.464', '2019-09-08 13:37:27.464', 0);

-- ----------------------------
-- Table structure for presetpoint
-- ----------------------------
DROP TABLE IF EXISTS "public"."presetpoint";
CREATE TABLE "public"."presetpoint" (
  "id" int8 NOT NULL DEFAULT nextval('presetpoint_id_seq'::regclass),
  "lng" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
  "lat" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
  "edge_number" varchar(20) COLLATE "pg_catalog"."default",
  "preid" int8 NOT NULL,
  "create_time" timestamp(6) NOT NULL,
  "modify_time" timestamp(6) NOT NULL
)
;
COMMENT ON COLUMN "public"."presetpoint"."edge_number" IS '所属边的编号';
COMMENT ON COLUMN "public"."presetpoint"."preid" IS '预设方案id,外键';

-- ----------------------------
-- Records of presetpoint
-- ----------------------------
INSERT INTO "public"."presetpoint" VALUES (3, '116.2124837757744', '40.03262649351757', NULL, 7, '2019-08-09 13:22:42', '2019-08-09 13:22:42');
INSERT INTO "public"."presetpoint" VALUES (4, '116.21479542215067', '40.03277846782997', NULL, 7, '2019-08-09 13:22:42', '2019-08-09 13:22:42');
INSERT INTO "public"."presetpoint" VALUES (5, '116.21367904902414', '40.027088647268364', NULL, 9, '2019-08-09 13:33:31', '2019-08-09 13:33:31');
INSERT INTO "public"."presetpoint" VALUES (6, '116.21540071604684', '40.0278362540195', NULL, 9, '2019-08-09 13:33:31', '2019-08-09 13:33:31');
INSERT INTO "public"."presetpoint" VALUES (11, '116.21611832813267', '40.023618329987976', NULL, 11, '2019-08-12 07:54:01', '2019-08-12 07:54:01');
INSERT INTO "public"."presetpoint" VALUES (12, '116.21620414390415', '40.02848198228615', NULL, 11, '2019-08-12 07:54:01', '2019-08-12 07:54:01');
INSERT INTO "public"."presetpoint" VALUES (13, '116.21620414390415', '40.03196519581994', NULL, 11, '2019-08-12 07:54:01', '2019-08-12 07:54:01');
INSERT INTO "public"."presetpoint" VALUES (14, '116.2241850106537', '40.02808764501753', NULL, 11, '2019-08-12 07:54:01', '2019-08-12 07:54:01');
INSERT INTO "public"."presetpoint" VALUES (15, '116.22710274688473', '40.02079199436821', NULL, 11, '2019-08-12 07:54:01', '2019-08-12 07:54:01');
INSERT INTO "public"."presetpoint" VALUES (16, '116.20418802652118', '40.02156760530186', NULL, 12, '2019-08-12 07:55:25', '2019-08-12 07:55:25');
INSERT INTO "public"."presetpoint" VALUES (17, '116.21491499795873', '40.02439390879281', NULL, 12, '2019-08-12 07:55:25', '2019-08-12 07:55:25');
INSERT INTO "public"."presetpoint" VALUES (18, '116.22040720733474', '40.02748299031203', NULL, 12, '2019-08-12 07:55:25', '2019-08-12 07:55:25');
INSERT INTO "public"."presetpoint" VALUES (19, '116.22229515430774', '40.02459108838816', NULL, 12, '2019-08-12 07:55:25', '2019-08-12 07:55:25');
INSERT INTO "public"."presetpoint" VALUES (20, '116.22504125899573', '40.01959569632139', NULL, 12, '2019-08-12 07:55:25', '2019-08-12 07:55:25');
INSERT INTO "public"."presetpoint" VALUES (21, '116.20358731612068', '40.032543523690144', NULL, 13, '2019-08-12 08:00:59', '2019-08-12 08:00:59');
INSERT INTO "public"."presetpoint" VALUES (22, '116.21036676206921', '40.03661796601365', NULL, 13, '2019-08-12 08:00:59', '2019-08-12 08:00:59');
INSERT INTO "public"."presetpoint" VALUES (23, '116.22014976002025', '40.038523669993886', NULL, 13, '2019-08-12 08:00:59', '2019-08-12 08:00:59');
INSERT INTO "public"."presetpoint" VALUES (24, '116.22126536504975', '40.031492015188775', NULL, 13, '2019-08-12 08:00:59', '2019-08-12 08:00:59');
INSERT INTO "public"."presetpoint" VALUES (25, '116.22281004893674', '40.02741726662971', NULL, 13, '2019-08-12 08:00:59', '2019-08-12 08:00:59');
INSERT INTO "public"."presetpoint" VALUES (26, '116.22349657510874', '40.02347372981264', NULL, 13, '2019-08-12 08:00:59', '2019-08-12 08:00:59');
INSERT INTO "public"."presetpoint" VALUES (27, '116.21817790664869', '40.02084457862593', NULL, 14, '2019-08-12 08:04:04', '2019-08-12 08:04:04');
INSERT INTO "public"."presetpoint" VALUES (28, '116.2195509589927', '40.02616850463476', NULL, 14, '2019-08-12 08:04:04', '2019-08-12 08:04:04');
INSERT INTO "public"."presetpoint" VALUES (29, '116.21937932744969', '40.03011188567943', NULL, 14, '2019-08-12 08:04:04', '2019-08-12 08:04:04');
INSERT INTO "public"."presetpoint" VALUES (30, '116.21105519761416', '40.02045019720973', NULL, 15, '2019-08-12 08:05:18', '2019-08-12 08:05:18');
INSERT INTO "public"."presetpoint" VALUES (31, '116.21517435464618', '40.02590560445986', NULL, 15, '2019-08-12 08:05:18', '2019-08-12 08:05:18');
INSERT INTO "public"."presetpoint" VALUES (32, '116.21628995967569', '40.03162345463799', NULL, 15, '2019-08-12 08:05:18', '2019-08-12 08:05:18');
INSERT INTO "public"."presetpoint" VALUES (33, '116.20307433086464', '40.02075255652443', NULL, 16, '2019-08-12 08:06:12', '2019-08-12 08:06:12');
INSERT INTO "public"."presetpoint" VALUES (34, '116.21611832813267', '40.02732525339317', NULL, 16, '2019-08-12 08:06:12', '2019-08-12 08:06:12');
INSERT INTO "public"."presetpoint" VALUES (35, '116.20933888218416', '40.02489342935255', NULL, 17, '2019-08-12 08:08:25', '2019-08-12 08:08:25');
INSERT INTO "public"."presetpoint" VALUES (36, '116.19878163291665', '40.021436146476105', NULL, 18, '2019-08-12 08:09:12', '2019-08-12 08:09:12');
INSERT INTO "public"."presetpoint" VALUES (37, '116.2119972617277', '40.02413100177918', NULL, 18, '2019-08-12 08:09:12', '2019-08-12 08:09:12');
INSERT INTO "public"."presetpoint" VALUES (38, '116.21860507613323', '40.027745884408006', NULL, 18, '2019-08-12 08:09:12', '2019-08-12 08:09:12');
INSERT INTO "public"."presetpoint" VALUES (39, '116.2154298925877', '40.021699063874294', NULL, 18, '2019-08-12 08:09:12', '2019-08-12 08:09:12');
INSERT INTO "public"."presetpoint" VALUES (40, '116.22298168047976', '40.01966142753885', NULL, 18, '2019-08-12 08:09:12', '2019-08-12 08:09:12');
INSERT INTO "public"."presetpoint" VALUES (41, '116.22632849556828', '40.024065274867475', NULL, 18, '2019-08-12 08:09:12', '2019-08-12 08:09:12');
INSERT INTO "public"."presetpoint" VALUES (42, '116.22735828482625', '40.02794305431511', NULL, 18, '2019-08-12 08:09:12', '2019-08-12 08:09:12');
INSERT INTO "public"."presetpoint" VALUES (43, '116.20556310757395', '40.02018727499924', NULL, 19, '2019-08-12 08:15:18', '2019-08-12 08:15:18');
INSERT INTO "public"."presetpoint" VALUES (44, '116.21714823672647', '40.02794305431511', NULL, 19, '2019-08-12 08:15:18', '2019-08-12 08:15:18');
INSERT INTO "public"."presetpoint" VALUES (45, '116.218263841756', '40.02932322770746', NULL, 19, '2019-08-12 08:15:18', '2019-08-12 08:15:18');
INSERT INTO "public"."presetpoint" VALUES (46, '116.21611844746849', '40.021278394949114', NULL, 20, '2019-08-13 02:21:13', '2019-08-13 02:21:13');
INSERT INTO "public"."presetpoint" VALUES (47, '116.22092413067253', '40.0273252527664', NULL, 20, '2019-08-13 02:21:13', '2019-08-13 02:21:13');
INSERT INTO "public"."presetpoint" VALUES (48, '116.223412788046', '40.02916549441716', NULL, 20, '2019-08-13 02:21:13', '2019-08-13 02:21:13');
INSERT INTO "public"."presetpoint" VALUES (49, '116.1975821214886', '40.01887264875', NULL, 21, '2019-08-13 02:22:15', '2019-08-13 02:22:15');
INSERT INTO "public"."presetpoint" VALUES (50, '116.20822327715466', '40.02288217962882', NULL, 21, '2019-08-13 02:22:15', '2019-08-13 02:22:15');
INSERT INTO "public"."presetpoint" VALUES (51, '116.21766301201967', '40.02616850463476', NULL, 21, '2019-08-13 02:22:15', '2019-08-13 02:22:15');
INSERT INTO "public"."presetpoint" VALUES (52, '116.22332685293871', '40.03063765259486', NULL, 21, '2019-08-13 02:22:15', '2019-08-13 02:22:15');
INSERT INTO "public"."presetpoint" VALUES (53, '116.19680787017214', '40.028008777490804', NULL, 22, '2019-08-13 02:22:39', '2019-08-13 02:22:39');
INSERT INTO "public"."presetpoint" VALUES (54, '116.20367313189217', '40.0314262953692', NULL, 22, '2019-08-13 02:22:39', '2019-08-13 02:22:39');
INSERT INTO "public"."presetpoint" VALUES (55, '116.2077922889242', '40.03589509884803', NULL, 22, '2019-08-13 02:22:39', '2019-08-13 02:22:39');
INSERT INTO "public"."presetpoint" VALUES (56, '116.21173981441319', '40.03819510414171', NULL, 22, '2019-08-13 02:22:39', '2019-08-13 02:22:39');
INSERT INTO "public"."presetpoint" VALUES (57, '116.21568733990222', '40.027745884408006', NULL, 22, '2019-08-13 02:22:39', '2019-08-13 02:22:39');
INSERT INTO "public"."presetpoint" VALUES (58, '116.20899561909812', '40.02338171113174', NULL, 23, '2019-08-13 03:06:57', '2019-08-13 03:06:57');
INSERT INTO "public"."presetpoint" VALUES (59, '116.21946514322119', '40.03153144694986', NULL, 23, '2019-08-13 03:06:57', '2019-08-13 03:06:57');
INSERT INTO "public"."presetpoint" VALUES (60, '116.22538643145472', '40.03836595848214', NULL, 23, '2019-08-13 03:06:57', '2019-08-13 03:06:57');
INSERT INTO "public"."presetpoint" VALUES (61, '116.20444738320862', '40.0174791164707', NULL, 10, '2019-08-13 10:34:32', '2019-08-13 10:34:32');
INSERT INTO "public"."presetpoint" VALUES (62, '116.21405874961665', '40.02109435154144', NULL, 10, '2019-08-13 10:34:32', '2019-08-13 10:34:32');
INSERT INTO "public"."presetpoint" VALUES (63, '116.21980840630718', '40.022606120318784', NULL, 10, '2019-08-13 10:34:32', '2019-08-13 10:34:32');
INSERT INTO "public"."presetpoint" VALUES (64, '116.22221124790917', '40.01925389216737', NULL, 10, '2019-08-13 10:34:32', '2019-08-13 10:34:32');
INSERT INTO "public"."presetpoint" VALUES (65, '116.21185352453206', '40.017636876969455', NULL, 10, '2019-08-13 10:34:32', '2019-08-13 10:34:32');
INSERT INTO "public"."presetpoint" VALUES (66, '116.21456101238479', '40.01710114970745', NULL, 10, '2019-08-13 10:34:32', '2019-08-13 10:34:32');
INSERT INTO "public"."presetpoint" VALUES (67, '116.2124837758', '40.0326264935', NULL, 4, '2019-08-14 07:42:30', '2019-08-14 07:42:30');
INSERT INTO "public"."presetpoint" VALUES (68, '116.2124837758', '40.0326264935', NULL, 4, '2019-08-14 07:46:19', '2019-08-14 07:46:19');
INSERT INTO "public"."presetpoint" VALUES (69, '116.2124837758', '40.0326264935', NULL, 31, '2019-08-14 08:34:38', '2019-08-14 08:34:38');
INSERT INTO "public"."presetpoint" VALUES (70, '116.2124837758', '40.0326264935', NULL, 32, '2019-08-14 08:36:17', '2019-08-14 08:36:17');
INSERT INTO "public"."presetpoint" VALUES (71, '116.2124837758', '40.0326264935', NULL, 33, '2019-08-14 08:36:44', '2019-08-14 08:36:44');
INSERT INTO "public"."presetpoint" VALUES (72, '116.2124837758', '40.0326264935', NULL, 34, '2019-08-14 08:37:10', '2019-08-14 08:37:10');
INSERT INTO "public"."presetpoint" VALUES (73, '116.2044473832', '40.0174791165', NULL, 37, '2019-08-16 08:06:18', '2019-08-16 08:06:18');
INSERT INTO "public"."presetpoint" VALUES (74, '116.2140587496', '40.0210943515', NULL, 37, '2019-08-16 08:06:18', '2019-08-16 08:06:18');
INSERT INTO "public"."presetpoint" VALUES (75, '116.2198084063', '40.0226061203', NULL, 37, '2019-08-16 08:06:18', '2019-08-16 08:06:18');
INSERT INTO "public"."presetpoint" VALUES (76, '116.2222112479', '40.0192538922', NULL, 37, '2019-08-16 08:06:18', '2019-08-16 08:06:18');
INSERT INTO "public"."presetpoint" VALUES (77, '116.2118535245', '40.017636877', NULL, 37, '2019-08-16 08:06:18', '2019-08-16 08:06:18');
INSERT INTO "public"."presetpoint" VALUES (78, '116.2145610124', '40.0171011497', NULL, 37, '2019-08-16 08:06:18', '2019-08-16 08:06:18');
INSERT INTO "public"."presetpoint" VALUES (96, '108.91498838826598', '34.5573383637679', NULL, 39, '2019-08-26 09:31:23', '2019-08-26 09:31:23');
INSERT INTO "public"."presetpoint" VALUES (97, '108.91466330880127', '34.5573471995964', NULL, 39, '2019-08-26 09:31:23', '2019-08-26 09:31:23');
INSERT INTO "public"."presetpoint" VALUES (98, '108.91463863674623', '34.55714574244688', NULL, 39, '2019-08-26 09:31:23', '2019-08-26 09:31:23');
INSERT INTO "public"."presetpoint" VALUES (99, '108.91499500086165', '34.55713513985836', NULL, 39, '2019-08-26 09:31:23', '2019-08-26 09:31:23');
INSERT INTO "public"."presetpoint" VALUES (100, '108.91648828983307', '34.55709891240487', NULL, 39, '2019-08-26 09:31:23', '2019-08-26 09:31:23');
INSERT INTO "public"."presetpoint" VALUES (101, '108.91668537849623', '34.557104214375734', NULL, 39, '2019-08-26 09:31:23', '2019-08-26 09:31:23');
INSERT INTO "public"."presetpoint" VALUES (102, '108.91709603905838', '34.55709096018949', NULL, 39, '2019-08-26 09:31:23', '2019-08-26 09:31:23');
INSERT INTO "public"."presetpoint" VALUES (103, '108.91712823672763', '34.557303020444614', NULL, 39, '2019-08-26 09:31:23', '2019-08-26 09:31:23');
INSERT INTO "public"."presetpoint" VALUES (104, '108.91775428504741', '34.557279163719855', NULL, 39, '2019-08-26 09:31:23', '2019-08-26 09:31:23');
INSERT INTO "public"."presetpoint" VALUES (105, '108.91860830608826', '34.55728358163736', NULL, 39, '2019-08-26 09:31:23', '2019-08-26 09:31:23');
INSERT INTO "public"."presetpoint" VALUES (106, '108.91650032079184', '34.55733129509435', NULL, 39, '2019-08-26 09:31:23', '2019-08-26 09:31:23');
INSERT INTO "public"."presetpoint" VALUES (107, '108.95765295414074', '34.28948560115715', NULL, 40, '2019-09-08 13:37:27.479', '2019-09-08 13:37:27.479');
INSERT INTO "public"."presetpoint" VALUES (108, '108.95955384150545', '34.2894891470465', NULL, 40, '2019-09-08 13:37:27.482', '2019-09-08 13:37:27.482');
INSERT INTO "public"."presetpoint" VALUES (109, '108.95937144756319', '34.28949623868948', NULL, 40, '2019-09-08 13:37:27.483', '2019-09-08 13:37:27.483');
INSERT INTO "public"."presetpoint" VALUES (110, '108.95956611518086', '34.29101288622302', NULL, 40, '2019-09-08 13:37:27.484', '2019-09-08 13:37:27.484');
INSERT INTO "public"."presetpoint" VALUES (111, '108.96105716421066', '34.29099515817834', NULL, 40, '2019-09-08 13:37:27.486', '2019-09-08 13:37:27.486');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS "public"."role";
CREATE TABLE "public"."role" (
  "id" int4 NOT NULL DEFAULT nextval('role_id_seq'::regclass),
  "description" varchar(150) COLLATE "pg_catalog"."default",
  "name" varchar(128) COLLATE "pg_catalog"."default",
  "available" char(1) COLLATE "pg_catalog"."default",
  "create_time" timestamp(6),
  "modify_time" timestamp(6)
)
;
COMMENT ON COLUMN "public"."role"."id" IS '角色表';
COMMENT ON COLUMN "public"."role"."available" IS '是否可用，1可用，0不可用';

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO "public"."role" VALUES (1, '拥有所有权限', 'admin', '1', '2019-08-11 10:36:54', '2019-08-11 10:36:58');

-- ----------------------------
-- Table structure for role_permission
-- ----------------------------
DROP TABLE IF EXISTS "public"."role_permission";
CREATE TABLE "public"."role_permission" (
  "role_id" int8 NOT NULL,
  "permission_id" int8 NOT NULL
)
;
COMMENT ON COLUMN "public"."role_permission"."role_id" IS '角色id';
COMMENT ON COLUMN "public"."role_permission"."permission_id" IS '权限id';

-- ----------------------------
-- Records of role_permission
-- ----------------------------
INSERT INTO "public"."role_permission" VALUES (1, 1);
INSERT INTO "public"."role_permission" VALUES (1, 3);
INSERT INTO "public"."role_permission" VALUES (1, 4);
INSERT INTO "public"."role_permission" VALUES (1, 5);
INSERT INTO "public"."role_permission" VALUES (1, 6);
INSERT INTO "public"."role_permission" VALUES (1, 7);
INSERT INTO "public"."role_permission" VALUES (1, 8);
INSERT INTO "public"."role_permission" VALUES (1, 9);
INSERT INTO "public"."role_permission" VALUES (1, 10);
INSERT INTO "public"."role_permission" VALUES (1, 11);
INSERT INTO "public"."role_permission" VALUES (1, 12);
INSERT INTO "public"."role_permission" VALUES (1, 13);
INSERT INTO "public"."role_permission" VALUES (1, 24);
INSERT INTO "public"."role_permission" VALUES (1, 15);
INSERT INTO "public"."role_permission" VALUES (1, 16);
INSERT INTO "public"."role_permission" VALUES (1, 17);
INSERT INTO "public"."role_permission" VALUES (1, 18);
INSERT INTO "public"."role_permission" VALUES (1, 19);
INSERT INTO "public"."role_permission" VALUES (1, 20);
INSERT INTO "public"."role_permission" VALUES (1, 21);
INSERT INTO "public"."role_permission" VALUES (1, 22);
INSERT INTO "public"."role_permission" VALUES (1, 23);
INSERT INTO "public"."role_permission" VALUES (1, 14);
INSERT INTO "public"."role_permission" VALUES (1, 25);
INSERT INTO "public"."role_permission" VALUES (1, 26);
INSERT INTO "public"."role_permission" VALUES (1, 27);
INSERT INTO "public"."role_permission" VALUES (1, 28);
INSERT INTO "public"."role_permission" VALUES (1, 29);
INSERT INTO "public"."role_permission" VALUES (1, 30);
INSERT INTO "public"."role_permission" VALUES (1, 31);
INSERT INTO "public"."role_permission" VALUES (1, 32);
INSERT INTO "public"."role_permission" VALUES (1, 33);
INSERT INTO "public"."role_permission" VALUES (1, 34);
INSERT INTO "public"."role_permission" VALUES (1, 35);
INSERT INTO "public"."role_permission" VALUES (1, 36);
INSERT INTO "public"."role_permission" VALUES (1, 37);
INSERT INTO "public"."role_permission" VALUES (1, 2);
INSERT INTO "public"."role_permission" VALUES (1, 38);
INSERT INTO "public"."role_permission" VALUES (1, 39);

-- ----------------------------
-- Table structure for route_bayonet_point
-- ----------------------------
DROP TABLE IF EXISTS "public"."route_bayonet_point";
CREATE TABLE "public"."route_bayonet_point" (
  "id" int8 NOT NULL DEFAULT nextval('route_bayonet_point_id_seq'::regclass),
  "lng" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
  "lat" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
  "rid" int8 NOT NULL,
  "edge_number" varchar(20) COLLATE "pg_catalog"."default",
  "create_time" timestamp(6) NOT NULL,
  "modify_time" timestamp(6) NOT NULL
)
;
COMMENT ON COLUMN "public"."route_bayonet_point"."rid" IS '所属车辆途径卡口方案，id';

-- ----------------------------
-- Records of route_bayonet_point
-- ----------------------------
INSERT INTO "public"."route_bayonet_point" VALUES (1, '116.20444738320862', '40.0174791164707', 2, NULL, '2019-08-24 08:22:16', '2019-08-24 08:22:16');
INSERT INTO "public"."route_bayonet_point" VALUES (2, '116.21456101238479', '40.01710114970745', 2, NULL, '2019-08-24 08:22:16', '2019-08-24 08:22:16');
INSERT INTO "public"."route_bayonet_point" VALUES (3, '116.21185352453206', '40.017636876969455', 2, NULL, '2019-08-24 08:22:16', '2019-08-24 08:22:16');
INSERT INTO "public"."route_bayonet_point" VALUES (9, '116.20444738320862', '40.0174791164707', 3, NULL, '2019-08-24 09:55:32', '2019-08-24 09:55:32');
INSERT INTO "public"."route_bayonet_point" VALUES (10, '116.21185352453206', '40.017636876969455', 3, NULL, '2019-08-24 09:55:32', '2019-08-24 09:55:32');
INSERT INTO "public"."route_bayonet_point" VALUES (11, '108.91463863674623', '34.55714574244688', 4, NULL, '2019-09-08 13:45:20.656', '2019-09-08 13:45:20.656');
INSERT INTO "public"."route_bayonet_point" VALUES (12, '108.91650032079184', '34.55733129509435', 4, NULL, '2019-09-08 13:45:20.659', '2019-09-08 13:45:20.659');
INSERT INTO "public"."route_bayonet_point" VALUES (13, '108.91775428504741', '34.557279163719855', 4, NULL, '2019-09-08 13:45:20.661', '2019-09-08 13:45:20.661');
INSERT INTO "public"."route_bayonet_point" VALUES (14, '108.91860830608826', '34.55728358163736', 4, NULL, '2019-09-08 13:45:20.662', '2019-09-08 13:45:20.662');

-- ----------------------------
-- Table structure for route_bayonet_scheme
-- ----------------------------
DROP TABLE IF EXISTS "public"."route_bayonet_scheme";
CREATE TABLE "public"."route_bayonet_scheme" (
  "id" int8 NOT NULL DEFAULT nextval('route_bayonet_scheme_id_seq'::regclass),
  "name" varchar(144) COLLATE "pg_catalog"."default",
  "description" varchar(250) COLLATE "pg_catalog"."default",
  "car_number" varchar(30) COLLATE "pg_catalog"."default",
  "bayonet_count" int4 DEFAULT 0,
  "actual_id" int8 NOT NULL,
  "user_id" int4 NOT NULL,
  "create_time" timestamp(6) NOT NULL,
  "modify_time" timestamp(6) NOT NULL,
  "deleted" int2 DEFAULT 0
)
;
COMMENT ON COLUMN "public"."route_bayonet_scheme"."user_id" IS '所属用户';
COMMENT ON COLUMN "public"."route_bayonet_scheme"."deleted" IS '逻辑删除，0未删除，1回收站';

-- ----------------------------
-- Records of route_bayonet_scheme
-- ----------------------------
INSERT INTO "public"."route_bayonet_scheme" VALUES (2, '333', '3333', '宁A·LVCN0', NULL, 6, 1, '2019-08-24 08:22:16', '2019-08-24 17:18:25', 1);
INSERT INTO "public"."route_bayonet_scheme" VALUES (3, '2222', '222', '浙C·93J34', 2, 6, 1, '2019-08-24 09:20:28', '2019-08-24 09:55:32', 1);
INSERT INTO "public"."route_bayonet_scheme" VALUES (4, '4444', '4444', '冀B·904B2', 4, 8, 1, '2019-09-08 13:45:20.651', '2019-09-08 13:45:20.651', 0);

-- ----------------------------
-- Table structure for routes
-- ----------------------------
DROP TABLE IF EXISTS "public"."routes";
CREATE TABLE "public"."routes" (
  "id" int8 NOT NULL DEFAULT nextval('routes_id_seq'::regclass),
  "distance" int4,
  "policy" varchar(100) COLLATE "pg_catalog"."default",
  "time" int4,
  "tolls" int4,
  "tolls_distance" int4,
  "origin" varchar(100) COLLATE "pg_catalog"."default",
  "destination" varchar(100) COLLATE "pg_catalog"."default",
  "path" bytea,
  "state" int4,
  "action_count" int4,
  "rid" int8 NOT NULL,
  "userid" int4 NOT NULL,
  "create_time" timestamp(6) NOT NULL,
  "modify_time" timestamp(6) NOT NULL,
  "deleted" int2 DEFAULT 0
)
;
COMMENT ON COLUMN "public"."routes"."rid" IS '所属车辆途径卡口方案';
COMMENT ON COLUMN "public"."routes"."userid" IS '所属用户';
COMMENT ON COLUMN "public"."routes"."deleted" IS '逻辑删除，0未删除，1回收站';

-- ----------------------------
-- Table structure for steps
-- ----------------------------
DROP TABLE IF EXISTS "public"."steps";
CREATE TABLE "public"."steps" (
  "id" int8 NOT NULL DEFAULT nextval('steps_id_seq'::regclass),
  "action" varchar(255) COLLATE "pg_catalog"."default",
  "assistant_action" varchar(255) COLLATE "pg_catalog"."default",
  "cities" varchar(255) COLLATE "pg_catalog"."default",
  "distance" int4,
  "instruction" varchar(255) COLLATE "pg_catalog"."default",
  "road" varchar(100) COLLATE "pg_catalog"."default",
  "time" int8,
  "path" bytea NOT NULL,
  "route_id" int8 NOT NULL,
  "create_time" timestamp(6) NOT NULL,
  "modify_time" timestamp(6) NOT NULL
)
;
COMMENT ON COLUMN "public"."steps"."road" IS '道路名称';
COMMENT ON COLUMN "public"."steps"."time" IS '所需时长';
COMMENT ON COLUMN "public"."steps"."route_id" IS '所属轨迹id';

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS "public"."user";
CREATE TABLE "public"."user" (
  "id" int4 NOT NULL DEFAULT nextval('user_id_seq'::regclass),
  "username" varchar(63) COLLATE "pg_catalog"."default" NOT NULL,
  "password" varchar(63) COLLATE "pg_catalog"."default" NOT NULL DEFAULT ''::character varying,
  "nickname" varchar(63) COLLATE "pg_catalog"."default" NOT NULL DEFAULT ''::character varying,
  "gender" int4 NOT NULL DEFAULT 0,
  "birthday" date,
  "mobile" varchar(20) COLLATE "pg_catalog"."default" DEFAULT ''::character varying,
  "email" varchar(25) COLLATE "pg_catalog"."default" NOT NULL DEFAULT ''::character varying,
  "avatar" varchar(255) COLLATE "pg_catalog"."default" NOT NULL DEFAULT ''::character varying,
  "description" varchar(145) COLLATE "pg_catalog"."default" DEFAULT ''::character varying,
  "status" int4 NOT NULL DEFAULT 0,
  "user_level" int4 DEFAULT 0,
  "last_login_time" timestamp(6),
  "last_login_ip" varchar(63) COLLATE "pg_catalog"."default" NOT NULL DEFAULT ''::character varying,
  "create_time" timestamp(6),
  "modify_time" timestamp(6),
  "deleted" int2 DEFAULT 0,
  "role_id" int8,
  "role_name" varchar(150) COLLATE "pg_catalog"."default"
)
;
COMMENT ON COLUMN "public"."user"."username" IS '用户名称';
COMMENT ON COLUMN "public"."user"."password" IS '用户密码';
COMMENT ON COLUMN "public"."user"."nickname" IS '用户昵称或网络名称';
COMMENT ON COLUMN "public"."user"."gender" IS '性别：0 未知， 1男， 1 女';
COMMENT ON COLUMN "public"."user"."birthday" IS '生日';
COMMENT ON COLUMN "public"."user"."mobile" IS '用户手机号码';
COMMENT ON COLUMN "public"."user"."email" IS '用户邮箱';
COMMENT ON COLUMN "public"."user"."avatar" IS '用户头像图片';
COMMENT ON COLUMN "public"."user"."description" IS '用户个人说明';
COMMENT ON COLUMN "public"."user"."status" IS '0 可用, 1 禁用, 2 注销';
COMMENT ON COLUMN "public"."user"."user_level" IS '0 普通用户，1 VIP用户，2 高级VIP用户';
COMMENT ON COLUMN "public"."user"."last_login_time" IS '最近一次登录时间';
COMMENT ON COLUMN "public"."user"."last_login_ip" IS '最近一次登录IP地址';
COMMENT ON COLUMN "public"."user"."create_time" IS '创建时间';
COMMENT ON COLUMN "public"."user"."modify_time" IS '更新时间';
COMMENT ON COLUMN "public"."user"."deleted" IS '逻辑删除';
COMMENT ON COLUMN "public"."user"."role_id" IS '用户角色id';
COMMENT ON COLUMN "public"."user"."role_name" IS '用户角色名称';
COMMENT ON TABLE "public"."user" IS '用户表';

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO "public"."user" VALUES (1, 'guqing', '$2a$10$nmNj03xy8zPEKtsJ0bu.yODNsHgXHi1uTBafcOjXTCqjXOA/R/WCy', '聽見下雨的聲音', 0, '2019-08-09', '', 'guqing3478520@qq.com', '', '', 0, 0, NULL, '', '2019-08-09 15:33:41', '2019-08-09 15:33:47', 0, 1, 'admin');

-- ----------------------------
-- Primary Key structure for table actual_bayonet_point
-- ----------------------------
ALTER TABLE "public"."actual_bayonet_point" ADD CONSTRAINT "actual_bayonet_point_primary" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table actual_layout_scheme
-- ----------------------------
ALTER TABLE "public"."actual_layout_scheme" ADD CONSTRAINT "actual_layout_scheme_primary" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table log
-- ----------------------------
ALTER TABLE "public"."log" ADD CONSTRAINT "log_primary" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table permission
-- ----------------------------
ALTER TABLE "public"."permission" ADD CONSTRAINT "permission_primary" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table permission_action
-- ----------------------------
ALTER TABLE "public"."permission_action" ADD CONSTRAINT "permission_action_primary" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table preset_scheme
-- ----------------------------
ALTER TABLE "public"."preset_scheme" ADD CONSTRAINT "preset_scheme_primary" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table presetpoint
-- ----------------------------
ALTER TABLE "public"."presetpoint" ADD CONSTRAINT "presetpoint_primary" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table role
-- ----------------------------
ALTER TABLE "public"."role" ADD CONSTRAINT "role_primary" PRIMARY KEY ("id");

-- ----------------------------
-- Indexes structure for table role_permission
-- ----------------------------
CREATE INDEX "role_permission_idx" ON "public"."role_permission" USING btree (
  "role_id" "pg_catalog"."int8_ops" ASC NULLS LAST
);

-- ----------------------------
-- Primary Key structure for table route_bayonet_point
-- ----------------------------
ALTER TABLE "public"."route_bayonet_point" ADD CONSTRAINT "route_bayonet_point_primary" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table route_bayonet_scheme
-- ----------------------------
ALTER TABLE "public"."route_bayonet_scheme" ADD CONSTRAINT "route_bayonet_scheme_primary" PRIMARY KEY ("id");

-- ----------------------------
-- Indexes structure for table routes
-- ----------------------------
CREATE UNIQUE INDEX "rid" ON "public"."routes" USING btree (
  "rid" "pg_catalog"."int8_ops" ASC NULLS LAST
);
CREATE UNIQUE INDEX "userid" ON "public"."routes" USING btree (
  "userid" "pg_catalog"."int4_ops" ASC NULLS LAST
);

-- ----------------------------
-- Primary Key structure for table routes
-- ----------------------------
ALTER TABLE "public"."routes" ADD CONSTRAINT "routes_primary" PRIMARY KEY ("id");

-- ----------------------------
-- Indexes structure for table steps
-- ----------------------------
CREATE UNIQUE INDEX "route_id" ON "public"."steps" USING btree (
  "route_id" "pg_catalog"."int8_ops" ASC NULLS LAST
);

-- ----------------------------
-- Primary Key structure for table steps
-- ----------------------------
ALTER TABLE "public"."steps" ADD CONSTRAINT "steps_primary" PRIMARY KEY ("id");

-- ----------------------------
-- Indexes structure for table user
-- ----------------------------
CREATE UNIQUE INDEX "email" ON "public"."user" USING btree (
  "email" COLLATE "pg_catalog"."default" "pg_catalog"."text_ops" ASC NULLS LAST
);
CREATE UNIQUE INDEX "user_name" ON "public"."user" USING btree (
  "username" COLLATE "pg_catalog"."default" "pg_catalog"."text_ops" ASC NULLS LAST
);

-- ----------------------------
-- Primary Key structure for table user
-- ----------------------------
ALTER TABLE "public"."user" ADD CONSTRAINT "user_primary" PRIMARY KEY ("id");
