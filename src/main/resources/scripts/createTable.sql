create table if not exists url (
  id          int(11) AUTO_INCREMENT,
  description text,
  value       varchar(100),
  PRIMARY KEY (id)
);

create table if not exists user (
  id       int(11) AUTO_INCREMENT,
  login    varchar(20),
  password varchar(50),
  enable   int(2),
  PRIMARY KEY (id)
);

create table if not exists role (
  id      int(11) AUTO_INCREMENT,
  name    varchar(10),
  user_id int(11),
  PRIMARY KEY (id),
  constraint fk_role_on_user FOREIGN KEY (user_id) references user (id)
);