DELIMITER ;
delete from oauth2_user;
delete from oauth2_client;

insert into oauth2_user values(1,'admin','d3c59d25033dbf980d29554025c23a75','8d78869f470951332959580424d4bf4f');
insert into oauth2_client values(1,'chapter17-client','c1ebe466-1cdc-4bd3-ab69-77c3561b9dee','d8346ea2-6017-43ed-ad68-19c0f971738b');