create table IF NOT EXISTS account
(
  id serial primary key ,
  user_name varchar not null UNIQUE ,
  first_name varchar ,
  last_name varchar  ,
  email varchar ,
  password varchar not null,
  is_dashboard BOOLEAN,
  created_date              TIMESTAMP,
  updated_date              TIMESTAMP
);

create table IF NOT EXISTS role
(
 id serial primary key,
  role_name varchar  not null,
  created_by varchar ,
  created_date              TIMESTAMP,
  updated_date              TIMESTAMP
);

create table IF NOT EXISTS account_role
(
	user_id INT REFERENCES account (id) NOT NULL,
	role_id INT REFERENCES role (id) NOT NULL,
	CONSTRAINT user_role_pk PRIMARY KEY (user_id,role_id)
);