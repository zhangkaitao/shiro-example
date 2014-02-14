DELIMITER ;
delete from sys_user;
delete from sys_role;
delete from sys_resource;
delete from sys_organization;

insert into sys_user values(1,1,'admin','9b23e4ad8843c28a9880ac91d299ea17','0f8dd0d14a1cd8e52912e027be51668a', '1', false);
insert into sys_organization values(1, '总公司', 0, '0/', true);
insert into sys_organization values(2, '分公司1', 1, '0/1/', true);
insert into sys_organization values(3, '分公司2', 1, '0/1/', true);
insert into sys_organization values(4, '分公司11', 2, '0/1/2/', true);

insert into sys_resource values(1, '资源', 'menu', 0, '0/', '', true);

insert into sys_resource values(10, '组织机构管理', 'menu', 1, '0/1/', '', true);
insert into sys_resource values(11, '组织机构新增', 'button', 10, '0/1/10/', 'organization:create', true);
insert into sys_resource values(12, '组织机构修改', 'button', 10, '0/1/10/', 'organization:update', true);
insert into sys_resource values(13, '组织机构删除', 'button', 10, '0/1/10/', 'organization:delete', true);
insert into sys_resource values(14, '组织机构查看', 'button', 10, '0/1/210/', 'organization:view', true);

insert into sys_resource values(21, '用户管理', 'menu', 1, '0/1/', '', true);
insert into sys_resource values(22, '用户新增', 'button', 21, '0/1/21/', 'user:create', true);
insert into sys_resource values(23, '用户修改', 'button', 21, '0/1/21/', 'user:update', true);
insert into sys_resource values(24, '用户删除', 'button', 21, '0/1/21/', 'user:delete', true);
insert into sys_resource values(25, '用户查看', 'button', 21, '0/1/21/', 'user:view', true);

insert into sys_resource values(31, '资源管理', 'menu', 1, '0/1/', '', true);
insert into sys_resource values(32, '资源新增', 'button', 31, '0/1/31/', 'resource:create', true);
insert into sys_resource values(33, '资源修改', 'button', 31, '0/1/31/', 'resource:update', true);
insert into sys_resource values(34, '资源删除', 'button', 31, '0/1/31/', 'resource:delete', true);
insert into sys_resource values(35, '资源查看', 'button', 31, '0/1/31/', 'resource:view', true);

insert into sys_resource values(41, '角色管理', 'menu', 1, '0/1/', '', true);
insert into sys_resource values(42, '角色新增', 'button', 41, '0/1/41/', 'role:create', true);
insert into sys_resource values(43, '角色修改', 'button', 41, '0/1/41/', 'role:update', true);
insert into sys_resource values(44, '角色删除', 'button', 41, '0/1/41/', 'role:delete', true);
insert into sys_resource values(45, '角色查看', 'button', 41, '0/1/41/', 'role:view', true);

insert into sys_role values(1, 'admin', '超级管理员', '12,13,14,15,22,23,24,25,32,33,34,35,42,43,44,45', true);