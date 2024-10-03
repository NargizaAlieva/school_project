create table if not exists m2m_users_roles (
    user_id bigint references users(id),
    role_id bigint references roles(id)
);

insert into m2m_users_roles (user_id, role_id)
values (2, 1),
       (1, 5),
       (2, 2),
       (1, 6),
       (3, 3),
       (4, 6),
       (5, 6),
       (6, 6),
       (7, 9),
       (8, 9),
       (9, 9),
       (10, 8),
       (11, 8),
       (12, 8),
       (12, 7);
