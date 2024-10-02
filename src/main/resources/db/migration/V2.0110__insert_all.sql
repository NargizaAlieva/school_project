INSERT INTO users (login, first_name, last_name, middle_name, phone, email, password, role)
VALUES
    ('johndoe', 'John', 'Doe', 'Michael', '+1234567890', 'john@example.com', 'password123', 'CLASS_TEACHER'),
    ('janesmith', 'Jane', 'Smith', NULL, '+9876543210', 'jane@example.com', 'password456', 'SCHOOL_PRINCIPAL'),
    ('williamgreen', 'William', 'Green', 'Edward', '+1123456789', 'william.green@example.com', 'password789', 'SECRETARY'),
    ('emilywhite', 'Emily', 'White', 'Anna', '+1321654987', 'emily.white@example.com', 'password101', 'TEACHER'),
    ('chrisbrown', 'Chris', 'Brown', NULL, '+1213467890', 'chris.brown@example.com', 'password202', 'TEACHER'),
    ('sophiablack', 'Sophia', 'Black', 'Maria', '+1221346789', 'sophia.black@example.com', 'password303', 'TEACHER'),
    ('oliviascott', 'Olivia', 'Scott', NULL, '+1423678901', 'olivia.scott@example.com', 'password404', 'PARENT'),
    ('liamwright', 'Liam', 'Wright', NULL, '+1543789012', 'liam.wright@example.com', 'password505', 'PARENT'),
    ('noahking', 'Noah', 'King', 'James', '+1654890123', 'noah.king@example.com', 'password606', 'PARENT'),
    ('avawilson', 'Ava', 'Wilson', 'Rose', '+1765901234', 'ava.wilson@example.com', 'password707', 'STUDENT'),
    ('ethanmartin', 'Ethan', 'Martin', 'Robert', '+1876012345', 'ethan.martin@example.com', 'password808', 'STUDENT'),
    ('isabellajones', 'Isabella', 'Jones', NULL, '+1987123456', 'isabella.jones@example.com', 'password909', 'CLASS_REPRESENTATIVE');

INSERT INTO employees (position, salary, user_id)
VALUES
    ('Maths Teacher', 50000, 1),
    ('School Principal', 100000, 2),
    ('Secretary', 600000, 3),
    ('Dean', 80000, 4),
    ('Biology Teacher', 50000, 5),
    ('English Teacher', 50000, 6);

INSERT INTO parents (user_id)
VALUES
    (7),
    (8),
    (9);


INSERT INTO grades (grade_title, teacher_id)
VALUES
    ('10A', 1),
    ('10B', 5);

INSERT INTO students (birthday, grade_id, user_id, parent_id, parent_status)
VALUES
    ('2008-01-15', 1, 10, 1, 'MOTHER'),
    ('2007-05-10', 2, 11, 2, 'FATHER'),
    ('2007-05-10', 2, 12, 3, 'MOTHER');

INSERT INTO subjects (subject_title, description)
VALUES
    ('Mathematics', 'Study of numbers and calculations'),
    ('History', 'Study of past events');

INSERT INTO announcements (title, description, employee_id)
VALUES
    ('Meeting Reminder', 'All teachers should attend the meeting on Friday', 2),
    ('New Policy', 'New grading policies are implemented starting next semester', 3);

INSERT INTO schedules (day_of_week, quarter, due_time, year, subject_id, teacher_id, grade_id)
VALUES
    ('Monday', 1, '08:00', '2024', 1, 1, 1),
    ('Tuesday', 1, '09:00', '2024', 2, 1, 2);

INSERT INTO lessons (topic, homework, schedule_id)
VALUES
    ('Algebra Basics', 'Solve equations', 1),
    ('World War II', 'Read chapter 4', 2);

INSERT INTO marks (mark, student_id, lesson_id)
VALUES
    (85, 1, 1),
    (90, 2, 2);

INSERT INTO attendances (attended, student_id, lesson_id)
VALUES
    (true, 1, 1),
    (false, 2, 2);

INSERT INTO m2m_subjects_teachers (subject_id, teacher_id)
VALUES
    (1, 1),
    (2, 1);

INSERT INTO reviews (review, student_id, author_id)
VALUES
    ('Excellent performance in mathematics', 1, 2),
    ('Needs improvement in history', 2, 1);

INSERT INTO assignments (assignment, author_id, receiver_id)
VALUES
    ('Prepare the annual report', 2, 1),
    ('Organize the parent-teacher meeting', 1, 2);

INSERT INTO messages (message, author_id, receiver_id)
VALUES
    ('Please review the report', 2, 1),
    ('Meeting at 3 PM', 1, 2);

INSERT INTO charters (title, description, employee_id)
VALUES
    ('School Rules', 'Set of rules governing the school', 1),
    ('Code of Conduct', 'Behavioral expectations for students', 2);

INSERT INTO homeworks (lesson_id, student_id, is_done, teacher_review, mark)
VALUES
    (1, 1, true, 'Great work!', 95),
    (2, 2, false, NULL, NULL);