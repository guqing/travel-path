create table menu
(
    id          bigint auto_increment
        primary key,
    parent_id   bigint       not null,
    title       varchar(100) not null,
    name        varchar(50),
    path        varchar(255),
    redirect    varchar(150),
    component   varchar(255),
    icon        varchar(50),
    keep_alive  int,
    hidden      int    default 1,
    perms       varchar(50),
    type        char(2)      not null,
    sort_index  bigint default 0,
    create_time datetime     not null,
    modify_time datetime
);

comment on column menu.id is '菜单/按钮id';

comment on column menu.parent_id is '上级菜单id';

comment on column menu.title is '菜单或按钮的标题';

comment on column menu.name is '组件名称';

comment on column menu.path is '对应路由path';

comment on column menu.redirect is '重定向到路径';

comment on column menu.component is '对应路由组件component';

comment on column menu.icon is '图标';

comment on column menu.hidden is '控制路由和子路由是否显示在 sidebar';

comment on column menu.perms is '权限标识';

comment on column menu.type is '类型 0菜单 1按钮';

comment on column menu.sort_index is '排序';

comment on column menu.create_time is '创建时间';

comment on column menu.modify_time is '修改时间';

create index menu_id
    on menu (id);

create index menu_parent_id
    on menu (parent_id);

create table role
(
    id          bigint auto_increment
        primary key,
    role_name   varchar(10)   not null,
    remark      varchar(100),
    is_default  int default 0 not null,
    deleted     int default 0,
    create_time datetime      not null,
    modify_time datetime
);

comment on column role.id is '角色id';

comment on column role.role_name is '角色名称';

comment on column role.remark is '角色描述';

comment on column role.is_default is '是否是默认角色';

comment on column role.deleted is '删除状态';

comment on column role.create_time is '创建时间';

comment on column role.modify_time is '修改时间';


create table role_menu
(
    role_id bigint not null,
    menu_id bigint not null,
    primary key (role_id, menu_id)
);

create index role_menu_menu_id
    on role_menu (menu_id);

create index role_menu_role_id
    on role_menu (role_id);


create table user
(
    id              bigint auto_increment
        primary key,
    username        varchar(50)  not null,
    password        varchar(128) not null,
    nickname        varchar(100) default '',
    group_id        bigint,
    email           varchar(128)
        constraint user_email_uindex
            unique,
    mobile          varchar(20)
        constraint rms_user_mobile
            unique,
    gender          varchar(30),
    is_tab          int,
    theme           varchar(10),
    avatar          varchar(100),
    description     varchar(150),
    last_login_time datetime,
    status          int          not null,
    deleted         int          default 0,
    create_time     datetime     not null,
    modify_time     datetime
);

comment on column user.id is '用户id';

comment on column user.username is '用户名';

comment on column user.password is '密码';

comment on column user.nickname is '昵称';

comment on column user.group_id is '用户组';

comment on column user.email is '邮箱';

comment on column user.mobile is '联系电话';

comment on column user.gender is '性别 MALE男 FEMALE女 UNKNOWN保密';

comment on column user.is_tab is '是否开启tab，0关闭 1开启';

comment on column user.theme is '主题';

comment on column user.avatar is '头像';

comment on column user.description is '描述';

comment on column user.last_login_time is '最近访问时间';

comment on column user.status is '状态 0锁定 1有效';

comment on column user.deleted is '删除状态，0正常，1已删除';

comment on column user.create_time is '创建时间';

comment on column user.modify_time is '修改时间';

create index user_username
    on user (username);


create table user_connection
(
    user_id          varchar(50) not null,
    provider_name      varchar(20) not null,
    provider_user_id   varchar(50) not null,
    provider_user_name varchar(50),
    nick_name          varchar(50),
    avatar             varchar(512),
    location           varchar(255),
    remark             varchar(255),
    primary key (user_id, provider_name, provider_user_id),
    constraint user_connection_rank
        unique (user_id, provider_name, provider_user_id)
);

comment on column user_connection.user_id is '系统用户id';

comment on column user_connection.provider_name is '第三方平台名称';

comment on column user_connection.provider_user_id is '第三方平台账户id';

comment on column user_connection.provider_user_name is '第三方平台用户名';

comment on column user_connection.nick_name is '第三方平台昵称';

comment on column user_connection.avatar is '第三方平台头像';

comment on column user_connection.location is '地址';

comment on column user_connection.remark is '备注';


