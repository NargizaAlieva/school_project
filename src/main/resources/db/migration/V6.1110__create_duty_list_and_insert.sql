    create table if not exists duty_list (
        id bigserial primary key,
        duty_date date not null,
        student_id bigint references students(id),
        grade_id bigint references grades(id)
    );

    insert into duty_list(duty_date, student_id, grade_id)
    values
        ('2024-10-11', 1, 1),
        ('2024-10-12', 2, 2),
        ('2024-10-13', 3, 2);