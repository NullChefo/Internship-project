insert into `users` (first_name,last_name,email,username,password,account_locked,account_expired,credentials_non_expired,enabled,roles)
values('Admin','Adminov','admin@webserve.org','admin','$2a$10$GRLdNijSQMUvl/au9ofL.eDwmoohzzS7.rmNSJZ.0FxO/BTk76klW',0,0,1,1,'ROLE_ADMIN');

insert into `users` (first_name,last_name,email,username,password,account_locked,account_expired,credentials_non_expired,enabled,roles)
values('User','Userov','user@webserve.org','user','$2a$10$GRLdNijSQMUvl/au9ofL.eDwmoohzzS7.rmNSJZ.0FxO/BTk76klW',0,0,1,1,'ROLE_USER');