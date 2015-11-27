DELIMITER ;
delete from sys_user;
delete from sys_app;
delete from sys_user_app_roles;
delete from sys_role;
delete from sys_resource;
delete from sys_organization;

insert into sys_user values(1,1,'admin','d3c59d25033dbf980d29554025c23a75','8d78869f470951332959580424d4bf4f', false);
insert into sys_app values(1, '中心服务器', '645ba616-370a-43a8-a8e0-993e7a590cf0', 'bb74abb6-bae0-47dd-a7b1-9571ea3a0f33', true);
insert into sys_app values(2, 'APP-1', '645ba612-370a-43a8-a8e0-993e7a590cf0', 'bb74abb2-bae0-47dd-a7b1-9571ea3a0f33', true);
insert into sys_app values(3, 'APP-2', '645ba613-370a-43a8-a8e0-993e7a590cf0', 'bb74abb3-bae0-47dd-a7b1-9571ea3a0f33', true);

insert into sys_user_app_roles values(1, 1, 1, '1');
insert into sys_user_app_roles values(2, 1, 2, '1,2');
insert into sys_user_app_roles values(3, 1, 3, '1,3');

insert into sys_organization values(1, '总公司', 0, '0/', true);
insert into sys_organization values(2, '分公司1', 1, '0/1/', true);
insert into sys_organization values(3, '分公司2', 1, '0/1/', true);
insert into sys_organization values(4, '分公司11', 2, '0/1/2/', true);

insert into sys_resource values(1, '资源', 'menu', '', 0, '0/', '', true);

insert into sys_resource values(11, '组织机构管理', 'menu', '/organization', 1, '0/1/', 'organization:*', true);
insert into sys_resource values(12, '组织机构新增', 'button', '', 11, '0/1/11/', 'organization:create', true);
insert into sys_resource values(13, '组织机构修改', 'button', '', 11, '0/1/11/', 'organization:update', true);
insert into sys_resource values(14, '组织机构删除', 'button', '', 11, '0/1/11/', 'organization:delete', true);
insert into sys_resource values(15, '组织机构查看', 'button', '', 11, '0/1/11/', 'organization:view', true);

insert into sys_resource values(16, '应用管理', 'menu', '/app', 1, '0/1/', 'app:*', true);
insert into sys_resource values(17, '应用新增', 'button', '', 16, '0/1/16/', 'app:create', true);
insert into sys_resource values(18, '应用修改', 'button', '', 16, '0/1/16/', 'app:update', true);
insert into sys_resource values(19, '应用删除', 'button', '', 16, '0/1/16/', 'app:delete', true);
insert into sys_resource values(20, '应用查看', 'button', '', 16, '0/1/16/', 'app:view', true);

insert into sys_resource values(21, '用户管理', 'menu', '/user', 1, '0/1/', 'user:*', true);
insert into sys_resource values(22, '用户新增', 'button', '', 21, '0/1/21/', 'user:create', true);
insert into sys_resource values(23, '用户修改', 'button', '', 21, '0/1/21/', 'user:update', true);
insert into sys_resource values(24, '用户删除', 'button', '', 21, '0/1/21/', 'user:delete', true);
insert into sys_resource values(25, '用户查看', 'button', '', 21, '0/1/21/', 'user:view', true);

insert into sys_resource values(31, '资源管理', 'menu', '/resource', 1, '0/1/', 'resource:*', true);
insert into sys_resource values(32, '资源新增', 'button', '', 31, '0/1/31/', 'resource:create', true);
insert into sys_resource values(33, '资源修改', 'button', '', 31, '0/1/31/', 'resource:update', true);
insert into sys_resource values(34, '资源删除', 'button', '', 31, '0/1/31/', 'resource:delete', true);
insert into sys_resource values(35, '资源查看', 'button', '', 31, '0/1/31/', 'resource:view', true);

insert into sys_resource values(41, '角色管理', 'menu', '/role', 1, '0/1/', 'role:*', true);
insert into sys_resource values(42, '角色新增', 'button', '', 41, '0/1/41/', 'role:create', true);
insert into sys_resource values(43, '角色修改', 'button', '', 41, '0/1/41/', 'role:update', true);
insert into sys_resource values(44, '角色删除', 'button', '', 41, '0/1/41/', 'role:delete', true);
insert into sys_resource values(45, '角色查看', 'button', '', 41, '0/1/41/', 'role:view', true);

insert into sys_resource values(51, '授权管理', 'menu', '/authorization', 1, '0/1/', 'authorization:*', true);
insert into sys_resource values(52, '授权新增', 'button', '', 51, '0/1/51/', 'authorization:create', true);
insert into sys_resource values(53, '授权修改', 'button', '', 51, '0/1/51/', 'authorization:update', true);
insert into sys_resource values(54, '授权删除', 'button', '', 51, '0/1/51/', 'authorization:delete', true);
insert into sys_resource values(55, '授权查看', 'button', '', 51, '0/1/51/', 'authorization:view', true);

insert into sys_role values(1, 'admin', '超级管理员', '11,16,21,31,41,51', true);
insert into sys_role values(2, 'role1', 'APP1管理员', '11,16,21,31,41,51', true);
insert into sys_role values(3, 'role2', 'APP2管理员', '11,16,21,31,41,51', true);