insert into role(role_name,created_by) values ('Admin','');
insert into role(role_name,created_by) values ('User','');

insert into account(user_name,first_name,last_name,email,password) values ('ThaoNguyen','thao','thao','thao@gmail.com','{bcrypt}$2a$10$1U7UsNBdi1x42rYYKaZx3O9LW2hcnUzzBzEbWLOW899gEhYmABESK');

insert into account_role(user_id,role_id) values (1,1);