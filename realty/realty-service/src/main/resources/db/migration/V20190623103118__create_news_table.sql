create table IF NOT EXISTS news
(
  id serial primary key,
  title varchar not null,
  content varchar not null,
  status int,
  author varchar ,
  location varchar,
  price varchar ,
  created_date timestamp,
  updated_date timestamp
);
create table IF NOT EXISTS images
(
  id serial primary key,
  news_id INT REFERENCES news (id) NOT NULL,
  name varchar not null,
  link_view varchar ,
  link_download varchar ,
  created_date timestamp,
  updated_date timestamp
);
