drop table if exists sys_user;
drop table if exists sys_app;
drop table if exists sys_user_app_roles;
drop table if exists sys_organization;
drop table if exists sys_resource;
drop table if exists sys_role;
drop table if exists sessions;

create table sessions (
  id varchar(200),
  session varchar(2000),
  constraint pk_sessions primary key(id)
) charset=utf8 ENGINE=InnoDB;


create table sys_user (
  id bigint auto_increment,
  organization_id bigint,
  username varchar(100),
  password varchar(100),
  salt varchar(100),
  locked bool default false,
  constraint pk_sys_user primary key(id)
) charset=utf8 ENGINE=InnoDB;
create unique index idx_sys_user_username on sys_user(username);
create index idx_sys_user_organization_id on sys_user(organization_id);

create table sys_app (
  id bigint auto_increment,
  name varchar(100),
  app_key varchar(100),
  app_secret varchar(100),
  available bool default false,
  constraint pk_sys_app primary key(id)
) charset=utf8 ENGINE=InnoDB;
create unique index idx_sys_app_app_key on sys_app(app_key);

create table sys_user_app_roles (
  id bigint auto_increment,
  user_id bigint,
  app_id bigint,
  role_ids varchar(100),
  constraint pk_sys_user_app_roles primary key(id)
) charset=utf8 ENGINE=InnoDB;
create unique index sys_user_app_roles_user_id_app_id on sys_user_app_roles(user_id, app_id);




create table sys_organization (
  id bigint auto_increment,
  name varchar(100),
  parent_id bigint,
  parent_ids varchar(100),
  available bool default false,
  constraint pk_sys_organization primary key(id)
) charset=utf8 ENGINE=InnoDB;
create index idx_sys_organization_parent_id on sys_organization(parent_id);
create index idx_sys_organization_parent_ids on sys_organization(parent_ids);


create table sys_resource (
  id bigint auto_increment,
  name varchar(100),
  type varchar(50),
  url varchar(200),
  parent_id bigint,
  parent_ids varchar(100),
  permission varchar(100),
  available bool default false,
  constraint pk_sys_resource primary key(id)
) charset=utf8 ENGINE=InnoDB;
create index idx_sys_resource_parent_id on sys_resource(parent_id);
create index idx_sys_resource_parent_ids on sys_resource(parent_ids);

create table sys_role (
  id bigint auto_increment,
  role varchar(100),
  description varchar(100),
  resource_ids varchar(100),
  available bool default false,
  constraint pk_sys_role primary key(id)
) charset=utf8 ENGINE=InnoDB;
create index idx_sys_role_resource_ids on sys_role(resource_ids);