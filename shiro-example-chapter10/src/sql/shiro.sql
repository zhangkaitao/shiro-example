drop table if exists sessions;

create table sessions (
  id varchar(200),
  session varchar(2000),
  constraint pk_sessions primary key(id)
) charset=utf8 ENGINE=InnoDB;
