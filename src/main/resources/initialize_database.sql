USE freelearn3;
-- Populando a tabela course_category

INSERT INTO course_category (id, description, name)
VALUES
    (1, 'Description 1', 'Category 1'),
    (2, 'Description 2', 'Category 2'),
    (3, 'Description 3', 'Category 3'),
    (4, 'Description 4', 'Category 4'),
    (5, 'Description 5', 'Category 5'),
    (6, 'Description 6', 'Category 6'),
    (7, 'Description 7', 'Category 7'),
    (8, 'Description 8', 'Category 8'),
    (9, 'Description 9', 'Category 9'),
    (10, 'Description 10', 'Category 10');

-- Populando a tabela course_category_seq
INSERT INTO course_category_seq (next_val) VALUES (1);

-- Populando a tabela course_module_seq
INSERT INTO course_module_seq (next_val) VALUES (1);

-- Populando a tabela course_seq
INSERT INTO course_seq (next_val) VALUES (1);

-- Populando a tabela lesson_seq
INSERT INTO lesson_seq (next_val) VALUES (1);

-- Populando a tabela review_seq
INSERT INTO review_seq (next_val) VALUES (1);

-- Populando a tabela student_seq
INSERT INTO student_seq (next_val) VALUES (1);

-- Populando a tabela teacher_seq
INSERT INTO teacher_seq (next_val) VALUES (1);

-- Populando a tabela user
INSERT INTO user (id, email, name, password)
VALUES
    (1, 'user1@example.com', 'User 1', 'password1'),
    (2, 'user2@example.com', 'User 2', 'password2'),
    (3, 'user3@example.com', 'User 3', 'password3'),
    (4, 'user4@example.com', 'User 4', 'password4'),
    (5, 'user5@example.com', 'User 5', 'password5'),
    (6, 'user6@example.com', 'User 6', 'password6'),
    (7, 'user7@example.com', 'User 7', 'password7'),
    (8, 'user8@example.com', 'User 8', 'password8'),
    (9, 'user9@example.com', 'User 9', 'password9'),
    (10, 'user10@example.com', 'User 10', 'password10');

-- Populando a tabela student
INSERT INTO student (hours_watched, number_of_courses_subscribed, id, user_id)
VALUES
    (20, 2, 1, 1),
    (25, 3, 2, 2),
    (15, 1, 3, 3),
    (30, 4, 4, 4),
    (10, 1, 5, 5),
    (35, 3, 6, 6),
    (18, 2, 7, 7),
    (22, 2, 8, 8),
    (27, 3, 9, 9),
    (40, 5, 10, 10);

-- Populando a tabela teacher
INSERT INTO teacher (hours_taught, overall_rating, id, user_id, employee_id)
VALUES
    (50, 4.5, 1, 1, 'EMP001'),
    (45, 4.2, 2, 2, 'EMP002'),
    (60, 4.8, 3, 3, 'EMP003'),
    (55, 4.6, 4, 4, 'EMP004'),
    (48, 4.4, 5, 5, 'EMP005'),
    (52, 4.7, 6, 6, 'EMP006'),
    (47, 4.3, 7, 7, 'EMP007'),
    (49, 4.4, 8, 8, 'EMP008'),
    (53, 4.6, 9, 9, 'EMP009'),
    (58, 4.9, 10, 10, 'EMP010');

-- Populando a tabela course
INSERT INTO course (duration_hours, id, teacher_id, description, image_url, language, link, title)
VALUES
    (10, 1, 1, 'Course 1 description', 'image_url_1', 'English', 'link_1', 'Course 1'),
    (15, 2, 2, 'Course 2 description', 'image_url_2', 'English', 'link_2', 'Course 2'),
    (12, 3, 3, 'Course 3 description', 'image_url_3', 'English', 'link_3', 'Course 3'),
    (8, 4, 4, 'Course 4 description', 'image_url_4', 'English', 'link_4', 'Course 4'),
    (20, 5, 5, 'Course 5 description', 'image_url_5', 'English', 'link_5', 'Course 5'),
    (18, 6, 6, 'Course 6 description', 'image_url_6', 'English', 'link_6', 'Course 6'),
    (14, 7, 7, 'Course 7 description', 'image_url_7', 'English', 'link_7', 'Course 7'),
    (16, 8, 8, 'Course 8 description', 'image_url_8', 'English', 'link_8', 'Course 8'),
    (22, 9, 9, 'Course 9 description', 'image_url_9', 'English', 'link_9', 'Course 9'),
    (25, 10, 10, 'Course 10 description', 'image_url_10', 'English', 'link_10', 'Course 10');

