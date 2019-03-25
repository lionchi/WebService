insert into url (description, value) values ('Тестовая ссылка', 'https://yandex.ru');
insert into url (description, value) values ('Очень тестовая ссылка', 'https://www.google.ru');

insert into user (login, password, enable) VALUES ('admin', md5('123456'), 1);
insert into user (login, password, enable) VALUES ('user', md5('123456'), 1);

insert into role (name, user_id) VALUES ('ADMIN', 1);
insert into role (name, user_id) VALUES ('USER', 1);
insert into role (name, user_id) VALUES ('USER', 2);