--grant usage on schema banking to thomas;
--grant create on schema banking to thomas;
--grant all privileges on all tables in schema banking to thomas;

set search_path to banking;
show search_path;

--select current_user;

drop table if exists app_user;
drop table if exists bank_account;
drop table if exists account_transaction;


CREATE TABLE app_user (
  username varchar(20) not null,
  password varchar(20) not null,
  email varchar(320) not null,
  first_name varchar(20) not null,
  last_name varchar(20) not null,
  address varchar(40) not null,
  city varchar(40) not null,
  state varchar(2) not null,
  account_id serial
);

CREATE TABLE bank_account (
  account_id serial not null,
  username varchar(20) not null,
  account_type varchar(8) not null,
  date_created date default current_date,
  balance numeric (8, 2) default 0
);

CREATE TABLE account_transaction (
  transaction_id serial not null,
  username varchar(20) not null,
  account_id serial not null,
  amount numeric (5, 2) not null, --transactions capped at $99,999.99
  transaction_date date not null
);


  alter table app_user
  add constraint app_user_pk
  primary key (username);
 
  alter table bank_account
  add constraint account_id_pk
  primary key (account_id);
 
  alter table app_user
  add constraint app_user_account_fk
  foreign key (account_id)
  references bank_account
  on delete cascade;
 
 
  alter table bank_account
  add constraint username_fk
  foreign key (username)
  references app_user;
 
 
  alter table account_transaction
  add constraint transaction_id_pk
  primary key (transaction_id);
 
  alter table account_transaction
  add constraint username_fk
  foreign key (username)
  references app_user;
 
  alter table account_transaction
  add constraint account_id_fk
  foreign key (account_id)
  references bank_account;



alter table app_user
drop constraint app_user_account_fk;
 
 
insert into app_user (username, password, email, first_name, last_name, address, city, state)
values('gexthegecko', 'password', 'thomas@revature.net', 'thomas', 'diendorf', '12345 My Lane', 'Apple Valley', 'CA');

delete from app_user
where username = 'gexthegecko';

insert into bank_account (username, account_type, balance)
values('gexthegecko', 'checking', 0.00);