-- Populando a tabela course_category_courses
INSERT INTO course_category_courses (course_categories_id, courses_id)
VALUES
    (1, 1),
    (2, 2),
    (3, 3),
    (4, 4),
    (5, 5),
    (6, 6),
    (7, 7),
    (8, 8),
    (9, 9),
    (10, 10);


-- Populando a tabela course_module (continuação)
INSERT INTO course_module (sequence_number, course_id, id, description, title)
VALUES
    (1, 1, 1, 'Module 1 description', 'Module 1'),
    (2, 2, 2, 'Module 2 description', 'Module 2'),
    (3, 3, 3, 'Module 3 description', 'Module 3'),
    (4, 4, 4, 'Module 4 description', 'Module 4'),
    (5, 5, 5, 'Module 5 description', 'Module 5'),
    (6, 6, 6, 'Module 6 description', 'Module 6'),
    (7, 7, 7, 'Module 7 description', 'Module 7'),
    (8, 8, 8, 'Module 8 description', 'Module 8'),
    (9, 9, 9, 'Module 9 description', 'Module 9'),
    (10, 10, 10, 'Module 10 description', 'Module 10');

-- Populando a tabela lesson
INSERT INTO lesson (duration_minutes, id, module_id, title, video_url)
VALUES
    (30, 1, 1, 'Lesson 1', 'video_url_1'),
    (35, 2, 1, 'Lesson 2', 'video_url_2'),
    (40, 3, 2, 'Lesson 3', 'video_url_3'),
    (25, 4, 2, 'Lesson 4', 'video_url_4'),
    (30, 5, 3, 'Lesson 5', 'video_url_5'),
    (40, 6, 3, 'Lesson 6', 'video_url_6'),
    (35, 7, 4, 'Lesson 7', 'video_url_7'),
    (45, 8, 4, 'Lesson 8', 'video_url_8'),
    (50, 9, 5, 'Lesson 9', 'video_url_9'),
    (55, 10, 5, 'Lesson 10', 'video_url_10');

-- Populando a tabela review
INSERT INTO review (course_id, id, student_id, teacher_id, comment)
VALUES
    (1, 1, 1, 1, 'Great course, highly recommended!'),
    (2, 2, 2, 2, 'Very informative, enjoyed every bit of it.'),
    (3, 3, 3, 3, 'Excellent content, helped me a lot.'),
    (4, 4, 4, 4, 'Could be better, lacked depth.'),
    (5, 5, 5, 5, 'Awesome instructor, loved the teaching style.'),
    (6, 6, 6, 6, 'The course was okay, expected more.'),
    (7, 7, 7, 7, 'Not satisfied, felt like a waste of time.'),
    (8, 8, 8, 8, 'Highly engaging course, kept me hooked.'),
    (9, 9, 9, 9, 'Needs improvement, concepts not well explained.'),
    (10, 10, 10, 10, 'Best course I''ve ever taken, worth every penny.');

-- Populando a tabela student_enrolled_courses
INSERT INTO student_enrolled_courses (enrolled_courses_id, enrolled_students_id)
VALUES
    (1, 1),
    (2, 2),
    (3, 3),
    (4, 4),
    (5, 5),
    (6, 6),
    (7, 7),
    (8, 8),
    (9, 9),
    (10, 10);

