create table IF NOT EXISTS recruitment
(
  id serial primary key,
  title varchar  not null,
  content varchar not null,
  created_date              TIMESTAMP,
  updated_date              TIMESTAMP
);
