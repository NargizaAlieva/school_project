create table if not exists users (
    id bigserial primary key,
    login varchar unique not null,
    first_name varchar not null,
    last_name varchar not null,
    middle_name varchar,
    phone varchar,
    email varchar not null unique,
    password varchar not null,
    creation_date timestamp with time zone not null default now(),
    is_active boolean not null default true,
    role varchar not null
);

create table if not exists employees (
    id bigserial primary key,
    position varchar not null,
    salary int not null,
    user_id bigserial references users(id) not null
);

create table if not exists parents (
    id bigserial primary key,
    user_id bigserial references users(id) not null
);

--классы (10А)
create table if not exists grades (
    id bigserial primary key,
    grade_title varchar not null,
    teacher_id bigint references employees(id),
    creation_date timestamp with time zone not null default now(),
    is_active boolean not null default true
);

create table if not exists students (
    id bigserial primary key,
    birthday date not null,
    grade_id bigserial references grades(id) not null,
    user_id bigserial references users(id) not null,
    parent_id bigint references parents(id) not null,
    parent_status varchar not null
);

-- предметы
create table if not exists subjects (
    id bigserial primary key,
    subject_title varchar not null unique,
    description text,
    is_active boolean not null default true
);

-- объявления
create table if not exists announcements (
    id bigserial primary key,
    title varchar not null,
    description text not null,
    for_whom varchar not null,
    employee_id bigint references employees(id),
    creation_date timestamp not null default now(),
    is_active boolean not null default true
);

create table if not exists schedules (
    id bigserial primary key,
    day_of_week varchar not null,
    quarter int not null,
    due_time varchar not null,
    year varchar not null,
    subject_id bigint references subjects(id),
    teacher_id bigint references employees(id),
    grade_id bigint references grades(id),
    is_approve boolean default false,
    is_active boolean not null default true
);

--Урок
create table if not exists lessons (
    id bigserial primary key,
    topic varchar,
    homework varchar,
    schedule_id bigint references schedules(id),
    creation_date timestamp not null default now()
);

--Оценки
create table if not exists marks (
    id bigserial primary key,
    mark int not null,
    student_id bigint references students(id),
    lesson_id bigint references lessons(id)
);

--Посещяемость
create table if not exists attendances (
    id bigserial primary key,
    attended boolean not null,
    student_id bigint references students(id),
    lesson_id bigint references lessons(id)
);

create table if not exists m2m_subjects_teachers (
    subject_id bigint references subjects(id),
    teacher_id bigint references employees(id)
);

--отзывы (учитель-ученик, староста-ученик)
create table if not exists reviews (
    id bigserial primary key,
    review varchar not null,
    student_id bigint references students(id),
    author_id bigint references users(id),
    creation_date timestamp not null default now(),
    is_active boolean not null default true
);

--поручения (директор-секретарь, класс.рук.-староста, зауч-класс.рук.)
create table if not exists assignments (
    id bigserial primary key,
    assignment varchar not null,
    author_id bigint references employees(id),
    receiver_id bigint references users(id),
    creation_date timestamp not null default now(),
    is_done boolean default false,
    is_active boolean not null default true
);

--обмен сообщениями
create table if not exists messages (
    id bigserial primary key,
    message varchar not null,
    theme varchar not null,
    title varchar,
    author_id bigint references users(id),
    receiver_id bigint references users(id),
    creation_date timestamp not null default now(),
    is_read boolean default false,
    is_active boolean not null default true
);

--уставы
create table if not exists charters (
    id bigserial primary key,
    title varchar not null,
    description text not null,
    employee_id bigint references employees(id),
    creation_date timestamp not null default now(),
    is_active boolean not null default true
);

--домашнее задание
create table if not exists homeworks (
    id bigserial primary key,
    lesson_id bigint references lessons(id),
    student_id bigint references students(id),
    is_done boolean default false,
    teacher_review text,
    mark int,
    creation_date timestamp not null default now(),
    is_active boolean not null default true
);