-- Populando a tabela teacher_areas_of_expertise
INSERT INTO teacher_areas_of_expertise (teacher_id, areas_of_expertise)
VALUES
    (1, 'Programming'),
    (2, 'Data Science'),
    (3, 'Machine Learning'),
    (4, 'Web Development'),
    (5, 'Graphic Design'),
    (6, 'Marketing'),
    (7, 'Finance'),
    (8, 'Photography'),
    (9, 'Business Management'),
    (10, 'Language Learning');

-- Populando a tabela user_seq
INSERT INTO user_seq (next_val) VALUES (11);
-- Populando a tabela user_seq
INSERT INTO user_seq (next_val) VALUES (21);

-- Populando a tabela user (novos usuários)
INSERT INTO user (id, email, name, password)
VALUES
    (11, 'user11@example.com', 'User 11', 'password11'),
    (12, 'user12@example.com', 'User 12', 'password12'),
    (13, 'user13@example.com', 'User 13', 'password13'),
    (14, 'user14@example.com', 'User 14', 'password14'),
    (15, 'user15@example.com', 'User 15', 'password15'),
    (16, 'user16@example.com', 'User 16', 'password16'),
    (17, 'user17@example.com', 'User 17', 'password17'),
    (18, 'user18@example.com', 'User 18', 'password18'),
    (19, 'user19@example.com', 'User 19', 'password19'),
    (20, 'user20@example.com', 'User 20', 'password20');

-- Populando a tabela student (novos estudantes)
INSERT INTO student (hours_watched, number_of_courses_subscribed, id, user_id)
VALUES
    (20, 2, 11, 11),
    (25, 3, 12, 12),
    (15, 1, 13, 13),
    (30, 4, 14, 14),
    (10, 1, 15, 15),
    (35, 3, 16, 16),
    (18, 2, 17, 17),
    (22, 2, 18, 18),
    (27, 3, 19, 19),
    (40, 5, 20, 20);

-- Matriculando estudantes em cursos
-- Estudante 11
INSERT INTO student_enrolled_courses (enrolled_courses_id, enrolled_students_id)
VALUES
    (1, 11),
    (3, 11),
    (5, 11),
    (7, 11),
    (9, 11);

-- Estudante 12
INSERT INTO student_enrolled_courses (enrolled_courses_id, enrolled_students_id)
VALUES
    (2, 12),
    (4, 12),
    (6, 12),
    (8, 12),
    (10, 12);

-- Estudante 13
INSERT INTO student_enrolled_courses (enrolled_courses_id, enrolled_students_id)
VALUES
    (1, 13),
    (2, 13),
    (3, 13);

-- Estudante 14
INSERT INTO student_enrolled_courses (enrolled_courses_id, enrolled_students_id)
VALUES
    (4, 14),
    (5, 14),
    (6, 14),
    (7, 14);

-- Estudante 15
INSERT INTO student_enrolled_courses (enrolled_courses_id, enrolled_students_id)
VALUES
    (8, 15),
    (9, 15),
    (10, 15);

-- Estudante 16
INSERT INTO student_enrolled_courses (enrolled_courses_id, enrolled_students_id)
VALUES
    (1, 16),
    (2, 16),
    (3, 16),
    (4, 16),
    (5, 16);

-- Estudante 17
INSERT INTO student_enrolled_courses (enrolled_courses_id, enrolled_students_id)
VALUES
    (6, 17),
    (7, 17),
    (8, 17),
    (9, 17),
    (10, 17);

-- Estudante 18
INSERT INTO student_enrolled_courses (enrolled_courses_id, enrolled_students_id)
VALUES
    (1, 18),
    (2, 18),
    (3, 18),
    (4, 18);

-- Estudante 19
INSERT INTO student_enrolled_courses (enrolled_courses_id, enrolled_students_id)
VALUES
    (5, 19),
    (6, 19),
    (7, 19),
    (8, 19);

-- Estudante 20
INSERT INTO student_enrolled_courses (enrolled_courses_id, enrolled_students_id)
VALUES
    (9, 20),
    (10, 20);


