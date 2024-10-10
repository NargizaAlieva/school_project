create table if not exists roles (
    id bigserial primary key,
    title varchar not null
);

insert into roles (title)
values ('ADMIN'),
       ('PRINCIPAL'),
       ('SECRETARY'),
       ('DEAN'),
       ('CLASS_TEACHER'),
       ('TEACHER'),
       ('CLASS_REPRESENTATIVE'),
       ('STUDENT'),
       ('PARENT'),
       ('USER');