create table user_group
(
    id          bigint auto_increment
        primary key,
    parent_id   bigint       not null,
    group_name  varchar(100) not null,
    sort_index  bigint,
    create_time datetime,
    modify_time datetime
);

comment on column user_group.id is '用户组id';

comment on column user_group.parent_id is '上级分组id';

comment on column user_group.group_name is '分组名称';

comment on column user_group.sort_index is '排序';

comment on column user_group.create_time is '创建时间';

comment on column user_group.modify_time is '修改时间';

create index user_group_group_name
    on user_group (group_name);

create index user_group_parent_id
    on user_group (parent_id);


create table user_login_log
(
    id         bigint auto_increment
        primary key,
    username   varchar(50) not null,
    login_time datetime    not null,
    location   varchar(50),
    ip         varchar(50),
    system     varchar(50),
    browser    varchar(50)
);

comment on column user_login_log.id is 'id';

comment on column user_login_log.username is '用户名';

comment on column user_login_log.login_time is '登录时间';

comment on column user_login_log.location is '登录地点';

comment on column user_login_log.ip is 'ip地址';

comment on column user_login_log.system is '操作系统';

comment on column user_login_log.browser is '浏览器';

create index user_login_log_login_time
    on user_login_log (login_time);


create table user_role
(
    user_id bigint not null,
    role_id bigint not null,
    primary key (user_id, role_id)
);

comment on column user_role.user_id is '用户id';

comment on column user_role.role_id is '角色id';


create table action_log
(
    id          bigint auto_increment
        primary key,
    username    varchar(50),
    operation   text,
    execution_time  decimal(11),
    method      text,
    params      text,
    ip          varchar(64),
    location    varchar(50),
    create_time datetime
);

comment on column action_log.id is '日志id';

comment on column action_log.username is '操作用户';

comment on column action_log.operation is '操作内容';

comment on column action_log.execution_time is '耗时';

comment on column action_log.method is '操作方法';

comment on column action_log.params is '方法参数';

comment on column action_log.ip is '操作者ip';

comment on column action_log.location is '操作地点';

comment on column action_log.create_time is '创建时间';

create index action_log_create_time
    on action_log (create_time);

create table user_setting_option
(
    id           bigint auto_increment
        primary key,
    user_id bigint not null,
    option_key   VARCHAR(100) not null
        constraint setting_option_option_key_uindex
            unique,
    option_value VARCHAR(100) not null,
    create_time  datetime     not null,
    modify_time  datetime     not null
);

comment on column user_setting_option.option_key is 'key名称';

comment on column user_setting_option.option_value is '值';

comment on column user_setting_option.create_time is '创建时间';

comment on column user_setting_option.modify_time is '修改时间';

create table preset_plan (
    id bigint not null primary key auto_increment,
    user_id bigint not null,
    count int default 0 comment '卡口数量',
    name varchar(150) not null comment '方案名称',
    description varchar(255) comment '备注',
    deleted int default 0 comment '逻辑删除',
    create_time datetime not null,
    modify_time datetime not null
);

create table preset_node(
    id bigint not null primary key auto_increment,
    preset_id bigint not null comment '所属预设卡口方案',
    lat double not null comment '纬度',
    lng double not null comment '经度'
);

create table deploy_plan (
    id bigint not null primary key auto_increment,
    user_id bigint not null,
    preset_id bigint not null,
    count int default 0 comment '卡口数量',
    name varchar(150) not null comment '方案名称',
    description varchar(255) comment '备注',
    deleted int default 0 comment '逻辑删除',
    create_time datetime not null,
    modify_time datetime not null
);

create table deploy_node(
    id bigint not null primary key auto_increment,
    deploy_id bigint not null,
    lat double not null comment '纬度',
    lng double not null comment '经度'
);

create table route(
    id bigint not null primary key auto_increment,
    user_id bigint not null,
    car_number varchar(30) not null,
    points text not null comment '轨迹坐标点lat lng序列',
    distance double not null default 0 comment '距离ms',
    time double not null default 0 comment '时间ms',
    average_speed double not null default 0 comment '平均速度km/h',
    regular_turn_count double not null default 0 comment '转弯次数',
    sharp_turn_count double not null default 0 comment '急转弯次数',
    u_turn_count double not null default 0 comment '掉头次数',
    create_time datetime not null,
    modify_time datetime not null
);

create table route_check_point_sequence(
    id bigint not null primary key auto_increment,
    route_id bigint not null,
    index int not null,
    lat double not null comment '纬度',
    lng double not null comment '经度',
    create_time datetime not null,
    modify_time datetime not null
);